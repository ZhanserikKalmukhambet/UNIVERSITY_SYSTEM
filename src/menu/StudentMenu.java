package menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import enums.Gender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import university.*;

public class StudentMenu extends Menu{
	private static final long serialVersionUID = 1L;
	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

	public StudentMenu() {
		super();
	}
	public StudentMenu(Student user) {
		super(user);
	}

	int chCourse;
	int chReq;
	int ind;

	public void displayMenu() {
		System.out.println();
		System.out.println("***********************Desktop***********************");
		System.out.println("=====================================================");
		System.out.println("           1.Register for discipline                 ");
		System.out.println("           2.Drop discipline                         ");
		System.out.println("           3.View disciplines                        "); 
		System.out.println("           4.Student Request                         ");
		System.out.println("           5.Bypass sheet                            ");
		System.out.println("           6.Register for lesson                     ");
		System.out.println("           7.Personal info                           ");
		System.out.println("           8.Put attendance                          ");
		System.out.println("           9.Schedule                                ");
		System.out.println("           10.Transcript                             ");
		System.out.println("           0. Exit                                   ");
		System.out.println("=====================================================");
	}

	public void registerForCourse(Student s) throws NumberFormatException, IOException {
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Choose course that you want to register : ");
		ind = 1;
		for(Course c : UniSystem.getDatabase().getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}

		chCourse = Integer.parseInt(inp.readLine());
		Course course = UniSystem.getDatabase().getCourses().get(chCourse-1);
		if(s.getCourses().contains(course)) {
			System.out.println(course.getTitle() + " has already been added!");
		}
		else {
			s.registerForCourse(course);
			System.out.println(course.getTitle() + " is added successfully!");
		}
	}

	public void dropCourse(Student s) throws NumberFormatException, IOException {
		if(s.getCourses().isEmpty()) {
			System.out.println("There is no course to drop!");
		}
		else {
			System.out.println("Choose course that you want to drop : ");
			viewMyCourses(s);

			chCourse = Integer.parseInt(inp.readLine());
			System.out.println(s.getCourses().get(chCourse-1).getTitle() + " is dropped successfully!");
			s.dropCourse(s.getCourses().get(chCourse-1));
		}
	}

	public void viewDisciplines(Student s) {
		if(s.getCourses().isEmpty()) {
			System.out.println("You have not added courses yet!");
		}
		else {
			System.out.println("The courses that you are studying currently : ");
			viewMyCourses(s);
		}

	}

	public void applyForRequest(Student s) throws NumberFormatException, IOException {
		System.out.println("Apply for request : ");

		ind = 1;
		for(Request r : UniSystem.getDatabase().getRequests()) {
			if(r instanceof StudentRequest) {
				StudentRequest sr = (StudentRequest)r;
				System.out.println(ind + ". " + sr.getType());
				ind++;
			}
		}

		chCourse = Integer.parseInt(inp.readLine());
		Request req = UniSystem.getDatabase().getRequests().get(chReq-1);
		StudentRequest sr = (StudentRequest)req;

		s.applyForRequest(sr);
		System.out.println(sr.getType() + " is applied successfully!");
	}

	public void viewRequest(Student s) {
		if(s.getRequests().isEmpty()) {
			System.out.println("You have not applied for request yet!");
		}
		else {
			System.out.println("Your requests : \n");
			for(Request r : s.getRequests()) {
				System.out.println(r.toString());
			}
		}
	}

	public void fillPersonalInfo(Student s) throws IOException {
		System.out.println("Fill these fields : ");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		System.out.println("1) Birth date(d/MM/YYYY) : ");
		String date = inp.readLine();

		System.out.println("2) Gender : ");
		String gen = inp.readLine();
		Gender gender = null;
		if(gen.equals("MALE"))
			gender = Gender.MALE;
		if(gen.equals("FEMALE"))
			gender = Gender.FEMALE;

		System.out.println("3) Nation : ");
		String nat = inp.readLine();

		System.out.println("4) Mobile phone : ");
		String mp = inp.readLine();

		System.out.println("5) Address : ");
		String add = inp.readLine();

		s.setPersonalInfo(new UserPersonalInfo(LocalDate.parse(date, formatter), gender, nat, mp, add));

		System.out.println("\n");
		System.out.println(s.getPersonalInfo().viewPersonalInfo());
	}

