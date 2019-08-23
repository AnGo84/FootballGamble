package ua.com.footballgamble.exception;

public class RestAPIServerException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RestAPIServerException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestAPIServerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RestAPIServerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RestAPIServerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RestAPIServerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
