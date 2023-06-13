package com.hunk.simpleflow.enums;

import com.hunk.simpleflow.flow.IFlowDefine;
import com.hunk.simpleflow.parse.json.FlowDefineByJson;
import com.hunk.simpleflow.parse.xml.FlowDefineByXml;
import com.hunk.simpleflow.parse.xml2.FlowDefineByXml2;

import lombok.Getter;

import java.util.function.Supplier;

/**
 * Created on 2023/4/25.
 *
 * @author YCKJ4297
 *     <p>文件类型
 */
public enum FileTypeEnum {

    /** xml解析器 */
    XML("xml", FlowDefineByXml::new),

    /** json解析器 */
    JSON("json", FlowDefineByJson::new),

    /** xml2解析器 */
    XML2("xml", FlowDefineByXml2::new);

    @Getter private final String suffixName;

    @Getter private final Supplier<IFlowDefine> flowDefine;

    FileTypeEnum(String suffixName, Supplier<IFlowDefine> flowDefine) {
        this.suffixName = suffixName;
        this.flowDefine = flowDefine;
    }
}
