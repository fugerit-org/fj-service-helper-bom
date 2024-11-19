package org.fugerit.java.simple.config.microprofile.helper;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
class TestSimpleConfigOverride {

    private  static final String KEY1 = "key1";

    private static final String VALUE1 = "value1";

    private static final String VALUE1_ALT = "alt-value1";

    private static final String KEY_NA = "key-na";

    @Test
    void testConfigOverride() {
        // simple override properties
        Map<String, String> map = new HashMap<>();
        map.put(KEY1, VALUE1);
        map.put("key2", "value2");
        // override configuration
        ConfigProviderResolver.setInstance( this.newConfigProviderResolver() );
        Assertions.assertEquals( VALUE1_ALT, ConfigProviderResolver.instance().getConfig().getValue( KEY1, String.class ) );
        SimpleConfigOverride.overrideConfig( map );
        Assertions.assertEquals( VALUE1, ConfigProviderResolver.instance().getConfig().getValue( KEY1, String.class ) );
        Assertions.assertNull( ConfigProviderResolver.instance().getConfig().getValue( KEY_NA, String.class ) );
        Assertions.assertThrows( ConfigRuntimeException.class, () -> SimpleConfigOverride.toOptional( "test", Date.class, "value" ) );
        // just testing ConfigProviderResolver methods
        ConfigProviderResolver.instance().releaseConfig( null );
        ConfigProviderResolver.instance().registerConfig( null, this.getClass().getClassLoader() );
        Assertions.assertNotNull( ConfigProviderResolver.instance().getConfig() );
        Assertions.assertNotNull( ConfigProviderResolver.instance().getConfig( this.getClass().getClassLoader() ) );
        // just testing Config methods
        Config config = ConfigProviderResolver.instance().getConfig();
        log.info( "config : {}", config );
        Assertions.assertNull( config.getOptionalValues( KEY_NA, String.class ) );
        Assertions.assertNull( config.getValues( KEY_NA, String.class ) );
        Assertions.assertNull( config.getConfigValue( KEY_NA ) );
        Assertions.assertNotNull( config.getPropertyNames() );
        Assertions.assertNull( config.getConfigSources() );
        Assertions.assertNull( config.unwrap(String.class) );
        Assertions.assertEquals( Optional.empty(), config.getConverter(String.class) );
        Assertions.assertNull( new ConfigWrapper( this.newMockConfig() ).getValue( KEY_NA, String.class ) );
        ConfigProviderResolverWrapper wrapper = new ConfigProviderResolverWrapper( ConfigProviderResolver.instance() );
        Assertions.assertNotNull( wrapper.getConfig() );
        Assertions.assertNotNull( wrapper.getConfig( this.getClass().getClassLoader() ) );
        Assertions.assertNull( wrapper.getBuilder() );
    }

    private ConfigProviderResolver newConfigProviderResolver() {
        return new ConfigProviderResolver() {

            @Override
            public Config getConfig() {
                return newMockConfig();
            }

            @Override
            public Config getConfig(ClassLoader loader) {
                return getConfig();
            }

            @Override
            public ConfigBuilder getBuilder() {
                return null;
            }

            @Override
            public void registerConfig(Config config, ClassLoader classLoader) {
                // do nothing implementation for test purposes
            }

            @Override
            public void releaseConfig(Config config) {
                // do nothing implementation for test purposes
            }

        };
    }

    private Config newMockConfig() {
        return new Config() {
            @Override
            public <T> T getValue(String propertyName, Class<T> propertyType) {
                if ( KEY1.equals( propertyName ) ) {
                    return (T)VALUE1_ALT;
                } else {
                    return null;
                }
            }

            @Override
            public ConfigValue getConfigValue(String propertyName) {
                return null;
            }

            @Override
            public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
                return Optional.empty();
            }

            @Override
            public Iterable<String> getPropertyNames() {
                return Arrays.asList( KEY1 );
            }

            @Override
            public Iterable<ConfigSource> getConfigSources() {
                return null;
            }

            @Override
            public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
                return Optional.empty();
            }

            @Override
            public <T> T unwrap(Class<T> type) {
                return null;
            }

            @Override
            public <T> List<T> getValues(String propertyName, Class<T> propertyType) {
                return null;
            }

            @Override
            public <T> Optional<List<T>> getOptionalValues(String propertyName, Class<T> propertyType) {
                return null;
            }
        };
    }

}
