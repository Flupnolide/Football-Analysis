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

	public class Pass {

		private static Random randomChance = new Random();
		private static Random rng = new Random();

		public static boolean isPassComplete() {
			double chanceOfCompletion = 65.6; // Alter to change chance of completion (assuming 2.4% chance of interception for now)

			double pass = randomChance.nextDouble() * 100;

			if (pass <= chanceOfCompletion) {
				return true;
			}
			
			return false;
		}

		public static  boolean isCompleteNormalPass() {

			double chanceOfNormalPass = 94.5; // Alter this to change chance of normal pass (93 normal)

			double pass = randomChance.nextDouble() * 100;

			if (pass <= chanceOfNormalPass) {
				return true;
			}
			
			return false;

		}
		public static double yardsForAbnormalPass() {
			Random randomChance = new Random();

			double longPass = randomChance.nextDouble() * 100;

			double[] differenceCalculator = new double[20];

			double total = 0;
			for (int i = 0; i < 20; i++ ) {
				total = total + 3.1 + (i * 0.2);
				differenceCalculator[i] = total;
			}


			if (longPass <= 70) {
				int yardarge = randomChance.nextInt(100);
				for (int i = 1; i < 20; i++ ) {
					if ( yardarge < differenceCalculator[20 - i] && yardarge > differenceCalculator[19-i]) {
						return Rounder.round(31 + i); //31 normal
					}
				}
				return Rounder.round(52);			
			}
			if ( longPass >= 70 && longPass <= 84) {
				return Rounder.round(100);
			}
			else {
				return Rounder.round(randomChance.nextDouble() * 20 + 53);
			}
		}
		public static double yardsForNormalPass() {
			double mean = 10.00, sd = 6.7; 

   			double answer =  Rounder.round(mean + sd * rng.nextGaussian());

   			return answer;
		}

		public static boolean isCatchAndFumble() {
			double chanceOfFumble = .65;

			double play = randomChance.nextDouble() * 100;

			if (play <= chanceOfFumble) {
				return true;
			}

			return false;
		}

		public static double handlePass() {
			double howLongWasPlay = 0;

			if (isInterception()) {
					return -100;
			}	
			if (isPassComplete()) {
				if (isCatchAndFumble()) {
					return -100;
				}
				if (isCompleteNormalPass()) {
					howLongWasPlay =  yardsForNormalPass();
					return howLongWasPlay;
				}
				else {
					howLongWasPlay = yardsForAbnormalPass();
					return howLongWasPlay;
				}
			}
			else {
			
				return 0;
			}
		}

		public static double completedPassGain() {
			double howLongWasPlay = 0;

			if (isCompleteNormalPass()) {
				howLongWasPlay =  yardsForNormalPass();
				return howLongWasPlay;
			}
			else {
				howLongWasPlay = yardsForAbnormalPass();
				return howLongWasPlay;
			}

		}

		public static boolean isInterception() {
			//chance of int = 2.4%

			double chanceOfInterception = 2.4;

			double pass = randomChance.nextDouble() * 100;

			if (pass <= chanceOfInterception) {
				return true;
			}

			return false;
		}
	}