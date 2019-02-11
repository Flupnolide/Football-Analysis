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

	public class PenaltyResults {

		private boolean isOffensivePenalty;
		private double yardsPenalized;
		private boolean isAutomaticFirstDown;
		private boolean isDefensivePI;
		private boolean isLossOfDowns;

		public PenaltyResults(boolean isOffensivePenalty, double yardsPenalized, boolean isAutomaticFirstDown, boolean isDefensivePI, boolean isLossOfDowns ) {
			this.isOffensivePenalty = isOffensivePenalty;
			this.yardsPenalized = yardsPenalized;
			this.isAutomaticFirstDown = isAutomaticFirstDown;
			this.isDefensivePI = isDefensivePI;
			this.isLossOfDowns = isLossOfDowns;
		}

		public boolean getOffensivePenalty() {
			return this.isOffensivePenalty;
		}

		public boolean getAutomaticFirstDown() {
			return this.isAutomaticFirstDown;
		}

		public boolean getDefensivePI() {
			return this.isDefensivePI;
		}

		public boolean getLossOfDowns() {
			return this.isLossOfDowns;
		}

		public double getYardsPenalized() {
			return this.yardsPenalized;
		}

		

	}
