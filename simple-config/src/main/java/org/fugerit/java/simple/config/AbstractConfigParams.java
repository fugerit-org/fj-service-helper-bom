package org.fugerit.java.simple.config;

import java.util.Optional;

public abstract class AbstractConfigParams implements  ConfigParams {

    public static final String DEFAULT_NAMESPACE = "";

    private String configNamespace;

    protected AbstractConfigParams(String configNamespace) {
        this.configNamespace = configNamespace == null ? DEFAULT_NAMESPACE : configNamespace;
    }

    protected abstract String getValueNamespace( String name );

    protected abstract Optional<String> getOptionalValueNamespace( String name );

    protected String toNamespace( String name ) {
        return this.configNamespace+name;
    }

    @Override
    public String getValue(String name) {
        return this.getValueNamespace( this.toNamespace( name ) );
    }

    @Override
    public Optional<String> getOptionalValue(String name) {
        return this.getOptionalValueNamespace( this.toNamespace( name ) );
    }

}
