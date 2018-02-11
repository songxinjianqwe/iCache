package com.sinjinsong.icache;

import com.sinjinsong.icache.jsr107.iCache;
import com.sinjinsong.icache.store.impl.lru.LRUDataStore;

/**
 * @author sinjinsong
 * @date 2018/2/11
 */
public class MyClient {
    public static void main(String[] args) {
        iCache cache = new iCache(new LRUDataStore(10));
        for (int i = 0; i < 10; i++) {
            cache.put(i, i * i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(cache.get(i));
        }
        
        cache.get(0);
        cache.put(10, 100);
        System.out.println("-------------------");
        for (int i = 0; i < 11; i++) {
            System.out.println(cache.get(i));
        }
    }
}
