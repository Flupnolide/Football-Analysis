import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.*;
import java.util.Scanner;
import java.util.Random;
import java.lang.Math;

public class DataCompiler {

	public static double dataCompacter(String filename) throws Exception {
		String fileName = filename;

		String line = null;

      int yardsTravelled = 0;

      double numberOfPasses = 0;

      double numberOfYards = 0;

      double mean = 73080/7309;

      double variance = 0;

		FileReader fileReader = new FileReader(fileName);

   		BufferedReader bufferedReader = new BufferedReader(fileReader);

   		int[] dataJuicer = new int[120];

         int numberofZeros = 0;
         int numberOfIncompletions  = 0;

   		while ( (line = bufferedReader.readLine()) != null ) {
            String[] parts = line.split(",");

            if (parts[5].equalsIgnoreCase("")) {
               yardsTravelled = 100;
            }
            else {
               yardsTravelled = Integer.parseInt(parts[5]);
            }
   			if ( (yardsTravelled > -8 && yardsTravelled < 32) ) {
                  numberOfPasses++;
                  numberOfYards = numberOfYards + yardsTravelled;
                  variance = variance + Math.pow( mean - yardsTravelled, 2);
            } 
   		}

         System.out.println(variance);
         System.out.println(Math.sqrt(variance / (numberOfPasses - 1)));
         System.out.println(numberOfYards/numberOfPasses);
         System.out.println(numberOfPasses);
         System.out.println(numberOfYards); 


   		return 27;
	}

   public static void fieldGoalReader() throws Exception {
      String fileName = "FieldGoalAnalysis.csv";

      String line = null;

      FileReader fileReader = new FileReader(fileName);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      int[] fieldGoalMadeChart = new int[100];
      int[] fieldGoalAttemptChart = new int[100];

      while ( (line = bufferedReader.readLine()) != null ) {

         String[] parts = line.split(",");

         String[] partsTwo = parts[7].split(" ");

         if ( Double.parseDouble(parts[11]) == 3 ) {
            fieldGoalMadeChart[Integer.parseInt(partsTwo[1]) + 18]++;
            fieldGoalAttemptChart[Integer.parseInt(partsTwo[1]) + 18]++;
         }
         else {
            fieldGoalAttemptChart[Integer.parseInt(partsTwo[1]) + 18]++;
         }
     }

     for (int i = 0; i < 100; i++) {
      if (fieldGoalAttemptChart[i] > 0) {
         System.out.println(i +  "," + fieldGoalMadeChart[i] + "," + fieldGoalAttemptChart[i]);
      }
     }

   }

   public static void dataReader() throws Exception {
      String fileName = "SackData.csv";

      String line = null;

      FileReader fileReader = new FileReader(fileName);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      int[] sackLossChart = new int[35];

      double numberOfSacks = 0;

      double sackLossDistance = 0;

      double mean = -6.734694;

      double variance = 0;

      int sackLoss = 0;

      while ( (line = bufferedReader.readLine()) != null ) {
            String[] parts = line.split(",");

            sackLoss = Integer.parseInt(parts[9]);

            if ( (sackLoss > -11 && sackLoss < -2) ) {
                  numberOfSacks++;
                  sackLossDistance = sackLossDistance + sackLoss;
                  variance = variance + Math.pow( mean - sackLoss, 2);
            } 


            sackLossChart[sackLoss + 30]++;

      }

      for (int i = 0; i < 35; i++) {
         if (sackLossChart[i] != 0 ) {
            System.out.println( i - 30 + "," + sackLossChart[i] );
         }
      }

      System.out.println(variance);
      System.out.println(sackLossDistance / numberOfSacks);
      System.out.println(Math.sqrt(variance / (numberOfSacks - 1)));

   }

   public static void dataCompacterTwo(String filename) throws Exception {
      String fileName = filename;

      String line = null;

      FileReader fileReader = new FileReader(fileName);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      int[] dataCollector = new int[150];

      double yardsRushed = 0;

      double numberOfRuns = 0;

      double totalRushDistance = 0;

      double mean =  3.4477;

      double variance = 0;

      while ( (line = bufferedReader.readLine()) != null ) {
            String[] parts = line.split(",");

            yardsRushed = Integer.parseInt(parts[9]);

            if ( ( yardsRushed >= -3 &&  yardsRushed <= 12) ) {
               numberOfRuns++;
               totalRushDistance = totalRushDistance + yardsRushed;
               variance = variance + Math.pow( mean - yardsRushed, 2);
            } 

         //   dataCollector[yardsRushed + 50]++;
      }

  /*    for (int i = 0; i < 150; i++) {
         if (dataCollector[i] != 0) {
            System.out.println(i - 50 + "," + dataCollector[i]);
         }
      } */

      System.out.println(variance);
      System.out.println(numberOfRuns);
      System.out.println(totalRushDistance / numberOfRuns);
      System.out.println(Math.sqrt(variance / (numberOfRuns - 1)));

   }

   public static void dataForDowns(String filename) throws Exception {


      //regular input but up till 35 yard on opps only for completions, add sacks / incompletions 

      String fileName = filename;

      String line = null;

      FileReader fileReader = new FileReader(fileName);

      BufferedReader bufferedReader = new BufferedReader(fileReader);

      int[] dataCollector = new int[150];

      int yardsPassed = 0;

       while ( (line = bufferedReader.readLine()) != null ) {
            String[] parts = line.split(",");

            if (parts[9].equalsIgnoreCase("")) {
               yardsPassed = 0;
            }
            else {
               yardsPassed = Integer.parseInt(parts[9]);
            }

            dataCollector[yardsPassed + 25]++;           
      }

      for (int i = 0; i < 150; i++ ) {
         if (dataCollector[i] > 0) {
            System.out.println(i - 25 + "," + dataCollector[i]);
         }
      }


   }

	public static void main( String[] args) throws Exception {
      // -2 to 31, mean: 10.14, sd: 6.5
      // -11 to 31, mean: 10.00, sd: 6.7 accounts for IQR
		// double chris = dataCompacter("PassAnalysiscopy.csv");
      //dataReader();
    //  dataCompacterTwo("Rush.csv");
      dataForDowns("1stFiveRush.csv");


      
    
	}
}