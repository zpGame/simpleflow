package com.hunk.simpleflow.utils;

import cn.hutool.core.lang.Tuple;

import java.util.Objects;

/**
 * Created on 2023/8/2.
 *
 * @author norbit
 *     <p>
 */
public class Tuple2<T1, T2> extends Tuple {

    private T1 t1;
    private T2 t2;

    public static <T1, T2> Tuple2<T1, T2> of(T1 t1, T2 t2) {
        return new Tuple2<>(t1, t2);
    }

    public Tuple2(T1 t1, T2 t2) {
        super(t1, t2);
    }

    public T1 getFirst() {
        return super.get(0);
    }

    public T2 getSec() {
        return super.get(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(t1, tuple2.t1) && Objects.equals(t2, tuple2.t2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), t1, t2);
    }
}
