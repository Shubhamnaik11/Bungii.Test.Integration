package com.bungii.common.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class PropertyUtility {
    private static final String IMAGE_PROPERTY_FILE = "/UserProperties/images.properties";
    private static final String FILE_LOCATION_PROPERTY_FILE = "/UserProperties/resourcesFilePaths.properties";
    private static final String CONFIG_PROPERY_FILE = "/UserProperties/config.properties";
    private static final String DATA_PROPERTY_FILE = "/UserProperties/data.properties";
    private static final String RESULT_CONFIG_PROPERTY_FILE = "/SystemProperties/resultConfig.properties";
    private static final String JDBC_CONFIG_PROPERTY_FILE = "/SystemProperties/jdbcConfig.properties";
    private static Properties properties;
    private static Properties fileLocations;
    private static Properties images;
    private static Properties data;
    private static Properties resultConfig;
    private static Properties jdbcConfig;

    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return properties.getProperty(key);

        }
    }


    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getFileLocations(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return fileLocations.getProperty(key);

        }
    }

    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getDataProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return data.getProperty(key);

        }
    }

    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getResultConfigProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return resultConfig.getProperty(key);

        }
    }


    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getJdbcConfigProperties(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return jdbcConfig.getProperty(key);

        }
    }
    /**
     * Gets the key from images.properties related to chosen profile
     *
     * @param key
     **/

    public static String getImageLocations(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return images.getProperty(key);

        }
    }

    /**
     * Gets the key from messages.properties for a framework
     *
     * @param key
     **/
    public static String getMessage(String key) {

        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            return ResourceBundle.getBundle("UserProperties/messages").getString(key);

        }
    }


    public static void loadRunConfigProps() {

        properties = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(CONFIG_PROPERY_FILE)) {
            properties.load(inputStream);
            properties.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }

        fileLocations = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(FILE_LOCATION_PROPERTY_FILE)) {
            fileLocations.load(inputStream);
            fileLocations.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }

        data = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(DATA_PROPERTY_FILE)) {
            data.load(inputStream);
            data.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        resultConfig = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(RESULT_CONFIG_PROPERTY_FILE)) {
            resultConfig.load(inputStream);
            resultConfig.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        jdbcConfig = new Properties();
        try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(JDBC_CONFIG_PROPERTY_FILE)) {
            jdbcConfig.load(inputStream);
            jdbcConfig.list(System.out);
        } catch (IOException e) {
            System.err.println(e);
        }
        try {
            images = new Properties();
            try (InputStream inputStream = PropertyUtility.class.getResourceAsStream(IMAGE_PROPERTY_FILE)) {
                images.load(inputStream);
                images.list(System.out);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
