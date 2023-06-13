package com.hunk.simpleflow.test.error;
/**
 * Created on 2023/5/11.
 *
 * @author YCKJ4297
 *     <p>
 */
public class TestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 异常信息 */
    private String message;

    public TestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
