package org.fugerit.java.dsb.attributes.redis;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.fugerit.java.dsb.attributes.SimpleServiceMap;

import io.quarkus.redis.datasource.RedisDataSource;
import io.quarkus.redis.datasource.value.ValueCommands;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleServiceMapRedisDefault implements SimpleServiceMap {

	@ConfigProperty( name = "cache.ttl-ms", defaultValue = "600000" ) // 10 minutes
	private long ttl;
	
	@Inject
	private RedisDataSource ds;
	
	public SimpleServiceMapRedisDefault() {
		log.debug( "SimpleServiceMapRedisDefault created!" );
	}
	
    private ValueCommands<String, String> com() {
    	return this.ds.value( String.class );
    }
    
    @Override
    public String get(String key) {
    	log.debug( "get key:{}", key );
        return this.com().get(key);
    }

    @Override
    public void set(String key, String value) {
    	log.debug( "set key:{}, ttl:{}", key, this.ttl );
    	this.com().psetex(key, this.ttl, value);
    }

    @Override
    public void remove(String key) {
    	log.warn( "remove method not implemented, key:{}", key );
    }
	
}
