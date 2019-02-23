package com.fernandoperez;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private Properties configFile;
    private InputStream inputStream;
    public Config() {
        configFile = new java.util.Properties();


        try {
            inputStream = new FileInputStream(new File("./configuration.cfg"));
            configFile.load(inputStream);
        } catch(Exception eta) {
            eta.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }

}
