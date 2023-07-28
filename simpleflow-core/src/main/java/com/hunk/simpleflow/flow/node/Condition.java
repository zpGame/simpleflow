package com.hunk.simpleflow.flow.node;

import static com.hunk.simpleflow.consts.Constant.*;

import cn.hutool.core.util.StrUtil;

import com.hunk.simpleflow.flow.IContext;

import lombok.ToString;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.*;

/**
 * Created on 2023/4/26.
 *
 * @author norbit
 *     <p>条件对象
 */
@ToString
public class Condition {

    /** empty (${paramOne}) or ${paramOne} ne 'true' */
    private final String condition;

    private final String expression;

    private final Set<String> strings;

    public static Condition of(String condition) {
        return StrUtil.isBlank(condition)
                ? new Condition(EMPTY, EMPTY, Collections.emptySet())
                : new Condition(condition.trim(), sub(condition), obtainParam(condition));
    }

    public Condition(String condition, String expression, Set<String> strings) {
        this.condition = condition;
        this.expression = expression;
        this.strings = strings;
    }

    private static String sub(String condition) {
        String trim = condition;
        int a = 0;
        while (trim.contains(EMPTY_CHAR)) {
            int i = trim.indexOf(EMPTY_CHAR);
            int beginIndex = i + EMPTY_CHAR_LENGTH + a;
            if (trim.charAt(beginIndex) != '(') {
                a++;
                continue;
            }
            trim = trim.replaceFirst(trim.substring(i, beginIndex), JUDGMENT);
            a = 0;
        }
        return trim;
    }

    private static Set<String> obtainParam(String condition) {
        Set<String> strings = new HashSet<>();
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
        return strings;
    }

    public boolean check(IContext ctx) {
        return StrUtil.isBlank(expression)
                || Boolean.TRUE.equals(
                        new SpelExpressionParser()
                                .parseExpression(obExpression(ctx))
                                .getValue(Boolean.class));
    }

    private String obExpression(IContext ctx) {
        String obExpression = expression;
        for (String string : strings) {
            String key =
                    StrUtil.replace(string, LEFT_BRACKET, EMPTY)
                            .replaceAll(RIGHT_BRACKET, EMPTY)
                            .trim();
            String param = ctx.getParam(key);
            obExpression =
                    StrUtil.replace(
                            obExpression,
                            string,
                            StrUtil.isBlank(param) ? "''" : "'" + param + "'");
        }
        return obExpression;
    }
}
