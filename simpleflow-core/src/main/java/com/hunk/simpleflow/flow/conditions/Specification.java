package com.hunk.simpleflow.flow.conditions;

/**
 * Created on 2023/8/28.
 *
 * @author norbit
 *     <p>条件规则
 */
public interface Specification<T> {

    /**
     * 是否满足
     *
     * @param t 对象
     * @return boolean
     */
    boolean isSatisfiedBy(T t);

    /**
     * and 条件
     *
     * @param specification specification
     * @return Specification
     */
    Specification<T> and(Specification<T> specification);

    /**
     * or 条件
     *
     * @param specification specification
     * @return Specification
     */
    Specification<T> or(Specification<T> specification);

    /**
     * not 条件
     *
     * @param specification specification
     * @return Specification
     */
    Specification<T> not(Specification<T> specification);
}
