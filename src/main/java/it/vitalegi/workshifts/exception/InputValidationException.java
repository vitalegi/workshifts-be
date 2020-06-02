package it.vitalegi.workshifts.exception;

public class InputValidationException extends Error {

	public InputValidationException() {
		super();
	}

	public InputValidationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public InputValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public InputValidationException(String message) {
		super(message);
	}

	public InputValidationException(Throwable cause) {
		super(cause);
	}

}
