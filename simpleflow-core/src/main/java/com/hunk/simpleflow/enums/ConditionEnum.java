package com.hunk.simpleflow.enums;

import lombok.Getter;

/**
 * Created on 2023/7/28.
 *
 * @author norbit
 *     <p>关系运算符
 */
@Getter
public enum ConditionEnum {
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
}
