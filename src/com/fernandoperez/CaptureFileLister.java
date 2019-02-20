package com.fernandoperez;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class CaptureFileLister {
    private String path;
    private HashMap<String,String> savedGames;
    private GameRetrieve gameRetrieve;

    public CaptureFileLister(String path, HashMap<String,String> savedGames) {
        this.path = path;
        this.savedGames = savedGames;
        gameRetrieve = new GameRetrieve();
    }

    private boolean hashExists(String hash) {
        return savedGames.containsKey(hash);
    }

    private void captureLister(String path) {
        File directory = new File(path);
        File[] fList = directory.listFiles();
        String[] filenameSplit;
        String[] hashSplit;

        Scanner scanner = new Scanner(System.in);

        if(fList != null) {
            for(File file : fList) {
                if(file.isDirectory()) {
                    captureLister(file.getAbsolutePath());
                } else if (file.isFile()) {
                    filenameSplit = file.getName().split("-");
                    hashSplit = filenameSplit[1].split("\\.");

                    if(!hashExists(hashSplit[0])) {
                        System.out.println("Game not found. Please enter Game's title:");
                        String gameName = scanner.nextLine();
                        gameRetrieve.setNewGame(hashSplit[0],gameName);
                        savedGames.put(hashSplit[0],gameName);
                    }
                }
            }
        } else {
            System.out.println("No directory found");
        }
    }

    public void lister() {
        captureLister(this.path);
    }
}