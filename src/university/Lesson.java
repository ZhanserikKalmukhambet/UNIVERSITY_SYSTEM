package university;

import java.io.Serializable;

import java.time.LocalTime;
import java.util.*;

import enums.Day;
import enums.FormatLesson;
import enums.TypeLesson;

public class Lesson implements Serializable, Cloneable{
	private static final long serialVersionUID = 1L;

	private Day day;
	private FormatLesson format;
	private TypeLesson type;
	private Teacher teacher;
	private LocalTime time;
	private Room room;
	private AttendanceReport ar = new AttendanceReport();
	private Vector<Student> students = new Vector<Student>();

	public Lesson(){

	}
	public Lesson(Day day, FormatLesson format, TypeLesson type) {
		this.day = day;
		this.format = format;
		this.type = type;
	}
	public Lesson(Day day, FormatLesson format, TypeLesson type, LocalTime time, Room room) {
		this(day, format, type);
		this.time = time;
		this.room = room;
	}
	public Lesson(Day day, FormatLesson format, TypeLesson type, LocalTime time, Room room, Teacher teacher) {
		this(day, format, type, time, room);
		this.teacher = teacher;
	}

	public Day getDay() {
		return day;
	}
	public void setDay(Day day) {
		this.day = day;
	}
	public AttendanceReport getAR() {
		return ar;
	}

	public FormatLesson getFormat() {
		return format;
	}
	public void setFormat(FormatLesson format) {
		this.format = format;
	}
	public TypeLesson getType() {
		return type;
	}
	public void setType(TypeLesson type) {
		this.type = type;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public String getLessonTitle() {
		for(Course c : UniSystem.getDatabase().getCourses()) {
			if(c.getLessons().contains(this)) {
				return c.getTitle();
			}
		}
		return null;
	}

	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Vector<Student> getStudents() {
		return students;
	}
	public void setStudents(Vector<Student> students) {
		this.students = students;
	}
	public AttendanceReport getAttendanceReport() {
		return this.ar;
	}
	@Override
	public String toString() {
		return getLessonTitle().toString();
	}
}
