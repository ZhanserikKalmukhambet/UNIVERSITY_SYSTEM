package university;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable, Cloneable, Comparable<Object>{
    private static final long serialVersionUID = 1L;
    
	public String title;
    public int credit;
    public int capacity = 300;
    public String code;
    public Vector<Teacher> instructors = new Vector<Teacher>();
    public Vector<Student> students = new Vector<Student>();
    public Course prerequisite;
    public Vector<Lesson> lessons = new Vector<Lesson>();
    public Vector<File> files = new Vector<File>();
    
	public Course() {
		
	}
	public Course(String title, String code) {
		this.title = title;
		this.code = code;
	}
	public Course(String title, String code, int credit, int capacity, Course prereq) {
		this(title, code);
		this.credit = credit;
		this.capacity = capacity;
		this.prerequisite = prereq;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Vector<Teacher> getInstructors() {
		return instructors;
	}
	public void setInstructors(Vector<Teacher> instructors) {
		this.instructors = instructors;
	}
	public Vector<Student> getStudents() {
		return students;
	}
	public void setStudents(Vector<Student> students) {
		this.students = students;
	}
	public Course getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(Course prerequisite) {
		this.prerequisite = prerequisite;
	}
	public Vector<Lesson> getLessons() {
		return lessons;
	}
	public void setLessons(Vector<Lesson> lessons) {
		this.lessons = lessons;
	}
	public Vector<File> getFiles() {
		return files;
	}
	public void addFile(File file) {
		files.add(file);
	}
	
	public boolean addStudent(Student s) {
		if(students.contains(s))
			return false;
		
		students.add(s);
		return true;
	}
	
	public void addLesson(Lesson l) {
		lessons.add(l);
	}
	
	public boolean addInstructor(Teacher t) {
		if(instructors.contains(t))
			return false;
		
		instructors.add(t);
		return true;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(code, prerequisite, title);
	}
		
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(code, other.code) && Objects.equals(prerequisite, other.prerequisite)
				&& Objects.equals(title, other.title);
	}
	public String toString() {
		return "Course [title=" + title + ", credit=" + credit + ", capacity=" + capacity + ", code=" + code
				+ ", instructors=" + instructors + ", students=" + students + ", prerequisites=" + prerequisite + "]";
	}
	@Override
	public int compareTo(Object o) {
		Course c = (Course)o;
		return this.title.compareTo(c.title);
	}
}
