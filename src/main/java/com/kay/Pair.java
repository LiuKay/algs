package com.kay;

import java.util.Objects;

public class Pair<K, V> {
    public final K k;
    public final V v;

    public Pair(K k, V v) {
        this.k = k;
        this.v = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(k, pair.k) && Objects.equals(v, pair.v);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k, v);
    }
}
