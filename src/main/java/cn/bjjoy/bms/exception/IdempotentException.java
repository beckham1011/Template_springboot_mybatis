package cn.bjjoy.bms.exception;

public class IdempotentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdempotentException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
