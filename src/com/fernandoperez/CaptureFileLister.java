
package com.fernandoperez;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

public class CaptureFileLister {
    /**
     * CaptureFileLister
     * Class to handle the files on the Capture directory.
     *
     * path Directory path where the captures begin.
     * savedGames Hash Map where the saved games from a file is stored.
     * gameRetrieve Object to save new games for external file
     *
     * TODO Option to delete original files
     *
     */
    private String path;
    private HashMap<String,String> savedGames;
    private GameRetrieve gameRetrieve;
    private Config config = new Config();

    public CaptureFileLister(String path, HashMap<String,String> savedGames) {
        this.path = path;
        this.savedGames = savedGames;
        gameRetrieve = new GameRetrieve(config.getProperty("savedGamesLocation"));
    }

    /**
     * hashExists
     * Confirm if the hash code exists already in the saved games database.
     * @param hash hash code of the game
     * @return boolean if the hash code exists.
     */
    public boolean hashExists(String hash) {
        return savedGames.containsKey(hash);
    }

    /**
     * captureLister
     * List the captured files' name on the provided path. This is done
     * recursively.
     * @param path path to list the files for.
     */
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
                        System.out.println("Reference: " + file.getAbsolutePath());
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

    /**
     * lister
     * Public facing method for access to captureLister
     */
    public void lister() {
        captureLister(this.path);
    }
}