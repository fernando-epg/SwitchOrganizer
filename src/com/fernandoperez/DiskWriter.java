package com.fernandoperez;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DiskWriter {
    /**
     * DiskWriter
     * Class to handle the disk writing operations
     *
     * originPath path where the captures are originally stored.
     * newPath path where the captures will be stored.
     * captureFileLister object where the capture files have been identified.
     * gameRetrieve object that handles the games that have been historically identified.
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
        String fullPath = newPath + new Setup().osModifier() + gameTitle;

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
                        Path targetDir = Paths.get(newPath + new Setup().osModifier() + title);
                        Path targetFile = targetDir.resolve(file.getName());

                        if (!new File(targetFile.toString()).exists()) {
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
    }

    /**
     * copier()
     *
     * Public facing method for fileCopier().
     */
    public void copier() {
        fileCopier(this.originPath);
    }

    /**
     * streamCopier()
     *
     * Uses stream to copy files.
     *
     * @param path path to review
     */

    private void streamCopier(String path) {
        File directory = new File(path);
        File[] fList = directory.listFiles();
        ArrayList<String> fListPath = new ArrayList<>();
        String[] filenameSplit;
        String[] hashSplit;
        //String origin;

        // CHECK IF NO FILES ARE FOUND
        if(fList == null) {
            return;
        }

        // ADD THE PATH FOR THE FILE.
        for(File file : fList) {
            if(file.isDirectory()) {
                streamCopier(file.getAbsolutePath());
            } else if(file.isFile()) {
                fListPath.add(file.getAbsolutePath());
            }
        }

        // HANDLE THE FILE VIA PATH
        for(String paths : fListPath) {
            File file = new File(paths);
            filenameSplit = file.getName().split("-");
            hashSplit = filenameSplit[1].split("\\.");
            //Path originPath = Paths.get(path);


            String osModifier = new Setup().osModifier();
            if(captureFileLister.hashExists(hashSplit[0])) {
                String title = gameRetrieve.getTitle(hashSplit[0]);
                createFolder(title);
                Path targetDir = Paths.get(newPath + "/" + title);
                Path targetFile = targetDir.resolve(file.getName());

                if(!new File(targetFile.toString()).exists()) {
                    FileChannel sourceChannel = null;
                    FileChannel targetChannel = null;

                    try{
                        sourceChannel = new FileInputStream(new File(paths)).getChannel();
                        targetChannel = new FileOutputStream(new File(targetFile.toString())).getChannel();
                        targetChannel.transferFrom(sourceChannel,0,sourceChannel.size());
                    }

                    catch(FileAlreadyExistsException ex) {
                        System.err.format(file.getName() + " already exists");
                    }
                    catch(IOException ex) {
                        System.err.format("I/O error while copying file");
                    }
                    finally {
                        try {
                            sourceChannel.close();
                            targetChannel.close();
                        } catch (IOException ex) {
                            System.err.format("Unable to close stream");
                        } catch (NullPointerException ex) {
                            System.err.format("Unable to find channel to close");
                        }
                    }
                }
            }
        }
    }

    /**
     * streamCopy()
     *
     * Public facing method for streamCopier
     */
    public void streamCopy() {
        streamCopier(this.originPath);
    }
}
