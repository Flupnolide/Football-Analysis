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


	public class DriveSimulator {
		//11510 passes, 3775 incompletions, 795 sacks (keep in mind sacks are w/ running plays too, so values will be high with passes alone)
		//for strictly passes 50 fumbles (catch than fumble)
		// additonal 58 sacks that ALSO resulted in a fumbles, so 853 total sacks, and  108 total fumbles

		// -- sacks only--- 
		// when it happens:
		// First Down: 28.6%, 2nd Down: 27.7%, Third Down: 43.8%
		// Yards to go: 0-3: 6%, 4-6: 14%, 7-10: 63.1%, 11+: 16.9%
		// 281 interceptions, something to consider is being 3rd down / 7-10+ yards has a pretty significant impact on chance of interception
		//as of right now interception +- yards are to difficult to track, just assume a change of possession for now

		//21335 total plays (4% chance of sack)
		// when sacked, 7% of fumble

		// --- rushes only ----
		// Mean of 3.54 and SD of 3.611, from -5 to 14 (94% of data is accounted for)
		// 69 total fumbles (fumble ratio, only for when opp recover)
		// .8% of time = fumble

		// LSRL -0.059796x^2 + 3.14394*x+57.0539

		private static Random randomChance = new Random();
		private static Random rng = new Random();
		public int previousPlay = 0;
		//1 = rush 2 = pass
		public int isIncompletion() {
			return 0;
		}

		public boolean isSackAndFumble() {
			double chanceOfFumble = 7; 

			double fumble = randomChance.nextDouble() * 100;

			if (fumble <= chanceOfFumble) {
				return true;
			}
			
			return false;
		}

		public double yardsLostForNormalSack() {
			//returns a positive number, subtract in main/other function
			double mean = -6.73, sd = 1.98; 

			double value = (mean + sd * rng.nextGaussian());
			//accounts for 86.6% of data 
			if (value > ( mean + (1.5 * sd)) && value <  ( mean - (1.5 * sd)) ) {
				return Rounder.round(value);
			}
			else {
				double roll = randomChance.nextDouble() * 100;
				 if (roll <= 70) {
				 	double rollTwo = randomChance.nextDouble() * 4.3;
				 	if (rollTwo >= 4) {
				 		return 3;
				 	}
				 	else {
						return Rounder.round(rollTwo);
				 	}
				 }
				 else {
				 	double rollThree = randomChance.nextDouble() * 4.5;
				 	if ((int)rollThree == 3) {
				 		return Rounder.round(10);
				 	}
				 	if ((int)rollThree == 4) {
				 		return Rounder.round(11);
				 	}
				 	if (rollThree < 3) {
				 		return Rounder.round(rollThree + 12);
				 	}
				 }
			}

			return Rounder.round(0);		
			
		}
		public double handleAllPossiblePlays(int downNumber, double distanceToFirstDown) {
			double rollPlay = randomChance.nextDouble() * 100;	
			double chanceOfSack = 6.4; // ONLY ON PASS PLAYS (natural rate)
			double chanceOfPass = new PlayCaller(downNumber, distanceToFirstDown, this.previousPlay).chanceOfPass();

				if (rollPlay <= chanceOfPass) {
					rollPlay = randomChance.nextDouble() * 100;
					if (rollPlay <= chanceOfSack) {
						this.previousPlay = 2;
						return handleSack();
					}
					else {
						this.previousPlay = 2;
						return Pass.handlePass();
					}	
				}
				else {
					this.previousPlay = 1;
					return Rush.handleRush();
				}

		} 
		public boolean isFieldGoalGood(double fieldPosition) {
			double chanceOfSuccess = ( -0.059796 * Math.pow(fieldPosition,2) ) + ( 3.14394 * fieldPosition ) + 57.0539;

			double kick = randomChance.nextDouble() * 100;

			if (kick <= chanceOfSuccess) {
				return true;
			}
			return false;
		}
		public double handleSack() {
			//returns -100 for fumbles
			if (isSackAndFumble()) {
				return -100;
			}
			else {
				return yardsLostForNormalSack();
			}
		}

		public boolean isPenalty() {
			double play = randomChance.nextDouble() * 100;

			double chanceOfPenalty = 7.5;

			if (play <= chanceOfPenalty) {
				return true;
			}

			return false;
		}

		public SingleDrive handlePenaltyToll(int downNumber, double distanceToFirstDown, double fieldPosition, PenaltyResults penalty, boolean acceptOffensivePenalty) {
			int currentDownNumber = downNumber;
			double currentDistanceToFirstDown = distanceToFirstDown;
			double currentFieldPosition = fieldPosition;
			PenaltyResults thePenalty = penalty;
			double penaltyEnforcer = thePenalty.getYardsPenalized();

					if (acceptOffensivePenalty) {
						if (thePenalty.getYardsPenalized() * 2 > currentFieldPosition) {
							penaltyEnforcer = currentFieldPosition / 2;
						}
						else {
							currentDistanceToFirstDown = currentDistanceToFirstDown + penaltyEnforcer;
							currentFieldPosition = currentFieldPosition - penaltyEnforcer;
						}
						if (thePenalty.getLossOfDowns()) {
							currentDownNumber++;
						}
						if (currentDistanceToFirstDown <= 0) {
							currentDownNumber = 1;
							currentDistanceToFirstDown = 10;
						}
					}
					else {
						if (thePenalty.getDefensivePI()) {
							double passGain = Pass.completedPassGain();
							currentDistanceToFirstDown = currentDistanceToFirstDown - passGain;
							currentFieldPosition = currentFieldPosition + passGain;
							if (currentFieldPosition >= 100) {
								currentFieldPosition = 99;
							}
						}
						else {

							if ( 100 - currentFieldPosition < 2 * penaltyEnforcer) {
								penaltyEnforcer = (100 - currentFieldPosition) / 2 + currentFieldPosition;
								currentDistanceToFirstDown = currentDistanceToFirstDown - penaltyEnforcer;
								currentFieldPosition = currentFieldPosition + penaltyEnforcer;
							}
							else {
								currentDistanceToFirstDown = currentDistanceToFirstDown - penaltyEnforcer;
								currentFieldPosition = currentFieldPosition + penaltyEnforcer;
							}
						}
						if (thePenalty.getAutomaticFirstDown()) {
							currentDownNumber = 1;
							currentDistanceToFirstDown = 10;
						}
						if (currentDistanceToFirstDown <= 0) {
							currentDownNumber = 1;
							currentDistanceToFirstDown = 10;
						}
					}

					return new SingleDrive(currentDownNumber, currentDistanceToFirstDown, currentFieldPosition);

		}

		public int computeTimeTaken(int numberOfRushes, int numberOfPasses) {
			return ( (numberOfRushes * 25)  + (numberOfPasses * 20) ); 
		}



		public EndDriveInformation simulateDrive(int downNumber, double distanceToFirstDown, double fieldPosition) {

			double fieldGoalKickSpot = 64; //adjust this for when to kick field goals
			double howLongWasPlay = 0;
			int currentDownNumber = downNumber;
			int numberOfPasses = 0;
			int numberOfRushes = 0;
			boolean isPunt = false;
			boolean isSafety = false;
			boolean isTurnOver = false;
			double currentDistanceToFirstDown = distanceToFirstDown;
			double currentFieldPosition = fieldPosition; 
			int timeTaken = 0;

			while (currentDownNumber < 4 && currentFieldPosition < 100) {
				boolean acceptOffensivePenalty = false;
				boolean acceptDefensivePenalty = false;
				boolean isPenalty = isPenalty();
				howLongWasPlay = handleAllPossiblePlays(downNumber, distanceToFirstDown);
				PenaltyResults thePenalty = null;
				if (isPenalty) {
					thePenalty = Penalty.handleAllPenalties();
					if( thePenalty.getOffensivePenalty() ) {
						if ( thePenalty.getYardsPenalized() < -1 * howLongWasPlay ) {
							acceptOffensivePenalty = false;
						}
						else {
							acceptOffensivePenalty = true;
						}
					}
					else {
						if (thePenalty.getYardsPenalized() > howLongWasPlay ) {
							acceptDefensivePenalty = true;
						}
						else {
							acceptDefensivePenalty = false;
						}
					}

				}
				if (acceptDefensivePenalty || acceptOffensivePenalty) {
					SingleDrive afterPenalty = handlePenaltyToll(currentDownNumber, currentDistanceToFirstDown, currentFieldPosition, thePenalty, acceptOffensivePenalty);
					currentDownNumber = afterPenalty.getDownNumber();
					currentFieldPosition = afterPenalty.getFieldPosition();
					currentDistanceToFirstDown = afterPenalty.getDistanceToFirstDown();
				}
				else {

					if (this.previousPlay == 1) {
						numberOfRushes++;
					}
					else {
						numberOfPasses++;
					}

					if (howLongWasPlay == -100) {
						timeTaken = computeTimeTaken(numberOfRushes, numberOfPasses);
						return new EndDriveInformation(currentFieldPosition, timeTaken, 0, true, false, false);
					}
					else {
						currentDistanceToFirstDown = currentDistanceToFirstDown - howLongWasPlay;
						currentFieldPosition = currentFieldPosition + howLongWasPlay;
						currentDownNumber++;
					}
					if (currentFieldPosition <= 0) {
						timeTaken = computeTimeTaken(numberOfRushes, numberOfPasses);
						return new EndDriveInformation(currentFieldPosition, timeTaken, 0, false, false, true);
					}
					if (currentDistanceToFirstDown <= 0) {
						currentDownNumber = 1;
						currentDistanceToFirstDown = 10;
					}
					else {
						if (currentDownNumber >= 4) {
							break;
						}
					}
				}
			}

			if (currentFieldPosition >= 100) {
				timeTaken = computeTimeTaken(numberOfRushes, numberOfPasses);
				return new EndDriveInformation(currentFieldPosition, timeTaken, 7, false, false, false);
			}
			if (currentFieldPosition < 100 && currentFieldPosition >= fieldGoalKickSpot) {
				if (isFieldGoalGood(100 - currentFieldPosition + 18)) {
					timeTaken = computeTimeTaken(numberOfRushes, numberOfPasses);
					return new EndDriveInformation(currentFieldPosition, timeTaken, 3, false, false, false);
				}

				timeTaken = computeTimeTaken(numberOfRushes, numberOfPasses);
				return new EndDriveInformation(currentFieldPosition, timeTaken, 0, true, false, false);
				
			}
			timeTaken = computeTimeTaken(numberOfRushes, numberOfPasses);
			return new EndDriveInformation(currentFieldPosition, timeTaken, 0, false, true, false);
		}
		
		public Score simulateGame(int teamOneScore, int teamTwoScore, int timeLeftInSeconds) {

		}

		public Score simulateHalf(int teamOneScore, int teamTwoScore) {
			//team one always starts
			int timeLeft = 1800;
			int fieldPosition = 25;
			EndDriveInformation drive = null; // just to declare for now
			while (timeLeft >= 0) {
				drive = simulateDrive(1, 10, fieldPosition);
				timeLeft -= drive.getTimeTaken();
				if (drive.getPointsScored() >= 0)

			}

		} 

		public static void main( String[] args) {
			// split fumbles half recovered by opposing team
			EndDriveInformation drive = new DriveSimulator().simulateDrive(1,10,30);
			System.out.println("The Yard Line is: " + drive.getYardLine());
			System.out.println("Points Scored: " + drive.getPointsScored());
			System.out.println("This drive took: " + drive.getTimeTaken() + " seconds.");
		
		}

	}
