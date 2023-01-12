package menu;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.time.LocalTime;

import enums.Day;
import enums.FormatLesson;
import enums.Language;
import enums.RequestStatus;
import enums.TypeLesson;
import university.*;

public class ManagerMenu extends Menu{
	private static final long serialVersionUID = 1L;

	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

	boolean reportingNews = false;
	boolean addingCourse = false;
	boolean droppingCourse = false;
	public ManagerMenu() {
		super();
	}
	public ManagerMenu(Manager user) {
		super(user);
	}

	public void displayMenu() {
		System.out.println("***********************Desktop***********************");
		System.out.println("=====================================================");
		System.out.println("           1.Add course to student                   ");
		System.out.println("           2.Drop course from student                ");
		System.out.println("           3.Report news                             ");
		System.out.println("           4.Remove news                             ");
		System.out.println("           5.View student info                       ");
		System.out.println("           6.View teacher info                       ");
		System.out.println("           7.Manage Request                          ");
		System.out.println("           8.Create new course                       ");
		System.out.println("           9.Add Lesson to course                    ");
		System.out.println("           10.Assign course for teacher               ");
		System.out.println("           0.Exit from account                       ");
		System.out.println("=====================================================");
		System.out.println("\nEnter your choice: ");
	}

	private void addCourseToStudent() throws Exception {
		System.out.println("Choose course that you want add to student : ");
		int ind = 1;
		for(Course c : UniSystem.getDatabase().getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}

		int chCourse = Integer.parseInt(inp.readLine());
		Course course = UniSystem.getDatabase().getCourses().get(chCourse-1);
		System.out.println("Choose student whom want add to course : ");
		ind = 1;
		for(Student s : UniSystem.getDatabase().getStudents()) {
			System.out.println(ind + ". " + s.getName() + " " + s.getSurname());
			ind++;
		}
		int chStudent = Integer.parseInt(inp.readLine());
		Student student = UniSystem.getDatabase().getStudents().get(chStudent-1);
		if(student.getCourses().contains(course)) {
			System.out.println(student.getName() + " has already registred for " + course.getTitle() + "\n\n");
			addingCourse = false;
		}else {
			((Manager)user).addCoursetoStudent(course, student);
			System.out.println(course.getTitle()  + " was saccessfully added to " + student.getName() + "\n\n");
			addingCourse = true;
		}
	}

	private void DropCourseFromStudent() throws Exception {
		System.out.println("Choose student to drop from the course : ");
		int ind = 1;
		for(Student s : UniSystem.getDatabase().getStudents()) {
			System.out.println(ind + ". " + s.getName() + " " + s.getSurname());
			ind++;
		}
		int chStudent = Integer.parseInt(inp.readLine());
		Student student = UniSystem.getDatabase().getStudents().get(chStudent-1);
		if(student.getCourses().size()==0) {
			System.out.println(student.getName() + " has no courses!");
			droppingCourse = false;
		}
		else {
			System.out.println("Choose students's course that you want to drop : ");
			ind = 1;
			for(Course c : student.getCourses()) {
				System.out.println(ind + ". " + c.getTitle());
				ind++;
			}
			int chCourse = Integer.parseInt(inp.readLine());;
			Course course = student.getCourses().get(chCourse-1);
			((Manager)user).dropCoursefromStudent(course, student);
			System.out.println(course.getTitle()  + " was saccessfully droped for + " + student.getName() + "\n\n");
			droppingCourse = true;
		}
	}

	private void reportNews() throws Exception {
		News n = new News();
		System.out.println("In which language will news be reported? ");
		Language[] ll = Language.values();
		int ind = 1;
		for (Language dir : ll) {
			System.out.println(ind + ". " + dir);
			ind++;
		}
		int chLanguage = Integer.parseInt(inp.readLine());
		Language l = (Language)Array.get(ll, chLanguage-1);
		n.setLanguage(l);
		System.out.print("Enter news title: ");
		String tit = inp.readLine();
		n.setTitle(tit);
		if(n.getTitle().length()!=0) {
			System.out.print("Enter information about news: ");
			String inf = inp.readLine();	
			n.setInfo(inf);
			if(n.getInfo().length()>0) {
				((Manager)user).reportNews(n);
				System.out.println("News was successfully reported!");	
				reportingNews = true;
			}
		}
	}

	private void removeNews() throws Exception {
		System.out.println("Choose news that you want remove: ");
		if(UniSystem.getDatabase().getNews().size() == 0) {
			System.out.println("No news in system");
		}else {
			int ind = 1;
			for(News n: UniSystem.getDatabase().getNews()) {
				System.out.println(ind + ". " + n.getTitle());
				ind++;
			}
			int chNews = Integer.parseInt(inp.readLine());
			News news = UniSystem.getDatabase().getNews().get(chNews-1);
			((Manager)user).removeNews(news);
			if(chNews > 0) {
				System.out.println("News successfully deleted!");
			}
		}
	}

