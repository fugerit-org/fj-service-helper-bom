package org.fugerit.java.dsb.attributes;

public interface ServiceMap<K,V> {

    V get(K key);

    void set(K key, V value);

    void remove(K key);

}
