package classPack; //Separate package for class to restrict access to certain methods for outside classes

import java.time.*;

public class Employee extends User {

	private LocalDateTime startTime = null;
	private double recHours = 0;
	private double recHoursTotal = 0;
	private Job job;

	public Employee(){
	}
	
	public Employee(int idNum, String givenName, String surname, Job job) {
		super(idNum, givenName, surname);
		this.job = job;
	}

	public Employee(Job job) {
		super();
		this.job = job;
	}

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

	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	protected void setStartTime(LocalDateTime startTime){
		this.startTime = startTime;
	}

	/*
	 * Employees can only change their own startTime and endTime, and can only
	 * use the current time
	 */

	public void startShift() {
		setStartTime(LocalDateTime.now());
	}

	public void endShift() {
		endShift(LocalDateTime.now());
	}

	protected void endShift(LocalDateTime endTime) {
		/*
		 * use to end a shift for employee at specified time check if there is a
		 * startTime gets workTime & converts to double hoursWorked increments
		 * recHours and recHoursTotal by hoursWorked0ooo
		 */
		if (getStartTime() != null) { // when there is a startTime
			Duration workTime = Duration.between(getStartTime(),
					endTime); /* Duration class from java.time */
			double hoursWorked = (workTime.toMinutes()) / 60.0;
			/*
			 * gets minutes of workTime then converts as Duration.toHours()
			 * doesn't account for minutes over the hours
			 */
			setRecHours(getRecHours() + hoursWorked);
			setRecHoursTotal(getRecHoursTotal() + hoursWorked);
			setStartTime(null);
			/*
			 * shift is over, so there is no longer a startTime
			 */
		} else {
			System.out.println("Shift was not started");
		}
	}
}
