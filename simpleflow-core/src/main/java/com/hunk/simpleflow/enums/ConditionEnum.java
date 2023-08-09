package com.hunk.simpleflow.enums;

import lombok.Getter;

import java.util.Arrays;

/**
 * Created on 2023/7/28.
 *
 * @author norbit
 *     <p>关系运算符
 */
@Getter
public enum ConditionEnum {

    empty("empty"),
    NotEmpty("notEmpty"),
    lt("<"),
    gt(">"),
    le("<="),
    ge(">="),
    eq("=="),
    ne("!="),
    div("/"),
    mod("%"),
    not("!"),
    and("&&"),
    or("||");

    private final String symbol;

    ConditionEnum(String symbol) {
        this.symbol = symbol;
    }

    public static ConditionEnum getTypeByName(String symbol) {
        return Arrays.stream(ConditionEnum.values())
                .filter(o -> o.name().equals(symbol))
                .findFirst()
                .orElse(null);
    }
}
