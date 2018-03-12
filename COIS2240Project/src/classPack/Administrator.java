package classPack;

import java.time.*;

public class Administrator extends User {

	
	public void startShift(Employee employee, LocalDateTime startTime){
		employee.setStartTime(startTime);
	}
	
	public void endShift(Employee employee, LocalDateTime endTime){
		employee.endShift(endTime);
	}
}
