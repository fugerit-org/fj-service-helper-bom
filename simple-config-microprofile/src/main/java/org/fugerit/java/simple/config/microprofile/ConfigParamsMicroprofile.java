package org.fugerit.java.simple.config.microprofile;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.fugerit.java.simple.config.AbstractConfigParams;

import java.util.Optional;

public class ConfigParamsMicroprofile extends AbstractConfigParams {

    public ConfigParamsMicroprofile() {
        this(DEFAULT_NAMESPACE, ConfigProvider.getConfig());
    }

    public ConfigParamsMicroprofile(String namespace, Config config) {
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
        return this.config.getValue( name, String.class );
    }

}