	private void viewStudentInfo() throws Exception {
		System.out.println("select the student from whom you want to view the full information : ");
		int ind = 1;
		for(Student s : UniSystem.getDatabase().getStudents()) {
			System.out.println(ind + ". " + s.getName() +  " "  + s.getSurname());
			ind++;
		}
		int chStudent = Integer.parseInt(inp.readLine());
		Student s = UniSystem.getDatabase().getStudents().get(chStudent-1);
		System.out.println(s.toString());
	}

	private void viewTeacherInfo() throws Exception {
		System.out.println("select the teacher from whom you want to view the full information : ");
		int ind = 1;
		for(Teacher s : UniSystem.getDatabase().getTeachers()) {
			System.out.println(ind + ". " + s.getName() +  " "  + s.getSurname());
			ind++;
		}
		int chTeacher = Integer.parseInt(inp.readLine());
		Teacher t = UniSystem.getDatabase().getTeachers().get(chTeacher-1);
		System.out.println(t.toString());
	}

	private void manageRequest() throws Exception{
		System.out.println("Choose request that you want execute: ");
		int ind = 1;
		for(Request r : UniSystem.getDatabase().getRequests()) {
			System.out.println(ind + ". " + r.toString());
			ind++;
		}
		int chReq = Integer.parseInt(inp.readLine());
		Request r = UniSystem.getDatabase().getRequests().get(chReq-1);
		r.setStatus(RequestStatus.COMPLETED);
		System.out.println(r.toString()  + "request successfully accepted");
	}

	private void createCourse() throws Exception {
		Course c = new Course();
		System.out.println("Write course title which you want to create: ");
		
		String ct = inp.readLine();
		c.setTitle(ct);
		
		System.out.println("How many credits in this course: ");
		int credit = Integer.parseInt(inp.readLine());
		c.setCredit(credit);
		System.out.println("How many student may study this course: ");
		int capacity = Integer.parseInt(inp.readLine());
		c.setCapacity(capacity);
		System.out.println("Choose Faculty of this course: ");
		int ind = 1;
		for(Faculty co : UniSystem.getDatabase().getFaculties()) {
			System.out.println(ind + ". " + co.getTitle());
			ind++;
		}
		int fac = Integer.parseInt(inp.readLine());
		Faculty f = UniSystem.getDatabase().getFaculties().get(fac-1);
		System.out.println("Have this course any prerequesite? [Y/N] ");
		String st = inp.readLine();
		if(st.equals("Y")) {
			System.out.println("Choose course that prerequesite to this your course : ");
			ind = 1;
			for(Course co : UniSystem.getDatabase().getCourses()) {
				System.out.println(ind + ". " + co.getTitle());
				ind++;
			}

			int chCourse = Integer.parseInt(inp.readLine());
			Course course = UniSystem.getDatabase().getCourses().get(chCourse-1);
			c.setPrerequisite(course);
			f.getCourses().add(c);
			System.out.println(c.getTitle() +  "course successfully created!");
		}else {
			f.getCourses().add(c);
			System.out.println(c.getTitle() +  " course successfully created!");

		}
	}

	private void addLessonToCourse() throws Exception {
		Lesson l = new Lesson();
		System.out.println("Choose course to which add new Lesson : ");
		int ind = 1;
		for(Course co : UniSystem.getDatabase().getCourses()) {
			System.out.println(ind + ". " + co.getTitle());
			ind++;
		}
		int chCourse = Integer.parseInt(inp.readLine());
		Course course = UniSystem.getDatabase().getCourses().get(chCourse-1);

		System.out.println("Choose day when you lesson will provided ");
		Day[] dd = Day.values();
		ind = 1;
		for (Day dir : dd) {
			System.out.println(ind + ". " + dir);
			ind++;
		}
		int chDay = Integer.parseInt(inp.readLine());
		Day d = (Day)Array.get(dd, chDay-1);
		l.setDay(d);

		System.out.println("Choose format of lesson: ");
		FormatLesson[] fl = FormatLesson.values();
		ind = 1;
		for (FormatLesson dir : fl) {
			System.out.println(ind + ". " + dir);
			ind++;
		}
		int chfl = Integer.parseInt(inp.readLine());
		FormatLesson f = (FormatLesson)Array.get(fl, chfl-1);
		l.setFormat(f);

		System.out.println("Choose type of lesson: ");
		TypeLesson[] tl = TypeLesson.values();
		ind = 1;
		for (TypeLesson dir : tl) {
			System.out.println(ind + ". " + dir);
			ind++;
		}
		int chtl = Integer.parseInt(inp.readLine());
		TypeLesson t = (TypeLesson)Array.get(tl, chtl-1);
		l.setType(t);

		System.out.println("Which time lesson will be [INT]: ");
		int hour = Integer.parseInt(inp.readLine());
		l.setTime(LocalTime.of(hour, 0));

		System.out.println("Choose room where lesson provide: ");
		ind = 1;
		for(Room r : UniSystem.getDatabase().getRooms()) {
			System.out.println(ind + ". " + r.number);
			ind++;
		}
		int chRoom = Integer.parseInt(inp.readLine());
		Room rum = UniSystem.getDatabase().getRooms().get(chRoom-1);
		l.setRoom(rum);

		System.out.println("Choose theacher who provide this lesson: ");
		ind = 1;
		for(Teacher tt : UniSystem.getDatabase().getTeachers()) {
			System.out.println(ind + ". " + tt.getName() + " " + tt.getSurname());
			ind++;
		}
		int chT = Integer.parseInt(inp.readLine());
		Teacher teacher = UniSystem.getDatabase().getTeachers().get(chT-1);
		l.setTeacher(teacher);
		course.addLesson(l);
		System.out.println("Lesson successfully added to " + course.getTitle() + "!");
	}
	
