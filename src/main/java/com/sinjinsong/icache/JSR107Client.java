package com.sinjinsong.icache;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public class JSR107Client {
    public static void main(String[] args) {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        CacheManager cacheManager = cachingProvider.getCacheManager();
        Cache<String, User> cache = cacheManager.createCache("Test", new MutableConfiguration<String, User>());
        User user = new User("leo");
        cache.put(user.getName(),user);
        System.out.println(cache.get("leo"));
        cache.remove("leo");
        System.out.println(cache.get("leo"));
    }
}
