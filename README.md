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
