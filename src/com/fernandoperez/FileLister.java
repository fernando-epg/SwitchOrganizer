package com.fernandoperez;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileLister {
    private String path;
    private HashMap<String, String> hashMap;
    private HashMap<String,String> savedGames;
    private GameRetrieve gameRetrieve;

    public FileLister(String path, HashMap<String,String> savedGames) {
        this.path = path;
        this.savedGames = savedGames;
        hashMap = new HashMap<>();
        gameRetrieve = new GameRetrieve();
    }

    private void gameRetrieveFill() {
        hashMap = gameRetrieve.savedGameRetriever();
    }

    public boolean hashExists(String hash) {
        return savedGames.containsKey(hash);
    }
}