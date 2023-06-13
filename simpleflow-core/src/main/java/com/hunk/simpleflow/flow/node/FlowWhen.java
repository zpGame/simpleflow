package com.hunk.simpleflow.flow.node;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created on 2023/4/27.
 *
 * @author YCKJ4297
 *     <p>flow 条件对象
 */
@Getter
@ToString
public class FlowWhen {

    private Condition condition;

    @Setter private String from;
    @Setter private String to;
    @Setter private String conditionDesc;

    public void setCondition(String condition) {
        this.condition = Condition.of(condition);
    }
}
