package comparators;

import java.util.Comparator;

import university.User;

public class SurnameComparator implements Comparator<User> {
	public int compare(User u1, User u2) {
		return u1.getSurname().compareTo(u2.getSurname());
	}	
}
