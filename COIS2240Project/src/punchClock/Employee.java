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

	public Employee(int idNum, String givenName, String surname, Job job) {
		super(idNum, givenName, surname);
		this.job = job;
	}

	public Employee(User user, Job job) {
		super(user.getIdNum(), user.getGivenName(), user.getSurname());
		this.job = job;
	}
	
	protected Employee(int idNum, String givenName, String surname, LocalDateTime startTime, double recHours,
			double recHoursTotal, Job job) {
		super(idNum, givenName, surname);
		this.startTime = startTime;
		this.recHours = recHours;
		this.recHoursTotal = recHoursTotal;
		this.job = job;
	}

	/* ***************************************************************
	 * Getters
	 * ***************************************************************
	 */

	public Employee() {
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

	public LocalDateTime getStartTime() {
		return startTime;
	}

	/* *************************************************************************
	 * Methods
	 * 
	 * startShift()
	 * 		start a shift at the current time
	 * 
	 * endShift()
	 * 		end a shift at the current time
	 * 
	 * startShift(LocalDateTime startTime)
	 * 		set startTime field
	 * 
	 * endShift(LocalDateTime endTime)
	 * 		end a shift at the specified time
	 * 		a shift must have a startTime to end
	 * 		a shift ends by calculating the hours worked and recording them
	 * 		the startTime is removed at the end of the shift
	 * 
	 * recordShift(LocalDateTime startTime, LocalDateTime endTime)
	 * 		records a shift that was worked from startTime to endTime
	 * 
	 * calcPay()
	 * 		calculates pay based on rate on pay and recorded hours
	 * 		overtime hours are calculated at overtime rate
	 * ************************************************************************* */
	public void startShift() {
		startShift(LocalDateTime.now());
	}

	public void endShift() {
		endShift(LocalDateTime.now());
	}

	protected void startShift(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	protected void endShift(LocalDateTime endTime) {
		if (startTime != null && endTime.isAfter(startTime)) { // checks if shift has been started and endTime is after startTime
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
			System.out.println("Invalid startTime");	//would display in GUI
		}
	}
	
	protected void recordShift(LocalDateTime startTime, LocalDateTime endTime){
		startShift(startTime);
		endShift(endTime);
	}
	
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

	@Override
	public String toString() {
		return "Employee [startTime=" + startTime + ", recHours=" + recHours + ", recHoursTotal=" + recHoursTotal
				+ ", job=" + job.toString() + ", idNum=" + getIdNum() + ", givenName=" + getGivenName()
				+ ", surname=" + getSurname() + "]";
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (Double.doubleToLongBits(recHours) != Double.doubleToLongBits(other.recHours))
			return false;
		if (Double.doubleToLongBits(recHoursTotal) != Double.doubleToLongBits(other.recHoursTotal))
			return false;
		if (startTime == null) {
			if (other.startTime != null)
				return false;
		} else if (!startTime.equals(other.startTime))
			return false;
		return true;
	}
}
