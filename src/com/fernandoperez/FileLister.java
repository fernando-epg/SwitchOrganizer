package com.fernandoperez;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class FileLister {
    private String path;
    private HashMap<String, String> hashMap;
    private GameRetrieve gameRetrieve;

    public FileLister(String path) {
        this.path = path;
        hashMap = new HashMap<String, String>();
        gameRetrieve = new GameRetrieve();
        gameRetrieveFill();
    }

    private void gameRetrieveFill() {
        hashMap = gameRetrieve.savedGameRetriever();
        System.out.println(hashMap);
    }

}