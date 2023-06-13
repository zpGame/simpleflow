package com.hunk.simpleflow.test.flow.param;
/**
 * Created on 2023/5/18.
 *
 * @author YCKJ4297
 *     <p>
 */
public class TestTwoResp {

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
        return "TestTwoResp{" +
                "respOne='" + respOne + '\'' +
                ", respTwo='" + respTwo + '\'' +
                '}';
    }
}
