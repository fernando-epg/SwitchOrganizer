
package com.fernandoperez;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class GameRetrieve {
    /**
     * GameRetrieve
     * Class to handle file where games are being stored.
     *
     * hashPath Directory where the file's stored. Includes the file's name.
     * hashFile File object to handle the stored file.
     * fileScan Scanner to read the file.
     * savedGames Hash Map to store the hash code and the game's title.
     *
     * TODO File creation
     */
    private String hashPath = "./HashListCSV.csv"; //This might change for an option. Still thinking.
    private File hashFile;
    private Scanner fileScan;
    private HashMap<String, String> savedGames;

    public GameRetrieve() {
        savedGames = new HashMap<>();
    }

    /**
     * savedGameRetriever
     * Reads the CSV file with the hash codes and the games
     * CSV Data format: [hash_code],[game_title]
     * @return savedGames
     */
    public HashMap<String,String> savedGameRetriever() {
        try {
            hashFile = new File(hashPath);
            fileScan = new Scanner(hashFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] tokens;

        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            tokens = line.split(",");
            savedGames.put(tokens[0],tokens[1]);
        }
        fileScan.close();
        return savedGames;
    }

    /**
     * getSavedGames
     * Public facing return current savedGames
     * @return savedGames
     */
    public HashMap<String, String> getSavedGames() {
        return savedGames;
    }

    /**
     * setNewGame
     * Saves a new game in the CSV.
     * @param hash Hash code of the game to save.
     * @param name Game title to save.
     */
    public void setNewGame(String hash, String name) {
        BufferedWriter writer;
        File file;
        try {
            file = new File(hashPath);
            writer = new BufferedWriter(new FileWriter(file,true));

            writer.newLine();
            writer.write(hash + "," + name);

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getTitle
     * Returns the game title for the provided hash code.
     * @param hash Hash code of the game to retrieve.
     * @return Game title as per savedGames.get()
     */
    public String getTitle(String hash) {
        return savedGames.get(hash);
    }

    public boolean hashExists(String hash) {
        return savedGames.containsKey(hash);
    }
}
