package org.fugerit.java.simple.config.microprofile;

import org.eclipse.microprofile.config.ConfigProvider;
import org.fugerit.java.simple.config.ConfigParams;

import java.util.Optional;

public class ConfigParamsMicroprofile implements ConfigParams {


    @Override
    public String getValue(String name) {
        return ConfigProvider.getConfig().getValue( name, String.class );
    }

    @Override
    public Optional<String> getOptionalValue(String name) {
        return ConfigProvider.getConfig().getOptionalValue( name, String.class );
    }
}
