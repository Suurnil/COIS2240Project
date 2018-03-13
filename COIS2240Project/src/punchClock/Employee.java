package punchClock; //Separate package for class to restrict access to certain methods for outside classes

import java.time.*;

public class Employee extends User {

	private LocalDateTime startTime = null;
	private double recHours = 0;
	private double recHoursTotal = 0;
	private Job job;

	/*
	 * ******************************************************** 
	 * Constructors
	 ********************************************************/
	public Employee() {
	}

	public Employee(int idNum, String givenName, String surname, Job job) {
		super(idNum, givenName, surname);
		this.job = job;
	}

	public Employee(Job job) {
		super();
		this.job = job;
	}

	/* ***************************************************************
	 * Getters
	 * ***************************************************************
	 */
	
	public double getRecHours() {
		return recHours;
	}

	public double getRecHoursTotal() {
		return recHoursTotal;
	}

	public Job getJob() {
		return job;	//this prevents Employee.job from being modified via Employee.getJob
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	/* ***************************************************************
	 * Methods
	 * ***************************************************************
	 */
	
	public double calcPay(){
		double pay = 0;
		
		if(recHours > job.getOvertimeHours()){
			pay = (recHours - job.getOvertimeHours()) * job.getOvertimeRate();	//overtimeRate * hours over overtimeHours
			pay += (job.getOvertimeHours() * job.getPayRate());	//regular rate for hours up to overtimeHours
		}
		else{
			pay = recHours * job.getPayRate();
		}
		
		return pay;
	}

	public void startShift() {
		/*starts a shift at the current time*/
		startShift(LocalDateTime.now());
	}

	public void endShift() {
		/*ends a shift at the current time*/
		endShift(LocalDateTime.now());
	}

	protected void startShift(LocalDateTime startTime) {
		/*starts a shift at the specified time*/
		this.startTime = startTime;
	}

	protected void endShift(LocalDateTime endTime) {
		/*ends a shift at the specified time*/
		if (startTime != null) { // checks if shift has been started
			Duration workTime = Duration.between(getStartTime(),
					endTime); /* Duration class from java.time */
			double hoursWorked = (workTime.toMinutes()) / 60.0;
			/*
			 * gets minutes of workTime then converts as Duration.toHours()
			 * doesn't account for minutes over the hours
			 */
			recHours += hoursWorked;
			recHoursTotal += hoursWorked;
			startTime = null;
			/*
			 * shift is over, so there is no longer a startTime
			 */
		} else {
			System.out.println("Shift was not started");
		}
	}
	
	protected void recordShift(LocalDateTime startTime, LocalDateTime endTime){
		/*records a shift starting at startTime and ending at endTime*/
		startShift(startTime);
		endShift(endTime);
	}
}
