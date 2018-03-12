import java.time.*;

import classPack.Administrator;
import classPack.Employee;

public class TestDriver {

	public static void main(String[] args) {

		
		Administrator admin = new Administrator();
		Employee emp = new Employee();
		
		
		admin.startShift(emp, LocalTime.of(13, 30));
		System.out.println(emp.getStartTime());
		
		admin.endShift(emp, LocalTime.of(19, 26));
		System.out.println(emp.getRecHours());
		
		System.out.println(emp.getStartTime());

	}

}
