
package com.fernandoperez;

import java.util.HashMap;

public class Main {
    /**
     * SWITCH CAPTURE ANALYZER
     *
     * Program intended to organize the captures done from the Nintendo Switch
     * to be organized in the computer based on Game title.
     *
     * TODO Language packages
     * TODO Save configuration
     * TODO Ask target game dump
     * TODO Ask if move or copy
     */
    public static void main(String[] args) {

        HashMap<String, String> storedGames;

        Setup setup = new Setup();
        setup.initialSetup();
        Config config = new Config();


        GameRetrieve gameRetrieve = new GameRetrieve(config.getProperty("savedGamesLocation"));
        gameRetrieve.savedGameRetriever();
        storedGames = gameRetrieve.getSavedGames();
        System.out.println("All games have been identified");

        CaptureFileLister captureFileLister = new CaptureFileLister(config.getProperty("nintendoLocation"),storedGames);
        captureFileLister.lister();
        System.out.println("All capture files have been listed");

        DiskWriter diskWriter = new DiskWriter(config.getProperty("nintendoLocation"),
                                               config.getProperty("destinationLocation"),
                                               captureFileLister,gameRetrieve);
        diskWriter.copier();
        System.out.println("All captures have been organized");
    }

}
