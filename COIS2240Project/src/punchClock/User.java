package punchClock;	//Separate package for class to restrict access to certain methods for outside classes

public abstract class User {

	private int idNum;
	private String givenName;
	private String surname;

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
}
