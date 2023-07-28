package com.hunk.simpleflow.flow.conditions;

import cn.hutool.core.util.ObjectUtil;

/**
 * Created on 2023/7/28.
 *
 * @author YCKJ4297
 *     <p>判断空条件
 */
public class EmptySpecification extends AbstractSpecification<Object> {
    @Override
    public boolean isSatisfiedBy(Object o) {
        return ObjectUtil.isEmpty(o);
    }
}
