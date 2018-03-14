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
		setPayRate(payRate);
		setOvertimeHours(overtimeHours);
		setOvertimeRate(overtimeRate);
		setMaxHours(maxHours);
		setMinHours(minHours);
		setTitle(title);
	}
	
	public Job(Job job){
		setPayRate(job.getPayRate());
		setOvertimeHours(job.getOvertimeHours());
		setOvertimeRate(job.getOvertimeRate());
		setMaxHours(job.getMaxHours());
		setMinHours(job.getMinHours());
		setTitle(job.getTitle());
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
	 * 
	 * all numbers must be >= 0
	 * minHours cannot be greater than maxHours
	 * maxHours cannot be less than minHours
	 * ***************************************************************
	 */
	
	protected void setPayRate(double payRate) {
		if (payRate >= 0) {
			this.payRate = payRate;
		}
		else{
			this.payRate = 0;
		}
	}

	protected void setOvertimeHours(double overtimeHours) {
		this.overtimeHours = overtimeHours;
		if (overtimeHours >= 0) {
			this.overtimeHours = overtimeHours;
		}
		else{
			this.overtimeHours = 0;
		}
	}

	protected void setOvertimeRate(double overtimeRate) {
		if (overtimeRate >= 0) {
			this.overtimeRate = overtimeRate;		}
		else{
			this.overtimeRate = 0;
		}
	}

	protected void setMaxHours(double maxHours) {
		if (this.minHours <= maxHours && maxHours >= 0){
			this.maxHours = maxHours;
		}
		else{
			this.maxHours = this.minHours;
		}
	}

	protected void setMinHours(double minHours) {
		if (this.maxHours >= minHours && minHours >= 0){
			this.minHours = minHours;
		}
		else{
			this.minHours = this.maxHours;
		}
	}

	protected void setTitle(String title) {
		this.title = title;
	}
}
