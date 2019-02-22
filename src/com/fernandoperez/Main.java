
package com.fernandoperez;

import java.util.HashMap;

public class Main {
    /**
     * SWITCH CAPTURE ANALYZER
     *
     * Program intended to organize the captures done from the Nintendo Switch
     * to be organized in the computer based on Game title.
     *
     * TODO Get output directory
     * TODO Get original drive
     * TODO Language packages
     */
    public static void main(String[] args) {

        HashMap<String, String> storedGames;

        GameRetrieve gameRetrieve = new GameRetrieve();

        // Test directory instead of Drive letter
        String originalLocation = "C:\\users\\ferna\\Desktop\\Switch MicroSD";
        // Edit to add the Nintendo folder
        String nintendoLocation = originalLocation + "\\Nintendo\\Album";
        // Test directory to receive the folders
        String destinationLocation = "C:\\users\\ferna\\Desktop\\Switch-a-roo";

        System.out.println(originalLocation); // Testing purpose display

        gameRetrieve.savedGameRetriever();
        storedGames = gameRetrieve.getSavedGames();

        CaptureFileLister captureFileLister = new CaptureFileLister(nintendoLocation,storedGames);
        captureFileLister.lister();

        DiskWriter diskWriter = new DiskWriter(nintendoLocation,destinationLocation,captureFileLister,gameRetrieve);
        diskWriter.copier();

    }

}
