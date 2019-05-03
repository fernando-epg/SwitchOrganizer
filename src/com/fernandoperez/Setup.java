package com.fernandoperez;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * Setup
 * Class to handle the setup and ultimately the configurations for the project.
 *
 * @author jumbonionga@gmail.com
 */
public class Setup {

    private Scanner scanner;
    private static Properties prop;

    public Setup() {
        scanner = new Scanner(System.in);
        prop = new Properties();
        if ((new File("./configuration.cfg")).exists()) {
            try {
                InputStream inputStream = new FileInputStream(new File("./configuration.cfg"));
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * setOriginalDirectory
     * Retrieves the original directory where the Nintendo folder is located.
     */
    public void setOriginalDirectory() {
        String originalDirectory;
        File originalDestination = null;
        String os = getProperty("OS");

        System.out.println("Please enter the original source of the Nintendo folder.");
        System.out.println("For drives, please follow this example: \"C:\\\" (without \")");
        originalDirectory = scanner.nextLine();
        originalDestination = new File(originalDirectory + System.getProperty("file.separator") + "Nintendo");

        if (originalDestination.exists()) {
            setProperty("originalLocation", originalDirectory);
        } else {
            System.out.println("Nintendo folder could't be found. Please check");
        }
    }

    /**
     * setDestinationDirectory
     * Set the destination folder, or create it if possible, if it doesn't exist.
     */
    public void setDestinationDirectory() {
        String destinationDirectory;
        File dumpDestination;

        System.out.println("Please enter the destination folder where the files will be organized");
        destinationDirectory = scanner.nextLine();
        dumpDestination = new File(destinationDirectory);
        if (dumpDestination.exists()) {
            setProperty("destinationLocation", destinationDirectory);
        } else {
            boolean createdDirectory = dumpDestination.mkdir();
            if (createdDirectory) {
                System.out.println("Directory not found. It was created");
            } else {
                System.out.println("Unable to find nor create directory. Please check");
            }
        }
    }

    /**
     * setSavedGamesDirectory
     * Set directory and filename for owned games
     */
    public void setSavedGamesDirectory() {
        String savedGamesDirectory;
        File savedGamesDestination;

        System.out.println("Please enter the destination to save your owned games");
        String ownedGamesDirectory = scanner.nextLine();
        System.out.println("How would you like to name the file?");
        String ownedGamesFileName = scanner.nextLine();
        savedGamesDirectory = ownedGamesDirectory + System.getProperty("file.separator") + ownedGamesFileName + ".csv";
        savedGamesDestination = new File(savedGamesDirectory);
        if (savedGamesDestination.exists()) {
            setProperty("savedGamesLocation", savedGamesDirectory);
        } else {
            try {
                boolean createFile = savedGamesDestination.createNewFile();
                if (createFile) {
                    System.out.println("File didn't exist, but it was created.");
                    setProperty("savedGamesLocation", savedGamesDirectory);
                } else {
                    System.out.println("File couldn't be found nor created");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * initialSetup()
     * Property initializer for future references.
     */
    public void initialSetup() {
        String configLocation = "./configuration.cfg";
        File configFile = new File(configLocation);

        while (!fullProperties()) {
            String os = null;

            if (getProperty("originalLocation") == null) {
                setOriginalDirectory();
            }

            if (getProperty("nintendoLocation") == null) {
                String originalLocation = getProperty("originalLocation");
                String nintendoLocation = originalLocation + System.getProperty("file.separator") + "Nintendo";
                setProperty("nintendoLocation", nintendoLocation);
            }

            if (getProperty("destinationLocation") == null) {
                setDestinationDirectory();
            }

            if (getProperty("savedGamesLocation") == null) {
                setSavedGamesDirectory();
            }
        }
    }

    /**
     * setProperty
     * <p>
     * Save properties for future references. Values saved in configuration.cfg
     *
     * @param property name of the property to save to
     * @param value    value of the property
     */
    private void setProperty(String property, String value) {
        prop.setProperty(property, value);
        try {
            prop.store(new FileOutputStream("./configuration.cfg"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getProperty
     * <p>
     * Get the requested property's value
     *
     * @param property
     * @return value
     */

    public static String getProperty(String property) {

        return prop.getProperty(property);
    }


    private boolean fullProperties() {
        return getProperty("nintendoLocation") != null &&
                getProperty("originalLocation") != null && getProperty("destinationLocation") != null &&
                getProperty("savedGamesLocation") != null;
    }
}
