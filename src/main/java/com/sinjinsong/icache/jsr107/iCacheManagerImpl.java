package com.sinjinsong.icache.jsr107;

import javax.cache.Cache;
import javax.cache.CacheException;
import javax.cache.configuration.Configuration;
import javax.cache.spi.CachingProvider;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public class iCacheManagerImpl implements javax.cache.CacheManager {
    private Map<String, Cache<?, ?>> caches = new ConcurrentHashMap<>();
    private volatile boolean closed;

    public CachingProvider getCachingProvider() {
        return null;
    }

    public URI getURI() {
        return null;
    }

    public ClassLoader getClassLoader() {
        return null;
    }

    public Properties getProperties() {
        return null;
    }

    /**
     * 缓存分类，每一类有一个name，对应一个Map
     *
     * @param cacheName
     * @param configuration
     * @param <K>
     * @param <V>
     * @param <C>
     * @return
     * @throws IllegalArgumentException
     */
    public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(String cacheName, C configuration) throws IllegalArgumentException {
        if (isClosed()) {
            throw new IllegalArgumentException();
        }
        assert cacheName != null;
        assert configuration != null;
        synchronized (caches) {
            if (caches.get(cacheName) != null) {
                throw new CacheException("A cache named" + cacheName + " already exists.");
            } else {
                iCache<?, ?> cache = new iCache<>(this, cacheName, configuration);
                caches.put(cacheName, cache);
                return (Cache<K, V>) cache;
            }
        }
    }

    public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyClass, Class<V> valueClass) {
        if (isClosed()) {
            throw new IllegalStateException();
        }
        assert keyClass != null;
        assert valueClass != null;
        Cache<K, V> cache = (Cache<K, V>) caches.get(cacheName);
        if (cache == null) {
            return null;
        } else {
            Configuration<?, ?> configuration = cache.getConfiguration(Configuration.class);
            if (configuration.getKeyType() != null && configuration.getKeyType().equals(keyClass)) {
                return cache;
            } else {
                throw new ClassCastException("Incompatible cache key types specified ,expected " +
                        configuration.getKeyType() + " ,but " + keyClass + " was specified");
            }
        }
    }

    public <K, V> Cache<K, V> getCache(String s) {
        return null;
    }

    public Iterable<String> getCacheNames() {
        return null;
    }

    public void destroyCache(String s) {

    }

    public void enableManagement(String s, boolean b) {

    }

    public void enableStatistics(String s, boolean b) {

    }

    public void close() {
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public <T> T unwrap(Class<T> aClass) {
        return null;
    }
}
