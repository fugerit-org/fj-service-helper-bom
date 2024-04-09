package org.fugerit.java.simple.config;

import java.util.Optional;

public class ConfigParamsWrapper implements ConfigParams {

    public ConfigParamsWrapper(ConfigParams wrapped) {
        this.wrapped = wrapped;
    }

    private ConfigParams wrapped;

    @Override
    public String getValue(String name) {
        return this.wrapped.getValue(name);
    }

    @Override
    public Optional<String> getOptionalValue(String name) {
        return this.wrapped.getOptionalValue(name);
    }

}
