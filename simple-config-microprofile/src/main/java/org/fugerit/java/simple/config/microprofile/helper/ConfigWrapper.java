package org.fugerit.java.simple.config.microprofile.helper;

import lombok.ToString;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.List;
import java.util.Optional;

@ToString
public class ConfigWrapper implements Config {

    private Config delegate;

    public ConfigWrapper(Config delegate) {
        this.delegate = delegate;
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return delegate.getConfigSources();
    }

    @Override
    public ConfigValue getConfigValue(String propertyName) {
        return delegate.getConfigValue(propertyName);
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
        return delegate.getConverter(forType);
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        return delegate.getOptionalValue(propertyName, propertyType);
    }

    @Override
    public <T> Optional<List<T>> getOptionalValues(String propertyName, Class<T> propertyType) {
        return delegate.getOptionalValues(propertyName, propertyType);
    }

    @Override
    public Iterable<String> getPropertyNames() {
        return delegate.getPropertyNames();
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        return delegate.getValue(propertyName, propertyType);
    }

    @Override
    public <T> List<T> getValues(String propertyName, Class<T> propertyType) {
        return delegate.getValues(propertyName, propertyType);
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return delegate.unwrap(type);
    }

}
