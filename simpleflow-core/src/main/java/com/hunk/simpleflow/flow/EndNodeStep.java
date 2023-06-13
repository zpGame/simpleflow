package com.hunk.simpleflow.flow;

import com.hunk.simpleflow.consts.Constant;
import com.hunk.simpleflow.flow.node.EndNode;

/**
 * Created on 2023/6/12.
 *
 * @author YCKJ4297
 *     <p>
 */
public class EndNodeStep extends BaseNodeStep<EndNode> {

    public static EndNodeStep of(EndNode endNode) {
        return new EndNodeStep(endNode);
    }

    public EndNodeStep(EndNode endNode) {
        super(endNode);
    }

    @Override
    protected String doCondition(IContext ctx) {
        return Constant.EXIT;
    }
}
