package punchClock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Database {
	private static Connection conn;

	private static Statement statement;

	private static ResultSet rs;

	public Database() {

		try {
			conn = DriverManager.getConnection("jdbc:sqlite:src\\punchClockDatabase.db");
			statement = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		createUserTable();
		createAdminTable();
		createJobTable();
		createEmployeeTable();
	}

	/*
	 * *************************************************************************
	 * Table Creation Methods
	 * *************************************************************************
	 */

	private void createUserTable() {
		// creates a table of users if one does not exist
		try {
			statement.execute(
					"CREATE TABLE IF NOT EXISTS user (idNum INTEGER PRIMARY KEY, givenName TEXT, surname TEXT)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createAdminTable() {
		try {
			statement.execute(
					"CREATE TABLE IF NOT EXISTS admin (idNum INTEGER NOT NULL, givenName TEXT, surname TEXT, FOREIGN KEY(idNum) REFERENCES user(idNum))");
		} catch (SQLException e) {

		}
	}

	private void createJobTable() {
		// creates a table of jobs if one does not exist
		try {
			statement.execute(
					"CREATE TABLE IF NOT EXISTS job (title TEXT PRIMARY KEY, payRate DOUBLE, overtimeRate DOUBLE, overtimeHours DOUBLE, minHours DOUBLE, maxHours DOUBLE)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createEmployeeTable() {
		try {
			statement.execute("CREATE TABLE IF NOT EXISTS employee " + "(idNum INTEGER NOT NULL," + "givenName TEXT,"
					+ "surname TEXT," + "job TEXT," + "startTime TEXT," + "recHours DOUBLE,"
					+ "recHoursTotal DOUBLE, FOREIGN KEY(job) REFERENCES job(title), FOREIGN KEY(idNum) REFERENCES user(idNum))");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public static class Update {

		/*
		 * *************************************************************************
		 * Table Insertion Methods
		 * *************************************************************************
		 */

		private static void insertUser(User user) {
			// insert new user record into table
			try {
				statement.executeUpdate("INSERT INTO user (givenName, surname) VALUES ('" + user.getGivenName() + "','"
						+ user.getSurname() + "')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void insertAdmin(Administrator admin) {
			try {
				statement.executeUpdate("INSERT INTO admin (idNum, givenName, surname) VALUES (" + admin.getIdNum() + ", '"
						+ admin.getGivenName() + "', '" + admin.getSurname() + "')");
			} catch (SQLException e) {

			}
		}

		private static void insertEmployee(Employee employee) {
			try {
				statement.executeUpdate(
						"INSERT INTO employee " + "(idNum, givenName, surname, job, recHours, recHoursTotal) " + "VALUES ("
								+ employee.getIdNum() + ",'" + employee.getGivenName() + "'," + "'" + employee.getSurname()
								+ "'," + "'" + employee.getJob().getTitle() + "'," + employee.getRecHours() + ","
								+ employee.getRecHoursTotal() + ")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void insertJob(Job job) {
			try {
				statement.executeUpdate("INSERT INTO job (title, payRate, overtimeRate, overtimeHours, minHours, maxHours)"
						+ "VALUES ('" + job.getTitle() + "'," + job.getPayRate() + "," + job.getOvertimeRate() + ","
						+ job.getOvertimeHours() + "," + job.getMinHours() + "," + job.getMaxHours() + ")");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void setUserId(User user) {
			// set the user idNum to the max value in table
			try {
				rs = statement.executeQuery("SELECT MAX(idNum) FROM user");
				user.setIdNum(rs.getInt(1));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * *************************************************************************
		 * Public Table Insertion Methods
		 * *************************************************************************
		 */

		public static void addUser(User user) {
			insertUser(user);
			setUserId(user);
		}

		public static void addEmployee(Employee employee) {
			if (Validate.validateUser(employee) == false) {
				addUser(employee);
			}
			if (Validate.validateJob(employee.getJob()) == false) {
				addJob(employee.getJob());
			}

			insertEmployee(employee);
		}

		public static void addJob(Job job) {
			if (Validate.validateJob(job) == false) {
				insertJob(job);
			}
		}

		public static void addAdmin(Administrator admin) {
			if (Validate.validateUser(admin) == false) {
				addUser(admin);
			}
			insertAdmin(admin);

		}
		
		/*
		 * *********************************************************************
		 * **** Complete Update Methods
		 * *********************************************************************
		 * ****
		 */

		public static void updateUser(User user) {
			if (Validate.validateUser(user)) {
				updateUserGivenName(user.getIdNum(), user.getGivenName());
				updateUserSurname(user.getIdNum(), user.getSurname());
			}

		}

		public static void updateEmployee(Employee employee) {
			if (Validate.validateEmployeeId(employee.getIdNum())) {
				updateUser(employee);
				updateEmployeeGivenName(employee.getIdNum(), employee.getGivenName());
				updateEmployeeSurname(employee.getIdNum(), employee.getSurname());
				updateEmployeeJob(employee.getIdNum(), employee.getJob());
				updateEmployeeRecHours(employee.getIdNum(), employee.getRecHours());
				updateEmployeeRecHoursTotal(employee.getIdNum(), employee.getRecHoursTotal());
				updateEmployeeStartTime(employee.getIdNum(), employee.getStartTime());
			}
		}

		public static void updateJob(Job job) {
			if (Validate.validateJob(job)) {
				updateJobPayRate(job.getTitle(), job.getPayRate());
				updateJobOvertimeHours(job.getTitle(), job.getOvertimeHours());
				updateJobOvertimeRate(job.getTitle(), job.getOvertimeRate());
				updateJobMaxHours(job.getTitle(), job.getMaxHours());
				updateJobMinHours(job.getTitle(), job.getMinHours());
			}

		}

		public static void updateAdmin(Administrator admin) {
			if (Validate.validateAdminId(admin.getIdNum())) {
				updateUser(admin);
				updateAdminGivenName(admin.getIdNum(), admin.getGivenName());
				updateAdminSurname(admin.getIdNum(), admin.getGivenName());
			}
		}

		/*
		 * *********************************************************************
		 * **** Field Update Methods
		 * *********************************************************************
		 * ****
		 */

		private static void updateUserGivenName(int idNum, String givenName) {
			try {
				statement.executeUpdate("UPDATE user SET givenName = '" + givenName + "' WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateUserSurname(int idNum, String surname) {
			try {
				statement.executeUpdate("UPDATE user SET surname = '" + surname + "' WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private static void updateEmployeeGivenName(int idNum, String givenName) {
			try {
				updateUserGivenName(idNum, givenName);
				statement.executeUpdate("UPDATE employee SET givenName = '" + givenName + "' WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateEmployeeSurname(int idNum, String surname) {
			try {
				updateUserSurname(idNum, surname);
				statement.executeUpdate("UPDATE employee SET surname = '" + surname + "' WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateEmployeeJob(int idNum, Job job) {
			if (Validate.validateJob(job)) {
				try {
					statement
							.executeUpdate("UPDATE employee SET job = '" + job.getTitle() + "' WHERE idNum = " + idNum);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		private static void updateEmployeeRecHours(int idNum, double recHours) {
			try {
				statement.executeUpdate("UPDATE employee SET recHours = " + recHours + " WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateEmployeeRecHoursTotal(int idNum, double recHoursTotal) {
			try {
				statement.executeUpdate(
						"UPDATE employee SET recHoursTotal = " + recHoursTotal + " WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateEmployeeStartTime(int idNum, LocalDateTime startTime) {
			if (startTime != null) {
				try {
					statement.executeUpdate(
							"UPDATE employee SET startTime = '" + startTime.toString() + "' WHERE idNum = " + idNum);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					statement.executeUpdate("UPDATE employee SET startTime = null WHERE idNum = " + idNum);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		private static void updateAdminGivenName(int idNum, String givenName) {
			try {
				updateUserSurname(idNum, givenName);
				statement.executeUpdate("UPDATE admin SET givenName = '" + givenName + "' WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateAdminSurname(int idNum, String surname) {
			try {
				updateUserSurname(idNum, surname);
				statement.executeUpdate("UPDATE admin SET surname = '" + surname + "' WHERE idNum = " + idNum);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private static void updateJobPayRate(String title, double payRate) {
			try {
				statement.executeUpdate("UPDATE job SET payRate = " + payRate + " WHERE title = '" + title + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private static void updateJobOvertimeHours(String title, double overtimeHours) {
			try {
				statement.executeUpdate(
						"UPDATE job SET overtimeHours = " + overtimeHours + " WHERE title = '" + title + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private static void updateJobOvertimeRate(String title, double overtimeRate) {
			try {
				statement.executeUpdate(
						"UPDATE job SET overtimeRate = " + overtimeRate + " WHERE title = '" + title + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private static void updateJobMaxHours(String title, double maxHours) {
			try {
				statement.executeUpdate("UPDATE job SET maxHours = " + maxHours + " WHERE title = '" + title + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		private static void updateJobMinHours(String title, double minHours) {
			try {
				statement.executeUpdate("UPDATE job SET minHours = " + minHours + " WHERE title = '" + title + "'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * *************************************************************************
	 * Data Validation Methods
	 * *************************************************************************
	 */
	public static class Validate {
		private static Boolean validateUser(User user) {
			// returns true if there is an existing record of user
			try {
				int idNum = user.getIdNum();
				return statement.executeQuery("SELECT * FROM user WHERE idNum = " + idNum).next();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		private static Boolean validateJob(Job job) {
			// return true if there is an existing record of job
			try {
				String title = job.getTitle();

				return statement.executeQuery("SELECT * FROM job WHERE title = '" + title + "'").next();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}

		public static Boolean validateUserId(int idNum) {
			try {
				return statement.executeQuery("SELECT * FROM user WHERE idNum = " + idNum).next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}

		public static Boolean validateEmployeeId(int idNum) {
			if (validateUserId(idNum)) {
				try {
					return statement.executeQuery("SELECT * FROM employee WHERE idNum = " + idNum).next();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
			return false;
		}

		public static Boolean validateAdminId(int idNum) {
			if (validateUserId(idNum)) {
				try {
					return statement.executeQuery("SELECT * FROM admin WHERE idNum = " + idNum).next();
				} catch (SQLException e) {
					return false;
				}
			}
			return null;
		}
	}

	public void closeConn() {
		try {
			statement.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static class Read {
		public static Employee readEmployee(int idNum) {
			try {
				rs = statement.executeQuery("SELECT * FROM employee WHERE idNum = " + idNum);
				if (rs.next()) {
					String givenName = rs.getString("givenName");
					String surname = rs.getString("surname");
					String title = rs.getString("job");
					LocalDateTime startTime;
					if (rs.getString("startTime") != null) {
						startTime = LocalDateTime.parse(rs.getString("startTime"));
					} else {
						startTime = null;
					}

					double recHours = rs.getDouble("recHours");
					double recHoursTotal = rs.getDouble("recHoursTotal");

					return new Employee(idNum, givenName, surname, startTime, recHours, recHoursTotal, readJob(title));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		public static Job readJob(String title) {
			try {
				rs = statement.executeQuery("SELECT * FROM job WHERE title = '" + title + "'");

				if (rs.next()) {

					double payRate = rs.getDouble("payRate");
					double overtimeHours = rs.getDouble("overtimeHours");
					double overtimeRate = rs.getDouble("overtimeRate");
					double maxHours = rs.getDouble("maxHours");
					double minHours = rs.getDouble("minHours");

					return new Job(payRate, overtimeHours, overtimeRate, maxHours, minHours, title);
				} else {
					return null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		public static Administrator readAdmin(int idNum) {
			try {
				rs = statement.executeQuery("SELECT * FROM admin WHERE idNum = " + idNum);

				if (rs.next()) {

					String givenName = rs.getString("givenName");
					String surname = rs.getString("surname");

					return new Administrator(idNum, givenName, surname);
				} else {
					return null;
				}
			} catch (SQLException e) {
				return null;
			}
		}

		public static String searchJob(String title) {
			String searchResults = "";

			try {
				rs = statement.executeQuery("SELECT * FROM job WHERE title = '" + title + "'");

				while (rs.next()) {
					searchResults = searchResults.concat(rs.getString("title") + ", " + rs.getString("payRate") + ", "
							+ rs.getString("overtimeHours") + ", " + rs.getString("overtimeRate") + ", "
							+ rs.getString("minHours") + ", " + rs.getString("maxHours") + "\n");
				}
				System.out.println(searchResults);
				System.out.println("searched");
				return searchResults;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return searchResults;
		}

		public static String searchJob() {
			String searchResults = "";

			try {
				rs = statement.executeQuery("SELECT * FROM job");

				while (rs.next()) {
					searchResults = searchResults.concat(rs.getString("title") + ", " + rs.getString("payRate") + ", "
							+ rs.getString("overtimeHours") + ", " + rs.getString("overtimeRate") + ", "
							+ rs.getString("minHours") + ", " + rs.getString("maxHours") + "\n");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return searchResults;
		}
		
		public static String searchEmployeeID(int idNum){
			String searchResults = "";

			try {
				rs = statement.executeQuery("SELECT * FROM employee WHERE idNum = " + idNum + "");

				while (rs.next()) {
					searchResults = searchResults.concat(rs.getString("idNum") + ", " + rs.getString("givenName") + ", "
							+ rs.getString("surname") + ", " + rs.getString("job") + ", "
							+ rs.getString("startTime") + ", " + rs.getString("recHours") + ", " + rs.getString("recHoursTotal") + "\n");
				}
				System.out.println(searchResults);
				System.out.println("searched");
				return searchResults;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return searchResults;
		}
		
		public static String searchEmployee(){
			String searchResults = "";

			try {
				rs = statement.executeQuery("SELECT * FROM employee");

				while (rs.next()) {
					searchResults = searchResults.concat(rs.getString("idNum") + ", " + rs.getString("givenName") + ", "
							+ rs.getString("surname") + ", " + rs.getString("job") + ", "
							+ rs.getString("startTime") + ", " + rs.getString("recHours") + ", " + rs.getString("recHoursTotal") + "\n");
				}
				System.out.println(searchResults);
				System.out.println("searched");
				return searchResults;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return searchResults;
		}
	}

	/*
	 * public void clearDB() throws SQLException{ statement.executeUpdate(
	 * "DELETE FROM employee"); statement.executeUpdate("DELETE FROM job"); }
	 */

	/*
	 * public Job readJob(String title) throws SQLException { rs =
	 * statement.executeQuery("SELECT * FROM job WHERE title = '" + title +
	 * "'");
	 * 
	 * if (rs.next()) { Job job = new Job(rs.getDouble("payRate"),
	 * rs.getDouble("overtimeHours"), rs.getDouble("overtimeRate"),
	 * rs.getDouble("maxHours"), rs.getDouble("minHours"), title); return job; }
	 * return null; }
	 */

	/*
	 * public void deleteJob(String title) throws SQLException{
	 * statement.executeUpdate("DELETE FROM job WHERE title = '" + title + "'");
	 * }
	 */
	/*
	 * 
	 * public Employee readEmployee(int idNum) throws SQLException { // reads
	 * employee record with idNum, then reads job record with // resulting job;
	 * returns new Employee with values in those records
	 * 
	 * rs = statement.executeQuery("Select * FROM employee WHERE idNum = " +
	 * idNum);
	 * 
	 * if (rs.next()) { String givenName = rs.getString("givenName"); String
	 * surname = rs.getString("surname"); LocalDateTime startTime; if
	 * (rs.getString("startTime") != null) { startTime =
	 * LocalDateTime.parse(rs.getString("startTime")); } else { startTime =
	 * null; } double recHours = rs.getDouble("recHours"); double recHoursTotal
	 * = rs.getDouble("recHoursTotal"); Employee employee = new Employee(idNum,
	 * givenName, surname, startTime, recHours, recHoursTotal,
	 * readJob(rs.getString("job"))); return employee; }else{ return null; } }
	 * 
	 * public void deleteEmployee(int idNum) throws SQLException{
	 * statement.executeUpdate("DELETE FROM employee WHERE idNum = " + idNum); }
	 * 
	 * public int getNewIDNum() throws SQLException {
	 * 
	 * rs = statement.executeQuery("SELECT MAX(idNum) FROM employee");
	 * 
	 * return (rs.getInt(1) + 1);
	 * 
	 * }
	 */
}
