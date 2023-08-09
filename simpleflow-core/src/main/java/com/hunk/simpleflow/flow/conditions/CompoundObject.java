package com.hunk.simpleflow.flow.conditions;

import com.hunk.simpleflow.enums.ConditionEnum;

import lombok.Getter;
import lombok.ToString;

/**
 * Created on 2023/7/31.
 *
 * @author norbit
 *     <p>复合对象
 */
@Getter
@ToString
public class CompoundObject {

    /** 位置 */
    private final int pos;

    private final int depth;

    private final ConditionEnum operator;

    private final ConditionObject conditionObject;

    public static CompoundObject of(
            int pos, int depth, String operator, ConditionObject conditionObject) {
        return new CompoundObject(pos, depth, operator(operator), conditionObject);
    }

    private static ConditionEnum operator(String operator) {
        return ConditionEnum.getTypeByName(operator);
    }

    public CompoundObject(
            int pos, int depth, ConditionEnum operator, ConditionObject conditionObject) {
        this.pos = pos;
        this.depth = depth;
        this.operator = operator;
        this.conditionObject = conditionObject;
    }
}
