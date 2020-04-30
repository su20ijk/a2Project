package Internal;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * MilkWeightTest.java created by lukemulligan on MacBook Pro in MilkWeight Project
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
 * MilkWeightTest - TODO description
 * @author lmulligan (2020)
 *
 */
class MilkWeightTest
  {
    private Data main;
    
    @BeforeEach
    void setUp() throws Exception 
    {
      main = new Data();
    }
    
    @Test
    void test001_read_file() 
    {
      main.readFile(new File("2019-1.csv"));
      main.readFile(new File("2019-2.csv"));
      main.readFile(new File("2019-3.csv"));
      int[] farmReport = main.farmReport("0", 2019);
      Map<String, Integer> yearReport = main.yearReport(2019);
      Map<String, Integer> monthReport = main.monthReport(2019, 1);
      Map<String, Integer> yearReportSorted = main.sortAscendingWeight(yearReport);
      Map<String, Integer> dateRangeReport = main.dateRangeReport(2019, 1, 8, 3, 18);
      double[] percents = main.percentList(yearReport);
      double[] percents2 = main.percentList(farmReport);
      System.out.println(yearReportSorted);
      System.out.println(dateRangeReport);
      for (int i = 0; i < percents2.length; i++)
      {
        System.out.println(percents2[i]);
      }
    }

  }

