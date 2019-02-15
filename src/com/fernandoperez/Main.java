package com.fernandoperez;

public class Main {

    public static void main(String[] args) {

        // Test directory instead of Drive letter
        String originalLocation = "C:\\users\\ferna\\Desktop\\Switch MicroSD";
        // Edit to add the Nintendo folder
        String nintendoLocation = originalLocation + "\\Nintendo\\Album";

        System.out.println(originalLocation); // Testing purpose display

        FileLister fileLister = new FileLister(nintendoLocation);
        fileLister.lister();
        fileLister.saveGameHash();
        fileLister.gameNamer();
        fileLister.printHashList();
    }

}
