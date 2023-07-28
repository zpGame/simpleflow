package com.hunk.simpleflow.flow.conditions;

/**
 * Created on 2023/8/28.
 *
 * @author norbit
 *     <p>条件规则抽象类
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public Specification<T> and(final Specification<T> specification) {
        return new AndSpecification<T>(this, specification);
    }

    @Override
    public Specification<T> or(final Specification<T> specification) {
        return new OrSpecification<T>(this, specification);
    }

    @Override
    public Specification<T> not(final Specification<T> specification) {
        return new NotSpecification<T>(specification);
    }
}
