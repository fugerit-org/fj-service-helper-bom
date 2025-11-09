package org.fugerit.java.simple.config.microprofile.helper;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.fugerit.java.simple.config.ConfigParamsHelper;

import java.util.Properties;

@Slf4j
public class ConfigProviderToProperties {

    private ConfigProviderToProperties() {}

    public static Properties configToProperties(Config  config, String fromNamespace, String toNamespace) {
        Properties params = configToProperties(config);
        return ConfigParamsHelper.convert( params, fromNamespace, toNamespace );
    }

    public static Properties configToProperties(Config  config) {
        Properties params = new Properties();
        for ( String propertyName : config.getPropertyNames() ) {
            params.setProperty( propertyName, ConfigProvider.getConfig().getValue(propertyName, String.class) );
        }
        return params;
    }

}