	private void teacherToCourse() throws NumberFormatException, IOException {
		System.out.println("Choose teacher whom you want to add course: ");
		int ind = 1;
		for(Teacher s : UniSystem.getDatabase().getTeachers()) {
			System.out.println(ind + ". " + s.getName() + " " + s.getSurname());
			ind++;
		}
		int chTeacher = Integer.parseInt(inp.readLine());
		Teacher teacher = UniSystem.getDatabase().getTeachers().get(chTeacher-1);
		System.out.println("Choose course that you want to add this course: ");
		ind = 1;
		for(Course c : UniSystem.getDatabase().getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}
		int chCourse = Integer.parseInt(inp.readLine());
		Course course = UniSystem.getDatabase().getCourses().get(chCourse-1);
		course.addInstructor(teacher);
		System.out.println(teacher.getName() + "successfully added to " + course.getTitle());
	}

	public void action() throws Exception {
		try {
			menu : while(true){
				displayMenu();
				int choice = Integer.parseInt(inp.readLine());
				if(choice==1){
					addCourseToStudent: while(true){
						addCourseToStudent();
						if(addingCourse) {
							System.out.println("Whould you like to add another course to this student? \n 1.Yes \n 2.Return back \n");
							choice = Integer.parseInt(inp.readLine());
							if(choice==1) continue addCourseToStudent;
							if(choice==2) continue menu;
							break;
						}else {
							System.out.println("Whould you like to try again? \n 1.Yes \n 2.Return back \n");
							choice = Integer.parseInt(inp.readLine());
							if(choice==1) continue addCourseToStudent;
							if(choice==2) continue menu;
							break;
						}
					}
				}else if(choice==2) {
					dropCourseFromStudent: while(true) {
						DropCourseFromStudent();
						if(droppingCourse) {
							System.out.println(" Whould you like to drop another course for this student? \n 1.Yes \n 2.Return back \n");
							choice = Integer.parseInt(inp.readLine());
							if(choice==1) continue dropCourseFromStudent;
							if(choice==2) continue menu;
							break;
						}
						else {
							continue menu;
						}

					}
				}else if(choice==3) {
					reportNews : while(true) {
						reportNews();
						if(reportingNews) {
							reportingNews = false;
							System.out.println(" Whould you like to report another news? \n 1.Yes \n 2.Return back \n");
							choice = Integer.parseInt(inp.readLine());
							if(choice==1) continue reportNews;
							if(choice==2) continue menu;
							break;
						}
						break;
					}
				}else if(choice==4) {
					removeNews : while(true) {
						removeNews();

						System.out.println("Whould you like to remove another news? \n 1.Yes \n 2.Return back \n ");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue removeNews;
						if(choice==2) continue menu;
						break;

					}
				}else if(choice==5) {
					viewStudentInfo : while(true) {
						viewStudentInfo();

						System.out.println("Whould you like to view info another student? \n 1.Yes \n 2.Return back \n");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue viewStudentInfo;
						if(choice==2) continue menu;
						break;
					}
				}else if(choice==6) {
					viewTeacherInfo : while(true) {
						viewTeacherInfo();
						System.out.println("Whould you like to view info another teacher? \n 1.Yes \n 2.Return back \n");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue viewTeacherInfo;
						if(choice==2) continue menu;
						break;
					}
				}else if(choice==7) {
					manageRequest : while(true) {
						manageRequest();
						System.out.println("Whould you like to accept another request? \n 1.Yes \n 2.Return back \n");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue manageRequest;
						if(choice==2) continue menu;
						break;
					}
				}else if(choice==8) {
					createCourse : while(true) {
						createCourse();
						System.out.println("Whould you like to create another course? \n 1.Yes \n 2.Return back \n");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue createCourse;
						if(choice==2) continue menu;
						break;
					}
				}else if(choice==9) {
					addLessonToCourse : while(true) {
						addLessonToCourse();
						System.out.println("Whould you like add another lesson? \n 1.Yes \n 2.Return back \n");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue addLessonToCourse;
						if(choice==2) continue menu;
						break;
					}
				}
				else if(choice==10) {
					teacherToCourse : while(true) {
						teacherToCourse();
						System.out.println("Whould you like assign another course to teacher? \n 1.Yes \n 2.Return back \n");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue teacherToCourse;
						if(choice==2) continue menu;
						break;
					}
				}else if(choice==0) {
					exit();
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
			UniSystem.write();
		}
	}
}