package org.rikardoricz.wolfsheep;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    private static final String PROPERTIES_FILE = "config.properties";
    private Properties props = null;

    public PropertyManager() {
        loadProperties(PROPERTIES_FILE);
    }

    private void loadProperties(String filename) {
        props = new Properties();
        InputStream instream = null;
        ClassLoader loader = this.getClass().getClassLoader();
        instream = loader.getResourceAsStream(filename);
        if (instream == null) {
            System.err.println("Unable to open properties file " + filename);
            System.out.println(loader);
            return;
        }

        // load all properties
        try {
            props.load(instream);
        } catch (IOException e) {
            System.err.println("Error reading properties file " + filename);
            System.err.println(e.getMessage());
        }

        // close input stream to free recources
        try {
            instream.close();
        } catch (IOException ioe) {
            // should hot happen
            ioe.printStackTrace();
        }
    }

    public int getIntProperty (String name) {
        return Integer.parseInt(props.getProperty(name));
    }
    public Double getDoubleProperty (String name) {
        return Double.valueOf(props.getProperty(name));
    }
}
