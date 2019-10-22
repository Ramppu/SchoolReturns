import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;


public class textEditor {
	
	public static void main(String args[]){	
		JFrame frame = new JFrame("Chat Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Menu
			JMenuBar mb = new JMenuBar();
			mb.setBackground(UIManager.getColor("Button.background"));
			JMenu m1= new JMenu("File");
			JMenu m2= new JMenu("Edit");
			JMenu m3= new JMenu("Details");
		
		//m1 Items
			JMenuItem m11 = new JMenuItem("Open");
			m11.setIcon(new ImageIcon(textEditor.class.getResource("/resources/open.png")));
			JMenuItem m12 = new JMenuItem("Save");
			m12.setIcon(new ImageIcon(textEditor.class.getResource("/resources/save.png")));
			JMenuItem m13 = new JMenuItem("Clear");
			JMenuItem m14 = new JMenuItem("Quit");
			m1.add(m11);
			m1.add(m12);
			m1.add(m13);
			m1.add(m14);
			
		//m2 Items
			JMenuItem m21 = new JMenuItem("Search");
			JMenuItem m22 = new JMenuItem("Replace");
			m2.add(m21);
			m2.add(m22);
			
		//m3 Items
			JMenuItem m31 = new JMenuItem("About");
			m3.add(m31);
			
			mb.add(m1);
			mb.add(m2);
			mb.add(m3);
			
			
		//Panel and it's content
			JPanel panel = new JPanel(new BorderLayout());
			JToolBar tb = new JToolBar();
			JTextArea ta = new JTextArea();
			panel.add(tb, BorderLayout.NORTH);
			panel.add(ta, BorderLayout.CENTER);
			
			JButton btn1 = new JButton("");
			btn1.setToolTipText("Open a desired text file");
			JButton btn2 = new JButton("");	
			btn2.setToolTipText("Save the written text into a desired file");
			JButton btn3 = new JButton("");
			
			btn1.setIcon(new ImageIcon(textEditor.class.getResource("/resources/open.png")));
			btn2.setIcon(new ImageIcon(textEditor.class.getResource("/resources/save.png")));
			btn3.setIcon(new ImageIcon(textEditor.class.getResource("/resources/cut.png")));
			
			tb.add(btn1);
			tb.add(btn2);
			tb.add(btn3); //THIS BUTTON HAS NO FUNCTIONALITY ADDED TO IT
			
		//Frame Content
			frame.getContentPane().setLayout(new BorderLayout());
			frame.getContentPane().add(mb, BorderLayout.NORTH);
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			
		
		//frame modifiers
			frame.setSize(400,400);
			frame.setResizable(false);
			frame.setVisible(true);
			

			 m11.addActionListener(new ActionListener() { // OpenMenu
					public void actionPerformed(ActionEvent e) {
						open(ta);
					}		
					});			 
			 btn1.addActionListener(new ActionListener() { // OpenButton
					public void actionPerformed(ActionEvent e) {
						open(ta);
					}		
					});	
			 m12 .addActionListener(new ActionListener() { // SaveMenu 
					public void actionPerformed(ActionEvent e) {				
						save(ta);
					}		
					});			 
			 btn2 .addActionListener(new ActionListener() { // SaveButton 
					public void actionPerformed(ActionEvent e) {				
						save(ta);
					}		
					});			 
			 m13.addActionListener(new ActionListener() {  // Clear
					public void actionPerformed(ActionEvent e) {	
						clear(ta);
					}		
					});			 
			 m14.addActionListener(new ActionListener() {  // Quit
					public void actionPerformed(ActionEvent e) {	
						close();
					}		
					});			 
			 m31.addActionListener(new ActionListener() {  //About
					public void actionPerformed(ActionEvent e) {	
						about();
					}		
					});
			 m21.addActionListener(new ActionListener() {  //Search
					public void actionPerformed(ActionEvent e) {	
							searchWord(ta);
					}		
					});			 
			 m22.addActionListener(new ActionListener() {  // Replace
					public void actionPerformed(ActionEvent e) {	
						replaceWord(ta);
					}		
					});
	}
		public static void replaceWord(JTextArea ta) {
			String content = ta.getText(); // Gathering the text within the area to a variable
			JTextField replaced = new JTextField(10); // Creating 2 text fields, that are going to be used for JOptionPane
			JTextField replacing = new JTextField(10);

		     JPanel dia = new JPanel();
		      dia.add(new JLabel("Replaced word:")); //Giving 'titles' to the text fields and adding them to the panel
		      dia.add(replaced);	
		      dia.add(new JLabel("Replacing word:"));								      
		      dia.add(replacing); // The order is important, so we get 'title -> text field -> title -> text field'

		    JOptionPane.showConfirmDialog(null, dia,"Replace the desired word", JOptionPane.OK_CANCEL_OPTION);
			int index = content.indexOf(replaced.getText()); // This index is used to find the starting point of the 'replaced' word
			try {
				ta.replaceRange(replacing.getText(), index,(index + replaced.getText().length() )); //Line to replace the word
			}
			catch (Exception e1) { // The most expected error that user might face is the replaced word not existing within the file, other than that user should not encounter any errors during replace
				JOptionPane.showMessageDialog(null, "The word you're trying to replace doesn't exist within the file!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		public static void searchWord(JTextArea ta) {
			String content = ta.getText(); // Gathering the text area into a variable
			String word = JOptionPane.showInputDialog(null,"Which word do you want to find?"); //User inputs a word they want to find
			int index = content.indexOf(word);
			if (index != -1) {	//If the word is not within the file, int index will always be -1. This if/else deals with that instance	
				ta.setSelectedTextColor(Color.YELLOW);
				ta.setSelectionStart(index);
				ta.setSelectionEnd(index + word.length() );
			}
			else {
				JOptionPane.showMessageDialog(null, "The word you're trying to search doesn't exist within the file!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		public static void about() {
			JOptionPane.showMessageDialog(null, "Made By\nRasmus Karjalainen", "About", JOptionPane.INFORMATION_MESSAGE); 
			// Message dialog that shows the creator
		}
		public static void close() {
			System.exit(0);
			// Closes the text editor
		}
		public static void clear(JTextArea ta) {
			ta.setText(null); //Empties the text area by giving a null value to it
		}
		public static void save(JTextArea ta) {	
				JFileChooser fc = new JFileChooser(); // User chooses the .txt file, where the text area  content is saved
				fc.setDialogTitle("Save to a text file");
				fc.setApproveButtonText("Save"); // File chooser elements have 'open' in them by default. We change them to 'saved' so the user doesn't get confused
				fc.setApproveButtonToolTipText("Save to a file");
				fc.showOpenDialog(null);
				String newFile = fc.getSelectedFile().getAbsolutePath(); //File is defined by the selection made by user
				File file = new File(newFile);
				
				try {
					PrintWriter pw = new PrintWriter(file);
					String content = ta.getText(); // Content from ta into a variable
					pw.println(content); // That content is printed into the file
					
					pw.flush(); // Empty the writer 
					pw.close(); // Close  the writer
				}
				catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		public static void open(JTextArea ta) {
			JFileChooser fc = new JFileChooser(); // The exact same process when  compared to the save at first
			fc.showOpenDialog(null); // Now that we're opening a file, there is no need to switch any of  the text contents in the filechooser
			String newFile = fc.getSelectedFile().getAbsolutePath();	//File is once again declared by the user				
				File file = new File(newFile);
				try {
					Scanner reader = new Scanner(file); //Scanner reads the declared text file
					while(reader.hasNextLine()) { // While the  scanner has not gone through the whole file
						String txt = reader.nextLine(); // Read file is put into a variable
						ta.append(txt + "\n"); // That variable is then appended into the text area, followed up with a line switch
					}
					reader.close(); //Reader is closed
				}
				 catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}											
		}
}	
