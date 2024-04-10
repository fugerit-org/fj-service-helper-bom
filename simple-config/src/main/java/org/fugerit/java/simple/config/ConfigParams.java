package org.fugerit.java.simple.config;

import java.util.Optional;

public interface ConfigParams {

    String getValue( final String name );

    Optional<String> getOptionalValue( final String name );

    default ConfigParams withDebugLog() {
        return ConfigParamsLogger.wrapLogDebug(  this );
    }

    default ConfigParams withInfoLog() {
        return ConfigParamsLogger.wrapLogInfo(  this );
    }

    default ConfigParams withNamespace( final String namespace ) {
        return new ConfigParamsWrapper( this ) {
            @Override
            public String getValue(String name) {
                return super.getValue(namespace+name);
            }
            @Override
            public Optional<String> getOptionalValue(String name) {
                return super.getOptionalValue(namespace+name);
            }
            @Override
            public String toString() {
                return ConfigParams.class.getSimpleName()+"[namespace:"+namespace+"]";
            }
        };
    }

}
