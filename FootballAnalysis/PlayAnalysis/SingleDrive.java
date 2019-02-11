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

	public class SingleDrive {

		private int downNumber;
		private double distanceToFirstDown;
		private double fieldPosition;

		public SingleDrive(int downNumber, double distanceToFirstDown, double fieldPosition) {
			this.downNumber = downNumber;
			this.distanceToFirstDown = distanceToFirstDown;
			this.fieldPosition = fieldPosition;
		}

		public int getDownNumber() {
			return this.downNumber;
		}

		public double getDistanceToFirstDown() {
			return this.distanceToFirstDown;
		}

		public double getFieldPosition() {
			return this.fieldPosition;
		}
		
	}