package com.kay.ds;

import java.util.LinkedHashMap;

public class LRUByLinkedHashMap {

    private LinkedHashMap<String, String> cache;
    private int cap;

    public LRUByLinkedHashMap(int cap) {
        this.cap = cap;
        this.cache = new LinkedHashMap<>();
    }

    public String get(String key) {
        if (!this.cache.containsKey(key)) {
            return null;
        }
        makeRecently(key);
        return this.cache.get(key);
    }

    private void makeRecently(String key) {
        String remove = this.cache.remove(key);
        this.cache.put(key, remove);
    }

    public void put(String key, String value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
            return;
        }

        if (cache.size() >= this.cap) {
            String oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }

        cache.put(key, value);
    }

    public static void main(String[] args) {
        LRUByLinkedHashMap cache = new LRUByLinkedHashMap(2);
        cache.put("k1", "v1");
        cache.put("k2", "v2");

        cache.get("k1");

        cache.put("k3", "v3");

        assert cache.get("k2") == null;
    }
}
