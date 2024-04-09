package org.fugerit.java.simple.config;

import java.util.Optional;
import java.util.Properties;

public class ConfigParamsDefault extends AbstractConfigParams implements ConfigParams {

    private Properties configProperties;

    public ConfigParamsDefault(String configNamespace, Properties configProperties) {
        super( configNamespace );
        this.configProperties = configProperties;
    }

    public ConfigParamsDefault(Properties configProperties) {
        this( DEFAULT_NAMESPACE, configProperties );
    }

    @Override
    protected Optional<String> getOptionalValueNamespace(String name) {
        String value = this.getValueNamespace( name );
        if ( value == null ) {
            return Optional.empty();
        } else {
            return Optional.of( value );
        }
    }

    @Override
    protected String getValueNamespace(String name) {
        return this.configProperties.getProperty( name );
    }

}
