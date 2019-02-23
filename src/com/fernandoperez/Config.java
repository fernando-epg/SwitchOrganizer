package com.fernandoperez;

import java.io.*;
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

    public void setProperty(String property, String value) {
        configFile.setProperty(property,value);
        try {
            configFile.store(new FileOutputStream("./configuration.cfg"),null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
