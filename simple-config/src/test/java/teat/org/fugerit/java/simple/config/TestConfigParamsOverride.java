package teat.org.fugerit.java.simple.config;

import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.simple.config.ConfigParams;
import org.fugerit.java.simple.config.ConfigParamsDefault;
import org.fugerit.java.simple.config.ConfigParamsOverride;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Properties;

class TestConfigParamsOverride {

    private static final String TEST_KEY = "testconfig.param1";

    private static final String TEST_OVERRIDE_VALUE = "override1";

    @Test
    void testConfigParamsOverride() {
        Properties configProperties = PropsIO.loadFromClassLoaderSafe("testconfig.properties");
        ConfigParams config = new ConfigParamsDefault(configProperties).withDebugLog();
        Assertions.assertEquals( "value1", config.getValue( TEST_KEY ) );
        Properties overrideProperties = new Properties();
        overrideProperties.setProperty( TEST_KEY, TEST_OVERRIDE_VALUE );
        ConfigParams override = new ConfigParamsOverride( config, new ConfigParamsDefault( overrideProperties ) );
        Assertions.assertEquals( TEST_OVERRIDE_VALUE, override.getValue( TEST_KEY ) );
        Assertions.assertEquals( "value2", override.getValue( "testconfig.param2" ) );
    }

}
