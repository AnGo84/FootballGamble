package ua.com.footballgamble.exception;

public class NotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NotFoundException(String mes) {
		super(mes);
	}

}
