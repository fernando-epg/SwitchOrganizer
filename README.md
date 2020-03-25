# Switch capture organizer
This is a Java implementation to organize the files that result from the Nintendo Switch.

## How it works
This works on a 3 step basis.
1. Identify the games that have been captured, and compare them to an already saved collection.
2. Create a folder for each of these games.
3. Copy each of the files that are found.

## Why do this?
Nintendo Switch would organize each of the capture files in the following structure:

\[Year folder\] -> \[Month folder\] -> \[Day folder\] -> \[File\]

So you'll get a lot of folders. If you would like to find that particular capture, it would be difficult.

This will organize all the captures based on the game, leaving the file name as it is. The filename already has the date in the format YYYYMMDDHHMMSS00 at the start of the filename.

## How to download it?
You can either download the source code, or go to the Releases section.

## How to use it
If it's the first time you run the program, it will begin by asking the settings for the operation. First, it will ask for the source of the files (if it's on an SD card, then use the SD card's drive letter). If you just copy and paste the Album folder, then you will need to save that on a folder named **Nintendo** (Sorry!).

Next, it will ask for the destination where the new folders (based on the game's title) will be saved.

Next, for your own purposes, you will be asked where you would like to save your database. After entering the location, you will be asked for the name of the file to save the games. DO NOT include the extension.

Last, it will ask if you would like to delete the original files. BE CAREFUL WITH THIS. The developer isn't responsible if you lose any information, so be **VERY** careful with the option.

This will happen only once.

You will then be shown the options. 3 simple options:
1. Organize - performs all the options.
2. Change settings - offers you the option to change settings.
3. Quit

On option 2 you will be prompted to chose the setting you will like to change, or just get back to the menu.

Once you select option 1, and press ENTER, the operations will begin. These are:
* If a non-recognized game is found, it will show the first file related to it. It could be either a screenshot or a video capture. The program will stop until you enter the game's name. This will be the same name that will be used for the folders. This "ideally" should happen only once, but there will be some games (e.g. Super Smash Bros Ultimate) that might need to be entered more than once.
* The file will be copied. No change on the file's name will be performed.
* If the option to delete the file is active, the original file will be deleted.
* Rinse repeate for all files.
* Once every file is organized, the program will exit automatically.

If you have any feedback, please drop it here: https://docs.google.com/forms/d/e/1FAIpQLSeoHSnvGka20B_ivfdU2ZaifzKv49bHeGY7u3FxnvAcPhSrng/viewform.

Ideally, graphic interface will come in the future.
