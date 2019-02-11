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

	public class PlayCaller {

		private int downNumber;
		private double distanceToFirstDown;
		private int previousPlay;
		// 0 = neither, 1 = rush, 2 = pass

		public PlayCaller(int downNumber, double distanceToFirstDown, int previousPlay) {
			this.downNumber = downNumber;
			this.distanceToFirstDown = distanceToFirstDown;
			this.previousPlay = previousPlay;
		}

		public double chanceOfPass() {
			//return any number between 0-100; 
			//this is the chance of a pass play called


			//line of best fit for linear for 2nd down 
			double passCallerForSecond = 27.3138 + ( 3.6257 * distanceToFirstDown);
			if (passCallerForSecond > 80 ) {
				passCallerForSecond = 80;
			}
			if (previousPlay == 2) {
				passCallerForSecond = passCallerForSecond - 10;
			}
			if (previousPlay == 1) {
				passCallerForSecond = passCallerForSecond + 10;
			}
			
			double passCallerForThird = -52.2 + ( 101.624 * distanceToFirstDown ) - ( 24.6679 * Math.pow(distanceToFirstDown, 2) ) + ( 2.00833 * Math.pow(distanceToFirstDown, 3) ); 
			if (passCallerForThird > 92 ) {
				passCallerForThird = 92;
			}

			if (downNumber == 1) {
				if (distanceToFirstDown == 10.0) {
					return 51.76;
				}
				if (distanceToFirstDown == 5.0) {
					return 40;
				}

				return passCallerForSecond - 7.5; // these are 1st in goal, promoting run

			}

			if (downNumber == 2) {
				
				return passCallerForSecond;
			}

			if (downNumber == 3) {

				return passCallerForThird;
			}

		return 0;

		}


	}