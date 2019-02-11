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

	public class Penalty {

		private static Random randomChance = new Random();
		private static Random rng = new Random();

		// Offensive of Holding = 716 counts, 10 yards 21.4% 
		// false start (Offensive) = 602 counts, 5 yards 18%
		// Defesnive Pass Interference = 287 counts, auto 1st down 8.6%
		// Defensive of Holding = 213 counts, 5 yards + auto 1st down 6.4%
		// Defensive of Offside = 172 counts, 5 yards 5.1%
		// Unnecessary Roughness = 148 defensive penalties, 55 offensive. 15 yards, auto 1st down if by defense 6.1%, 4.5% D, 1.6% O
		// Delay of game, 141 counts, 5 yards 4.2%
		// Illegal block above the waist, 123 counts, 50/50 for offense/defense, 10 yards, 3.7%
		// Illegal use of hands 91 counts, , 50/50 for O/D, 10 yards if done by Offense, 5 yards + auto 1st down if by defense, 2.7%
		// Neutral zone infraction, 118 counts, 5 yards, (defense only), 3.5%
		// Offensive pass interference, 92 counts, 10 yards, 2.8%
		// Face mask, 32 offsensive counts, 70 defensive counts 15 yards + auto 1st down if defense, 3.1%, 1% 0, 2% D
		// Roughing the passer, 89 counts, 15 yards + auto 1st down, 2.6%
		// Unsportsmanlike Conduct, 77 counts, 15 yards + auto 1st down, (50/50 O/D), 2.3% (1.15% EA)
		// Illegal Formation, 67 counts, 5 yards (offensive penalty only), 2%
		// Illegal Contact, 48 counts, 5 yards + auto first down, 1.4%
		// Encroachment, 49 counts, 5 yards, defense penalty only, 1.5%
		// Defensive 12 on field, 47 counts, 5 yards, 1.4%
		// Illegal Shift, 34 counts, 50/50 O/D, 5 yards, 1%, 0.5% ea
		// Taunting, 28 counts, 15 yards + auto 1st down if defense, 50/50 O/D, 0.8%, 0.4% ea
		// Intentional Grounding, 28 counts, 10 yards + loss of down, safety if in endzone, 0.8%
		// Horse Collar, 20 counts, defense only, 15 yards, 0.6%
		// 3347 total



		public static double penalizesOffense() {
			double chanceOfFalseStart = 31.6; // 5
			double chanceOfDelayOfGame = 7.4; // 5
			double chanceOfIllegalFormation = 3.5; // 5
			double chanceOfIllegalShift = .9; // 5
			double chanceOfIllegalBlockAboveWaist = 3.2; // 10
			double chanceOfIllegalUseOfHands = 2.4; // 10
			double chanceOfOffensivePI = 4.9; // 10
			double chanceOfHolding = 37.5; // 10
			double chanceOfUnecessaryRoughness = 2.8; //15
			double chanceOfTaunting = .6; // 15
			double chanceOfFaceMask = 1.8; // 15
			double chanceOfUnsportsmanlikeConduct = 2; // 15
			double chanceOfIntentionalGrounding = 1.4; // 10 + loss of downs

			double randomPenalty = randomChance.nextDouble() * 100;

			double chanceOfFiveYardPenalty = chanceOfFalseStart + chanceOfIllegalFormation + chanceOfIllegalShift + chanceOfDelayOfGame;
			double chanceOfTenYardPenalty = chanceOfHolding + chanceOfIllegalBlockAboveWaist + chanceOfIllegalUseOfHands + chanceOfOffensivePI;
			double chanceOfFifteenYardPenalty = chanceOfUnecessaryRoughness + chanceOfTaunting + chanceOfFaceMask + chanceOfUnsportsmanlikeConduct;

			if (randomPenalty <= chanceOfFiveYardPenalty) {
				return 5;
			}
			if (randomPenalty > chanceOfFiveYardPenalty && randomPenalty <= chanceOfFiveYardPenalty + chanceOfTenYardPenalty) {
				return 10;
			}
			if (randomPenalty > chanceOfFiveYardPenalty + chanceOfTenYardPenalty && randomPenalty <= chanceOfFiveYardPenalty + chanceOfTenYardPenalty + chanceOfFifteenYardPenalty) {
				return 15;
			}

			return -10; //loss of downs = negative
		}

		public static double penalizesDefense() {
			double chanceOfDefensivePI = 20; // auto 1st down, spot of the foul
			double chanceOfHolding = 15; // 5 + auto 1st
			double chanceOfIllegalUseOfHands = 3.1; // 5 + auto 1st
			double chanceOfIllegalContact = 3.3; // 5 + auto 1st
			double chanceOfOffside = 11.9; // 5 yards
			double chanceOfEncroachment = 3.5; // 5 yards
			double chanceOfTwelveOnField = 3.3; // 5 yards
			double chanceOfIllegalShift = 1.2; // 5 yards
			double chanceOfNeutralZoneInfraction = 8.1; // 5 yards
			double chanceOfIllegalBlockAboveWaist = 4.3; // 10 yards
			double chanceOfUnecessaryRoughness = 10.5; // 15 yards + auto 1st
			double chanceOfFaceMask = 4.7; // 15 + auto 1st
			double chanceOfRoughingThePasser = 6.1; // 15 + auto 1st
			double chanceOfUnsportsmanlikeConduct = 2.7; // 15 + auto 1st
			double chanceOfTaunting = 0.9; // 15 + auto 1st
			double chanceOfHorseCollar = 1.4; // 15

			double randomPenalty = randomChance.nextDouble() * 100;

			double chanceOfFiveAndAuto = chanceOfHolding + chanceOfIllegalUseOfHands + chanceOfIllegalContact;
			double chanceOfFive = chanceOfOffside + chanceOfEncroachment + chanceOfTwelveOnField + chanceOfIllegalShift + chanceOfNeutralZoneInfraction;
			double chanceOfTen = chanceOfIllegalBlockAboveWaist;
			double chanceOfFifteenAndAuto = chanceOfUnecessaryRoughness + chanceOfFaceMask + chanceOfRoughingThePasser + chanceOfUnsportsmanlikeConduct + chanceOfTaunting;
			double chanceOfFifteen = chanceOfHorseCollar;

			if (randomPenalty <= chanceOfFiveAndAuto) {
				return -5;
			}
			if (randomPenalty > chanceOfFiveAndAuto && randomPenalty <= chanceOfFiveAndAuto + chanceOfFive) {
				return 5;
			}
			if (randomPenalty > chanceOfFiveAndAuto + chanceOfFive && randomPenalty <= chanceOfFiveAndAuto + chanceOfFive + chanceOfFifteenAndAuto) {
				return -15;
			}
			if (randomPenalty > chanceOfFiveAndAuto + chanceOfFive + chanceOfFifteenAndAuto && randomPenalty <=  chanceOfFiveAndAuto + chanceOfFive + chanceOfFifteenAndAuto + chanceOfTen ) {
				return 10;
			}
			if (randomPenalty > chanceOfFiveAndAuto + chanceOfFive + chanceOfFifteenAndAuto + chanceOfTen && randomPenalty <=  chanceOfFiveAndAuto + chanceOfFive + chanceOfFifteenAndAuto + chanceOfTen + chanceOfFifteen ) {
				return 15;
			}

			return 0; // negative is auto 1st down, 0 isSpotOfFoul
		}

		public static boolean isOffensivePenalty() {
			double chanceOfOffensivePenalty = 57.05;
			double randomPenalty = randomChance.nextDouble() * 100;

			if (randomPenalty <= chanceOfOffensivePenalty ) {
				return true;
			} 

			return false;
		}

		public static PenaltyResults handleAllPenalties() {
			boolean isOffensivePenalty = true;
			double yardsPenalized = 0;
			boolean isAutomaticFirstDown = true;
			boolean isDefensivePI = true;
			boolean isLossOfDowns = true;

			if (isOffensivePenalty()) {
				isOffensivePenalty = true;
				yardsPenalized = penalizesOffense();
				isAutomaticFirstDown = false;
				isDefensivePI = false;
				if (yardsPenalized < 0) {
					yardsPenalized = yardsPenalized * -1;
					isLossOfDowns = true;
				}
				else {
					isLossOfDowns = false;
				}
			}
			else {
				isOffensivePenalty = false;
				isLossOfDowns = false;
				yardsPenalized = penalizesDefense();
				if (yardsPenalized < 0) {
					yardsPenalized = yardsPenalized * -1;
					isAutomaticFirstDown = true;
					isDefensivePI = false;
				}
				else if (yardsPenalized == 0) {
					isAutomaticFirstDown = false;
					isDefensivePI = true;
				}
				else {
					isDefensivePI = false;
					isAutomaticFirstDown = false;
				}

			}

			PenaltyResults results = new PenaltyResults(isOffensivePenalty, yardsPenalized, isAutomaticFirstDown, isDefensivePI, isLossOfDowns);	

			return results;

		} 
		
	}