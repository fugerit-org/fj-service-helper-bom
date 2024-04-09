package teat.org.fugerit.java.simple.config;

import org.fugerit.java.simple.config.ConfigParams;
import org.fugerit.java.simple.config.ConfigParamsDefault;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

class TestConfigParamsDefault {

    @Test
    void testConfigParams() throws IOException {
        try (InputStream is = new FileInputStream( "src/test/resources/testconfig.properties" ) ) {
            Properties configProperties = new Properties();
            configProperties.load( is );
            ConfigParams config = new ConfigParamsDefault( configProperties );
            String value1 = config.getValue( "testconfig.param1" );
            Assertions.assertEquals( "value1", value1 );
            Optional<String> value2 = config.getOptionalValue( "testconfig.param2" );
            Assertions.assertEquals( "value2", value2.get() );
            Optional<String> valueX = config.getOptionalValue( "testconfig.paramX" );
            Assertions.assertFalse( valueX.isPresent() );
        }
    }

}

