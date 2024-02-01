package org.fugerit.java.sh.demo.redis;

import org.fugerit.java.dsb.attributes.SimpleServiceMap;
import org.fugerit.java.dsb.attributes.redis.SimpleServiceMapRedisDefault;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;

@ApplicationScoped
@Default
public class SessionRedis extends SimpleServiceMapRedisDefault implements SimpleServiceMap {
	
}
