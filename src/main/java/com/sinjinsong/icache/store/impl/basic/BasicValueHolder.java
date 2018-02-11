package com.sinjinsong.icache.store.impl.basic;

import com.sinjinsong.icache.store.ValueHolder;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public class BasicValueHolder<V> implements ValueHolder<V> {
    private V value;

    public BasicValueHolder(V value) {
        this.value = value;
    }

    @Override
    public V value() {
        return value;
    }
}
