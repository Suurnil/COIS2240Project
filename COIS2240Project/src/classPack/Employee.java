package classPack; //Separate package for class to restrict access to certain methods for outside classes

import java.time.*;

public class Employee extends User {

	private LocalTime startTime = null;
	private double recHours;
	private double recHoursTotal;
	private Job job;

	public double getRecHours() {
		return recHours;
	}

	public double getRecHoursTotal() {
		return recHoursTotal;
	}

	public Job getJob() {
		return job;
	}

	protected void setRecHours(double recHours) {
		this.recHours = recHours;
	}

	protected void setRecHoursTotal(double recHoursTotal) {
		this.recHoursTotal = recHoursTotal;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public LocalTime getStartTime() {
		return startTime;
	}
	
	protected void setStartTime(LocalTime startTime){
		this.startTime = startTime;
	}

	/*
	 * Employees can only change their own startTime and endTime, and can only
	 * use the current time
	 */

	public void startShift() {
		setStartTime(LocalTime.now());
	}

	public void endShift() {
		endShift(this, LocalTime.now());
	}

}
