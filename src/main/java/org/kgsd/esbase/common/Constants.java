package org.kgsd.esbase.common;

import lombok.extern.log4j.Log4j2;
import ro.pippo.core.PippoRuntimeException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class Constants {

    public static final Properties properties = getProperties();
    // App Info
    public static final Integer APP_PORT = Integer.parseInt(properties.getProperty("esbase.port",
            "9090"));

    public static final String INDEX = "index";
    public static final String EXISTING_COLLECTIONS = "collections";

    // Solr Info
    public static final String SOLR_URL = properties.getProperty("solr.base.url",
            "http://localhost:8983/solr") ;
    public static final Integer SHARD_COUNT = Integer.parseInt(properties.getProperty("solr.shard.count",
            "1"));
    public static final Integer REPLICA_COUNT = Integer.parseInt(properties.getProperty("solr.replica.count",
            "2"));


    private static Properties getProperties() {
        String configFile = System.getProperty("configPath");
        Properties properties = new Properties();
        try (InputStream inputStream = configFile != null ? new FileInputStream(configFile):
                Constants.class.getClassLoader().getResourceAsStream("config.properties")) {
            assert inputStream != null;
            properties.load(inputStream);
        } catch (IOException e) {
            log.error("Loading properties file failed ", e);
            throw new PippoRuntimeException(e);
        }
        Properties systemProperties = System.getProperties();
        for (String key : systemProperties.stringPropertyNames()) {
            String value = systemProperties.getProperty(key);
            properties.setProperty(key, value);
        }
        log.info("Final properties: " +  properties);
        return properties;
    }
}
