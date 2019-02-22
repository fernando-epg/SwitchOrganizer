package com.fernandoperez;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DiskWriter {
    /**
     * DiskWriter
     * Class to handle the disk writing operations
     *
     * path Path where the directories will be stored.
     *
     * TODO Option to delete original files
     */
    private String originPath;
    private String newPath;
    private CaptureFileLister captureFileLister;
    private GameRetrieve gameRetrieve;

    public DiskWriter(String originPath, String newPath, CaptureFileLister captureFileLister,
                      GameRetrieve gameRetrieve) {
        this.originPath = originPath;
        this.newPath = newPath;
        this.captureFileLister = captureFileLister;
        this.gameRetrieve = gameRetrieve;
    }

    /**
     * createFolder
     * Creates a folder with a Game's title name if it doesn't exist.
     *
     * @param gameTitle Game's title to create folder.
     */
    private void createFolder(String gameTitle) {
        String fullPath = newPath + "\\"+ gameTitle;

        File directory = new File(fullPath);

        if(!directory.exists()) {
            boolean result = directory.mkdir();
        }
    }

    /**
     * fileCopier
     * Copies the file to directory. Also calls the createFolder.
     *
     * @param path path to review
     */
    private void fileCopier(String path) {
        File directory = new File(path);
        File[] fList = directory.listFiles();
        String[] filenameSplit;
        String[] hashSplit;
        String origin;

        if(fList != null) {
            for(File file : fList) {
                if(file.isDirectory()) {
                    fileCopier(file.getAbsolutePath());
                } else if(file.isFile()) {
                    filenameSplit = file.getName().split("-");
                    hashSplit = filenameSplit[1].split("\\.");
                    origin = file.getAbsolutePath();
                    Path originPath = Paths.get(origin);

                    if(captureFileLister.hashExists(hashSplit[0])) {
                        String title = gameRetrieve.getTitle(hashSplit[0]);
                        createFolder(title);
                        Path targetDir = Paths.get(newPath + "\\" + title);
                        Path targetFile = targetDir.resolve(file.getName());

                        try {
                            Files.copy(originPath,targetFile);
                        } catch (FileAlreadyExistsException ex) {
                            System.err.format(file.getName() + " already exists\n");
                        } catch (IOException ex) {
                            System.err.format("I/O error while copying file");
                        }
                    }

                }
            }
        }
    }

    public void copier() {
        fileCopier(this.originPath);
    }
}