	public void registerForLesson(Student s) throws NumberFormatException, IOException {
		System.out.println("Choose course that in which you will select lesson : ");

		viewMyCourses(s);

		int ch = Integer.parseInt(inp.readLine());
		Course course = s.getCourses().get(ch-1);
		System.out.println("Choose the lesson period : ");

		int ind = 1;
		for(Lesson l : course.getLessons()) {
			System.out.println(ind + ". " + l);
			ind++;
		}

		ch = Integer.parseInt(inp.readLine());
		Lesson les = course.getLessons().get(ch-1);
		les.getStudents().add(s);
		System.out.println("Lesson is added successfully!");
	}

	public void viewTranscript(Student s) {
		s.viewTranscript();
	}

	public void viewMyCourses(Student s) {
		int ind = 1;
		for(Course c : s.getCourses()) {
			System.out.println(ind + ". " + c.getTitle());
			ind++;
		}
	}

	public void putAttendance(Student s) {
		System.out.println("This is active attendance window for you : ");

		for(Lesson l : s.getLessons()) {
			if(l.getAR()!=null) {
				System.out.println(l.getLessonTitle() + " lesson " + l.getDay() + " at " + l.getTime().getHour()+":00" +  " attendance close at " + l.getAR().getCloseTime().getHour() + ":" + l.getAR().getCloseTime().getMinute());
				s.putAttendanceMark(l.getAR());
				System.out.println("you successfully attend");
			}
		}
	}
	
	public void viewSchedule(Student s) {
		StudentLessonsSchedule sls = new StudentLessonsSchedule(s);
		sls.fillSchedule();
		sls.getSchedule();
	}

	@SuppressWarnings("unused")
	public void action() throws Exception {
		Student s = (Student)user;

		menu : while (true) {
			displayMenu();
			System.out.print("\nEnter Your Choice: ");
			int ch = Integer.parseInt(inp.readLine());

			if(ch == 1) {
				registerForCourse : while(true) {
					registerForCourse(s);

					System.out.println("\n 1. Register for another course \n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue registerForCourse;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}

					break;
				}
			}
			if(ch == 2) {
				dropCourse: while(true) {
					dropCourse(s);

					System.out.println("\n 1. Drop another course \n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue dropCourse;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}

					break;
				}
			}

			if(ch == 3) {
				viewDisciplines: while(true) {
					viewDisciplines(s);

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}

					break;
				}
			}
			if(ch == 4) {
				applyForRequest : while(true) {
					applyForRequest(s);

					System.out.println("\n 1. Apply for another request \n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue applyForRequest;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}

					break;
				}
			}
			if(ch == 5) {
				viewRequests : while(true) {
					viewRequest(s);

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}

					break;
				}
			}
			if(ch == 6) {
				registerForLesson : while(true) {
					registerForLesson(s);

					System.out.println("\n 1. Register for another lesson \n 2. Return back \n 3. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue registerForLesson;
					if(ch==2) continue menu;
					if(ch==3) {exit(); break menu;}

					break;
				}
			}
			if(ch == 7) {
				fillPersonalInfo : while(true) {
					fillPersonalInfo(s);

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}

					break;
				}
			}
			if(ch == 8) {
				putAttendance : while(true) {
					putAttendance(s);

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}

					break;
				}
			}
			if(ch == 9) {
				viewSchedule : while(true) {
					viewSchedule(s);
					
					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}

					break;
				}
			}
			if(ch == 10) {
				viewTranscript : while(true) {
					viewTranscript(s);

					System.out.println("\n 1. Return back \n 2. Exit");
					ch = Integer.parseInt(inp.readLine());
					if(ch==1) continue menu;
					if(ch==2) {exit(); break menu;}

					break;
				}
			}
			if(ch == 0) {
				exit();
			}
		}
	}
}
