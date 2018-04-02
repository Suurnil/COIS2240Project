package punchClockTest;

import punchClock.*;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.SQLException;

@SuppressWarnings("unused")
public class PunchClockDBTest {
	
	
	@Test
	public void readTest() throws SQLException{
		PunchClockDB db = new PunchClockDB();
		
		
		Job expectedJob = new Job(10, 15, 20, 60, 0, "Job");
		Employee expectedEmp = new Employee(1, "John", "Smith", expectedJob);
		
		Employee observedEmp = db.readEmployee(1);
		
		assertEquals(expectedEmp, observedEmp);
		assertEquals(expectedJob, observedEmp.getJob());
		
		db.closeConn();
	}
	
	@Test
	public void writeTest() throws SQLException{
		PunchClockDB db = new PunchClockDB();
		
		int idNum = db.getNewIDNum();
		
		Job expectedJob = new Job(10, 20, 15, 60, 0, "Cook");
		Employee expectedEmp = new Employee(idNum, "Kevin", "Clarke", expectedJob);
		
		db.addEmployee(expectedEmp);
		
		Employee observedEmp = db.readEmployee(idNum);
		
		assertEquals(expectedEmp, observedEmp);
		assertEquals(expectedJob, observedEmp.getJob());
		
		db.closeConn();
	}
	
	@Test
	public void deleteTest() throws SQLException{
		PunchClockDB db = new PunchClockDB();

		Employee employee = new Employee(0, "Joe", "Brown", new Job(0, 0, 0, 0, 0, "Unemployed"));
		
		int expectedIDNum = db.getNewIDNum();
		
		db.addEmployee(employee);
		
		db.deleteEmployee(db.getNewIDNum() - 1);
		
		db.deleteJob("Unemployed");
		
		int observedIDNum = db.getNewIDNum();
		
		assertTrue(expectedIDNum == observedIDNum);
		
		db.closeConn();
	}
}
