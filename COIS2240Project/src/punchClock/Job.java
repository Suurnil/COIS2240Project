package punchClock;	//Separate package for class to restrict access to certain methods for outside classes
public class Job {

	private double payRate;
	private double overtimeHours;
	private double overtimeRate;
	private double maxHours;
	private double minHours;
	private String title;

	public Job(double payRate, double overtimeHours, double overtimeRate, double maxHours, double minHours,
			String title) {
		this.payRate = payRate;
		this.overtimeHours = overtimeHours;
		this.overtimeRate = overtimeRate;
		this.maxHours = maxHours;
		this.minHours = minHours;
		this.title = title;
	}
	
	public Job(Job job){
		this.payRate = job.getPayRate();
		this.overtimeHours = job.getOvertimeHours();
		this.overtimeRate = job.getOvertimeRate();
		this.maxHours = job.getMaxHours();
		this.minHours = job.getMinHours();
		this.title = job.getTitle();
	}

	/* ***************************************************************
	 * Getters
	 * ***************************************************************
	 */
	
	public double getPayRate() {
		return payRate;
	}

	public double getOvertimeHours() {
		return overtimeHours;
	}

	public double getOvertimeRate() {
		return overtimeRate;
	}

	public double getMaxHours() {
		return maxHours;
	}

	public double getMinHours() {
		return minHours;
	}

	public String getTitle() {
		return title;
	}

	/* ***************************************************************
	 * Setters
	 * ***************************************************************
	 */
	
	protected void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	protected void setOvertimeHours(double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	protected void setOvertimeRate(double overtimeRate) {
		this.overtimeRate = overtimeRate;
	}

	protected void setMaxHours(double maxHours) {
		if (this.minHours <= maxHours)
			this.maxHours = maxHours;
	}

	protected void setMinHours(double minHours) {
		if (this.maxHours >= minHours)
			this.minHours = minHours;
	}

	protected void setTitle(String title) {
		this.title = title;
	}
}
