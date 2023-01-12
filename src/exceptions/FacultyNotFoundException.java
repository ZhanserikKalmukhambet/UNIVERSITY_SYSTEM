package exceptions;

public class FacultyNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public FacultyNotFoundException() {
		
	}
	public FacultyNotFoundException(String message) {
		super(message);
	}
}
