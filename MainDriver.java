/*
 * MainDriver.java
 */

/**
 * Driver class for our Dictionary Count application.
 * @author Mark Ortega-Ponce
 */
public class MainDriver {
	
	/**
	 * Creates FileFunctions object from filename
	 * passed in from the command line arguments.
	 * Reads in a file, writes out to file,
	 * and prints output to terminal window.
	 * If no file is passed in through arguments,
	 * then event controller will be instantiated
	 * and gui set to visible
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		try {
			String filename = args[0];
			FileFunctions fileWork = new FileFunctions(filename);
			fileWork.readFile();
			fileWork.writeToFile();
			fileWork.printResults();
			
		}catch (ArrayIndexOutOfBoundsException e){
			
			EventController gui = new EventController();
		}

	}
}
