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

	public class Rush {

		private static Random randomChance = new Random();
		private static Random rng = new Random();

		public static double handleRush() {
			double roll = randomChance.nextDouble() * 100;

			if (isRushAndFumble()) {
				return Rounder.round(-100);
			}
			else {
				if ( roll <= 94) {
					return normalRushYardsGained();
				}
				else {
					return abnormalRushYardsGained();
				}
			}
		}

		public static boolean isRushAndFumble() {
			double roll = randomChance.nextDouble() * 100;

			double chanceOfFumble = 0.8;

			if (roll <= chanceOfFumble) {
				return true;
			}

			return false;
		}

		public static double normalRushYardsGained() {

			double mean = 3.26, sd = 2.557; 

			double value = mean + sd * rng.nextGaussian();
			
			return Rounder.round(value);
			
		}

		public static double abnormalRushYardsGained() {
			double roll = randomChance.nextDouble() * 100;

			double[] differenceCalculator = new double[10];

			double total = 0;
			for (int i = 0; i < 10; i++ ) {
				total = total + 1 + (i * 2);
				differenceCalculator[i] = total;
			}
			if (roll <= 80) {
				roll = randomChance.nextDouble() * 100;
				for (int i = 1; i < 10; i++ ) {
					if ( roll < differenceCalculator[10 - i] && roll > differenceCalculator[9-i]) {
						return Rounder.round( (double)(13 + i)); //31 normal
					}
				}
				return Rounder.round(23);
			}
			else {
				return Rounder.round((double)randomChance.nextInt(76) + 24); 
			}

		}
	}