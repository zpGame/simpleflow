package com.hunk.simpleflow.test.flow.param;

/**
 * Created on 2023/5/11.
 *
 * @author norbit
 *     <p>
 */
public class ParamOne {

    private final String one;

    public static ParamOne of(String one) {
        return new ParamOne(one);
    }

    public ParamOne(String one) {
        this.one = one;
    }

    public String getOne() {
        return one;
    }

    @Override
    public String toString() {
        return "Param{" + "one='" + one + '\'' + '}';
    }
}
