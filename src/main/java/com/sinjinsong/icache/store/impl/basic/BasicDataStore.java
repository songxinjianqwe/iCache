package com.sinjinsong.icache.store.impl.basic;

import com.sinjinsong.icache.store.DataStore;
import com.sinjinsong.icache.store.StoreAccessException;
import com.sinjinsong.icache.store.ValueHolder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用普通的ConcurrentHashMap构建的缓存
 * @author sinjinsong
 * @date 2018/2/11
 */
public class BasicDataStore<K, V> implements DataStore<K, V> {
    private Map<K,ValueHolder<V>> map = new ConcurrentHashMap<>();
    @Override
    public ValueHolder<V> get(K key) throws StoreAccessException {
        return map.get(key);
    }

    @Override
    public void put(K key, V value) throws StoreAccessException {
        map.put(key,new BasicValueHolder<>(value));
    }

    @Override
    public ValueHolder<V> remove(K key) throws StoreAccessException {
        return map.remove(key);
    }

    @Override
    public void clear() throws StoreAccessException {
        map.clear();
    }
}
