package menu;

import java.io.InputStreamReader;
import java.io.BufferedReader;

import library.Librarian;
import university.*;

public final class MainMenu {
	BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
	
	private static final MainMenu MM = new MainMenu();
    
    private MainMenu() {
    }

	public void displayMenu() {
		System.out.println("**************** Welcome to KBTU system ****************");
		System.out.println("========================================================");
	}

	public void enterToTheSystem() throws Exception {
		displayMenu();
		
		Menu m = null;
		User userType = null;
		boolean studentMenu = false;
		boolean teacherMenu = false;
		boolean managerMenu = false;
		boolean librarianMenu = false;

		while (!studentMenu && !teacherMenu && !managerMenu && !librarianMenu) {
			System.out.print("Login: ");
			String login  = inp.readLine();
			System.out.print("Password: ");
			String password = inp.readLine();

			for(User user : UniSystem.getDatabase().getUsers()) {
				if(user.getLogin().equals(login)) {				
					if(user.getPassword().equals(password)) {
						if(user instanceof Student) {
							studentMenu = true;
							Student s = (Student)user;
							userType = s;
						}
						else if(user instanceof Manager) {
							managerMenu = true;
							Manager mn = (Manager)user;
							userType = mn;
						}
						else if(user instanceof Teacher) {
							teacherMenu = true;
							Teacher t = (Teacher)user;
							userType = t;
						}
						else if(user instanceof Librarian){
							librarianMenu = true;
							Librarian l = (Librarian)user;
							userType = l;
						}
						break;
					}
					else {
						break;
					}
				}
			}

			if(!studentMenu && !teacherMenu && !managerMenu && !librarianMenu) {
				System.out.println("Invalid login or password, try again!\n");
			}
		}

		if(studentMenu) {
			m = new StudentMenu((Student) userType);
		}
		else if(teacherMenu) {
			m = new TeacherMenu((Teacher) userType);
		}
		else if(librarianMenu) {
			m = new LibrarianMenu((Librarian) userType);
		}
		else if(managerMenu) {
			m = new ManagerMenu((Manager) userType);
		}
		m.action();
		
	}

	public static MainMenu getMainMenu() {
		return MM;
	}
}