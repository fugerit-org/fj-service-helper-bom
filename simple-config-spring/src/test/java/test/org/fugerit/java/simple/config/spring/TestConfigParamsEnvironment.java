package test.org.fugerit.java.simple.config.spring;

import org.fugerit.java.simple.config.ConfigParams;
import org.fugerit.java.simple.config.ConfigParamsDefault;
import org.fugerit.java.simple.config.spring.ConfigParamsEnvironment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

class TestConfigParamsEnvironment {

    @Test
    void testConfigParamsMicroprofile() throws IOException {
        Properties configProperties = new Properties();
        try (InputStream is = new FileInputStream( "src/test/resources/testconfig.properties" ) ) {
            configProperties.load( is );
        }
        final ConfigParamsDefault configHelper = new ConfigParamsDefault( configProperties );
        final Environment environment = new Environment() {
            @Override
            public String[] getActiveProfiles() {
                return new String[0];
            }

            @Override
            public String[] getDefaultProfiles() {
                return new String[0];
            }

            @Override
            public boolean acceptsProfiles(String... profiles) {
                return false;
            }

            @Override
            public boolean acceptsProfiles(Profiles profiles) {
                return false;
            }

            @Override
            public boolean containsProperty(String key) {
                return false;
            }

            @Override
            public String getProperty(String key) {
                return configHelper.getValue( key );
            }

            @Override
            public String getProperty(String key, String defaultValue) {
                return "";
            }

            @Override
            public <T> T getProperty(String key, Class<T> targetType) {
                return null;
            }

            @Override
            public <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
                return null;
            }

            @Override
            public String getRequiredProperty(String key) throws IllegalStateException {
                return "";
            }

            @Override
            public <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException {
                return null;
            }

            @Override
            public String resolvePlaceholders(String text) {
                return "";
            }

            @Override
            public String resolveRequiredPlaceholders(String text) throws IllegalArgumentException {
                return "";
            }
        };
        // actual testing
        ConfigParams config = new ConfigParamsEnvironment( environment );
        String value1 = config.getValue( "testconfig.param1" );
        Assertions.assertEquals( "value1", value1 );
        Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
        Assertions.assertEquals( "value2", value2.get() );
        Optional<String> valueX = config.getOptionalValue( "testconfig.paramX" );
        Assertions.assertFalse( valueX.isPresent() );
    }

}
