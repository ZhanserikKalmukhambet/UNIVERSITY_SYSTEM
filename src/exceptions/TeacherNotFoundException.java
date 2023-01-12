package exceptions;

public class TeacherNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public TeacherNotFoundException() {
		
	}
	public TeacherNotFoundException(String message) {
		super(message);
	}
}
