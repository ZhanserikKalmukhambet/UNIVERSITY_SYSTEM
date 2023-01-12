package menu;

import java.io.Serializable;

import university.UniSystem;
import university.User;

public abstract class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
	public User user;
	
	public Menu() {
		
	}
	public Menu(User u) {
		this.user = u;
	}
	
	public void exit() throws Exception {
		try {
			UniSystem.write();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("\n");
		MainMenu.getMainMenu().enterToTheSystem();

	}
	
	public abstract void displayMenu();

	public abstract void action() throws Exception;
}
