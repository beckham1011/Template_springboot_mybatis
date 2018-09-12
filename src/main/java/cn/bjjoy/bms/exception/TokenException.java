package cn.bjjoy.bms.exception;

public class TokenException extends RuntimeException {

	public TokenException() {
		super();
	}

	public TokenException(String s) {
		super(s);
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
	}

	public TokenException(Throwable cause) {
		super(cause);
	}

	private static final long serialVersionUID = -5365630128856068164L;
}
