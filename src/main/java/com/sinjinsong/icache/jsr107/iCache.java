package com.sinjinsong.icache.jsr107;

import com.sinjinsong.icache.store.DataStore;
import com.sinjinsong.icache.store.StoreAccessException;
import com.sinjinsong.icache.store.ValueHolder;
import lombok.extern.slf4j.Slf4j;

import javax.cache.CacheManager;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
@Slf4j
public class iCache<K, V> implements javax.cache.Cache<K, V> {
    private CacheManager cacheManager;
    private String cacheName;
    private Configuration<K, V> configuration;
    private DataStore<K, V> dataStore;

    public iCache(DataStore<K, V> dataStore) {
        this.dataStore = dataStore;
    }

    public <C extends Configuration<K, V>> iCache(CacheManager cacheManager, String cacheName, C configuration) {
        this.cacheManager = cacheManager;
        this.cacheName = cacheName;
        this.configuration = configuration;
        //创建dataStore
        this.dataStore = DataStore.getDataStore();
    }

    public V get(K k) {
        try {
            ValueHolder<V> holder = dataStore.get(k);
            if (holder != null) {
                return holder.value();
            } else {
                return null;
            }
        } catch (StoreAccessException e) {
            log.error("store access error :" + e.getMessage());
            log.error(e.getStackTrace().toString());
            return null;
        }
    }

    public void put(K k, V v) {
        try {
            dataStore.put(k, v);
        } catch (StoreAccessException e) {
            log.error("store access error :" + e.getMessage());
            log.error(e.getStackTrace().toString());
        }
    }

    public boolean remove(K k) {
        try {
            ValueHolder<V> valueHolder = dataStore.remove(k);
            return valueHolder != null;
        } catch (StoreAccessException e) {
            log.error("store access error :" + e.getMessage());
            log.error(e.getStackTrace().toString());
            return false;
        }
    }

    public void clear() {
        try {
            dataStore.clear();
        } catch (StoreAccessException e) {
            log.error("store access error :" + e.getMessage());
            log.error(e.getStackTrace().toString());
        }
    }
    
    


    public Map<K, V> getAll(Set<? extends K> set) {
        return null;
    }

    public boolean containsKey(K k) {
        return false;
    }

    public void loadAll(Set<? extends K> set, boolean b, CompletionListener completionListener) {

    }

    public V getAndPut(K k, V v) {
        return null;
    }

    public void putAll(Map<? extends K, ? extends V> map) {

    }

    public boolean putIfAbsent(K k, V v) {
        return false;
    }

    public boolean remove(K k, V v) {
        return false;
    }

    public V getAndRemove(K k) {
        return null;
    }

    public boolean replace(K k, V v, V v1) {
        return false;
    }

    public boolean replace(K k, V v) {
        return false;
    }

    public V getAndReplace(K k, V v) {
        return null;
    }

    public void removeAll(Set<? extends K> set) {

    }

    public void removeAll() {

    }


    public <C extends Configuration<K, V>> C getConfiguration(Class<C> aClass) {
        return (C) configuration;
    }

    public <T> T invoke(K k, EntryProcessor<K, V, T> entryProcessor, Object... objects) throws EntryProcessorException {
        return null;
    }

    public <T> Map<K, EntryProcessorResult<T>> invokeAll(Set<? extends K> set, EntryProcessor<K, V, T> entryProcessor, Object... objects) {
        return null;
    }

    public String getName() {
        return null;
    }

    public CacheManager getCacheManager() {
        return null;
    }

    public void close() {

    }

    public boolean isClosed() {
        return false;
    }

    public <T> T unwrap(Class<T> aClass) {
        return null;
    }

    public void registerCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

    }

    public void deregisterCacheEntryListener(CacheEntryListenerConfiguration<K, V> cacheEntryListenerConfiguration) {

    }

    public Iterator<Entry<K, V>> iterator() {
        return null;
    }
}
