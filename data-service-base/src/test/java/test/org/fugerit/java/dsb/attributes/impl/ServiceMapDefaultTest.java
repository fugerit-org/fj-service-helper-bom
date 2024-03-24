package test.org.fugerit.java.dsb.attributes.impl;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.dsb.attributes.SimpleServiceMap;
import org.fugerit.java.dsb.attributes.impl.SimpleServiceMapDefault;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
class ServiceMapDefaultTest {

    @Test
    void testMapSimple() {
        String key = "testKey";
        String value = "testValue";
        SimpleServiceMap map = new SimpleServiceMapDefault();
        map.set( key, value );
        Assertions.assertEquals( value, map.get( key ) );
        map.remove( key );
        Assertions.assertNull( map.get( key ) );
        log.info( "test key:{}, value:{} ok", key, value );
    }

}