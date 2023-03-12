/*
 * FileFunctions.java
 */

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
/**
 * File class for reading/writing files. Print results to console output
 * 
 * @author Mark Ortega-Ponce
 */
public class FileFunctions {

	/** Comparator for ArrayList sorting */
	public static Comparator<String> stringComparator = new Comparator<String>() {
		@Override
		public int compare(String s1, String s2) {
			return s1.compareTo(s2);
		}
	};
	/** Hashmap to keep track of new words and their occurences. */
	private HashMap<String, Integer> dictionaryCount = new HashMap<String, Integer>();
	/** Static count to keep track of new FileFunctions object, used for outfile name. */
	private static int mapCount = 0;
	/** Outfile name used to write out sorted output. */
	private String outfile;
	/** Filename used to read in text from. */
	private String filename = null;
	/** ArayList for putting in dict. keys, used for writing out sorted output file. */
	private ArrayList<String> fileStrings = new ArrayList<String>();
	/** wordCount of file passed in, used for printResults function.  */
	private Integer wordCount = 0;
	/** Adding file object for constructor, gui */
	private File fileChosen;
	/**
	 * Creates FileFunctions object.
	 * @param fileName Sets filename to read in text from.
	 */
	public FileFunctions(String fileName) {

		this.filename = fileName;
		// to access in a static way, you do class name and attribute. Not just attribute.
		FileFunctions.mapCount += 1;
		String outfileName = "outfile" + mapCount + ".txt";
		this.outfile = outfileName;
	}
	/**
	 * Creates FileFunctions object, overloaded for Gui use
	 * @param file sets filename to read in text from.
	 */
	public FileFunctions(File file) {
		this.fileChosen = file;
		FileFunctions.mapCount += 1;
		String outfileName = "outfile" + mapCount + ".txt";
		this.outfile = outfileName;	
	}
	/**
	 * Call this function to read in the file. The function will go
	 * through the text in the document and add any new words
	 * to the hashmap instance. Any time it encounters a
	 * prviously added word, it replaces its count, with count+1
	 * Also keeps track of word count, and adds new strings
	 * to arrayList for sorting output to files and terminal window
	 */
	public void readFile() {
		
		if (this.filename != null) {
			this.fileChosen = new File(this.filename);
		}
		
		Scanner scnr = null;

		try {
			scnr = new Scanner(this.fileChosen);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening file");
			e.printStackTrace();
		}

		String tempString;
		Integer tempCount;

		// as long as scanner has next line, and another word it can keep going
		while (scnr.hasNextLine() && scnr.hasNext()) {

			tempString = scnr.next();
			tempString = tempString.toLowerCase();
			// https://www.codegrepper.com/code-examples/java/how+to+remove+all+commas+in+a+string+java
			tempString = tempString.replaceAll("[^a-zA-Z]", "");

			if (!this.dictionaryCount.containsKey(tempString)) {
				// Associates the specified value with the specified key in this map.
				this.dictionaryCount.put(tempString, 1);
				this.wordCount += 1;
				this.fileStrings.add(tempString);
			} else {
				// Returns the value to which the specified key is mapped, or null if no mapping
				tempCount = this.dictionaryCount.get(tempString);
				// replace(K key, V oldValue, V newValue)
				this.dictionaryCount.replace(tempString, tempCount, tempCount + 1);
				this.wordCount += 1;
			}
		}
		// sort for dictionary order output
		this.fileStrings.sort(stringComparator);
	}
	/**
	 * Call this function to write out sorted output
	 * to a .txt file. Will have total word count at top.
	 * Followed by all words in dictionary order, along with their count.
	 * Output file will be called outfile + mapCount + .txt
	 * Eg. outfile1.txt, replace number with mapCount 
	 */
	public void writeToFile() {

		try {
			FileWriter writer = new FileWriter(this.outfile);

			writer.write("Total Count of Words: " + this.wordCount);
			writer.write("\n");
			String builder = null;
			String key = null;
			Integer value = null;

			for (String keys : this.fileStrings) {
				key = keys;
				value = dictionaryCount.get(keys);
				// https://dzone.com/articles/java-string-format-examples
				builder = String.format("Key: %-20s Count: %d", key, value);
				writer.write(builder + "\n");
			}
			// always want to close files after done using them
			writer.close();

		} catch (IOException e) {

			System.out.println("Error opening file");
			e.printStackTrace();
		}

	}
	/**
	 * Function to print results to the terminal window:
	 * Total word Count:
	 * Distinct word Count:
	 * Results written out to:
	 * Most common word:
	 * Least common word:
	 * Followed by words and their respective count in the hashmap
	 */
	public void printResults() {

		String builder = null;
		String key = null;
		Integer value = null;

		System.out.println("Total word count: " + this.wordCount);
		System.out.println("Distinct word count: " + getDistinctWordCount());
		System.out.println("Results written out to: " + this.outfile);
		System.out.println("Most common word: " + getMostCommonWord());
		System.out.println("Least common word: " + getLeastCommonWord());
		
		for (String keys : this.fileStrings) {
			key = keys;
			value = dictionaryCount.get(keys);
			builder = String.format("Key: %-20s Count: %d", key, value);
			System.out.println(builder);
		}	
	}
	/**
	 * Allow user to change outfile name to
	 * something more memorable.
	 * @param differentName sets different name for outfile
	 */
	public void setOutfileName(String differentName) {
		this.outfile = differentName;
	}
	/**
	 * Gets word count from data read in from textfile
	 * @return word count of text file
	 */
	public Integer getWordCount() {
		return this.wordCount;
	}
	/**
	 * Gets distinct number of words inside the text document.
	 * @return dictinct words inside text
	 */
	public Integer getDistinctWordCount() {
		return this.fileStrings.size();
	}
	/**
	 * Gets mostCommon word from text document passed in.
	 * @return Most common word inside text
	 */
	public String getMostCommonWord() {

		int higherValue = 0;
		String highestWord = null;

		for (String word : fileStrings) {
			if (dictionaryCount.get(word) > higherValue) {
				higherValue = dictionaryCount.get(word);
				highestWord = word;
			}
		}
		return highestWord;
	}
	/**
	 * Gets least common word from text document passed in.
	 * Works better with larger text rather than just a paragraph
	 * @return Least common word inside text
	 */
	public String getLeastCommonWord() {
		int lowestValue = 2_000_000_000;
		String lowestWord = null;

		for (String word : fileStrings) {
			if (dictionaryCount.get(word) < lowestValue) {
				lowestValue = dictionaryCount.get(word);
				lowestWord = word;
			}
		}
		return lowestWord;
	}
}
