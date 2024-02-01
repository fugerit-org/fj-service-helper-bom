package test.org.fugerit.java.dsb.attributes.impl;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.dsb.attributes.SimpleServiceMap;
import org.fugerit.java.dsb.attributes.impl.SimpleServiceMapDefault;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class ServiceMapDefaultTest {

    @Test
    public void testMapSimple() {
        String key = "testKey";
        String value = "testValue";
        SimpleServiceMap map = new SimpleServiceMapDefault();
        map.set( key, value );
        Assert.assertEquals( value, map.get( key ) );
        map.remove( key );
        Assert.assertNull( map.get( key ) );
        log.info( "test key:{}, value:{} ok", key, value );
    }

}