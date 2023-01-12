package menu;

import library.Librarian;
import university.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

public class AdminMenu implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final AdminMenu INSTANCE = new AdminMenu();

	private AdminMenu() {

	}

	public static AdminMenu getInstance() {
		return INSTANCE;
	}

	int ch;

	public void exit() throws Exception {
		UniSystem.write();

		System.out.println("\nThanks for using system!");
		System.exit(0);
	}

	public void displayMenu() {
		System.out.println("\n******************* Admin Commands ******************");
		System.out.println("=====================================================");
		System.out.println("           1.Add User           				     ");
		System.out.println("           2.Remove User                             ");
		System.out.println("           3.View Users                              ");
		System.out.println("           0.Exit                                    ");
		System.out.println("=====================================================");
		System.out.println("*****************************************************");
	}

	public void displayUserTypes() {
		System.out.println("******************* User types **********************");
		System.out.println("=====================================================");
		System.out.println("           1.Student           						 ");
		System.out.println("           2.Teacher        						 ");
		System.out.println("           3.Librarian        						 ");
		System.out.println("           4.Manager 								 ");
		System.out.println("=====================================================");					
		System.out.println("*****************************************************");
	}

	public void addUser() throws IOException {
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("You are adding user to University system ...\n");

		displayUserTypes();
		System.out.println("Enter the User type : ");

		ch = Integer.parseInt(inp.readLine());

		System.out.print("Enter the Name : ");                    
		String curname = inp.readLine();

		System.out.print("Enter the Surname : ");                    
		String cursurname = inp.readLine();

		if(ch==1) {
			Student u = new Student(curname, cursurname);
			System.out.print("Enter faculty:\n");  
			int ind = 1;
			for(Faculty f : UniSystem.getDatabase().getFaculties()) {
				System.out.println(ind + ". " + f.getTitle());
				ind++;
			}
			int chFaculty = Integer.parseInt(inp.readLine());
			Faculty f = UniSystem.getDatabase().getFaculties().get(chFaculty);

			u.setFaculty(f);
			Admin.getInstance().addUser(u);
		}
		else if(ch==2) {
			Teacher u = new Teacher(curname, cursurname);
			Admin.getInstance().addUser(u);
		}
		else if(ch==3) {
			Librarian u = new Librarian(curname, cursurname);
			Admin.getInstance().addUser(u);
		}
		else if(ch==4) {
			Manager u = new Manager(curname, cursurname);

			System.out.print("Enter faculty:\n");  
			int ind = 1;
			for(Faculty f : UniSystem.getDatabase().getFaculties()) {
				System.out.println(ind + ". " + f.getTitle());
				ind++;
			}
			int chFaculty = Integer.parseInt(inp.readLine());
			Faculty f = UniSystem.getDatabase().getFaculties().get(chFaculty);

			f.setManager(u);

			Admin.getInstance().addUser(u);
		}

		System.out.println("User is successfully added! ");

	}

	public void removeUser() throws NumberFormatException, IOException {
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("You are removing user in University system ...\n");

		displayUserTypes();
		System.out.print("Enter the User type : ");
		ch = Integer.parseInt(inp.readLine());

		if(ch==1) {
			System.out.println("Choose student that you want to remove : ");
			int ind = 1;
			for(Student s : UniSystem.getDatabase().getStudents()) {
				System.out.println(ind + ". " + s.getName() + " " + s.getSurname() + " Course: " + s.getCourse() + " Faculty: " + s.getFaculty());
				ind++;
			}

			int chSt = Integer.parseInt(inp.readLine());
			Admin.getInstance().removeUser(UniSystem.getDatabase().getStudents().get(chSt-1));
		}
		else if(ch==2) {
			System.out.println("Choose Teacher that you want to remove: ");
			int ind = 1;
			for(Teacher t : UniSystem.getDatabase().getTeachers()) {
				System.out.println(ind + ". " + t.getName() + " " + t.getSurname());
				ind++;
			}

			int chTch = Integer.parseInt(inp.readLine());
			Admin.getInstance().removeUser(UniSystem.getDatabase().getTeachers().get(chTch-1));
		}
		else if(ch==3) {
			System.out.println("Choose librarian that you want to remove : ");
			int ind = 1;
			for(Librarian s : UniSystem.getDatabase().getLibrarian()) {
				System.out.println(ind + ". " + s.getName() + " " + s.getSurname());
				ind++;
			}
			int chSt = Integer.parseInt(inp.readLine());
			chSt--;
			Admin.getInstance().removeUser(UniSystem.getDatabase().getLibrarian().get(chSt));
		}
		else if(ch==4) {
			System.out.println("Choose manager that you want to remove: ");
			int ind = 1;
			for(Manager m : UniSystem.getDatabase().getManagers()) {
				System.out.println(ind + ". " + m.getName() + " " + m.getSurname());
				ind++;
			}

			int chMn = Integer.parseInt(inp.readLine());
			chMn--;
			Admin.getInstance().removeUser(UniSystem.getDatabase().getStudents().get(chMn));
		}
		System.out.println("User was removed!");
	}

	@SuppressWarnings("unused")
	public void command() throws Exception{
		BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));

		try {
			menu : while(true){
				displayMenu();
				int choice = Integer.parseInt(inp.readLine());

				if(choice==1){
					AddUser: while(true){
						addUser();

						System.out.println("Whould you like to add another user to UniSystem? \n 1.Yes \n 2.No");
						choice = Integer.parseInt(inp.readLine());

						if(choice==1) continue AddUser;
						if(choice==2) continue menu;
						break;
					}
				}
				else if(choice==2) {
					RemoveUser: while(true) {
						if(UniSystem.getDatabase().getUsers().isEmpty()) {
							System.out.println("You have not added any user to UniSystem yet! Try next time!");

							continue menu;
						}
						else {
							removeUser();

							System.out.println("Whould you like to remove another user from UniSystem? \n 1.Yes \n 2.No");
							choice = Integer.parseInt(inp.readLine());

							if(choice==1) continue RemoveUser;
							if(choice==2) continue menu;
							break;
						}
					}
				}
				else if(choice == 3){
					if(UniSystem.getDatabase().getUsers().isEmpty()) {
						System.out.println("You have not added any user to UniSystem yet! Try next time!");
					}
					else {
						int ind = 1;
						for(User u : UniSystem.getDatabase().getUsers()) {
							System.out.println(ind + ". " + u.getLogin());
							ind++;
						}
					}
				}
				else if(choice == 0) {
					exit();
				}
			}
		} 
		catch (Exception e) {
			System.out.println("Something bad happened... \nSaving resources...");
			e.printStackTrace();
			UniSystem.write();
		}
	}

}

