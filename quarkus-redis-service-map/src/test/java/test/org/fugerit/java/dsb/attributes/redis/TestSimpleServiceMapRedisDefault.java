package test.org.fugerit.java.dsb.attributes.redis;

import org.fugerit.java.dsb.attributes.redis.SimpleServiceMapRedisDefault;
import org.junit.Assert;
import org.junit.Test;

public class TestSimpleServiceMapRedisDefault {

	@Test
	public void testSimpleServiceMapRedisDefault() {
		SimpleServiceMapRedisDefault serviceMap = new SimpleServiceMapRedisDefault();
		Assert.assertNotNull( serviceMap );
		serviceMap.remove( "a" );
		Assert.assertThrows( Exception.class , () -> serviceMap.set("b", "c"));
		Assert.assertThrows( Exception.class , () -> serviceMap.get("d"));
	}
	
}
