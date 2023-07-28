package com.hunk.simpleflow.test.core;

import cn.hutool.core.util.StrUtil;

import com.hunk.simpleflow.flow.ConditionContext;
import com.hunk.simpleflow.flow.node.Condition;
import org.junit.Test;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import java.util.*;

/**
 * Created on 2023/4/26.
 *
 * @author norbit
 *         <p>
 */
public class ConditionTest {

    @Test
    public void condition() {
        String condition = "empty (${ is_condition}) or ${is_condition} ne 'true'";
        Condition of = Condition.of(condition);
        System.out.println(of);
        System.out.println(of.check(ConditionContext.of()));
        System.out.println(of);
    }

    @Test
    public void bol() {
        String expressionString = "T(cn.hutool.core.util.StrUtil).isBlank('frfr') and '1' eq '1'";
        System.out.println(
                new SpelExpressionParser()
                        .parseExpression(expressionString)
                        .getValue(Boolean.class));
    }

    private static final String EMPTY = "empty";

    private static final String JUDGMENT = "T(cn.hutool.core.util.StrUtil).isBlank";

    Set<String> strings = new HashSet<>();

    @Test
    public void expressionString() {
        String trim = "empty (${ is_condition}) or ${is_condition} ne 'true'";
        trim = StrUtil.replace(trim, "${ is_condition}", "'1'").replaceAll("\\$\\{is_condition}", "'1'");
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
        System.out.println(trim);
    }

    @Test
    public void param() {
        String trim = "empty (${ is_condition}) or ${is_condition} ne 'true'";
        int count = StrUtil.count(trim, "${");
        int l = 0, r = 0;
        int[] left = new int[count], right = new int[count];
        char[] charArray = trim.toCharArray();
        for (int i = 0; i < trim.toCharArray().length; i++) {
            if (charArray[i] == '$') {
                left[l] = i;
                l++;
            } else if (charArray[i] == '}') {
                right[r] = i + 1;
                r++;
            }
        }
        for (int i = 0; i < left.length; i++) {
            strings.add(trim.substring(left[i], right[i]));
        }
    }
}
