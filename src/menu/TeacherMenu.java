package menu;

import java.io.*;
import java.lang.reflect.Array;

import enums.Language;
import enums.MarkType;
import enums.RequestForm;
import enums.TeacherRequestType;
import university.*;

public class TeacherMenu extends Menu{
	private static final long serialVersionUID = 1L;
	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

	public TeacherMenu() {
		super();
	}
	public TeacherMenu(Teacher user) {
		super(user);
	}

	public void displayMenu() {
		System.out.println("***********************Desktop***********************");
		System.out.println("=====================================================");
		System.out.println("           1.Put Mark                                ");
		System.out.println("           2.Personal info                           ");
		System.out.println("           3.Start attendance                        ");
		System.out.println("           4.News                                    ");
		System.out.println("           5.Put later attendance mark to Student    ");
		System.out.println("           6.Apply for request                       ");
		System.out.println("           0.Exit                                    ");
		System.out.println("=====================================================");
	}

	private void viewPersonalInfo() throws Exception {
		System.out.println(((Teacher)user).toString());
	}

	public void putMark() throws NumberFormatException, IOException {
		int ind = 1;
		for(Course c : ((Teacher) user).getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}

		ind = 1;
		System.out.println("Choose the course you want to rate : ");
		int chCourse = Integer.parseInt(inp.readLine());
		Course c = ((Teacher) user).getCourses().get(chCourse-1);

		for(Student s : c.getStudents()) {
			System.out.println(ind + ". " + s.getName() + " " + s.getSurname());
			ind++;
		}
		ind = 1;
		System.out.println("Choose student you want to rate : ");

		int chStudent = Integer.parseInt(inp.readLine());
		Student s = c.getStudents().get(chStudent-1);

		MarkType[] mt = MarkType.values();
		for (MarkType dir : mt) {
			System.out.println(ind + ". " + dir);
			ind++;
		}
		System.out.println("Choose type of mark you want to put : ");

		int chMark = Integer.parseInt(inp.readLine());
		MarkType m = (MarkType)Array.get(mt, chMark-1);

		System.out.println("Write marks value you want put: ");
		Double markVal = Double.parseDouble(inp.readLine());

		((Teacher) user).putMark(c, s, m, markVal);
		System.out.println(markVal + " points is put to " + s.getName() +  " on " + m + "!");
	}

	public void attendance() throws NumberFormatException, IOException {
		System.out.println("Select course in which you provide lessons : ");
		int ind = 1;
		for(Course c : UniSystem.getDatabase().getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}
		int chCourse = Integer.parseInt(inp.readLine());
		Course c = UniSystem.getDatabase().getCourses().get(chCourse-1);
		System.out.println("Select lesson for conduct attendance: ");
		ind = 1;
		for(Lesson l : c.getLessons()) {
			System.out.println(ind + ". " + l.toString());
			ind++;
		}
		int chLesson = Integer.parseInt(inp.readLine());
		Lesson l = c.getLessons().get(chLesson-1);
		System.out.println("Enter minute for what you want open attendance: ");
		int min = Integer.parseInt(inp.readLine());
		((Teacher)user).startAttendance(l, min);
	}

	public void putLaterAttendance() throws NumberFormatException, IOException{
		System.out.println("Select course where your lesson provite: ");
		int ind = 1;
		for(Course c : UniSystem.getDatabase().getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}
		int chCourse = Integer.parseInt(inp.readLine());
		Course c = UniSystem.getDatabase().getCourses().get(chCourse-1);
		System.out.println("Select lesson for view attendance: ");
		ind = 1;
		for(Lesson l : c.getLessons()) {
			System.out.println(ind + ". " + l.toString());
			ind++;
		}
		int chLesson = Integer.parseInt(inp.readLine());
		Lesson l = c.getLessons().get(chLesson-1);
		System.out.println("Select student for what you want put late mark: ");
		ind = 1;
		for(Student s : l.getStudents()) {
			System.out.println(ind + ". " + s.getName() + s.getSurname());
			ind++;
		}
		int chSt = Integer.parseInt(inp.readLine());
		Student s = l.getStudents().get(chSt-1);

		((Teacher)user).putLateAttendanceMark(s, l.getAR());
	}

