package org.jeecg.modules.healthmanage.measure.utils;

public class YsylException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errorCode;

    public YsylException() {
        super("参数异常");
    }

    public YsylException(String message) {
        super(message);
    }

    public YsylException(String message, Throwable cause) {
        super(message, cause);
    }

    public YsylException(String message, String errorCode) {
        super(message);
        this.setErrorCode(errorCode);
    }

    public YsylException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}