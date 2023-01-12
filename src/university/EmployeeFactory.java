package university;

import java.io.Serializable;

import library.Librarian;

public class EmployeeFactory implements Serializable{
	private static final long serialVersionUID = 1L;

	public static Employee getEmployee(String employeeType) {
		if (employeeType == null) {
			return null;
		}
		if (employeeType.equalsIgnoreCase("TEACHER")) {
			return new Teacher();
		}
		if (employeeType.equalsIgnoreCase("LIBRARIAN")) {
			return new Librarian();
		}
		if (employeeType.equalsIgnoreCase("MANAGER")) {
			return new Manager();
		}
		
		return null;
	}
}
