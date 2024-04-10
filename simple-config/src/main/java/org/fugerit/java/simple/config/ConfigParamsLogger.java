package org.fugerit.java.simple.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.function.BiConsumer;

@Slf4j
public class ConfigParamsLogger extends ConfigParamsWrapper {

    public static ConfigParams wrapLogDebug( ConfigParams params ) {
        return new ConfigParamsLogger( params );
    }

    public static ConfigParams wrapLogInfo( ConfigParams params ) {
        return new ConfigParamsLogger( params, ( n, v ) -> log.debug( "wraps : {}, name : {}, value : {} ", params, n, v ) );
    }

    private BiConsumer<String, Object> logFun;

    public ConfigParamsLogger(final ConfigParams wrapped) {
        this( wrapped, ( n, v ) -> log.debug( "wraps : {}, name : {}, value : {} ", wrapped, n, v ) );
    }

    public ConfigParamsLogger(final ConfigParams wrapped, final BiConsumer<String, Object> logFun) {
        super( wrapped );
        this.logFun = logFun;
    }

    @Override
    public String getValue(String name) {
        String value = super.getValue( name );
        this.logFun.accept( name, value );
        return value;
    }

    @Override
    public Optional<String> getOptionalValue(String name) {
        Optional<String> value = super.getOptionalValue( name );
        this.logFun.accept( name, value );
        return value;
    }

}
