package cn.bjjoy.bms.setting.exception;

public class DaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DaoException(String message) {
		super(message);
	}
	
    public DaoException(Throwable cause) {
        super(cause);
    }

}
