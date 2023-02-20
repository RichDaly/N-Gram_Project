# N-Gram_Project
College Assignment to create an N-Gram Frequency Builder with a simple command line interface. Collections Framework was not allowed for this project. 

Submitted August 2022 as first Java project after introductory module.

Menu Driven Features.
1. Specify Directory.
> Sets instance variable directory to user input and used in parseDirectory method. Parser Class.
> User directory is passed to validateDirectory method in Utils Class to check if exists.
If returns false directory will not be set.

2. Parsing Style.
> Changes behaviour to either parse text by line or individual words.
Sets instance variable parseByLine to input and changes parseFile method behaviour. Parser Class.

3. N-Gram Size.
> Set instance variable ngramSize to input. Used in createNgram(). NgramGenerator Class.

4. ASCII Range.
> Changes behaviour of replaceAll in parseFile method, Parser Class. Sets range and replacement character by changing instance
variables asciiRange and replaceChar.
> asciiRange format maintained by setAsciiRange method in Parser Class. Makes use of a StringBuilder.
> replaceChar can have only two values. Set by setAsciiRange in Menu Class.

5. Output File.
> Set instance variable ngramOutput to user input. Stored in NgramGenerator Class.

6. Build N-Grams.
> Calls multiple methods parseDirectory --> parseFile --> createNgram --> addNgram.
> Outputs N-Grams by calling saveToFile from Utils Class.

7. Quit.
> Closes Application by setting keepRunning variable to false, ending while loop in start method Menu Class.
