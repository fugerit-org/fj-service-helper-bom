package org.fugerit.java.dsb.attributes.impl;

import org.fugerit.java.dsb.attributes.ServiceMap;

import java.util.HashMap;
import java.util.Map;

public class ServiceMapDefault<K, V> implements ServiceMap<K, V> {

    private Map<K, V> map;
    public ServiceMapDefault() {
        this.map = new HashMap<>();
    }

    @Override
    public V get(K key) {
        return this.map.get( key );
    }

    @Override
    public void set(K key, V value ) {
        this.map.put( key, value );
    }

    @Override
    public void remove(K key) {
        this.map.remove( key );
    }
}
