package punchClock; //Separate package for class to restrict access to certain methods for outside classes

import java.time.*;

public class Administrator extends User {
	/* *************************************************************************
	 * Constructors
	 * ************************************************************************* */
	public Administrator(int idNum, String givenName, String surname) {
		super(idNum, givenName, surname);
	}

	public Administrator(User user) {
		super(user.getIdNum(), user.getGivenName(), user.getSurname());
	}
	/* *************************************************************************
	 * Employee Methods
	 * 
	 * startShift(Employee employee, LocalDateTime startTime)
	 * 		start an employee's shift at the specified time
	 *  
	 * endShift(Employee employee, LocalDateTime endTime)
	 * 		end the employee's current shift
	 * 
	 * recordShift(Employee employee, LocalDateTime startTime, LocalDateTime endTime)
	 * 		record a shift that the employee worked
	 * 
	 * calcPay(Employee employee)
	 * 		return the pay an employee is owed for their recorded hours
	 * 
	 * createEmployee(User user, Job job)
	 * 		return a new employee 
	 * ************************************************************************* */
	public void startShift(Employee employee, LocalDateTime startTime) {
		employee.startShift(startTime);
	}

	public void endShift(Employee employee, LocalDateTime endTime) {
		employee.endShift(endTime);
	}

	public void recordShift(Employee employee, LocalDateTime startTime, LocalDateTime endTime) {
		employee.recordShift(startTime, endTime);
	}
	
	public double calcPay(Employee employee) {
		return employee.calcPay();
	}
	
	public Employee createEmployee(User user, Job job) {
		return new Employee(user, job);
	}
	/* *************************************************************************
	 * Job Methods
	 * 
	 * Job createJob(double payRate, double overtimeHours, double overtimeRate, double maxHours, double minHours,
	 *		String title)
	 *		return a new job that can be assigned to employees
	 *
	 * changePay(Job job, double newPayRate)
	 * 		change the payRate of a job
	 * 
	 * changeOvertime(Job job, double overtimeHours, double overtimeRate)
	 * 		change the hours needed for overtime and the overtime pay of a job
	 * 
	 * changeMinMaxHours(Job job, double minHours, double maxHours)
	 * 		change the min and max hours a job requires
	 * ************************************************************************* */
	public Job createJob(double payRate, double overtimeHours, double overtimeRate, double maxHours, double minHours,
			String title){
				return new Job(payRate, overtimeHours, overtimeRate, maxHours, minHours, title);
	}
	
	public void changePay(Job job, double newPayRate){
		job.setPayRate(newPayRate);
	}
	
	public void changeOvertime(Job job, double overtimeHours, double overtimeRate){
		job.setOvertimeHours(overtimeHours);
		job.setOvertimeRate(overtimeRate);
	}
	
	public void changeMinMaxHours(Job job, double minHours, double maxHours){
		job.setMinHours(minHours);
		job.setMinHours(minHours);
	}
}
