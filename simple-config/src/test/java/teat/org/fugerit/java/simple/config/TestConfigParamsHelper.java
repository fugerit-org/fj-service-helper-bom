
package teat.org.fugerit.java.simple.config;

import lombok.extern.slf4j.Slf4j;
import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.util.PropsIO;
import org.fugerit.java.simple.config.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
class TestConfigParamsHelper {

    private static final String NOT_FOUND = "not-found";

    @Test
    void testConfigParamsHelper() throws IOException {
        Properties configProperties = PropsIO.loadFromClassLoaderSafe( "configparamshelper.properties" );
        ConfigParams config = new ConfigParamsDefault( configProperties ).withDebugLog();
        // test mode class loader
        try (InputStream is1 = ConfigParamsHelper.resolveStream( config, "path1", "mode1", null, null )) {
            Assertions.assertNotNull( is1 );
        }
        // test mode path
        try (InputStream is2 = ConfigParamsHelper.resolveStream( config, "path2", "mode2", null, null )) {
            Assertions.assertNotNull( is2 );
        }
        // test default mode
        try (InputStream is3 = ConfigParamsHelper.resolveStream( config, NOT_FOUND, NOT_FOUND, "configparamshelper.properties", ConfigParamsHelper.PATH_MODE_CL )) {
            Assertions.assertNotNull( is3 );
        }
        // test cl not found
        try ( InputStream is4 = ConfigParamsHelper.resolveStream( config, NOT_FOUND, NOT_FOUND, "not-found.properties", ConfigParamsHelper.PATH_MODE_CL ) ) {
            Assertions.assertNull( is4 );
        }
        Assertions.assertThrows( IOException.class, () ->
                ConfigParamsHelper.resolveStream( config, NOT_FOUND, NOT_FOUND, "not-found.properties", ConfigParamsHelper.PATH_MODE_FILE ) );
        Assertions.assertThrows( IOException.class, () ->
                ConfigParamsHelper.resolveStream( config, NOT_FOUND, NOT_FOUND, null, ConfigParamsHelper.PATH_MODE_CL ) );
        Assertions.assertEquals( ConfigParamsHelper.PATH_MODE_FILE, ConfigParamsHelper.resoveRequired( config, "mode2" )  );
        Assertions.assertThrows( ConfigRuntimeException.class, () -> ConfigParamsHelper.resoveRequired( config, NOT_FOUND ) );
    }

}

