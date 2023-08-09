package com.hunk.simpleflow.flow.conditions;

import cn.hutool.core.util.StrUtil;

/**
 * Created on 2023/7/31.
 *
 * @author norbit
 *     <p>
 */
public class NotEmptySpecification extends AbstractSpecification<ConditionObject> {

    @Override
    public boolean isSatisfiedBy(ConditionObject condition) {
        return StrUtil.isNotBlank(condition.getSource());
    }

}
