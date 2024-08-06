package com.mangoket.camunda;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigValue;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class CamundaAppConfiguration {

    private final Config config;

    public CamundaAppConfiguration() {
        this(ConfigFactory.load("config/defaults.conf"));
    }

    private CamundaAppConfiguration(@Nonnull Config config) {
        this.config = config;
    }

    @Nullable
    public String getString(@Nonnull String key) {
        return getStringOrDefault(key, null);
    }

    public String getStringOrDefault(@Nonnull String key, String defaultValue) {
        return config.hasPath(key) ? config.getString(key) : defaultValue;
    }

    public List<String> getStringList(@Nonnull String key) {
        return getStringListOrDefault(key, new ArrayList<>());
    }

    public List<String> getStringListOrDefault(@Nonnull String key, List<String> defaultValue) {
        return config.hasPath(key) ? config.getStringList(key) : defaultValue;
    }

    public int getInt(@Nonnull String key) {
        return config.getInt(key);
    }

    public int getIntOrDefault(@Nonnull String key, int defaultValue) {
        return config.hasPath(key) ? config.getInt(key) : defaultValue;
    }

    public boolean getBoolean(@Nonnull String key) {
        return config.getBoolean(key);
    }

    public boolean getBooleanOrDefault(@Nonnull String key, boolean defaultValue) {
        return config.hasPath(key) ? config.getBoolean(key) : defaultValue;
    }

    public CamundaAppConfiguration getConfig(@Nonnull String key) {
        return new CamundaAppConfiguration(config.getConfig(key));
    }

    public Map<String, ConfigValue> getConfigMap(@Nonnull String key) {
        return config.getConfig(key)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }

    public Map<String, String> getStringMap(@Nonnull String key) {
        return config.getConfig(key)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> config.getString(key + "." + entry.getKey())
                ));
    }
}