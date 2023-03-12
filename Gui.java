/*
 * Gui.java
 */

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Class for displaying application to user
 * @author Mark Ortega-Ponce
 */

public class Gui extends JFrame {

	/* Label for "total count" */
	private final JLabel totalCountLabel = new JLabel("Total Count: ");
	/* Label for number of "distinct words" */
	private final JLabel distinctWordsLabel = new JLabel("Distinct Words: ");
	/* Label for "most common word" */
	private final JLabel mostCommonWordLabel = new JLabel("Most common word: ");
	/* Label for "least common word" */
	private final JLabel leastCommonWordLabel = new JLabel("Least common word: ");
	/* Label for setting outfileName */
	private final JLabel setOutfileNameLabel = new JLabel("Set outfileName(.txt)");
	/* Textfield for total count output */
	protected final JTextField totalTextField = new JTextField(10);
	/* Textfield for discting word output */
	protected final JTextField distinctTextField = new JTextField(10);
	/* Textfield for most common word output */
	protected final JTextField mostCommonTextField = new JTextField(15);
	/* Textfield for least common word output */
	protected final JTextField leastCommonTextField = new JTextField(15);
	/* Textfield for input, takes outfilename */
	protected final JTextField outfileTextField = new JTextField(30);
	/* Label for button object "Choose file" */
	private final JButton chooseFileButton = new JButton("Choose File");
	/* Allow user to not write to file, add separate button */
	private final JButton writeToFileButton = new JButton("Write to file");
		
	/**
	 * Constructor for Gui panel
	 * @param control, parameter to give control to EventController object when events happen.
	 */
	public Gui(EventController control) {
		// javax.swing.JFrame.Jframe(String title)
		super("Dictionary Application");

		// not taking input, dont let user change output box
		this.totalTextField.setEditable(false);
		this.distinctTextField.setEditable(false);
		this.mostCommonTextField.setEditable(false);
		this.leastCommonTextField.setEditable(false);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		//set size of Content Pane
		this.setSize(600, 400);
		// we are doing our own layout, this function disables layout manager
		this.getContentPane().setLayout(null);

		// setBounds(int x, int y, width, height)
		this.totalCountLabel.setBounds(40, 60, 150, 40);
		this.getContentPane().add(this.totalCountLabel);
		this.totalTextField.setBounds(230, 60, 150, 40);
		this.getContentPane().add(this.totalTextField);

		this.distinctWordsLabel.setBounds(40, 110, 150, 40);
		this.getContentPane().add(this.distinctWordsLabel);
		this.distinctTextField.setBounds(230, 110, 150, 40);
		this.getContentPane().add(this.distinctTextField);

		this.mostCommonWordLabel.setBounds(40, 160, 150, 40);
		this.getContentPane().add(this.mostCommonWordLabel);
		this.mostCommonTextField.setBounds(230, 160, 150, 40);
		this.getContentPane().add(this.mostCommonTextField);

		this.leastCommonWordLabel.setBounds(40, 210, 150, 40);
		this.getContentPane().add(this.leastCommonWordLabel);
		this.leastCommonTextField.setBounds(230, 210, 150, 40);
		this.getContentPane().add(this.leastCommonTextField);
		
		// need listeners for buttons
		this.chooseFileButton.setBounds(200, 280, 150, 60);
		this.chooseFileButton.addActionListener(control);
		this.getContentPane().add(this.chooseFileButton);
		
		/* Adding write to file functionality */
		this.setOutfileNameLabel.setBounds(410, 90, 150, 40);
		this.getContentPane().add(this.setOutfileNameLabel);
		this.outfileTextField.setBounds(400, 130, 150, 40);
		this.getContentPane().add(this.outfileTextField);
		this.writeToFileButton.setBounds(400, 170, 150, 40);
		this.writeToFileButton.addActionListener(control);
		this.getContentPane().add(this.writeToFileButton);
		

	}

}
