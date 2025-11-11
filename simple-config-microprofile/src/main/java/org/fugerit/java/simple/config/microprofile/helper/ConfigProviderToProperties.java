package org.fugerit.java.simple.config.microprofile.helper;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.fugerit.java.core.function.SafeFunction;
import org.fugerit.java.core.function.UnsafeVoid;
import org.fugerit.java.core.lang.helpers.StringUtils;
import org.fugerit.java.simple.config.ConfigParamsHelper;

import java.util.Properties;
import java.util.function.Consumer;

@Slf4j
public class ConfigProviderToProperties {

    private ConfigProviderToProperties() {}

    private static void handleCurrent(Config config, String propertyName, String newPropertyName, String toNamespace, Properties params, Consumer<UnsafeVoid<Exception>> handler ) {
        handler.accept( () -> {
            if ( toNamespace != null ) {
                params.setProperty( String.format( "%s%s", toNamespace , newPropertyName), config.getValue( propertyName, String.class ) );
            } else {
                params.setProperty( newPropertyName, config.getValue( propertyName, String.class ) );
            }
        } );

    }

    private static Consumer<UnsafeVoid<Exception>> toUnsafeHandler( boolean silent ) {
        if ( silent ) {
            return SafeFunction::applySilent;
        } else {
            return SafeFunction::apply;
        }
    }

    public static final boolean DEFAULT_SILENT = Boolean.FALSE;

    public static Properties configToProperties(Config  config, String fromNamespace, String toNamespace) {
        return configToProperties( config, fromNamespace, toNamespace, DEFAULT_SILENT );
    }

    public static Properties configToProperties(Config  config, String fromNamespace, String toNamespace, boolean silent) {
        Properties params = new Properties();
        for ( String propertyName : config.getPropertyNames() ) {
            if ( fromNamespace != null ) {
                if ( propertyName.startsWith( fromNamespace ) ) {
                    handleCurrent( config, propertyName, propertyName.substring( fromNamespace.length() ), toNamespace, params, toUnsafeHandler( silent ) );
                }
            } else {
                handleCurrent( config, propertyName, propertyName, toNamespace, params, toUnsafeHandler( silent ) );
            }
        }
        return params;
    }

    public static Properties configToProperties(Config  config) {
        return configToProperties(config, null, null, DEFAULT_SILENT );
    }

}
