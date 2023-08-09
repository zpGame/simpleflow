package com.hunk.simpleflow.flow.conditions;

import cn.hutool.core.util.StrUtil;

/**
 * Created on 2023/7/28.
 *
 * @author norbit
 *     <p>
 */
public class EmptySpecification extends AbstractSpecification<ConditionObject> {

    @Override
    public boolean isSatisfiedBy(ConditionObject condition) {
        return StrUtil.isBlank(condition.getSource());
    }
}
