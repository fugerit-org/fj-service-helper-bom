package org.fugerit.java.simple.config.microprofile.helper;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.fugerit.java.core.cfg.ConfigRuntimeException;

import java.util.*;

/**
 * This class will allow config values override.
 *
 * NOTE: this wrapper is only meant for development and test purpose.
 */
public class SimpleConfigOverride extends ConfigWrapper {

    private Map<String, String> overrides;

    public SimpleConfigOverride(Config delegate, Map<String, String> overrides) {
        super(delegate);
        this.overrides = overrides;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        String override = this.overrides.get(propertyName);
        if ( override != null ) {
            return toOptional( propertyName, propertyType, override );
        } else {
            return super.getOptionalValue( propertyName, propertyType );
        }
    }


    @Override
    public Iterable<String> getPropertyNames() {
        Set<String> names = new HashSet<>( this.overrides.keySet() );
        for ( String key : super.getPropertyNames() ) {
            names.add( key );
        }
        return names;
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        return this.getOptionalValue( propertyName, propertyType ).orElse( null );
    }

    public static <T> Optional<T> toOptional(String propertyName, Class<T> propertyType, String override ) {
        if ( propertyType.isAssignableFrom( override.getClass() ) ) {
            return Optional.of( (T)override );
        } else {
            throw new ConfigRuntimeException( String.format( "Wrong property type, name : %s, expected : %s, actual : %s", propertyName, propertyType.getName(), override.getClass().getName() ) );
        }
    }

    public static void overrideConfig( Map<String, String> overrides ) {
        overrideConfig( overrides, ConfigProviderResolver.instance() );
    }

    public static void overrideConfig( Map<String, String> overrides, ConfigProviderResolver delegate ) {
        ConfigProviderResolver.setInstance( new ConfigProviderResolverWrapper( delegate ) {
            @Override
            public Config getConfig() {
                return new SimpleConfigOverride( delegate.getConfig(), overrides );
            }
            @Override
            public Config getConfig(ClassLoader loader) {
                return new SimpleConfigOverride( delegate.getConfig(loader), overrides );
            }
        } );
    }

    @Override
    public String toString() {
        return super.toString()+"[overrides:"+this.overrides+"]";
    }
}
