package classPack;
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

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getOvertimeHours() {
		return overtimeHours;
	}

	public void setOvertimeHours(double overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	public double getOvertimeRate() {
		return overtimeRate;
	}

	public void setOvertimeRate(double overtimeRate) {
		this.overtimeRate = overtimeRate;
	}

	public double getMaxHours() {
		return maxHours;
	}

	public void setMaxHours(double maxHours) {
		if (this.minHours <= maxHours)
			this.maxHours = maxHours;
	}

	public double getMinHours() {
		return minHours;
	}

	public void setMinHours(double minHours) {
		if (this.maxHours >= minHours)
			this.minHours = minHours;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
