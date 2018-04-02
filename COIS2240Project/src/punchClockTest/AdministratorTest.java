package punchClockTest;

import java.time.*;

import static org.junit.Assert.*;

import org.junit.Test;

import punchClock.*;

public class AdministratorTest {

	Administrator admin = new Administrator(0, "", "");
	
	@Test
	public void creationTest(){
		
		Job expectedJob = new Job(14.0, 40, 20, 60, 15, "Cook");
		Job observedJob = admin.createJob(14.0, 40, 20, 60, 15, "Cook");
		
		assertEquals(expectedJob, observedJob);
		
		Employee expectedEmp = new Employee(1, "Kevin", "Clarke", expectedJob);
		Employee observedEmp = admin.createEmployee(1, "Kevin", "Clarke", observedJob );
		
		assertEquals(expectedEmp, observedEmp);
	}
	
	@Test
	public void shiftTest(){
		Employee employee = new Employee(1, "Kevin", "Clarke", new Job(10, 20, 15, 40, 10, "Cook"));
		
		LocalDateTime expectedStartTime = LocalDateTime.of(2000, 1, 1, 12, 00);
		
		admin.startShift(employee, LocalDateTime.of(2000, 1, 1, 12, 00));
		
		assertEquals(employee.getStartTime(), expectedStartTime);
		
		double expectedRecHours = 5.5;
		double expectedRecHoursTotal = 5.5;
		expectedStartTime = null;
		
		admin.endShift(employee, LocalDateTime.of(2000, 1, 1, 17, 30));
		
		assertTrue(employee.getRecHours() == expectedRecHours);
		assertTrue(employee.getRecHoursTotal() == expectedRecHoursTotal);
		assertEquals(employee.getStartTime(), expectedStartTime);
		
		expectedRecHours = 11.5;
		expectedRecHoursTotal = 11.5;
		
		admin.recordShift(employee, LocalDateTime.of(2000, 1, 2, 12, 00), LocalDateTime.of(2000, 1, 2, 18, 00));
		
		assertTrue(employee.getRecHours() == expectedRecHours);
		assertTrue(employee.getRecHoursTotal() == expectedRecHoursTotal);
		assertEquals(employee.getStartTime(), expectedStartTime);
		
		double expectedPay = 115.0;
		double observedPay = admin.calcPay(employee);
		
		assertTrue(expectedPay == observedPay);
	}
	
}
