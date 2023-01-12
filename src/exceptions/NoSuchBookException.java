package exceptions;

public class NoSuchBookException extends Exception{
	private static final long serialVersionUID = 1L;

	public NoSuchBookException(String errorMessage) {
        super(errorMessage);
    }
}
