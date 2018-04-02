package punchClock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class PunchClockDB {
	private Connection conn;

	private Statement statement;

	private ResultSet rs;

	public PunchClockDB() throws SQLException {
		conn = DriverManager.getConnection("jdbc:sqlite:src\\punchClockDatabase.db");
		statement = conn.createStatement();

		// PRIMARY KEY generates an integer value that is the number of records

		statement.execute("CREATE TABLE IF NOT EXISTS user (idNum INTEGER PRIMARY KEY, givenName TEXT, surname TEXT)");
		
		statement.execute(
				"CREATE TABLE IF NOT EXISTS job (title TEXT PRIMARY KEY, payRate DOUBLE, overtimeRate DOUBLE, overtimeHours DOUBLE, minHours DOUBLE, maxHours DOUBLE)");

		// FOREIGN KEY requires that field to exist as a PRIMARY KEY in the
		// referenced table (i.e. job must be an existing title in the job
		// table)

		statement.execute("CREATE TABLE IF NOT EXISTS employee " + "(idNum INTEGER," + "givenName TEXT,"
				+ "surname TEXT," + "job TEXT," + "startTime TEXT," + "recHours DOUBLE,"
				+ "recHoursTotal DOUBLE, FOREIGN KEY(job) REFERENCES job(title), FOREIGN KEY(idNum) REFERENCES user(idNum))");

	}

	public void closeConn() throws SQLException {
		statement.close();
		conn.close();
	}
	
	public void clearDB() throws SQLException{
		statement.executeUpdate("DELETE FROM employee");
		statement.executeUpdate("DELETE FROM job");
	}

	/*
	 * *************************************************************************
	 * Job Methods
	 * *************************************************************************
	 */
	public void addJob(Job job) throws SQLException {
		statement.executeUpdate("INSERT INTO job (title, payRate, overtimeRate, overtimeHours, minHours, maxHours)"
				+ "VALUES ('" + job.getTitle() + "'," + job.getPayRate() + "," + job.getOvertimeRate() + ","
				+ job.getOvertimeHours() + "," + job.getMinHours() + "," + job.getMaxHours() + ")");
	}

	public Job readJob(String title) throws SQLException {
		rs = statement.executeQuery("SELECT * FROM job WHERE title = '" + title + "'");

		if (rs.next()) {
			Job job = new Job(rs.getDouble("payRate"), rs.getDouble("overtimeHours"), rs.getDouble("overtimeRate"),
					rs.getDouble("maxHours"), rs.getDouble("minHours"), title);
			return job;
		}
		return null;
	}
	
	public void deleteJob(String title) throws SQLException{
		statement.executeUpdate("DELETE FROM job WHERE title = '" + title + "'");
	}

	public void updateJob(Job job) throws SQLException {
		updateJobPayRate(job, job.getPayRate());
		updateJobOvertimeHours(job, job.getOvertimeHours());
		updateJobOvertimeRate(job, job.getOvertimeRate());
		updateJobMaxHours(job, job.getMaxHours());
		updateJobMinHours(job, job.getMinHours());
	}

	private void updateJobPayRate(Job job, double payRate) throws SQLException {
		statement.executeUpdate("UPDATE job SET payRate = " + payRate + "WHERE title = " + job.getTitle());
	}

	private void updateJobMaxHours(Job job, double maxHours) throws SQLException {
		statement.executeUpdate("UPDATE job SET maxHours = " + maxHours + "WHERE title = " + job.getTitle());
	}

	private void updateJobMinHours(Job job, double minHours) throws SQLException {
		statement.executeUpdate("UPDATE job SET minHours = " + minHours + "WHERE title = " + job.getTitle());
	}

	private void updateJobOvertimeRate(Job job, double overtimeRate) throws SQLException {
		statement.executeUpdate("UPDATE job SET overtimeRate = " + overtimeRate + "WHERE title = " + job.getTitle());
	}

	private void updateJobOvertimeHours(Job job, double overtimeHours) throws SQLException {
		statement.executeUpdate("UPDATE job SET overtimeHours = " + overtimeHours + "WHERE title = " + job.getTitle());
	}

	/*
	 * *************************************************************************
	 * User Methods
	 * *************************************************************************
	 */
	
	public void addUser(User user) throws SQLException{
		statement.executeUpdate("INSERT INTO user (givenName, surname) VALUES ('" + user.getGivenName() + "','" + user.getSurname() + "')");
		user.setIdNum(getNewUserIdNum());
	}
	
	private int getNewUserIdNum() throws SQLException{
		rs = statement.executeQuery("SELECT MAX(idNum) FROM user");
		
		return rs.getInt(1);
	}
	
	/*
	 * *************************************************************************
	 * Employee Methods
	 * *************************************************************************
	 */
	public void addEmployee(Employee employee) throws SQLException {

		// adds a new record to employee and returns the idNum

		rs = statement.executeQuery("SELECT * FROM job WHERE title = '" + employee.getJob().getTitle() + "'");

		if (rs.next() == false) {
			addJob(employee.getJob());
		}
		if (employee.getStartTime() != null) {
			statement.executeUpdate(
					"INSERT INTO employee " + "(givenName, surname, job, startTime, recHours, recHoursTotal) "
							+ "VALUES ('" + employee.getGivenName() + "'," + "'" + employee.getSurname() + "'," + "'"
							+ employee.getJob().getTitle() + "'," + "'" + employee.getStartTime().toString() + "',"
							+ employee.getRecHours() + "," + employee.getRecHoursTotal() + ")");
		} else {
			statement.executeUpdate("INSERT INTO employee " + "(givenName, surname, job, recHours, recHoursTotal) "
					+ "VALUES ('" + employee.getGivenName() + "'," + "'" + employee.getSurname() + "'," + "'"
					+ employee.getJob().getTitle() + "'," + employee.getRecHours() + "," + employee.getRecHoursTotal()
					+ ")");
		}
	}

	public Employee readEmployee(int idNum) throws SQLException {
		// reads employee record with idNum, then reads job record with
		// resulting job; returns new Employee with values in those records

		rs = statement.executeQuery("Select * FROM employee WHERE idNum = " + idNum);

		if (rs.next()) {
			String givenName = rs.getString("givenName");
			String surname = rs.getString("surname");
			LocalDateTime startTime;
			if (rs.getString("startTime") != null) {
				startTime = LocalDateTime.parse(rs.getString("startTime"));
			} else {
				startTime = null;
			}
			double recHours = rs.getDouble("recHours");
			double recHoursTotal = rs.getDouble("recHoursTotal");
			Employee employee = new Employee(idNum, givenName, surname, startTime, recHours, recHoursTotal,
					readJob(rs.getString("job")));
			return employee;
		}else{
			return null;
		}
	}
	
	public void deleteEmployee(int idNum) throws SQLException{
		statement.executeUpdate("DELETE FROM employee WHERE idNum = " + idNum);
	}

	public int getNewIDNum() throws SQLException {

		rs = statement.executeQuery("SELECT MAX(idNum) FROM employee");

		return (rs.getInt(1) + 1);

	}

	public void updateEmployee(Employee employee) throws SQLException {
		updateEmployeeJob(employee, employee.getJob());
		updateEmployeeRecHours(employee, employee.getRecHours());
		updateEmployeeRecHoursTotal(employee, employee.getRecHoursTotal());
		updateEmployeeStartTime(employee, employee.getStartTime());
	}

	private void updateEmployeeStartTime(Employee employee, LocalDateTime startTime) throws SQLException {
		if (startTime != null) {
			statement.executeUpdate("UPDATE employee SET startTime = " + startTime.toString() + " WHERE idNum = "
					+ employee.getIdNum());
		} else {
			statement.executeUpdate("UPDATE employee SET startTime = null WHERE idNum = " + employee.getIdNum());
		}
	}

	private void updateEmployeeRecHours(Employee employee, double recHours) throws SQLException {
		statement.executeUpdate("UPDATE employee SET recHours = " + recHours + " WHERE idNum = " + employee.getIdNum());
	}

	private void updateEmployeeRecHoursTotal(Employee employee, double recHoursTotal) throws SQLException {
		statement.executeUpdate(
				"UPDATE employee SET recHoursTotal = " + recHoursTotal + " WHERE idNum = " + employee.getIdNum());
	}

	private void updateEmployeeJob(Employee employee, Job job) throws SQLException {
		statement
				.executeUpdate("UPDATE employee SET job = " + job.getTitle() + " WHERE idNum = " + employee.getIdNum());
	}
}
