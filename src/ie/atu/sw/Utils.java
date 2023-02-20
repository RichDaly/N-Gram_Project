package ie.atu.sw;

import java.io.*;

public class Utils {
	
	/*
	 * Kept all methods in this class static as only serving a single purpose and
	 * not changing or altering information.
	 */

	/*
	 * Final Method used in the application. Saves N-grams to output file in Comma
	 * Separated Values using PrintWriter. Extra functionality added that it does
	 * not write any null values in the table array to files.
	 */
	public static void saveToFile(Object[][] table, String file) throws FileNotFoundException {

		PrintWriter pw = new PrintWriter(new File(file));

		for (int row = 0; row < table.length; row++) {
			if (table[row][0] != null) { // Do not print any null values from object array.
				pw.write(table[row][0] + "," + table[row][1] + "\n");
			}
		}
		pw.close();

	}

	/*
	 * Check if user directory input is and actual directory and return true or
	 * false.
	 */
	public static boolean validateDirectory(String s) {
		File f = new File(s);
		boolean exists = f.isDirectory();
		return exists;

	}

	/*
	 * Progress Bar provided in code stubs. Moved to Utils as did not belong in any
	 * other Class.
	 */
	public static void printProgress(int index, int total) {
		if (index > total)
			return; 
		int size = 50; 
		char done = '='; 
		char todo = ' '; 

		int complete = (100 * index) / total;
		int completeLen = size * complete / 100;

		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append((i < completeLen) ? done : todo);
		}

		System.out.print("\r" + sb + "] " + complete + "%");

		if (index == total)
			System.out.println("\n");

	}

}
