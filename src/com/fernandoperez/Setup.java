package com.fernandoperez;

import java.io.File;
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
                    System.out.println("Unable to create directory. Please check");
                }
                return "";
            }
        } while(dumpDestination == null);
    }
}
