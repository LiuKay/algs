package com.kay.ds;

import com.kay.Assert;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Least Frequency Used
 * <p>
 * 1. if the LFU cache size reaches the capacity, remove the key with minimal frequency, if the frequency is associated
 * with multiple keys, then remove the oldest key(first put in).
 * <p>
 * 2. get/put time cost is O(1)
 * <p>
 * 3. get/put for existed key, the frequency of the key will increase 1.
 */
public class LFUCache {

    private int capacity;
    // key -value
    private Map<String, String> kvMap;
    // key - frequency
    private Map<String, Integer> kfMap;
    // frequency - keys
    private Map<Integer, LinkedHashSet<String>> fkMap;

    private Integer minFreq;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity should be large than 0.");
        }

        this.capacity = capacity;
        this.kvMap = new HashMap<>();
        this.kfMap = new HashMap<>();
        this.fkMap = new HashMap<>();
        this.minFreq = 0;
    }

    public String get(String key) {
        if (!this.kvMap.containsKey(key)) {
            return null;
        }

        increaseFrequencyForKey(key);
        return this.kvMap.get(key);
    }

    void put(String key, String value) {
        this.kvMap.computeIfPresent(key, (k, v) -> {
            increaseFrequencyForKey(k);
            return value;
        });

        if (this.kvMap.size() >= this.capacity) {
            //remove old key and put new key
            removeOldestKey();
        }

        this.kvMap.put(key, value);
        this.kfMap.put(key, 1);
        this.fkMap.putIfAbsent(1, new LinkedHashSet<>());
        this.fkMap.get(1).add(key);

        //for each put operation, the minFreq is 1 for the new key.
        this.minFreq = 1;
    }

    private void increaseFrequencyForKey(String key) {
        Integer oldFreq = this.kfMap.get(key);
        int newFreq = oldFreq + 1;

        this.kfMap.put(key, newFreq);
        this.fkMap.putIfAbsent(newFreq, new LinkedHashSet<>());
        this.fkMap.get(newFreq).add(key);

        LinkedHashSet<String> keysForOldFreq = this.fkMap.get(oldFreq);
        keysForOldFreq.remove(key);
        if (keysForOldFreq.isEmpty()) {
            this.fkMap.remove(oldFreq);

            // the minFreq keys is empty, the new minimal frequency is ++minFreq
            if (oldFreq.equals(this.minFreq)) {
                this.minFreq++;
            }
        }
    }

    private void removeOldestKey() {
        Integer minimalFreq = this.minFreq;

        LinkedHashSet<String> keysSet = this.fkMap.get(minimalFreq);
        String oldestKey = keysSet.iterator().next();

        keysSet.remove(oldestKey);
        if (keysSet.isEmpty()) {
            this.fkMap.remove(minimalFreq);
        }
        kvMap.remove(oldestKey);
        kfMap.remove(oldestKey);
    }


    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put("k1", "v1");
        lfuCache.put("k2", "v2");

        String k1 = lfuCache.get("k1");
        assert k1.equals("v1");

        //remove k2
        lfuCache.put("k3", "v3");

        Assert.isNull(lfuCache.get("k2"));
        Assert.isTrue(lfuCache.fkMap.size() == 2);
    }
}
