package com.hunk.simpleflow.error;
/**
 * Created on 2023/4/27.
 *
 * @author norbit
 *     <p>文件解析异常
 */
public class FileParseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 异常信息 */
    private String message;

    public FileParseException(String message) {
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
