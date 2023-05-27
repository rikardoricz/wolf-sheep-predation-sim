package org.rikardoricz.wolfsheep;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
        String value = props.getProperty(name);
        if (value == null)
            return 0;

        try {
            int intValue = Integer.parseInt(value);
            if (intValue < 1) {
                throw new IllegalArgumentException("The value of the property " + name + " is not positive.");
            }
            return intValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The value of the property " + name + " is not a valid integer.");
        }
    }
    public Double getDoubleProperty (String name) {
        String value = props.getProperty(name);
        if (value == null) {
            return 0.0;
        }

        try {
            double doubleValue = Double.parseDouble(value);
            if (doubleValue < 0 || doubleValue > 1) {
                throw new IllegalArgumentException("The value of the property " + name + " is not correct probability value.");
            }
            return doubleValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("The value of the property " + name + " is not a valid double.");
        }
    }

    public void validateProperties() {
        String[] integerProperties = {
                "board.width",
                "board.height",
                "simulation.duration.ticks",
                "initial.sheep.amount",
                "initial.wolf.amount",
                "sheep.grass.energy",
                "wolf.sheep.energy",
                "grass.regrowth.ticks"
        };
        Set<String> propertyNames = new HashSet<>(Arrays.asList(integerProperties));

        // Check if all the required properties exist
        for (String propertyName : propertyNames) {
            if (!props.containsKey(propertyName)) {
                System.out.println("The property " + propertyName + " does not exist in the configuration file.");
                System.exit(1);
            }
        }
    }
}
