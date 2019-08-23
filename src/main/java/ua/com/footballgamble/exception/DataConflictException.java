package ua.com.footballgamble.exception;

public class DataConflictException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataConflictException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DataConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public DataConflictException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DataConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DataConflictException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
