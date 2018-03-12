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

	
}
