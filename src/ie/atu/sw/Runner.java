package ie.atu.sw;

/**
 * Contains the main method for running the application. Launches the Command Line User
 * Interface.
 *
 * @author Richard Daly
 */
public class Runner {

	public static void main(String[] args) throws Exception {

		/*
		 * Created a new instance of Menu and called the start() method to begin
		 * application. All functionality is handled in Menu Class.
		 */
		Menu m = new Menu();
		m.start();

	}
}
