package com.sinjinsong.icache.store;

import com.sinjinsong.icache.store.impl.basic.BasicDataStore;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public interface DataStore<K, V> {
    ValueHolder<V> get(K key) throws StoreAccessException;

    void put(K key, V value) throws StoreAccessException;

    ValueHolder<V> remove(K key) throws StoreAccessException;

    void clear() throws StoreAccessException;

    static <K, V> DataStore<K, V> getDataStore() {
        return new BasicDataStore<>();
    }
}
