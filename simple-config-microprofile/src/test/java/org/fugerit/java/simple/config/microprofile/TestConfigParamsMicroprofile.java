package org.fugerit.java.simple.config.microprofile;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;
import org.fugerit.java.simple.config.ConfigParams;
import org.fugerit.java.simple.config.ConfigParamsDefault;
import org.fugerit.java.simple.config.microprofile.ConfigParamsMicroprofile;
import org.fugerit.java.simple.config.microprofile.ConfigParamsMicroprofileLoose;
import org.fugerit.java.simple.config.microprofile.helper.ConfigProviderToProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

class TestConfigParamsMicroprofile {

    @Test
    @SuppressWarnings("unchecked")
    void testConfigParamsMicroprofile() throws IOException {
        Properties configProperties = new Properties();
        try (InputStream is = new FileInputStream( "src/test/resources/testconfig.properties" ) ) {
            configProperties.load( is );
        }
        ConfigParamsDefault configHelper = new ConfigParamsDefault( configProperties );
        ConfigProviderResolver.setInstance(new ConfigProviderResolver() {
            @Override
            public Config getConfig() {
                return new Config() {
                    @Override
                    public <T> T getValue(String s, Class<T> aClass) {
                        if ( aClass == String.class ) {
                            return (T) configHelper.getValue( s );
                        } else {
                            return null;
                        }
                    }
                    @Override
                    public ConfigValue getConfigValue(String s) {
                        return null;
                    }
                    @Override
                    public <T> Optional<T> getOptionalValue(String s, Class<T> aClass) {
                        if ( aClass == String.class ) {
                            return (Optional<T>) configHelper.getOptionalValue( s );
                        } else {
                            return null;
                        }
                    }
                    @Override
                    public Iterable<String> getPropertyNames() {
                        return configProperties.stringPropertyNames();
                    }
                    @Override
                    public Iterable<ConfigSource> getConfigSources() {
                        return null;
                    }
                    @Override
                    public <T> Optional<Converter<T>> getConverter(Class<T> aClass) {
                        return Optional.empty();
                    }
                    @Override
                    public <T> T unwrap(Class<T> aClass) {
                        return null;
                    }
                };
            }
            @Override
            public Config getConfig(ClassLoader classLoader) {
                return null;
            }
            @Override
            public ConfigBuilder getBuilder() {
                return null;
            }
            @Override
            public void registerConfig(Config config, ClassLoader classLoader) {
            }
            @Override
            public void releaseConfig(Config config) {
            }
        });
        // actual testing
        ConfigParams config = new ConfigParamsMicroprofile();
        String value1 = config.getValue( "testconfig.param1" );
        Assertions.assertEquals( "value1", value1 );
        Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
        Assertions.assertEquals( "value2", value2.get() );
        Optional<String> valueX = config.getOptionalValue( "testconfig.paramX" );
        Assertions.assertFalse( valueX.isPresent() );
        // loose testing
        ConfigParams configLoose = new ConfigParamsMicroprofileLoose();
        String value3 = configLoose.getValue( "testconfig.param3" );
        Assertions.assertEquals( "value3", value3 );
        Assertions.assertNull( configLoose.getValue( "notPresent" ) );
        // test config provider to properties
        Properties convert = ConfigProviderToProperties.configToProperties(ConfigProvider.getConfig(), null, null );
        Assertions.assertEquals( "value1", convert.getProperty( "testconfig.param1" ) );
    }

}
