package ie.atu.sw;

import java.util.Scanner;

public class Menu {
	private boolean keepRunning = true; // Boolean to keep Application running until user quits.
	private Scanner scanner; // Instance Variable for Scanner so that can use throughout the class.
	private NgramGenerator ngram; // Instance Variable for NgramGenerator so that can use throughout the class.
	private Parser parser; // Instance Variable for Parser so that can use throughout the class.

	/*
	 * Passed the new Instance of NgramGenerator into Parser so both are working
	 * together. Calling methods from either Class works on the same information.
	 * Allowed me to separate methods into two distinct classes instead of one large
	 * cluttered class breaking Single Responsibility.
	 */
	public Menu() {
		scanner = new Scanner(System.in); // Initialize Scanner to accept system inputs.
		ngram = new NgramGenerator(); // Create a new Instance of the NgramGenerator.
		parser = new Parser(ngram); // Create a new Instance of the Parser.
	}

	
	/*
	 * Method that starts the Application and keeps it running with a while loop.
	 * User input is parsed into an Integer to work with Switch Statement.
	 * Surrounded by a try and catch block to deal with unexpected user input.
	 * Switch Statement is kept simple, calls methods and case's link with options
	 * from showOptions().
	 */
	public void start() throws Exception { // Exception thrown from buildNgram().
		while (keepRunning) { // While KeepRunning is True run the following.

			try {
				showOptions(); // Display options.
				int choice = Integer.parseInt(scanner.next()); // Parse integer from user input.
				switch (choice) {
					case 1 -> fileDirectory();
					case 2 -> parsingStyle();
					case 3 -> ngramSize();
					case 4 -> setAsciiRange();
					case 5 -> outputFile();
					case 6 -> buildNgram();
					case 7 -> quit();
					default -> invalidChoice();
				}

			} catch (Exception e) { // Catch exceptions caused by Scanner.
				invalidInput();

			}
		}
	}

	
	// Displays Application Functions and asks for input. Private as only used within Menu Class.
	private void showOptions() {
		System.out.println("************************************************************");
		System.out.println("*      ATU - Dept. Computer Science & Applied Physics      *");
		System.out.println("*                                                          *");
		System.out.println("*                  N-Gram Frequency Builder                *");
		System.out.println("*                                                          *");
		System.out.println("************************************************************");
		System.out.println("(1) Specify Text File Directory");
		System.out.println("(2) Specify Parsing Style");
		System.out.println("(3) Specify n-Gram Size");
		System.out.println("(4) Specify ASCII Range");
		System.out.println("(5) Specify Output File");
		System.out.println("(6) Build n-Grams ");
		System.out.println("(7) Quit");
		System.out.println();
		System.out.print("Select Option [1 - 7]>");
		System.out.println();

	}

	
	/*
	 * Sets File Directory to input from user. Check if directory exists and
	 * displays if was successfully added or the directory could not be found. Catch
	 * any path error early so can troubleshoot before running rest of application.
	 * Private as only used within Menu Class.
	 */
	private void fileDirectory() {
		System.out.println("Please input path to your chosen directory >");
		String choice = scanner.next(); // Get String input from user.

		// Validate users directory.
		boolean validate = Utils.validateDirectory(choice);
		// If Statement working on results of boolean validate.
		if (validate == true) {
			parser.setDirectory(choice); // Set directory.
			System.out.println("[INFO] Directory has been set.");
			System.out.println();
		} else if (validate == false) {
			System.out.println("[ERROR] Directory could not be found, please try again");
			System.out.println();
		}

	}

	
	/*
	 * Solicits choice from user to either parse the text directory by line or by
	 * individual words. Private as only used within the Menu Class.
	 */
	private void parsingStyle() {
		System.out.println("Please select option from the following options:");
		System.out.println("(1) Parse Files by line");
		System.out.println("(2) Parse Files by Word");
		System.out.println("Select Option 1 or 2 >");

		int choice = Integer.parseInt(scanner.next()); // Parse integer from user input.
		// Switch Statement that sets choice.
		switch (choice) {
			case 1 -> parser.setParseByLine(true); // Parse by line.
			case 2 -> parser.setParseByLine(false); // Parse by Word.
			default -> invalidChoice();
		}

		System.out.println("[INFO] Parsing Style has been set.");
		System.out.println();
	}

	
	/*
	 * Sets N-Gram Size to input from user. Private as only used within the Menu
	 * Class.
	 */
	private void ngramSize() {
		System.out.println("Please enter an n-Gram size in the range [1 - 5]>");
		int choice = Integer.parseInt(scanner.next()); // Parse integer from user input.
		ngram.setNgramSize(choice); // set N-gram Size.

		System.out.println("[INFO] N-Gram size has been set.");
		System.out.println();

	}

	
	/*
	 * Solicit choice from user to set ASCII range used when parsing text files. Two
	 * inputs expected. Information on expected format given. Also asks what user
	 * wants to replace unwanted characters outside of range with. The underscore
	 * pairs well with parsing by line if selected in parsingStyle(). Private as
	 * only used within the Menu Class.
	 */
	private void setAsciiRange() {
		System.out.println("Please enter the start of ASCII range from [a - z]");
		System.out.println("Single Character Expected. Example input: a");
		System.out.println("Input Character >>>");
		String a = scanner.next(); // Get String input from user.

		System.out.println("Please enter the end of range from [a - z] >");
		System.out.println("NB: Must come chronologically after start of range.");
		System.out.println("Single Character Expected. Example input: d");
		System.out.println("Input Character >>>");
		String b = scanner.next(); // Get String input from user.
		
		parser.setAsciiRange(a, b); // Set range to user input.

		System.out.println("Please select option for Characters outside of range:");
		System.out.println("(1) Replace with an underscore '_'");
		System.out.println("(2) Remove completely");
		System.out.println("Select Option 1 or 2 >");

		int choice = Integer.parseInt(scanner.next()); // Parse integer from user input.
		// Switch Statement to set choice on characters outside range.
		switch (choice) {
			case 1 -> parser.setReplaceChar("_"); // Replace with underscore.
			case 2 -> parser.setReplaceChar(""); // Remove unwanted characters.
			default -> invalidChoice();
		}

		System.out.println("[INFO] ASCII Range has been set.");
		System.out.println();

	}

	
	/*
	 * Sets output file to input from user. Private as only used within the Menu
	 * Class.
	 */
	private void outputFile() {
		System.out.println("Please input path to your output file >");
		String choice = scanner.next(); // Get String input from user.
		ngram.setNgramOutput(choice); // Set output file.

		System.out.println("[INFO] Output File has been set.");
		System.out.println();

	}

