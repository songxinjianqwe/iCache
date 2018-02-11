package com.sinjinsong.icache.store.impl.lru;

import com.sinjinsong.icache.store.DataStore;
import com.sinjinsong.icache.store.StoreAccessException;
import com.sinjinsong.icache.store.ValueHolder;
import com.sinjinsong.icache.store.impl.basic.BasicValueHolder;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public class LRUDataStore<K, V> implements DataStore<K, V> {
    private Map<K, ValueHolder<V>> map;
    
    public LRUDataStore(int maxSize) {
        float loadFactor = 0.75f;
        int capacity = (int) (Math.ceil(maxSize / loadFactor) + 1);
        map = new LinkedHashMap(capacity,loadFactor,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxSize;
            }
        };
    }

    @Override
    public synchronized ValueHolder<V> get(K key) throws StoreAccessException {
        return map.get(key);
    }

    @Override
    public synchronized void put(K key, V value) throws StoreAccessException {
        map.put(key,new BasicValueHolder<>(value));
    }

    @Override
    public synchronized ValueHolder<V> remove(K key) throws StoreAccessException {
        return map.remove(key);
    }

    @Override
    public synchronized void clear() throws StoreAccessException {
        map.clear();
    }
}
