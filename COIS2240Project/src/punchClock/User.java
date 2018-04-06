package punchClock;	//Separate package for class to restrict access to certain methods for outside classes

public abstract class User {

	private int idNum;
	private String givenName;
	private String surname;
	
	public User(){}

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

	protected void setIdNum(int idNum){
		this.idNum = idNum;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (givenName == null) {
			if (other.givenName != null)
				return false;
		} else if (!givenName.equals(other.givenName))
			return false;
		if (idNum != other.idNum)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}
}
