package com.fernandoperez;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        // Test directory instead of Drive letter
        String originalLocation = "C:\\users\\ferna\\Desktop\\Switch MicroSD";
        // Edit to add the Nintendo folder
        String nintendoLocation = originalLocation + "\\Nintendo\\Album";

        System.out.println(originalLocation); // Testing purpose display

        fileLister(nintendoLocation);
    }

    private static void fileLister(String path) {
        File directory = new File(path);
        File[] fList = directory.listFiles();
        if (fList != null) {

            for (File file : fList) {
                if (file.isDirectory()) {
                    fileLister(file.getAbsolutePath());
                } else {
                    System.out.println(file.getAbsolutePath());
                    //System.out.println(file.getName());
                }
            }
        } else {
            System.out.println("No directory found");
        }
    }
}
