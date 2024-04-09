package org.fugerit.java.simple.config;

import java.util.Optional;

public interface ConfigParams {

    String getValue( String name );

    Optional<String> getOptionalValue( String name );

}
