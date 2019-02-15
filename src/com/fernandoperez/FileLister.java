package com.fernandoperez;

import java.io.File;
import java.util.ArrayList;

public class FileLister {
    private String path;
    private ArrayList<String> hashList;

    public FileLister(String path) {
        this.path = path;
        this.hashList = new ArrayList<>();
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
                } else {
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

    public void printGameList() {
        if(hashList != null) {
            for(String hash : hashList) {
                System.out.println(hash);
            }
        } else {
            System.out.println("hashList is empty");
        }
    }
}




