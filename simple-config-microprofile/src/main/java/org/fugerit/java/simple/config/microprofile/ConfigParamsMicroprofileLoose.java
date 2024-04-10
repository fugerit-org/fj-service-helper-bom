package org.fugerit.java.simple.config.microprofile;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.fugerit.java.simple.config.AbstractConfigParams;

import java.util.Optional;

public class ConfigParamsMicroprofileLoose extends AbstractConfigParams {

    public ConfigParamsMicroprofileLoose() {
        this(DEFAULT_NAMESPACE, ConfigProvider.getConfig());
    }

    public ConfigParamsMicroprofileLoose(String namespace, Config config) {
        super(namespace);
        this.config = config;
    }

    private Config config;

    @Override
    protected Optional<String> getOptionalValueNamespace(String name) {
        return this.config.getOptionalValue( name, String.class );
    }

    @Override
    protected String getValueNamespace(String name) {
        Optional<String> optional = this.getOptionalValueNamespace( name );
        if ( optional.isPresent() ) {
            return optional.get();
        } else {
            return null;
        }
    }

}
