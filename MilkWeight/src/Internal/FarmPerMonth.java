package Internal;
/**
 * FarmPerMonth.java created by lukemulligan on MacBook Pro in MilkWeight Project
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
 * FarmPerMonth - TODO description
 * @author lmulligan (2020)
 *
 */
public class FarmPerMonth 
{
  private int month; // the month identifier of the FarmPerMonth
  private String farmID; // the ID of the farm
  private int[] weightList; // the weights of the farm for the month separated by day
  
  /**
   * Constructor for the FarmPerMonth class
   * @param farmID - the ID of the farm
   * @param month - the month of the farm
   * @param weightList - a list of weights by day
   */
  public FarmPerMonth(String farmID, int month, int[] weightList)
  {
    this.month = month;
    this.farmID = farmID;
    this.weightList = weightList;
  }
  
  /**
   * Constructor for the FarmPerMonth class
   * @param farmID - the ID of the farm
   * @param month - the month of the farm
   */
  public FarmPerMonth(String farmID, int month)
  {
    this.month = month;
    this.farmID = farmID;
    this.weightList = new int[31];
  }
  
  /**
   * @param day - the day of the weight to be returned
   * @return the milk weight produced by the farm on a specified day
   */
  public int getWeightDay(int day)
  {
    return weightList[day-1];
  }
  
  /**
   * Add a weight on a given day
   * @param weight - the weight to be added
   * @param day - the day on which to add the weight
   */
  public void addWeight(int weight, int day)
  {
    weightList[day-1] = weight;
  }
  
  /**
   * @return the total weight of the month stored in the FarmPerMonth
   */
  public int getWeightTotal()
  {
    int total = 0;
    for (int i = 0; i < weightList.length; i++)
    {
      total += weightList[i];
    }
    return total;
  }
  
  /**
   * @param sDay - the start day of the range
   * @param eDay - the end day of the range
   * @return the total weight over the specified range
   */
  public int getWeightRange(int sDay, int eDay)
  {
    int total = 0;
    for (int i = sDay - 1; i < eDay; i++)
    {
      total += weightList[i];
    }
    return total;
  }
  
  /**
   * @return the ID of the farm
   */
  public String getID()
  {
    return farmID;
  }
}
