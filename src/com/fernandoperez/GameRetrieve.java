package com.fernandoperez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class GameRetrieve {
    private String hashPath = "./HashListCSV.csv"; //This might change for an option. Still thinking.
    private Scanner fileScan;
    private File hashFile;
    public GameRetrieve() {

    }

    public Game gameNamer(String hash) {
        try {
            hashFile = new File(hashPath);
            fileScan = new Scanner(hashFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] tokens;
        List<String> entries;

        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            tokens = line.split(",");
            if(tokens[0].equals(hash)) {
                Game newGame = new Game(tokens[0], tokens[1]);
                return newGame;
            }
        }
        return null;
    }
}
