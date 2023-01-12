package comparators;

import java.util.Comparator;

import university.User;

public class BirthDateComparator implements Comparator<User>{
	public int compare(User u1, User u2) {
		return u1.getPersonalInfo().getBirthDate().compareTo(u2.getPersonalInfo().getBirthDate());
	}
}
