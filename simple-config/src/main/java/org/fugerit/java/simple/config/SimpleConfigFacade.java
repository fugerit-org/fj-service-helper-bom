package org.fugerit.java.simple.config;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.function.SafeFunction;

import java.lang.reflect.Constructor;

@Slf4j
public class SimpleConfigFacade {

    private SimpleConfigFacade() {}

    public static <T extends SimpleConfigurable> T configure( String type, ConfigParams configParams ) {
        try {
        	@SuppressWarnings("unchecked")
            Class<T> c = (Class<T>) Class.forName( type );
            return configure( c, configParams );
        } catch ( ClassNotFoundException e ) {
            log.warn( "class not found: {}, returning null", type );
            return null;
        }
    }

    public static <T extends SimpleConfigurable> T configure( Class<T> c, ConfigParams configParams ) {
        return SafeFunction.get( () -> {
            log.info( "type : {}, configParams : {}", c.getCanonicalName(), configParams );
            Constructor<?> constructor = c.getDeclaredConstructor();
            log.info( "constructor : {}", constructor );
            @SuppressWarnings("unchecked")
			T res = (T) constructor.newInstance();
            log.info( "res, call configure() : {}", res );
            res.configure( configParams );
            return res;
        } );
    }

}
