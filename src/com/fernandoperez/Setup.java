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
@SuppressWarnings("LoopStatementThatDoesntLoop")
public class Setup {

    private Scanner scanner;
    public Setup() {
        scanner = new Scanner(System.in);
    }

    /**
     * getOriginalDirectory
     * Retrieves the original directory where the Nintendo folder is located.
     *
     * @return Drive or folder where Nintendo folder should be
     */
    public String getOriginalDirectory() {
        String originalDirectory;
        File originalDestination;

        do {
            System.out.println("Please enter the original source of the Nintendo folder.");
            System.out.println("For drives, please follow this example: \"C:\\\" (without \")");
            originalDirectory = scanner.nextLine();
            originalDestination = new File(originalDirectory + "\\Nintendo");
            if(originalDestination.exists()) {
                return originalDirectory;
            } else {
                System.out.println("Nintendo folder could't be found. Please check");
                return "";
            }
        } while (originalDestination == null);
    }

    /**
     * getDestinationDirectory
     * Retrieve the destination folder, or create it if possible, if it doesn't exist.
     *
     * @return Drive or folder where the dump will be performed.
     */
    public String getDestinationDirectory() {
        String destinationDirectory;
        File dumpDestination;

        do {
            System.out.println("Please enter the destination folder where the files will be organized");
            destinationDirectory = scanner.nextLine();
            dumpDestination = new File(destinationDirectory);
            if(dumpDestination.exists()) {
                return destinationDirectory;
            } else {
                boolean createdDirectory = dumpDestination.mkdir();
                if (createdDirectory) {
                    System.out.println("Directory not found. It was created");
                    return destinationDirectory;
                } else {
                    System.out.println("Unable to find nor create directory. Please check");
                }
                return "";
            }
        } while(dumpDestination == null);
    }

    public String getSavedGamesDirectory() {
        String savedGamesDirectory;
        File savedGamesDestination;

        do {
            System.out.println("Please enter the destination to save your owned games");
            String ownedGamesDirectory = scanner.nextLine();
            System.out.println("How would you like to name the file?");
            String ownedGamesFileName = scanner.nextLine();
            savedGamesDirectory = ownedGamesDirectory + "\\" + ownedGamesFileName + ".csv";
            savedGamesDestination = new File(savedGamesDirectory);
            if(savedGamesDestination.exists()) {
                return savedGamesDirectory;
            } else {
                try {
                    boolean createFile = savedGamesDestination.createNewFile();
                    if(createFile) {
                        System.out.println("File didn't exist, but it was created.");
                        return savedGamesDirectory;
                    } else {
                        System.out.println("File couldn't be found nor created");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return "";
            }
        } while(savedGamesDestination == null);
    }

    public void initialSetup () {
        String configLocation = "./configuration.cfg";
        File configFile = new File(configLocation);

        if(!configFile.exists() || configFile.length() == 0) {
            // PROPERTIES TO SAVE
            String originalLocation = "";
            String nintendoLocation = "";
            String destinationLocation = "";
            String savedGamesLocation = "";

            Properties prop = new Properties();

            while(originalLocation.equals("")) {
                originalLocation = getOriginalDirectory();
            }

            nintendoLocation = originalLocation + "\\Nintendo\\Album";

            while(destinationLocation.equals("")) {
                destinationLocation = getDestinationDirectory();
            }

            while(savedGamesLocation.equals("")) {
                savedGamesLocation = getSavedGamesDirectory();
            }

            prop.setProperty("originalLocation",originalLocation);
            prop.setProperty("nintendoLocation",nintendoLocation);
            prop.setProperty("destinationLocation",destinationLocation);
            prop.setProperty("savedGamesLocation",savedGamesLocation);

            try {
                prop.store(new FileOutputStream(configLocation),null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
