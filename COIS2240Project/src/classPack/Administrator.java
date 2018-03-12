package classPack;

import java.time.*;

public class Administrator extends User {

	
	public void startShift(Employee employee, LocalTime startTime){
		employee.setStartTime(startTime);
	}
	
	public void endShift(Employee employee, LocalTime endTime){
		super.endShift(employee, endTime);
	}
}
