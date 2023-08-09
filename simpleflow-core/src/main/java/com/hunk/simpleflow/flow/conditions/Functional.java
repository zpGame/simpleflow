package com.hunk.simpleflow.flow.conditions;

import lombok.Getter;
import lombok.ToString;

/**
 * Created on 2023/8/1.
 *
 * @author norbit
 *     <p>
 */
@Getter
@ToString
public class Functional {

    private final String symbol;

    private final int ops;

    public static Functional of(String symbol, int ops) {
        return new Functional(symbol, ops);
    }

    public Functional(String symbol, int ops) {
        this.symbol = symbol;
        this.ops = ops;
    }

    public String getSymbolFormat() {
        return symbol.trim();
    }
}
