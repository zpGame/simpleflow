package com.hunk.simpleflow.flow.node;

import cn.hutool.core.util.StrUtil;

import com.hunk.simpleflow.flow.IContext;
import com.hunk.simpleflow.consts.Constant;

import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2023/4/26.
 *
 * @author norbit
 *     <p>条件对象
 */
public class Condition {

    /** empty (${paramOne}) or ${paramOne} ne 'true' */
    private final String condition;

    private final String expressionString;

    /** 左括号 */
    private static final String LEFT_BRACKET = "${";

    /** 右括号 */
    private static final String RIGHT_BRACKET = "}";

    private static final String EMPTY = "empty";

    private static final String JUDGMENT = "T(cn.hutool.core.util.StrUtil).isBlank";

    Set<String> strings = new HashSet<>();

    public static Condition of(String condition) {
        return new Condition(condition);
    }

    public Condition(String condition) {
        this.condition = StrUtil.isBlank(condition) ? Constant.EMPTY : condition.trim();
        this.expressionString = StrUtil.isNotBlank(condition) ? sub() : Constant.EMPTY;
    }

    private String sub() {
        String trim = condition;
        int a = 0;
        int length = EMPTY.length();
        while (trim.contains(EMPTY)) {
            int i = trim.indexOf(EMPTY);
            int beginIndex = i + length + a;
            if (trim.charAt(beginIndex) != '(') {
                a++;
                continue;
            }
            trim = trim.replaceFirst(trim.substring(i, beginIndex), JUDGMENT);
            a = 0;
        }
        obtainParam();
        return trim;
    }

    private void obtainParam() {
        int count = StrUtil.count(condition, LEFT_BRACKET);
        int l = 0, r = 0;
        int[] left = new int[count], right = new int[count];
        char[] charArray = condition.toCharArray();
        for (int i = 0; i < condition.toCharArray().length; i++) {
            if (charArray[i] == '$') {
                left[l] = i;
                l++;
            } else if (charArray[i] == '}') {
                right[r] = i + 1;
                r++;
            }
        }
        for (int i = 0; i < left.length; i++) {
            strings.add(condition.substring(left[i], right[i]));
        }
    }

    public boolean check(IContext ctx) {
        return StrUtil.isBlank(expressionString)
                || Boolean.TRUE.equals(
                        new SpelExpressionParser()
                                .parseExpression(obExpression(ctx))
                                .getValue(Boolean.class));
    }

    private String obExpression(IContext ctx) {
        Map<String, String> stringStringMap = new HashMap<>(16);
        for (String string : strings) {
            String key =
                    StrUtil.replace(string, LEFT_BRACKET, Constant.EMPTY)
                            .replaceAll(RIGHT_BRACKET, Constant.EMPTY)
                            .trim();
            String param = ctx.getParam(key);
            stringStringMap.put(string, StrUtil.isBlank(param) ? "''" : "'" + param + "'");
        }
        String obExpression = expressionString;
        for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
            obExpression = StrUtil.replace(obExpression, entry.getKey(), entry.getValue());
        }
        return obExpression;
    }
}
