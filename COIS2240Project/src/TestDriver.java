import java.sql.SQLException;
import java.time.*;

import punchClock.*;

public class TestDriver {

	public static void main(String[] args) throws SQLException {

		PunchClockDB db = new PunchClockDB();
		
		
		Administrator admin = new Administrator(0, null, null);
		Employee emp = new Employee(1, "givenName", "surname",  new Job(13.6, 10, 1.5*13.6, 20, 10, "Title"));
		
		/*System.out.println(emp.getJob().getPayRate());	
		
		admin.startShift(emp, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 30)));
		System.out.println(emp.getStartTime());
		
		admin.endShift(emp,LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 54)));	//simulating overnight work from 1:30pm to 1:54am
		System.out.println(emp.getRecHours());
		
		System.out.println(emp.getStartTime());
		
		System.out.println(emp.calcPay());
		
		System.out.println(emp.getJob().getPayRate());*/
		
		int i = db.getNewIDNum();
		
		db.addEmployee(emp);
		
		Employee newEmp = db.readEmployee(i);
		
		
		System.out.println(i);
		
		System.out.println(newEmp.toString());
		
		db.closeConn();



	}

}
