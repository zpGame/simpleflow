package com.hunk.simpleflow.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created on 2023/6/2.
 *
 * @author norbit
 *     <p> 构建者
 */
public class Builder<T> {

    private final Supplier<T> supplier;

    private final List<Consumer<T>> list = new ArrayList<>();

    public Builder(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public static <T> Builder<T> of(Supplier<T> supplier) {
        return new Builder<T>(supplier);
    }

    public <V> Builder<T> width(BiConsumer<T, V> consumer, V value) {
        list.add(e -> consumer.accept(e, value));
        return this;
    }

    public <V> Builder<T> width(BiConsumer<T, V> consumer, V value, V orElse) {
        if (null == value) {
            list.add(e -> consumer.accept(e, value));
        } else {
            list.add(e -> consumer.accept(e, orElse));
        }
        return this;
    }

    public <V> Builder<T> width(boolean accept, BiConsumer<T, V> consumer, V value) {
        if (accept) {
            list.add(e -> consumer.accept(e, value));
        }
        return this;
    }

    public <V> Builder<T> width(boolean accept, BiConsumer<T, V> consumer, V value, V orElse) {
        if (accept) {
            list.add(e -> consumer.accept(e, value));
        } else {
            list.add(e -> consumer.accept(e, orElse));
        }
        return this;
    }

    public T build() {
        T t = supplier.get();
        Optional.ofNullable(list).orElse(new ArrayList<>()).stream()
                .filter(Objects::nonNull)
                .forEach(e -> e.accept(t));
        return t;
    }
}
