package org.fugerit.java.simple.config.microprofile.helper;

import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.spi.ConfigBuilder;
import org.eclipse.microprofile.config.spi.ConfigProviderResolver;

public class ConfigProviderResolverWrapper extends ConfigProviderResolver {

    private ConfigProviderResolver delegate;

    public ConfigProviderResolverWrapper( ConfigProviderResolver delegate ) {
        this.delegate = delegate;
    }

    @Override
    public ConfigBuilder getBuilder() {
        return delegate.getBuilder();
    }

    @Override
    public Config getConfig() {
        return delegate.getConfig();
    }

    @Override
    public Config getConfig(ClassLoader loader) {
        return delegate.getConfig(loader);
    }

    @Override
    public void registerConfig(Config config, ClassLoader classLoader) {
        delegate.registerConfig(config, classLoader);
    }

    @Override
    public void releaseConfig(Config config) {
        delegate.releaseConfig(config);
    }

}
