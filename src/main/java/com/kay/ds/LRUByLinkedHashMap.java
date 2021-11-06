package com.kay.ds;

import com.kay.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUByLinkedHashMap {

    private final LinkedHashMap<String, String> cache;
    private final int maxCap;

    public LRUByLinkedHashMap(int cap) {
        this.maxCap = cap;
        this.cache = new LinkedHashMap<String, String>() {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > maxCap;
            }
        };
    }

    public String get(String key) {
        if (!this.cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return this.cache.get(key);
    }

    public void put(String key, String value) {
        if (this.cache.containsKey(key)) {
            //insertion for existing key in LinkedHashKey will not affect the order
            this.cache.put(key, value);
            makeRecently(key);
            return;
        }

        this.cache.put(key, value);
    }

    private void makeRecently(String key) {
        String remove = this.cache.remove(key);
        this.cache.put(key, remove);
    }

    public static void main(String[] args) {
        LRUByLinkedHashMap cache = new LRUByLinkedHashMap(2);
        cache.put("k1", "v1");
        cache.put("k2", "v2");

        cache.put("k1", "k111"); // this put will move k1 to be the latest one

        cache.put("k3", "v3");

        Assert.isNull(cache.get("k2"));
    }
}
