package com.hunk.simpleflow.consts;

/**
 * Created on 2023/4/25.
 *
 * @author norbit
 *     <p>静态变量
 */
public class Constant {

    public Constant() {}

    public static final String EMPTY = "";

    public static final Object[] EMPTY_PARAMS = new Object[] {};

    public static final String EXIT = "exit";

    public static final String NULL = "null";

    /** 左括号 */
    public static final String LEFT_BRACKET = "${";

    /** 右括号 */
    public static final String RIGHT_BRACKET = "}";

    public static final String EMPTY_CHAR = "empty";

    public static final String NOT_EMPTY_CHAR = "NotEmpty";

    public static final int EMPTY_CHAR_LENGTH = EMPTY_CHAR.length();

    public static final int NOT_EMPTY_CHAR_LENGTH = NOT_EMPTY_CHAR.length();

    public static final String JUDGMENT = "T(cn.hutool.core.util.StrUtil).isBlank";

}
