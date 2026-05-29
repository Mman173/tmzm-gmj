package com.hg.tmzm.common.exception;

/**
 * Business service exception
 *
 * @author hg
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** Error code */
    private int code;

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
        this.code = 500;
    }

    public ServiceException(int code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
