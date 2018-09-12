package cn.bjjoy.bms.exception;

public class ParameterException extends RuntimeException {

	public ParameterException() {
		super();
	}

	public ParameterException(String s) {
		super(s);
	}

	public ParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public ParameterException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -5365630128856068164L;
}
