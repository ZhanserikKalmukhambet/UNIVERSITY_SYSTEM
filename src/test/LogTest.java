package test;

import java.time.LocalTime;

import enums.Day;
import enums.FormatLesson;
import enums.TypeLesson;
import menu.AdminMenu;
import university.Course;
import university.Faculty;
import university.Lesson;
import university.Room;
import university.UniSystem;

public class LogTest {

	public static void main(String[] args) throws Exception {
		
		AdminMenu a = AdminMenu.getInstance();
		
		
		UniSystem.getDatabase().getFaculties().add(new Faculty("SITE"));
		UniSystem.getDatabase().getFaculties().add(new Faculty("BS"));
		UniSystem.getDatabase().getFaculties().add(new Faculty("MCM"));
		UniSystem.getDatabase().getFaculties().add(new Faculty("ISE"));

		UniSystem.getDatabase().getFaculties().get(0).getCourses().add(new Course("PP1", "CSCI1103"));
		UniSystem.getDatabase().getFaculties().get(0).getCourses().add(new Course("OOP", "CSCI2106"));
		UniSystem.getDatabase().getFaculties().get(1).getCourses().add(new Course("Audit", "BS21076"));
		UniSystem.getDatabase().getFaculties().get(1).getCourses().add(new Course("Counting", "BS21032"));
		UniSystem.getDatabase().getFaculties().get(2).getCourses().add(new Course("Calculus", "MCM21306"));
		UniSystem.getDatabase().getFaculties().get(2).getCourses().add(new Course("Discrete Structure", "MCM21036"));
		UniSystem.getDatabase().getFaculties().get(3).getCourses().add(new Course("Macroeconomics", "ISE21206"));

		Room r1 = new Room(269, 70);
		Room r2 = new Room(461, 150);
		
		UniSystem.getDatabase().getRooms().add(r1);
		UniSystem.getDatabase().getRooms().add(r2);

		UniSystem.getDatabase().getFaculties().get(0).getCourses().get(0).getLessons().add(new Lesson(Day.FRIDAY, FormatLesson.OFFLINE, TypeLesson.PRACTICE, LocalTime.of(11, 0), r1));
		UniSystem.getDatabase().getFaculties().get(0).getCourses().get(0).getLessons().add(new Lesson(Day.MONDAY, FormatLesson.OFFLINE, TypeLesson.LECTURE, LocalTime.of(13, 0), r2));
		UniSystem.getDatabase().getFaculties().get(0).getCourses().get(0).getLessons().add(new Lesson(Day.TUESDAY, FormatLesson.OFFLINE, TypeLesson.LECTURE, LocalTime.of(9, 0), r2));
		UniSystem.getDatabase().getFaculties().get(0).getCourses().get(0).getLessons().add(new Lesson(Day.FRIDAY, FormatLesson.OFFLINE, TypeLesson.PRACTICE, LocalTime.of(10, 0), r1));

		
		a.command();
		
	}
}