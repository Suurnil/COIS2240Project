package classPack;

public class Employee extends User {

	// private int startTime;
	private double recHours;
	private double recHoursTotal;
	private Job job;
	
	
	
	
	
	

	public void startShift() {
		super.startShift(this);
	}
	
	public void endShift(){
		endShift(this);
	}
}
