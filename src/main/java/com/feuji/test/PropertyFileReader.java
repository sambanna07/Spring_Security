package com.feuji.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class PropertyFileReader {

    private final ConfigurableEnvironment environment;

    @Autowired
    public PropertyFileReader(ConfigurableEnvironment environment) {
        this.environment = environment;
    }

    public Map<String, String> getAllProperties() {
        Map<String, String> propertiesMap = new HashMap<>();

        Properties properties = new Properties();
        environment.getPropertySources().forEach(propertySource -> {
            if (propertySource instanceof PropertiesPropertySource) {
                PropertiesPropertySource propertiesPropertySource = (PropertiesPropertySource) propertySource;
                properties.putAll(propertiesPropertySource.getSource());
            }
        });

        for (String propertyName : properties.stringPropertyNames()) {
            String propertyValue = properties.getProperty(propertyName);
            propertiesMap.put(propertyName, propertyValue);
        }

        return propertiesMap;
    }
}
