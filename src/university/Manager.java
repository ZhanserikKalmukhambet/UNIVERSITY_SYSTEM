package university;

import java.time.LocalDate;

import java.util.Objects;

import enums.ManagerType;
import exceptions.*;

public class Manager extends Employee implements ManageNews, ManageCourseFiles, ViewInfo {

	private static final long serialVersionUID = 1L;

	private ManagerType type;

	public Manager() {
    	super();
    }
    public Manager(String name, String surname) {
    	super(name, surname);
    }
    public Manager(String name, String surname, double workExperience, int salary, LocalDate hireDate) {
    	super(name, surname, workExperience, salary, hireDate);
    }
    public Manager(String name, String surname, double workExperience, int salary, LocalDate hireDate, ManagerType type) {
    	super(name, surname, workExperience, salary, hireDate);
    	this.type = type;
    }

	public ManagerType getType() {
		return this.type;
	}
	public void setType(ManagerType type) {
		this.type = type;
	}

	public boolean approveStudentRegistration(Course c, Student s) {
		if(c.getStudents().contains(s))
			return false;

		if(c.capacity == c.getStudents().size())
			return false;

		if(s.getTotalCredits() + c.credit > 30)
			return false;

		return true;
	}

	public void addCoursetoStudent(Course c, Student s) throws CourseNotFoundException{
		try {
			if(UniSystem.getDatabase().getCourses().contains(c)) {
				c.getStudents().add(s);
			}
			else {
				throw new CourseNotFoundException("Course is not found in university database!");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public boolean dropCoursefromStudent(Course c, Student s) {
		return c.getStudents().remove(s);
	}
	
	public boolean addCourseToSystem(Course c) {
		if(UniSystem.getDatabase().getCourses().contains(c))
			return false;
		
		return UniSystem.getDatabase().getCourses().add(c);
	}

	public void assignCourseForTeacher(Course c, Teacher t) throws CourseNotFoundException{
		try {
			if(UniSystem.getDatabase().getCourses().contains(c)) {
				c.getInstructors().add(t);
			}
			else {
				throw new CourseNotFoundException("Course is not found in university database!");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	public String reportGeneration(Course c) {
		String report = "Performance of students in course " + c.getTitle() + " : \n";

		for(Student s : c.getStudents()) {
			report += s.getSurname() + " " + s.getName() + " " + s.getTranscript().getTotalScoreForGpa() + "\n";
		}

		return report;
	}

	@Override
	public String viewStudentInfo(Student s) throws StudentNotFoundException{
		try {
			if(UniSystem.getDatabase().getStudents().contains(s)) {
				return s.toString();
			}
			else {
				throw new StudentNotFoundException("No such student in university database!");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "";
	}

	@Override
	public String viewTeacherInfo(Teacher t) throws TeacherNotFoundException{
		try {
			if(UniSystem.getDatabase().getTeachers().contains(t)) {
				return t.toString();
			}
			else {
				throw new TeacherNotFoundException("No such teacher in university database!");
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return "";
	}

	@Override
	public boolean uploadFile(File file, Course c) {
		return c.getFiles().add(file);
	}

	@Override
	public boolean removeFile(File file, Course c) {
		return c.getFiles().remove(file);
	}

	public boolean acceptRequest(Request r) {
		return UniSystem.getDatabase().getRequests().add(r);
	}

	public boolean reportNews(News news) {
		if(UniSystem.getDatabase().getNews().contains(news))
			return false;

		UniSystem.getDatabase().getNews().add(news);
		return true;
	}
	public boolean removeNews(News news) {
		return UniSystem.getDatabase().getNews().remove(news);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(type);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manager other = (Manager) obj;
		return type == other.type;
	}

	@Override
	public String toString() {
		return "Manager - name - " + super.getName() + ", surname - " + super.getSurname();
	}
}
