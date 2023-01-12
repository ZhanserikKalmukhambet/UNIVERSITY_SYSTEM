
package university;

import java.util.Set;
import java.util.Vector;

import enums.MarkType;
import enums.Presence;
import enums.TitleTeacher;
import pairs.Pair1;
import pairs.Pair2;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class Teacher extends Employee implements CanBorrowBook, ManageCourseFiles, ViewInfo {
	private static final long serialVersionUID = 1L;

	private TitleTeacher title;
	private Double rating;
	private Double fee;

	public Teacher() {
    	super();
    }
    public Teacher(String name, String surname) {
    	super(name, surname);
    }
    public Teacher(String name, String surname, double workExperience, int salary, LocalDate hireDate) {
    	super(name, surname, workExperience, salary, hireDate);
    }
    public Teacher(String name, String surname, double workExperience, int salary, LocalDate hireDate, TitleTeacher title) {
    	super(name, surname, workExperience, salary, hireDate);
    	this.title = title;
    }

	public TitleTeacher getTitle() {
		return title;
	}
	public void setTitle(TitleTeacher title) {
		this.title = title;
	}
	public Double getRating() {
		return this.rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}

	public boolean putMark(Course course, Student s, MarkType m, double score) {
		if(UniSystem.getDatabase().getCourses().contains(course)) {
			if(course.getStudents().contains(s)) {
				Mark mark = new Mark();
				for(Pair1 p : s.getTranscript().getGrades()) {
					if(p.c.equals(course)) {
						mark = p.m;
						break;
					}
				}
				
				if(m == MarkType.ATT1) {
					mark.setAtt1Score(score);
				}
				else if(m == MarkType.ATT2) {
					mark.setAtt2Score(score);
				}
				else if(m == MarkType.FINAL) {
					mark.setFinalScore(score);
				}
				else if(m == MarkType.BONUS){
					mark.setBonus(score);
				}
				return true;
			}
			return false;
		}
		return false;
	}

	public Vector<Course> getCourses(){
		Vector<Course> myCourses = new Vector<Course>();
		for(Course c : UniSystem.getDatabase().getCourses()) {
			if(c.getInstructors().contains(this))
				myCourses.add(c);
		}

		return myCourses;
	}

	public Vector<Lesson> getLessons() {
		Vector<Lesson> myLessons = new Vector<Lesson>();
		for(Course c : getCourses()) {
			for(Lesson l : c.getLessons()) {
				if(l.getTeacher().equals(this))
					myLessons.add(l);
			}
		}
		return myLessons;
	}

	public HashMap<Course, Vector<Student>> getStudents(){
		HashMap<Course, Vector<Student>> students = new HashMap<Course, Vector<Student>>();
		for(Course c : getCourses()) {
			students.put(c, c.getStudents());
		}

		return students;
	}

	public boolean applyForRequest(Request r) {
		return UniSystem.getDatabase().getRequests().add(r);
	}

	@Override
	public String viewStudentInfo(Student s) {
		if(UniSystem.getDatabase().getStudents().contains(s)) {
			return s.toString();
		}
		return "No such student";
	}

	@Override
	public String viewTeacherInfo(Teacher t) {
		if(UniSystem.getDatabase().getTeachers().contains(t)) {
			return t.toString();
		}
		return "No such teacher";
	}

	@Override
	public boolean uploadFile(File file, Course c) {
		return c.getFiles().add(file);
	}

	@Override
	public boolean removeFile(File file, Course c) {
		return c.getFiles().remove(file);
	}	

	public void putLateAttendanceMark(Student ss,AttendanceReport ar) {
	    for(Pair2 p : ar.getAttendance()) {
	      if(p.getStudent().equals(ss)) {
	        p.p = Presence.LATE;
	      }
	    }

	  }

	  public void startAttendance(Lesson l, int minute) {
	    l.getAR().setCloseTime(minute);
	    for(Student s : l.getStudents()) {
	      l.getAR().getAttendance().add(new Pair2(s, Presence.ABSENT));
	    }
	  }
}
