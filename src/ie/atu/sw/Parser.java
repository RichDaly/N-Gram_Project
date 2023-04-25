package ie.atu.sw;

import java.io.*;

/**
 * Handles the Parsing of the directory and associated text files. Each line then passed on to be seperated into
 * N-Grams of a set size. Class has been left as submitted to the lecturer for grading. Surplus of comments is
 * due to this, as actions needed to be explained.
 *
 * @author Richard Daly
 */
public class Parser {
	/*
	 * Instance variables change the behavior of how Text Files are parsed. Default
	 * values are set in order for application to run correctly if certain options
	 * are not selected by user. All can be changed by user from Menu.
	 */
	private NgramGenerator ngram; // Instance Variable for NgramGenerator so that can use throughout the class.
	private String asciiRange = "[^a-zA-Z]"; // Default ASCII range unless specified by user.
	private String replaceChar = ""; // Remove unwanted characters by default unless specified by user.
	private boolean parseByLine = false; // Parse by word default unless specified by user.
	private String directory; // Directory path to be input by user.

	/*
	 * Using this constructor allows parser to work on same instance of
	 * NgramGenerator initialized in Menu Class.
	 */
	public Parser(NgramGenerator ngram) {
		this.ngram = ngram;
	}

	/*
	 * Begins the process of parsing text directory specified by user. Method also
	 * contains progress bar provided, increments bar each time a file in the
	 * directory has been parsed and remainder finished at the end of the method.
	 * Encountered an error where files in directory got listed successfully but
	 * when passed to parseFile could not be found. When calling parseFile(), I
	 * concatenated directory to file name to complete the path.
	 */
	public void parseDirectory() throws Exception {
		File f = new File(directory); // create New File Input from user input.

		int size = 100; // Print Progress Bar size.
		int progress = 0; // Print Progress starting value.

		String[] files = f.list(); // String array for all files in directory.

		for (String file : files) { // Do the following for each file in directory.

			Utils.printProgress(progress, size); // Display progress bar.
			parseFile(directory + "/" + file);
			progress++; // For each file parsed increment progress bar.
		}

		// For loop to complete progress bar once all files are parsed.
		for (int i = progress; i < size; i++) {
			Utils.printProgress(i + 1, size);
			Thread.sleep(10); // Slow the animation so user can see it operate.
		}

	}

	/*
	 * Files in directory are passed to this method. Uses a BufferedReader on the
	 * InputStream. Reads each line in the file. Behavior can be changed by user.
	 * Depending on style chosen by user it passes the entire line into createNgram
	 * or the individual words. Default setting is by word if not specified. The two
	 * instance variables asciiRange and replaceChar which can also be set by the
	 * user. Default is to to use the range a-z and remove unwanted characters.
	 */
	private void parseFile(String file) throws Exception {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file))))) {
			String line = null;

			while ((line = br.readLine()) != null) {

				// Depending on user input parse by line or break the line into words.
				if (parseByLine == true) { // Parse File by line.
					line = line.toLowerCase().trim().replaceAll(asciiRange, replaceChar);
					ngram.createNgram(line); // Pass line to createNgram.

				} else if (parseByLine == false) { // Parse File by line and break into words.
					String[] words = line.split("\\s+");

					for (String word : words) {
						word = word.toLowerCase().trim().replaceAll(asciiRange, replaceChar);
						ngram.createNgram(word); // Pass word to createNgram.
					}
				}
			}
			br.close(); // Flush and close the BufferedReader.
		}

	}

	/*
	 * Allows the user to specify a range to be used for n-grams. Takes two
	 * parameters which are passed into a temporary literal String Array in the
	 * required format used in replaceAll. String Builder used to change from String
	 * Array into a String. Sets new value to Instance Variable asciiRange used in
	 * parseFile().
	 */
	public void setAsciiRange(String a, String b) {
		// Using toLowerCase and toUpperCase to ensure correct format.
		String[] temp = { "[^", a.toLowerCase(), "-", b.toLowerCase(), a.toUpperCase(), "-", b.toUpperCase(), "]" };

		StringBuilder sb = new StringBuilder();
		for (String s : temp) { // For each String in String Array
			sb.append(s); // Concatenate to String Builder.

		}
		String t = sb.toString(); // Change String Builder to String.
		asciiRange = t; // Set value for asciiRange.

	}

	// Set Directory to input from user in menu.
	public void setDirectory(String directory) {
		this.directory = directory;

	}

	// Set parsing style to input from user in menu.
	public void setParseByLine(boolean parseByLine) {
		this.parseByLine = parseByLine;

	}

	// Set replace character to input from user in menu.
	public void setReplaceChar(String replaceChar) {
		this.replaceChar = replaceChar;

	}

}
