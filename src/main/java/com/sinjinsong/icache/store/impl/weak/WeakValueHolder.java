package com.sinjinsong.icache.store.impl.weak;

import com.sinjinsong.icache.store.ValueHolder;

import java.lang.ref.WeakReference;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public class WeakValueHolder<V> implements ValueHolder<V> {
    private WeakReference<V> reference;

    public WeakValueHolder(V value) {
        this.reference = new WeakReference<>(value);
    }
    
    @Override
    public V value() {
        return reference.get();
    }
}
