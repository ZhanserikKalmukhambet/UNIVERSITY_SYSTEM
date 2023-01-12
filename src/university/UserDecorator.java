package university;

import java.io.Serializable;

public class UserDecorator implements UserInt, Serializable{
	private static final long serialVersionUID = 1L;
	
	protected final UserInt decoratedUser;
	
	public UserDecorator(UserInt u) {
		this.decoratedUser = u;
	}
}
