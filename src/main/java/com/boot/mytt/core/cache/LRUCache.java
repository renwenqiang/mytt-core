package com.boot.mytt.core.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
/**
 * @author renwq
 * @date 2020/5/25
 */
public class LRUCache<K, V> {

    private int a;

    private final int MAX_CACHE_SIZE;

    private final float DEFAULT_LOAD_FACTORY = 0.75F;

    private SoftReference<HashMap<K, V>> sf = null;

    private HashMap<K, V> getCaches() {
        synchronized (this) {
            if (null == sf.get()) {
                sf = new SoftReference<>(new LinkedHashMap<K, V>(MAX_CACHE_SIZE, DEFAULT_LOAD_FACTORY, true){
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                        return size() > MAX_CACHE_SIZE;
                    }
                });
            }
        }
        return sf.get();
    }

    public LRUCache(int cacheSize) {
        MAX_CACHE_SIZE = cacheSize;
        int capacity = (int)Math.ceil(MAX_CACHE_SIZE / DEFAULT_LOAD_FACTORY) + 1;
        sf = new SoftReference<>(new LinkedHashMap<K, V>(MAX_CACHE_SIZE, DEFAULT_LOAD_FACTORY, true){
            private static final long serialVersionUID = 1L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        });
    }

    public synchronized void put(K k, V v) {
        getCaches().put(k, v);
    }

    public synchronized V get(K k) {
        return getCaches().get(k);
    }

    public synchronized void remove(K k) {
        getCaches().remove(k);
    }

    public synchronized Set<Map.Entry<K, V>> getAll() {
        return getCaches().entrySet();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<K, V> entry: getAll()) {
            sb.append(String.format("%s: %s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
