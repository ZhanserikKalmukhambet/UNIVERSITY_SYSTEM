package test;

import menu.*;

public class EnterTest {

	public static void main(String[] args) throws Exception {
		
		MainMenu menu = MainMenu.getMainMenu();
		
		menu.displayMenu();
		menu.enterToTheSystem();	
	}

}
