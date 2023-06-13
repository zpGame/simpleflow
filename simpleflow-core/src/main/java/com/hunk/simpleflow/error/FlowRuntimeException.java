package com.hunk.simpleflow.error;

import com.hunk.simpleflow.consts.Constant;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>
 */
public class FlowRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /** 异常信息 */
    private String messageKey;

    /** 参数 */ 
    private Object[] args;

    public FlowRuntimeException() {
        super();
    }

    public FlowRuntimeException(String messageKey) {
        super("");
        this.messageKey = messageKey;
        this.args = Constant.EMPTY_PARAMS;
    }

    public FlowRuntimeException(String messageKey, Object... args) {
        super(args.length > 0 ? String.valueOf(args[0]) : "");
        this.messageKey = messageKey;
        this.args = args;
    }

    public FlowRuntimeException(String messageKey, Throwable cause) {
        super(cause);
        this.messageKey = messageKey;
        this.args = Constant.EMPTY_PARAMS;
    }

    public FlowRuntimeException(String messageKey, Throwable cause, Object... args) {
        super(cause);
        this.messageKey = messageKey;
        this.args = args;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Object[] getArgs() {
        return args;
    }
}
