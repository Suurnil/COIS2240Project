import java.time.*;

import classPack.Administrator;
import classPack.Employee;
import classPack.Job;

public class TestDriver {

	public static void main(String[] args) {

		
		Administrator admin = new Administrator();
		Employee emp = new Employee(new Job(10, 40, 1.5, 20, 10, "Title"));
		
		Employee emp2 = new Employee(emp.getJob());
		
		System.out.println(emp.getJob().getPayRate());
		System.out.println(emp2.getJob().getPayRate());

		
		
		admin.startShift(emp, LocalDateTime.of(LocalDate.now(), LocalTime.of(13, 30)));
		System.out.println(emp.getStartTime());
		
		admin.endShift(emp,LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(1, 54)));	//simulating overnight work from 1:30pm to 1:54am
		System.out.println(emp.getRecHours());
		
		System.out.println(emp.getStartTime());
		
		emp.getJob().setPayRate(5.6);
		
		System.out.println(emp.getJob().getPayRate());
		System.out.println(emp2.getJob().getPayRate());



	}

}
