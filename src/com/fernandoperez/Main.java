
package com.fernandoperez;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /**
     * SWITCH CAPTURE ANALYZER
     *
     * Program intended to organize the captures done from the Nintendo Switch
     * to be organized in the computer based on Game title.
     *
     * TODO Language packages
     * TODO Ask if move or copy
     */
    public static void main(String[] args) {
        Setup setup = new Setup();
        setup.initialSetup();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while(option == 0) {
            menu();
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a correct value");
                option = 0;
            }

            switch(option) {
                case 1:
                    organize();
                    break;
                case 2:
                    changeSettings();
                    option = 0;
                    break;
                case 3:
                    break;
                default: {
                    System.out.println("Please enter a correct value");
                    option = 0;
                } break;
            }
        }
    }

    /**
     * organize()
     * Method that handles the hard work and calls the organizer.
     */
    private static void organize() {
        Setup setup = new Setup();
        HashMap<String, String> storedGames;
        GameRetrieve gameRetrieve = new GameRetrieve(setup.getProperty("savedGamesLocation"));
        gameRetrieve.savedGameRetriever();
        storedGames = gameRetrieve.getSavedGames();
        System.out.println("All games have been identified");

        CaptureFileLister captureFileLister = new CaptureFileLister(setup.getProperty("nintendoLocation"),storedGames);
        captureFileLister.lister();
        System.out.println("All capture files have been listed");

        /*DiskWriter diskWriter = new DiskWriter(setup.getProperty("nintendoLocation"),
                setup.getProperty("destinationLocation"),
                captureFileLister,gameRetrieve);*/
        //diskWriter.copier();
        //diskWriter.streamCopy();
        System.out.println("All captures have been organized");
    }

    /**
     * menu()
     * Presentation and show options.
     */
    private static void menu() {
        Setup setup = new Setup();
        System.out.println("SWITCH CAPTURE ORGANIZER");
        System.out.println("Organize the captures on your Switch's MicroSD or Directory based on game");
        System.out.println("Please select the option you wish to run:");
        System.out.println("\nCurrent source: " + setup.getProperty("originalLocation"));
        System.out.println("Current destination: " + setup.getProperty("destinationLocation"));
        if(setup.getProperty("deleteFiles").equals("true")) {
            System.out.println("\nWARNING! Files on source will be deleted");
        } else if (setup.getProperty("deleteFiles").equals("false")){
            System.out.println("\nFiles will be kept in the source directory.");
        }
        System.out.println("\n1 - Organize");
        System.out.println("2 - Change settings");
        System.out.println("3 - Quit");
    }


    private static void changeSettings() {
        int option = 0;
        Scanner scanner = new Scanner(System.in);
        Setup setup = new Setup();

        while (option == 0) {
            System.out.println("What setting would you wish to change?");
            System.out.println("1 - Source directory");
            System.out.println("2 - Destination directory");
            System.out.println("3 - Owned games directory and file name");
            System.out.println("4 - Source file deletion");
            System.out.println("5 - Get back to main menu");
            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a correct value\n");
                scanner.nextLine();
                option = 0;
            }
        }

        switch(option) {
            case 1: {
                setup.setOriginalDirectory();
            } break;

            case 2: {
                setup.setDestinationDirectory();
            } break;

            case 3: {
                setup.setSavedGamesDirectory();
            } break;

            case 4: {
                setup.setDeleteFiles();
            } break;

            case 5:
                break;

        }
    }
}
