package com.fernandoperez;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        HashMap<String, String> storedGames = new HashMap<>();

        // Test directory instead of Drive letter
        String originalLocation = "C:\\users\\ferna\\Desktop\\Switch MicroSD";
        // Edit to add the Nintendo folder
        String nintendoLocation = originalLocation + "\\Nintendo\\Album";
        // Test directory to receive the folders
        String destinationLocation = "C:\\users\\ferna\\Desktop\\Switch-a-roo";

        System.out.println(originalLocation); // Testing purpose display

        FileLister fileLister = new FileLister(nintendoLocation);

    }

}
