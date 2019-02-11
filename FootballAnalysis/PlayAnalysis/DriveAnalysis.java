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

public class DriveAnalysis {
	//7 = time
	//8 = yds
	public static int loadSingleTeamDrives(String filename) throws Exception {

		String fileName = filename;

		String line = null;

		FileReader fileReader = new FileReader(fileName);

   		BufferedReader bufferedReader = new BufferedReader(fileReader);

   		boolean isPastDriveATouchdown = false;

   		int doubleTD = 0;
   		int singleTD = 0;
   		int[] amountTracker = new int[200];
   		double averageYards = 0;
   		double variance = 0;
   		String currentOPP = null;
   		String currentYear = null;
   		String pastOPP = null;
   		String pastYear = null;

   		while ( (line = bufferedReader.readLine()) != null ) {
   			String[] parts = line.split(",");

   			boolean touchdown = parts[19].equalsIgnoreCase("Touchdown");
   			currentYear = parts[1];
   			currentOPP = parts[4];

   			if ( ( pastYear == null || pastYear.equalsIgnoreCase( currentYear ) ) || ( pastOPP.equalsIgnoreCase( currentOPP ) ) ) {
				isPastDriveATouchdown = touchdown;
				pastYear = currentYear;
				pastOPP = currentOPP;

				if ( touchdown ) {
					singleTD++;
				}
   			}
   			else {
				if ( touchdown ) {
					if ( isPastDriveATouchdown ) {
						doubleTD++;
					}
					else {
						singleTD++;
					}
				}

				isPastDriveATouchdown = touchdown;
   			}

   			amountTracker[Integer.parseInt(parts[8]) + 50]++;
   		}

   		for (int i = 0; i < amountTracker.length; i++) {
   			if (amountTracker[i] > 0) {
   				System.out.println(i-50 + ":" + amountTracker[i]);
   			}
   		}

   		System.out.println(doubleTD);
   		System.out.println(singleTD);
   		return 100;
	}
}
