package org.fugerit.java.simple.config;

import org.fugerit.java.core.cfg.ConfigRuntimeException;
import org.fugerit.java.core.io.helper.HelperIOException;
import org.fugerit.java.core.lang.helpers.ClassHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConfigParamsHelper {

    private ConfigParamsHelper() {}

    public static final String PATH_MODE_FILE = "file";
    public static final String PATH_MODE_CL = "cl";

    public static String resolveOptional( ConfigParams config, String key, String defValue ) {
        return config.getOptionalValue( key ).orElse(defValue);
    }

    public static String resoveRequired( ConfigParams config, String key ) {
        String value = config.getValue( key );
        if ( value == null ) {
            throw new ConfigRuntimeException( String.format( "Param %s must be present", key ) );
        }
        return value;
    }

    public static InputStream resolveStream(ConfigParams config, String keyPath, String keyMode, String defPath, String defMode ) throws IOException {
        // path
        String path = resolveOptional( config, keyPath, defPath );
        // mode
        String mode = resolveOptional( config, keyMode, defMode );
        if ( PATH_MODE_CL.equalsIgnoreCase( mode ) ) {
            try {
                return ClassHelper.loadFromDefaultClassLoader( path );
            } catch (Exception e) {
                throw HelperIOException.convertEx( e );
            }
        } else {
            File configFile = new File( path );
            if ( configFile.exists() ) {
                return new FileInputStream( configFile ) ;
            } else {
                throw new IOException( String.format( "File not found: %s", path ) );
            }
        }
    }

}
