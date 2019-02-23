
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
     * TODO Save configuration
     * TODO Ask target game dump
     * TODO Ask if move or copy
     */
    public static void main(String[] args) {

        HashMap<String, String> storedGames;

        Setup setup = new Setup();
        String originalLocation = "";
        while(originalLocation.equals("")) {
            originalLocation = setup.getOriginalDirectory();
        }

        String nintendoLocation = originalLocation + "\\Nintendo\\Album";



        // Test directory instead of Drive letter
//        String originalLocation = "C:\\users\\ferna\\Desktop\\Switch MicroSD";

        // Test directory to receive the folders
        String destinationLocation = "C:\\users\\ferna\\Desktop\\Switch-a-roo";

        GameRetrieve gameRetrieve = new GameRetrieve();
        gameRetrieve.savedGameRetriever();
        storedGames = gameRetrieve.getSavedGames();

        CaptureFileLister captureFileLister = new CaptureFileLister(nintendoLocation,storedGames);
        captureFileLister.lister();

        DiskWriter diskWriter = new DiskWriter(nintendoLocation,destinationLocation,captureFileLister,gameRetrieve);
        diskWriter.copier();
    }

}
