package com.feuji.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PropertyLogger implements CommandLineRunner {

    private final PropertyFileReader propertyFileReader;

    @Autowired
    public PropertyLogger(PropertyFileReader propertyFileReader) {
        this.propertyFileReader = propertyFileReader;
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, String> propertiesMap = propertyFileReader.getAllProperties();

        // Print properties map in logs
        for (Map.Entry<String, String> entry : propertiesMap.entrySet()) {
            String propertyName = entry.getKey();
            String propertyValue = entry.getValue();
            System.out.println(propertyName + " = " + propertyValue);
        }
    }
}
