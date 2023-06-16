package com.hunk.simpleflow.test.flow.param;
/**
 * Created on 2023/5/11.
 *
 * @author norbit
 *     <p>
 */
public class ParamTwo {

    private final String two;

    public static ParamTwo of(String two) {
        return new ParamTwo(two);
    }

    public ParamTwo(String two) {
        this.two = two;
    }

    public String getTwo() {
        return two;
    }

    @Override
    public String toString() {
        return "ParamTwo{" + "two='" + two + '\'' + '}';
    }
}
