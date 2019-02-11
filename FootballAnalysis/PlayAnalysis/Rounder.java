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

	public class Rounder {

		public Rounder() {

		}

		public static double round(double numberToRound) {
			double holder = numberToRound;

			int number = (int)numberToRound;

			double numberToSubtract = (double)number; 

			double leftOverDecimal = holder - numberToSubtract;

			boolean isNegative = false;

			if (leftOverDecimal < 0) {
				isNegative = true;
				leftOverDecimal = leftOverDecimal * -1;
			}

			if (leftOverDecimal < 0.125) {

				return numberToSubtract;

			}
			if (leftOverDecimal >= 0.125 && leftOverDecimal < 0.375) {
				if (isNegative) {
					return  numberToSubtract - 0.25;
				}
				return numberToSubtract + 0.25;
			}
			if (leftOverDecimal >= 0.375 && leftOverDecimal < 0.625 ) {
				if (isNegative) {
					return  numberToSubtract - 0.5;
				}
				return numberToSubtract + 0.5;
			}
			if (leftOverDecimal >= 0.625 && leftOverDecimal < 0.875 ) {
				if (isNegative) {
					return  numberToSubtract - 0.75;
				}
				return numberToSubtract + 0.75;
			}
			if (isNegative) {
					return numberToSubtract - 1.0;
			}
			return numberToSubtract + 1.0;

		}

	}