	/*
	 * Starts the main workload of the application. parseDirectory() is called which
	 * calls on methods from both the Parser and NgramGenerator Class. N-grams are
	 * stored Object[][] table in NgramGenerator.
	 */
	private void buildNgram() throws Exception {
		System.out.println("Building N-Grams, please wait .....");
		parser.parseDirectory();

		// Once directory is parsed N-grams are passed to save method in Utils.
		Utils.saveToFile(ngram.getTable(), ngram.getNgramOutput());
		System.out.println("[INFO] N-Grams Generated and output to file.");
		System.out.println();

	}

	/*
	 * Set KeepRunning to false, allowing application to close. Private as only used
	 * within Menu Class.
	 */
	private void quit() {
		System.out.println("[INFO] Shutting down");
		keepRunning = false;

	}

	/*
	 * Any unexpected Integer input from user displays message to review options and
	 * try again. Private as only used within Menu Class.
	 */
	private void invalidChoice() {
		System.out.println("[ERROR] Invalid Option: Please select from the options shown");
		System.out.println();

	}

	/*
	 * Any unexpected user input from Scanner is caught. Stops application from
	 * terminating. Private as only used within Menu Class.
	 */
	private void invalidInput() {
		System.out.println("[ERROR] Invalid Input, Please try again");
		System.out.println();

	}
}
