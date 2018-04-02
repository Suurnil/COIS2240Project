import java.time.*;

import punchClock.*;

public class TestDriver {

	public static void main(String[] args) {

		
		Administrator admin = new Administrator();
		Employee emp = new Employee(new Job(13.6, 10, 1.5*13.6, 20, 10, "Title"));
		
		System.out.println(emp.getJob().getPayRate());	
		
		admin.startShift(emp, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 30)));
		System.out.println(emp.getStartTime());
		
		admin.endShift(emp,LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 54)));	//simulating overnight work from 1:30pm to 1:54am
		System.out.println(emp.getRecHours());
		
		System.out.println(emp.getStartTime());
		
		System.out.println(emp.calcPay());
		
		System.out.println(emp.getJob().getPayRate());



	}

}
