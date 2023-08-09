package com.hunk.simpleflow.test.core;

import com.hunk.simpleflow.flow.ConditionContext;
import com.hunk.simpleflow.flow.conditions.ConditionFactory;

import org.junit.Test;

/**
 * Created on 2023/7/31.
 *
 * @author norbit
 *     <p>
 */
public class ConditionFactoryTest {

    static String condition_01 = "empty (${ paramOne}) or ${paramOne} ne 'true'";
    static String condition_02 = "NotEmpty (${ paramOne}) and ${paramOne} ne 'true'";
    static String condition_03 = "NotEmpty (${ paramOne}) and ${paramOne} ne 'true' or ${paramTwo} lt '3'";
    static String condition_04 = "NotEmpty (${ paramOne}) and ${paramOne} ne 'true' or ${paramTwo} lt '3' and ${paramTwo} gt '1'";
    static String condition_05 = "NotEmpty (${ paramOne}) and ${paramOne} ne 'true' and ${paramTwo} lt '3' or ${paramTwo} gt '1' ";
    static String condition_06 = "NotEmpty (${ paramOne}) and ${paramOne} ne 'true' and (${paramTwo} lt '3' or ${paramTwo} gt '1' )";

    @Test
    public void condition() {
        ConditionFactory of = ConditionFactory.of(condition_06);
        System.out.println(of.parse().execute(ConditionContext.of()));
    }

}
