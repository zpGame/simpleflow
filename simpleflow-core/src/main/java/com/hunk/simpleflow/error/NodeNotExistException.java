package com.hunk.simpleflow.error;
/**
 * Created on 2023/5/5.
 *
 * @author YCKJ4297
 *     <p>
 */
public class NodeNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 异常信息 */
    private String message;

    public NodeNotExistException(String message) {
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
