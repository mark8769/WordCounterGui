/*
 * EventController.java
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
/**
 * Controller class for dictionary count application
 * @author markortega-ponce
 */
public class EventController implements ActionListener {
	/** File that was chosen inside the gui */
	private String filename = null;
	/** File name for writing data out to */
	private String outfileName = null;
	/** The model for dictionary data */
	private FileFunctions fileWork = null;
	/** Gui for the dictionary application */
	private final Gui view;
	/** Filechooser object for our gui application, gives us Finder-like view */
	private final JFileChooser fileChooser = new JFileChooser();

	public EventController() {

		// passing itself, havent seen this before
		view = new Gui(this);
		view.setVisible(true);
	}
	
	/**
	 * When button is pressed, actionPerformed is called to handle the event
	 * Uses FileFunctions to get all the necessary information for the gui
	 * Word Count, distinct word count, most common word, least common word.
	 * If user wants to write out to file, also given the option.
	 * @param ae The action event, e.g the button being pressed in this case.
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		// description tag, accepted extensions you would like to take eg. .txt, .text
		fileChooser.setFileFilter( new FileNameExtensionFilter("Text Files", "txt", "text"));
		
		// if button is pressed, perform these actions
		if (ae.getActionCommand().equals("Choose File")) {
			
			//if person successfully chooses a file
			int response = fileChooser.showOpenDialog(null);
			
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				fileWork = new FileFunctions(file);
				fileWork.readFile();
				this.view.totalTextField.setText(fileWork.getWordCount().toString());
				this.view.distinctTextField.setText(fileWork.getDistinctWordCount().toString());
				this.view.mostCommonTextField.setText(fileWork.getMostCommonWord());
				this.view.leastCommonTextField.setText(fileWork.getLeastCommonWord());
			}
			
		}else if (ae.getActionCommand().equals("Write to file") ) {
			try {
				//throws null pointer exception if no text in box
				//give user the option, but dont force it either.
				outfileName = this.view.outfileTextField.getText();
				if(outfileName.contains(".txt") ) {
					fileWork.setOutfileName(outfileName);
				}
			}catch(NullPointerException e) {
				// do nothing, FileFunctions already coming up with new names for each file
				// using static count inside fileFunctions
				// every "new" increments static count by 1
			}
			fileWork.writeToFile();
		}
	}
}// end of class
