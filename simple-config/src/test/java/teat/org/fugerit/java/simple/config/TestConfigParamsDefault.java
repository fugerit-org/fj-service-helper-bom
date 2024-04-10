package teat.org.fugerit.java.simple.config;

import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.simple.config.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

class TestConfigParamsDefault {

    @Test
    void testConfigParams() throws IOException {
        Properties configProperties = PropsIO.loadFromClassLoaderSafe( "testconfig.properties" );
        ConfigParams config = ConfigParamsLogger.wrapLogDebug( new ConfigParamsDefault( configProperties ) );
        String value1 = config.getValue( "testconfig.param1" );
        Assertions.assertEquals( "value1", value1 );
        Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
        Assertions.assertEquals( "value2", value2.get() );
        Optional<String> valueX = config.getOptionalValue( "testconfig.paramX" );
        Assertions.assertFalse( valueX.isPresent() );
        // test null namepsace
        ConfigParams configAlt = new ConfigParamsDefault( null, configProperties );
        String value3 = config.getValue( "testconfig.param3" );
        Assertions.assertEquals( "value3", value3 );
        // simple configuration
        String typeOk = SimpleConfigurableObject.class.getName();
        SimpleConfigurable simpleConfig = SimpleConfigFacade.configure( typeOk, config );
        Assertions.assertNotNull( simpleConfig );
        String typeKo = typeOk+"KO";
        Assertions.assertNull( SimpleConfigFacade.configure( typeKo, config ) );
    }

}

