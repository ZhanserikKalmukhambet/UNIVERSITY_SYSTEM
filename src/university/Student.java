package university;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import enums.Degree;
import enums.Presence;
import library.Book;
import pairs.Pair2;


public class Student extends User implements CanBorrowBook, UserInt {
	private static final long serialVersionUID = 1L;
	private double gpa;
	private String ID;
	private Faculty faculty;
	private Degree degree;
	private int course;
	private Boolean grant;
	private LocalDate admissionDate;
	private double fee;
	private Vector<Book> books;
	private Vector<StudentRequest> requests = new Vector<StudentRequest>();
	private Transcript transcript = new Transcript();
	public Student() {

	}
	public Student(String name, String surname) {
		super(name, surname);
	}
	public Student(String name, String surname, String ID) {
		this(name, surname);
		this.ID = ID;
	}

	public Student(String name, String surname, String ID, Faculty faculty, Degree degree, LocalDate date) {
		this(name, surname, ID);
		this.faculty = faculty;
		this.degree = degree;
		this.admissionDate = date;
	}
	public Student(String name, String surname,String ID, Faculty faculty, Degree degree, LocalDate date, Boolean grant, int course) {
		this(name, surname, ID, faculty, degree, date);
		this.grant = grant;
		this.course = course;
	}

	public Faculty getFaculty() {
		return faculty;
	}
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	public Vector<StudentRequest> getRequests() {
		return requests;
	}
	public void setRequests(Vector<StudentRequest> requests) {
		this.requests = requests;
	}
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public int getCourse() {
		return course;
	}
	public void setCourse(int course) {
		this.course = course;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public Boolean getGrant() {
		return grant;
	}
	public void setGrant(Boolean grant) {
		this.grant = grant;
	}
	public Vector<Book> getBooks() {
		return books;
	}
	public void setBooks(Vector<Book> books) {
		this.books = books;
	}
	public LocalDate getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(LocalDate admissionDate) {
		this.admissionDate = admissionDate;
	}

	public boolean addLesson(Lesson lesson) {
		if(lesson.getStudents().contains(this))
			return false;

		lesson.getStudents().add(this);
		return true;
	}

	public boolean registerForCourse(Course course) {
		if(this.getManager().approveStudentRegistration(course, this)) {
			course.getStudents().add(this);

			this.transcript.addCourseToTranscript(course);
			return true;
		}
		return false;
	}

	@SuppressWarnings("unlikely-arg-type")
	public boolean dropCourse(Course course) {
		if(!course.getStudents().contains(this))
			return false;

		course.getStudents().remove(this);
		this.getTranscript().getGrades().remove(course);
		return true;
	}

	public Vector<Course> getCourses() {
		Vector<Course> myCourses = new Vector<Course>();
		for(Course c : UniSystem.getDatabase().getCourses()) {
			if(c.getStudents().contains(this))
				myCourses.add(c);
		}

		return myCourses;
	}

	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}

	public Manager getManager() {
		return faculty.getManager();
	}

	public void rateTeacher(Teacher t, double rate) {
		if(0 < rate && rate < 5)
			t.setRating(t.getRating() + rate);
	}

	public int getTotalCredits() {
		int cnt = 0;
		for(Course c : this.getCourses()) {
			cnt += c.credit;
		}
		return cnt;
	}

	public Vector<Lesson> getLessons(){
		Vector<Lesson> myLessons = new Vector<Lesson>();
		for(Course c : UniSystem.getDatabase().getCourses()) {
			for(Lesson l : c.getLessons()) {
				if(l.getStudents().contains(this))
					myLessons.add(l);
			}
		}

		return myLessons;
	}

	public boolean applyForRequest(StudentRequest r) {
		this.requests.add(r);

		return this.getManager().acceptRequest(r);
	}

	public Transcript getTranscript() {
		return this.transcript;
	}

	public void viewTranscript() {
		System.out.println(transcript.toString());
	}

	public double getGPA() {
		double score = this.transcript.getTotalScoreForGpa();

		if(score >= 94.5)
			return 4.0;
		else if(score >= 89.5)
			return 3.75;
		else if(score >= 84.5)
			return 3.5;
		else if(score >= 79.5)
			return 3.0;
		else if(score >= 74.5)
			return 2.5;
		else if(score >= 69.5)
			return 2;
		else if(score >= 64.5)
			return 1.5;
		else if(score >= 59.5)
			return 1.0;
		else if(score >= 54.5)
			return 0.5;

		return 0;
	}

	public int compareTo(Object o) {
		Student s = (Student) o;

		if(this.getGPA() > s.getGPA())
			return 1;
		if(this.getGPA() < s.getGPA())
			return -1;

		return 0;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(admissionDate, course, degree, faculty, fee, gpa, grant, transcript);
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
		Student other = (Student) obj;
		return Objects.equals(admissionDate, other.admissionDate) && course == other.course && degree == other.degree
				&& Objects.equals(faculty, other.faculty)
				&& Double.doubleToLongBits(fee) == Double.doubleToLongBits(other.fee)
				&& Double.doubleToLongBits(gpa) == Double.doubleToLongBits(other.gpa)
				&& Objects.equals(grant, other.grant) && Objects.equals(transcript, other.transcript);
	}

	public boolean putAttendanceMark(AttendanceReport ar) {
		if(ar.getCloseTime().compareTo(LocalTime.now()) > 0) {
			for(Pair2 i : ar.getAttendance()) {
				if(i.getStudent().equals(this)) {
					i.p = Presence.PRESENT;
				};
			}

			return true;
		}
		return false;
	}
}
