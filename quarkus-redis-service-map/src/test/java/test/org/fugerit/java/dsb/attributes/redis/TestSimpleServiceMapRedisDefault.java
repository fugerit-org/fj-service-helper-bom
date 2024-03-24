package test.org.fugerit.java.dsb.attributes.redis;

import org.fugerit.java.dsb.attributes.redis.SimpleServiceMapRedisDefault;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSimpleServiceMapRedisDefault {

	@Test
	void testSimpleServiceMapRedisDefault() {
		SimpleServiceMapRedisDefault serviceMap = new SimpleServiceMapRedisDefault();
		Assertions.assertNotNull( serviceMap );
		serviceMap.remove( "a" );
		Assertions.assertThrows( Exception.class , () -> serviceMap.set("b", "c"));
		Assertions.assertThrows( Exception.class , () -> serviceMap.get("d"));
	}
	
}
