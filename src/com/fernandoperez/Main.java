package com.fernandoperez;

import java.util.HashMap;

public class Main {

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

        FileLister fileLister = new FileLister(nintendoLocation,storedGames);

        boolean booltest = fileLister.hashExists("22A4BDEA5363AAA24F931D5AF2926082");
        System.out.println(booltest);


    }

}
