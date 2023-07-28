package com.hunk.simpleflow.flow.conditions;

/**
 * Created on 2023/8/28.
 *
 * @author norbit
 *     <p>none 条件
 */
public class NotSpecification<T> extends AbstractSpecification<T> {

    private final Specification<T> spec1;

    public NotSpecification(final Specification<T> spec1) {
        this.spec1 = spec1;
    }

    @Override
    public boolean isSatisfiedBy(final T t) {
        return !spec1.isSatisfiedBy(t);
    }
}
