package com.hunk.simpleflow.error;
/**
 * Created on 2023/4/25.
 *
 * @author norbit
 *     <p>文件未找到异常
 */
public class FileNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 异常信息 */
    private String message;

    public FileNotFoundException(String message) {
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
