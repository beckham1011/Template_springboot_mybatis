package cn.bjjoy.bms.exception;

public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerException(String message) {
		super(message);
	}
	
    public ControllerException(Throwable cause) {
        super(cause);
    }

}
