package com.fernandoperez;

import java.io.File;
import java.util.ArrayList;

public class FileLister {
    private String path;
    private ArrayList<String> hashList;
    private ArrayList<Game> gameList;

    public FileLister(String path) {
        this.path = path;
        this.hashList = new ArrayList<>();
        this.gameList = new ArrayList<>();
    }

    public void lister() {
        lister(this.path);
    }

    private void lister(String path) {
        File directory = new File(path);
        File[] fList = directory.listFiles();
        String[] filenameSplit;
        String[] hashSplit;
        if (fList != null) {
            for (File file : fList) {
                if (file.isDirectory()) {
                    lister(file.getAbsolutePath());
                } else if (file.isFile()){
                    filenameSplit = file.getName().split("-");
                    hashSplit = filenameSplit[1].split("\\.");

                    if(!hashList.contains(hashSplit[0])) {
                        hashList.add(hashSplit[0]);
                    }
//                    System.out.println(filenameSplit[0] + " - " + filenameSplit[1]);
                    //System.out.println(file.getAbsolutePath());
                }
            }
        } else {
            System.out.println("No directory found");
        }
    }

    public void printHashList() {
        if(hashList != null) {
            for(String hash : hashList) {
                System.out.println(hash);
            }
        } else {
            System.out.println("hashList is empty");
        }
    }
    public void saveGameHash() {
        for(String hash:hashList) {
            gameList.add(new Game(hash));
        }
    }
    public void gameNamer() {
        GameRetrieve gameRetrieve = new GameRetrieve();
        for(Game game : gameList) {
            gameRetrieve.gameNamer(game);
        }
    }
}