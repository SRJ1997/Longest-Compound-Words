This program will read a file containing a sorted list of words (one word per line, no spaces, all lower case). Then it identifies the longest word in the file that can be constructed by concatenating copies of shorter words also found in the file.
For example, if the file contained the following data:

cat
cats
catsdogcats
catxdogcatsrat
dog
dogcatsdog
hippopotamuses
rat
ratcatdogcat

It will output "ratcatdogcat" - at 12 letters, it is the longest word made up of other words in the list.

The program will then go on to report how many of the words in the list can be constructed from words in the list.

