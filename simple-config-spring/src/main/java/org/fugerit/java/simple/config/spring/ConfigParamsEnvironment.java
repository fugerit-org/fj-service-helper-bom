package org.fugerit.java.simple.config.spring;

import org.fugerit.java.simple.config.AbstractConfigParams;
import org.springframework.core.env.Environment;

import java.util.Optional;

public class ConfigParamsEnvironment extends AbstractConfigParams {

    private Environment config;

    public ConfigParamsEnvironment( Environment config ) {
        this(DEFAULT_NAMESPACE, config);
    }

    public ConfigParamsEnvironment(String namespace, Environment config) {
        super(namespace);
        this.config = config;
    }

    @Override
    protected String getValueNamespace(String name) {
        return this.config.getProperty( name );
    }

    @Override
    protected Optional<String> getOptionalValueNamespace(String name) {
        return Optional.ofNullable( this.getValueNamespace( name ) );
    }

}
