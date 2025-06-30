package org.fugerit.java.simple.config;

import java.util.Optional;

public class ConfigParamsOverride extends ConfigParamsWrapper {

    public ConfigParamsOverride(ConfigParams wrapped, ConfigParams override) {
        super(wrapped);
        this.override = override;
    }

    private ConfigParams override;

    @Override
    public String getValue(String name) {
        return this.getOptionalValue(name).orElseGet(() -> super.getValue(name));
    }

    @Override
    public Optional<String> getOptionalValue(String name) {
        String value = this.override.getValue(name);
        if ( value != null ) {
            return Optional.of(value);
        } else {
            return super.getOptionalValue(name);
        }
    }

}
