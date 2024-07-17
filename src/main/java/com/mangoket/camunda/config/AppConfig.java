package com.mangoket.camunda;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static AppConfig instance = null;

    private Properties properties;

    private AppConfig() {
        try {
            InputStream input = AppConfig.class.getClassLoader().getResourceAsStream("application.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
