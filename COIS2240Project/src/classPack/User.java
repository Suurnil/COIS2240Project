package classPack;

import java.time.*;

public abstract class User {

	private int idNum;
	private String givenName;
	private String surname;

	public User() {
	}

	public User(int idNum, String givenName, String surname) {
		this.idNum = idNum;
		this.givenName = givenName;
		this.surname = surname;
	}

	public int getIdNum() {
		return idNum;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	/*
	 * protected access modifier limits usage to classes in classPack Employee
	 * can use startShift(Employee) Main cannot use
	 * Employee.startShift(Employee) Main must use Employee.startShift() instead
	 */

	protected void endShift(Employee employee, LocalTime endTime) {
		/*
		 * use to end a shift for employee at specified time check if there is a
		 * startTime gets workTime & converts to double hoursWorked increments
		 * recHours and recHoursTotal by hoursWorked0ooo
		 */
		if (employee.getStartTime() != null) { // when there is a startTime
			Duration workTime = Duration.between(employee.getStartTime(),
					endTime); /* Duration class from java.time */
			double hoursWorked = (workTime.toMinutes()) / 60.0;
			/*
			 * gets minutes of workTime then converts as Duration.toHours()
			 * doesn't account for minutes over the hours
			 */
			employee.setRecHours(employee.getRecHours() + hoursWorked);
			employee.setRecHoursTotal(employee.getRecHoursTotal() + hoursWorked);
			employee.setStartTime(null);
			/*
			 * shift is over, so there is no longer a startTime
			 */
		} else {
			System.out.println("Shift was not started");
		}
	}
}
