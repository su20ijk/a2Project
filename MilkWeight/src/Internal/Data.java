package Internal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Main.java created by lukemulligan on MacBook Pro in MilkWeight Project
 *
 * Author: Luke Mulligan (lmulligan@wisc.edu)
 * Date: @date
 *
 * Course: CS400
 * Semester: Spring 2020
 * Lecture: 001
 *
 * IDE: Eclipse IDE for Java Developers
 * Version: 2019-06 (4.12.0)
 * Build ID: 20190614-1200
 *
 * List Collaborators: 
 */

/**
 * Main - TODO description
 * @author lmulligan (2020)
 *
 */
public class Data 
{
  // Main data structure, a Map of Farm IDs paired to a Map of years paired to
  // an array of FarmPerMonth objects
  private Map<String, Map<Integer, FarmPerMonth[]>> farmList;
  
  /**
   * Default no arg constructor of Main class
   */
  public Data()
  {
    farmList = new HashMap<String, Map<Integer, FarmPerMonth[]>>();
  }
  
  /**
   * Reads data from a csv file and adds farms and milk weight data to the DS by parsing
   * through the file and reading each line
   * @param file - the file containing the data to be added
   */
  public void readFile(File file)
  {
    String line = "";
    try (BufferedReader br = new BufferedReader(new FileReader(file))) 
    {
      br.readLine(); // Skip first line
      while ((line = br.readLine()) != null) 
      {
        try
        {
          // Separate Strings and convert necessary Strings to ints
          String[] data = line.split(",");        
          String[] date = data[0].split("-");
          int year = Integer.parseInt(date[0]);
          int month = Integer.parseInt(date[1]);
          int day = Integer.parseInt(date[2]);    
          String id = data[1].substring(5);
          int weight  = Integer.parseInt(data[2].trim());
          
          // Add all the values to the DS and create new objects if none exists
          farmList.putIfAbsent(id, new HashMap<Integer, FarmPerMonth[]>());
          farmList.get(id).putIfAbsent(year, new FarmPerMonth[12]);
          if (farmList.get(id).get(year)[month-1] == null)
            farmList.get(id).get(year)[month-1] = new FarmPerMonth(id, month);
          farmList.get(id).get(year)[month-1].addWeight(weight, day);
        } 
        catch (NumberFormatException e)
        {
          e.printStackTrace();
        }
      }
    } 
    catch (IOException e) 
    {
      e.printStackTrace();
    }
  }
  
  /**
   * Creates a report of all milk weights of a given farm for a given year by month
   * @param farmID - the farm to create a report for
   * @param year - the year to create a report for
   * @return an array of milk weights by month
   */
  public int[] farmReport(String farmID, int year) 
  {
    int[] report = new int[12];
    FarmPerMonth[] farm = farmList.get(farmID).get(year);
    if (farm != null)
    {
      for (int i = 0; i < farm.length; i++)
      {
        if (farm[i] != null)
          report[i] = farm[i].getWeightTotal();
      }
    }
    return report;
  }
  
  /**
   * Creates a report of all the farms milk weights in a given year
   * @param year - the year to create a report for
   * @return a Map of farm IDs paired to total milk weights for the year for that farm
   */
  public Map<String, Integer> yearReport(int year)
  {
    Map<String, Integer> report = new HashMap<String, Integer>();
    
    // Create Set from farmList
    Iterator<Entry<String, Map<Integer, FarmPerMonth[]>>> iter = farmList.entrySet().iterator();
    
    // Iterate through Set and add all mile weights of each FarmPermMonth for the year,
    // then pair total to farm ID and add pair to Map
    while (iter.hasNext())
    {
      Entry<String, Map<Integer, FarmPerMonth[]>> pair = iter.next();
      FarmPerMonth[] farm = pair.getValue().get(year);
      int totalWeight = 0;     
      if (farm != null)
      {
        for (int i = 0; i < farm.length; i++)
        {
          if(farm[i] != null)
            totalWeight += farm[i].getWeightTotal();
        }
      }
        report.put(pair.getKey(), totalWeight);
    }
    return report;
  }
  
  /**
   * Creates a report of all the farms milk weight in a given month in a given year
   * @param year - the year of the month the create a report for
   * @param month - the month to create a report for
   * @return a Map of farm IDs paired to total milk weights for the month for that farm
   */
  public Map<String, Integer> monthReport(int year, int month)
  {
    Map<String, Integer> report = new HashMap<String, Integer>();
    Iterator<Entry<String, Map<Integer, FarmPerMonth[]>>> iter = farmList.entrySet().iterator();
    while (iter.hasNext())
    {
      Entry<String, Map<Integer, FarmPerMonth[]>> pair = iter.next();
      if (pair.getValue().get(year) != null && pair.getValue().get(year)[month-1] != null)
        report.put(pair.getKey(), pair.getValue().get(year)[month-1].getWeightTotal());
    }
    return report;
  }
  