	public void viewNews() {
		if(UniSystem.getDatabase().getNews().isEmpty()) {
			System.out.println("No NEWS yet !");
		}
		else {
			int ind = 1;
			for(News news : UniSystem.getDatabase().getNews()) {
				System.out.println(ind + ". " + news);
				ind++;
			}
		}
	}

	public void applyForRequest(Teacher t) throws NumberFormatException, IOException {
		System.out.println("Apply for request : ");

		int ind = 1;
		TeacherRequestType[] rr = TeacherRequestType.values();
		System.out.println("Choose request that you want apply: ");
		for(TeacherRequestType r : rr) {
			System.out.println(ind + ". " + r);
			ind++;
		}
		int ch = Integer.parseInt(inp.readLine());
		TeacherRequestType srt = (TeacherRequestType)Array.get(rr, ch-1);

		System.out.println("In which language will request be reported? ");
		Language[] ll = Language.values();
		ind = 1;
		for (Language dir : ll) {
			System.out.println(ind + ". " + dir);
			ind++;
		}
		int chLanguage = Integer.parseInt(inp.readLine());
		Language l = (Language)Array.get(ll, chLanguage-1);
		TeacherRequest sr = new TeacherRequest(l, RequestForm.PDF, srt);
		t.applyForRequest(sr);
		System.out.println(sr.getType() + " is applied successfully!");
	}

	@SuppressWarnings("unused")
	public void action() throws FileNotFoundException, IOException {
		try {
			menu : while(true){
				displayMenu();
				System.out.print("Enter Your Choice ");
				int choice = Integer.parseInt(inp.readLine());

				if(choice == 0) {
					exit();
					break;
				}
				if(choice == 1) {
					putMark : while(true) {
						putMark();

						System.out.println("\n 1. Put mark to other student \n 2. Return back \n 3. Exit");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue putMark;
						if(choice==2) continue menu;
						if(choice==3) { exit(); break menu;}

						break;
					}
				}
				if(choice == 2) {
					viewPersonalInfo : while(true) {
						viewPersonalInfo();

						System.out.println("\n 1. Return back \n 2. Exit");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue menu;
						if(choice==2) { exit(); break menu;}

						break;
					}
				}
				if(choice == 3) {
					attendance : while(true) { 
						attendance();

						System.out.println("\n 1. Return back \n 2. Exit");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue menu;
						if(choice==2) { exit(); break menu;}

						break;
					}
				}
				if(choice == 4) {
					viewNews : while(true) {
						viewNews();

						System.out.println("\n 1. Return back \n 2. Exit");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue menu;
						if(choice==2) { exit(); break menu;}

						break;
					}
				}
				if(choice == 5) {
					putLaterAttendance : while(true) {
						putLaterAttendance();

						System.out.println("\n 1. Return back \n 2. Exit");
						choice = Integer.parseInt(inp.readLine());
						if(choice==1) continue menu;
						if(choice==2) { exit(); break menu;}

						break;
					}
				}
				if(choice == 6) {
					applyForRequest : while(true) {
						applyForRequest((Teacher)user);

						System.out.println("\n 1. Apply for another request \n 2. Return back \n 3. Exit");
						int ch = Integer.parseInt(inp.readLine());
						if(ch==1) continue applyForRequest;
						if(ch==2) continue menu;
						if(ch==3) {exit(); break menu;}

						break;
					}
				}
				if(choice == 0) {
					exit();
				}
			}
		}
		catch (Exception e) {
			System.out.println("Something bad happened... \n Saving resources...");
			e.printStackTrace();
			UniSystem.write();
		}
	}
}