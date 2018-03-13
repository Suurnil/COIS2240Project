package punchClock;	//Separate package for class to restrict access to certain methods for outside classes

import java.time.*;

public class Administrator extends User {

	
	public void startShift(Employee employee, LocalDateTime startTime){
		employee.startShift(startTime);
	}
	
	public void endShift(Employee employee, LocalDateTime endTime){
		employee.endShift(endTime);
	}
}
