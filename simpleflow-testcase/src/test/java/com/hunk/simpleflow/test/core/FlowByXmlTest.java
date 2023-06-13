package com.hunk.simpleflow.test.core;

import cn.hutool.core.io.IoUtil;
import com.hunk.simpleflow.parse.xml.FlowByXml;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>
 */
public class FlowByXmlTest {

    @Test
    public void parseXml(){
        String fileName = "flow/xml/sampleByXml.xml";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        String read = IoUtil.readUtf8(in);
        FlowByXml of = FlowByXml.of(read);
        System.out.println(of);
    }

}