  /**
   * Creates a report of all the farms milk weight in a certain date range
   * @param year - the year of the dates to create a report for
   * @param sMonth - the start month of the date range
   * @param sDay - the start day of the date range
   * @param eMonth - the end month of the date range
   * @param eDay - the end day of the date range
   * @return a Map of farm IDs paired to total milk weight over the specified range for that farm
   */
  public Map<String, Integer> dateRangeReport(int year, int sMonth, int sDay, int eMonth, int eDay)
  {
    Map<String, Integer> report = new HashMap<String, Integer>();
    Iterator<Entry<String, Map<Integer, FarmPerMonth[]>>> iter = farmList.entrySet().iterator();
    while (iter.hasNext())
    {
      Entry<String, Map<Integer, FarmPerMonth[]>> pair = iter.next();
      FarmPerMonth[] farm = pair.getValue().get(year);
      int weightTotal = 0;
      for (int i  = sMonth - 1; i < eMonth; i++)
      {
        if (farm[i] != null)
        {
          if (i == sMonth - 1)
          {
            if (sMonth == eMonth)
              weightTotal += farm[i].getWeightRange(sDay, eDay);
            else
              weightTotal += farm[i].getWeightRange(sDay, 31);
          }
          else if (i == eMonth - 1)
          {
            weightTotal += farm[i].getWeightRange(1, eDay);
          }
          else
          {
            weightTotal += farm[i].getWeightTotal();
          }
        }
      }
      report.put(pair.getKey(), weightTotal);
    }
    return report;
  }
  
  /**
   * Sorts a report(Map of farm IDs to milk weights) by ascending weight order
   * @param report - the report/Map to be sorted
   * @return a Map sorted in ascending order
   */
  public Map<String, Integer> sortAscendingWeight(Map<String, Integer> report)
  {
    // Convert Map to a List
    List<Entry<String, Integer>> reportList= new ArrayList(report.entrySet());
    
    // Lambda expression the sorts items in list be values/milk weights
    Collections.sort(reportList, 
        new Comparator<Entry<String, Integer>>()
        {
          @Override
          public int compare(Entry<String, Integer> farm1, Entry<String, Integer> farm2) 
          {
            return farm1.getValue().compareTo(farm2.getValue());
          }
        }
      );
    
    // Convert the list back to a Map
    Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
    for (Entry<String, Integer> e : reportList) 
      sorted.put(e.getKey(), e.getValue());
    return sorted;
  }
  
  /**
   * Sorts a report(Map of farm IDs to milk weights) by descending weight order
   * @param report - the report/Map to be sorted
   * @return a Map sorted in descending order
   */
  public Map<String, Integer> sortDescendingWeight(Map<String, Integer> report)
  {
    List<Entry<String, Integer>> reportList= new ArrayList(report.entrySet());
    Collections.sort(reportList, 
        new Comparator<Entry<String, Integer>>()
        {
          @Override
          public int compare(Entry<String, Integer> farm1, Entry<String, Integer> farm2) 
          {
            return farm2.getValue().compareTo(farm1.getValue());
          }
        }
      );
    Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
    for (Entry<String, Integer> e : reportList) 
      sorted.put(e.getKey(), e.getValue());
    return sorted;
  }
  
  /**
   * Sorts a report(Map of farm IDs to milk weights) by farm IDs
   * @param report - the report/Map to be sorted
   * @return a Map sorted by keys/farm IDs
   */
  public Map<String, Integer> sortFarmID(Map<String, Integer> report)
  {
    List<Entry<String, Integer>> reportList= new ArrayList(report.entrySet());
    Collections.sort(reportList, 
        new Comparator<Entry<String, Integer>>()
        {
          @Override
          public int compare(Entry<String, Integer> farm1, Entry<String, Integer> farm2) 
          {
            return farm1.getKey().compareTo(farm2.getKey());
          }
        }
      );
    Map<String, Integer> sorted = new LinkedHashMap<String, Integer>();
    for (Entry<String, Integer> e : reportList) 
      sorted.put(e.getKey(), e.getValue());
    return sorted;
  }
  
  /**
   * Creates an array of milk weight percentages(indiv. farm milk weight/total weight) 
   * for each farm in a given report, order of percents matches ordering in report,
   * i.e. if ordering report changes the array will not match
   * @param report - the report to generate percentages for
   * @return an array of percentages as doubles
   */
  public double[] percentList(Map<String, Integer> report)
  {
    double[] percents = new double[report.size()];
    int weightTotal = 0;
    
    // Iterate through report and calculate total weight
    Iterator<Entry<String, Integer>> iter = report.entrySet().iterator();
    while (iter.hasNext())
    {
      weightTotal += iter.next().getValue();
    }
    
    // Iterate through report and calculate and add percents to array
    Iterator<Entry<String, Integer>> iter2 = report.entrySet().iterator();
    for (int i = 0; i < percents.length; i++)
    {
      percents[i] = 100 * ((double)iter2.next().getValue() / weightTotal);
    }
    return percents;
  }
}
