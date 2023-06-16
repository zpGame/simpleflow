package com.hunk.simpleflow.test.flow.param;
/**
 * Created on 2023/5/18.
 *
 * @author norbit
 *     <p>
 */
public class TestResp {

    private String respOne;

    private String respTwo;

    public String getRespOne() {
        return respOne;
    }

    public void setRespOne(String respOne) {
        this.respOne = respOne;
    }

    public String getRespTwo() {
        return respTwo;
    }

    public void setRespTwo(String respTwo) {
        this.respTwo = respTwo;
    }

    @Override
    public String toString() {
        return "TestResp{" +
                "respOne='" + respOne + '\'' +
                ", respTwo='" + respTwo + '\'' +
                '}';
    }
}
