package com.hunk.simpleflow.flow.conditions;

import com.hunk.simpleflow.enums.ConditionEnum;

import lombok.Getter;
import lombok.ToString;

import java.util.function.BiFunction;
import java.util.function.Function;

import static com.hunk.simpleflow.consts.Constant.*;

/**
 * Created on 2023/7/28.
 *
 * @author norbit
 *     <p>条件对象
 */
@Getter
@ToString
public class ConditionObject {

    /** 源对象 */
    private final String source;

    /** 运算符 */
    private final ConditionEnum operator;

    /** 目标对象 */
    private final String target;

    static Function<ConditionEnum, String> format = source -> String.format(" %s ", source.name());
    static Function<ConditionEnum, String> format2 = source -> String.format("%s(", source.name());
    static Function<ConditionEnum, String> format3 = source -> String.format("%s (", source.name());

    static BiFunction<String, ConditionEnum, ConditionObject> sourceField =
            (source, enu) -> {
                String[] gtSplit = source.split(format.apply(enu));
                int start = gtSplit[0].indexOf(LEFT_BRACKET) + 2;
                int end = gtSplit[0].indexOf(RIGHT_BRACKET);
                return new ConditionObject(
                        gtSplit[0].substring(start, end).trim(), enu, gtSplit[1].trim());
            };

    static BiFunction<String, ConditionEnum, ConditionObject> emptyField =
            (source, enu) -> {
                int start = source.indexOf(LEFT_BRACKET) + 2;
                int end = source.indexOf(RIGHT_BRACKET);
                return new ConditionObject(source.substring(start, end).trim(), enu, EMPTY);
            };

    public static ConditionObject of(String condition) {

        if (condition.contains(format.apply(ConditionEnum.lt))) {
            return sourceField.apply(condition, ConditionEnum.lt);
        }
        if (condition.contains(format.apply(ConditionEnum.gt))) {
            return sourceField.apply(condition, ConditionEnum.gt);
        }
        if (condition.contains(format.apply(ConditionEnum.le))) {
            return sourceField.apply(condition, ConditionEnum.le);
        }
        if (condition.contains(format.apply(ConditionEnum.ge))) {
            return sourceField.apply(condition, ConditionEnum.ge);
        }
        if (condition.contains(format.apply(ConditionEnum.eq))) {
            return sourceField.apply(condition, ConditionEnum.eq);
        }
        if (condition.contains(format.apply(ConditionEnum.ne))) {
            return sourceField.apply(condition, ConditionEnum.ne);
        }
        if (condition.contains(format2.apply(ConditionEnum.empty))
                || condition.contains(format3.apply(ConditionEnum.empty))) {
            return emptyField.apply(condition, ConditionEnum.empty);
        }
        if (condition.contains(format2.apply(ConditionEnum.NotEmpty))
                || condition.contains(format3.apply(ConditionEnum.NotEmpty))) {
            return emptyField.apply(condition, ConditionEnum.NotEmpty);
        }
        return null;
    }

    public ConditionObject(String source, ConditionEnum operator, String target) {
        this.source = source;
        this.operator = operator;
        this.target = target;
    }
}
