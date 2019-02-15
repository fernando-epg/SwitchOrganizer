package com.fernandoperez;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameRetrieve {
    private String hashPath = "../HashListCSV.csv"; //This might change for an option. Still thinking.
    private Scanner fileScan;
    private File hashFile;
    public GameRetrieve() {
        try {
            hashFile = new File(hashPath);
            fileScan = new Scanner(hashFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Game gameNamer(Game game) {
        String[] tokens;

        while(fileScan.hasNextLine()) {
            String line = fileScan.nextLine();
            tokens = line.split(";|,");
        }

        return game;
    }
}
