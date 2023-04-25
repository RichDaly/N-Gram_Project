package ie.atu.sw;

/**
 * Takes parsed String and seperates it into the desired N-Gram size. All resulting N-Grams are stored
 * here as well. Class has been left as submitted to the lecturer for grading. Surplus of comments is 
 * due to this, as actions needed to be explained.
 *
 * @author Richard Daly
 */
public class NgramGenerator {
	private Object[][] table; // 2 Dimensional array to store n-grams and frequencies.
	private String ngramOutput; // The output file for table to be printed to.
	private int ngramSize = 1; // Default n-gram size unless specified by user.

	// Constructor for class setting size of table array.
	public NgramGenerator() {
		table = new Object[100000][2]; // Large object array to handle high volume of n-grams.

	}

	/*
	 * The results of parseFile in Parser are sent here. String array to store
	 * n-grams created, large in size to handle line of text. ngramSize subtracted
	 * from string length to stop index out of bounds error. Created ngramSize
	 * variable so that user can set size. Default size is 1 if not specified,
	 * allowing application to run.
	 */
	public void createNgram(String s) {
		String[] ngrams = new String[1000];

		// Loop over the String.
		for (int i = 0; i <= s.length() - ngramSize; i++) {
			ngrams[i] = s.substring(i, i + ngramSize);
			addNGram(ngrams[i]); // Pass each n-gram to addNgram().
		}

	}

	/*
	 * Add n-gram to table in style shown in assignment workshop. Creates a hashCode
	 * for each n-gram and adds it to table array. If n-gram already exists
	 * increment frequency by counter.
	 */
	private void addNGram(String ngram) {
		int index = ngram.hashCode() % table.length;

		long counter = 1;
		if (table[index][1] != null) {
			counter += (long) table[index][1];

		}
		table[index][0] = ngram;
		table[index][1] = counter;

	}

	// Get Table array storing n-grams and their frequencies.
	public Object[][] getTable() {
		return table;
	}

	// Get output file set by user.
	public String getNgramOutput() {
		return ngramOutput;
	}

	// Set output file to input from user in menu.
	public void setNgramOutput(String output) {
		this.ngramOutput = output;
	}

	// Set N-Gram Size to input from user in menu.
	public void setNgramSize(int ngramSize) {
		this.ngramSize = ngramSize;
	}

}
