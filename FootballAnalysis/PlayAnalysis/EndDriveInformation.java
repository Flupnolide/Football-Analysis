
public class EndDriveInformation {

	private double yardLine;
	private int timeTaken;
	private int pointsScored;
	private boolean isTurnOver;
	private boolean isPunt;
	private boolean isSafety;

	public EndDriveInformation(double yardLine, int timeTaken, int pointsScored, boolean isTurnOver, boolean isPunt, boolean isSafety) {
		this.yardLine = yardLine;
		this.pointsScored = pointsScored;
		this.timeTaken = timeTaken;
		this.isTurnOver = isTurnOver;
		this.isPunt = isPunt;
		this.isSafety = isSafety;
	}

	public double getYardLine() {
		return this.yardLine;
	}

	public int getPointsScored() {
		return this.pointsScored;
	}

	public int getTimeTaken() {
		return this.timeTaken;
	}

	public boolean getIsTurnOver() {
		return this.isTurnOver;
	}

	public boolean getPunt() {
		return this.isPunt;
	}

	public boolean get() {
		return this.isSafety;
	}

}