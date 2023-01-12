package university;

import java.util.*;

import enums.Day;
import enums.TypeLesson;

public class StudentLessonsSchedule {
	Student s;
	
	public StudentLessonsSchedule(Student s) {
		this.s = s;
	}
	
	List <Vector<Lesson>> vector2D = new ArrayList <Vector<Lesson>>(20);
	{
		for(int i=0;i<=20;i++ ) {
			Vector<Lesson> v = new Vector<Lesson>(7);
			vector2D.add(v);                                                   
		}	
	}

	public boolean fillSchedule() {
		Vector<Lesson> myLessons = s.getLessons();
		
		for(Lesson l : myLessons) {
			int Hour = l.getTime().getHour();
			if(l.getType() == TypeLesson.LECTURE) {
				vector2D.get(Hour+1).add(l);
			}
			vector2D.get(Hour).add(l);
		
		}
		return true;
	}
	
	public void getSchedule() {
		Day[] days= {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};
		
		System.out.print("          ");
		System.out.print("    ");
		for(Day d : days) {
			System.out.print(d + "        ");
			if(d == Day.MONDAY) {
				System.out.print("   ");
			}
			if(d == Day.TUESDAY) {
				System.out.print(" ");
			}
			if(d == Day.THURSDAY) {
				System.out.print("  ");
			}
			if(d == Day.FRIDAY) {
				System.out.print("  ");
			}
			if(d == Day.SATURDAY) {
				System.out.print("  ");
			}
			
		}
		System.out.println();
		
		
		for(int i=8;i<=20;i++ ) {
			System.out.print(i + ":00        ");
			
			if(i<=9) System.out.print(" ");
			for(Day d : days) {
				int flag=1;
				
				for(Lesson l : vector2D.get(i)) {
					if(l.getDay() == d) {
						System.out.print(l);
						flag=0;
						break;
					}
				
					
				}
				if(flag==1) System.out.print("|  ___  |" + "        ");
			}
			System.out.println();
		}	
	}
	

}
