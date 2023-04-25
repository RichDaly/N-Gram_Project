# N-Gram_Project
College Assignment to create an N-Gram Frequency Builder with a simple command line interface. Collections Framework was not allowed for this project. Submitted August 2022 as first Java project after introductory module.

## Project Requirements
To develop a menu-driven Java application that can:

1. Parse all the text files in a directory.
2. Process the text into a mapping of n-grams and their frequencies using arrays.
3. Output the map to a text file in CSV format.
4. Use the package name *ie.atu.sw* and place the main method in a class called *Runner*.
5. Provide a simple command-line user interface that enables a user to specify the following:
- A path and name for the directory of text files to process.
- The size of the n-grams to use. A value in the range 1 to 5 is appropriate.
- Optional additional features.
6. Build a frequency table for each unique lower-case n-gram in the text files and strip out any unwanted characters and whitespace.
7. Each method in your application must include comments.

### Received Result: 93/100

## Running the application

1. Download **src** directory.
2. Compile all Java files with the following command within src directory. `javac ie/atu/sw/*.java`
3. Run the application with the following command. `java ie.atu.sw.Runner`
4. Using the Menu outlined below ensuring that Specify Directory and Output File (Giving a path for each location) are competed before using Build N-Grams.

## Menu Driven Features Available

1. **Specify Directory.**
Sets instance variable directory to user input and used in parseDirectory method. Parser Class. User directory is passed to validateDirectory method in Utils Class to check if exists. If returns false directory will not be set.

2. **Parsing Style.**
Changes behaviour to either parse text by line or individual words. Sets instance variable parseByLine to input and changes parseFile method behaviour. Parser Class.

3. **N-Gram Size.**
Set instance variable ngramSize to input. Used in createNgram(). NgramGenerator Class.

4. **ASCII Range.**
Changes behaviour of replaceAll in parseFile method, Parser Class. Sets range and replacement character by changing instance
variables asciiRange and replaceChar. asciiRange format maintained by setAsciiRange method in Parser Class. Makes use of a StringBuilder. replaceChar can have only two values. Set by setAsciiRange in Menu Class.

5. **Output File.**
Set instance variable ngramOutput to user input. Stored in NgramGenerator Class.

6. **Build N-Grams.**
Calls multiple methods parseDirectory --> parseFile --> createNgram --> addNgram.
Outputs N-Grams by calling saveToFile from Utils Class.

7. **Quit.**
Closes Application by setting keepRunning variable to false, ending while loop in start method Menu Class.
