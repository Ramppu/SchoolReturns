package database;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;



import javax.swing.JTextPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane sp;
	private JPanel ap;
	private JTextField txtArt;
	private JTextField txtRD;
	private JTextField txtAN;
	private JLabel lblArt;
	private JLabel lblAN;
	private JLabel lblRD;
	private addFrame add;
	private String SQL;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(); 
					frame.setTitle("Database GUI");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 386);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		generateTable();
		initiateTable(SQL, model, table);
		
		JButton ar = new JButton("Add Row");
		ar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addRow();
			}		
		});			 
		ar.setBounds(262, 260, 89, 23);
		contentPane.add(ar);
		
		JButton dr = new JButton("Delete A selected Row");
		dr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteRow(table, model);
			}		
		});			 
		dr.setBounds(29, 260, 195, 23);
		contentPane.add(dr);
		
		JButton up = new JButton("Update The Table");
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(model, table);
			}		
		});			 
		up.setBounds(95, 295, 225, 29);
		contentPane.add(up);
		

	}
	public void addRow() {
		add = new addFrame();
		add.setVisible(true);
	}
	public void deleteRow(JTable table, DefaultTableModel model) {
		String select = (String) table.getValueAt(table.getSelectedRow(), table.getSelectedColumn());
		System.out.print(select);
		SQL = "DELETE FROM album WHERE Artist = '"+select+ "' OR Album = '"+select+"' OR Release_Date = '"+select+"';";
		
		 try {			 
			  String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7311114";
			  String USERID = "sql7311114";
			  String PASSWORD = "xi3Lf6E5Iz";
			
			  Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
			  System.out.println("Yhteys tietokantaan on luotu.");
			 
			  Statement stmt = con.createStatement();
			  int results = stmt.executeUpdate(SQL);
			  System.out.println("Update Affected "+results+ " rows.");
			  model.removeRow(table.getSelectedRow());
			  JOptionPane.showMessageDialog(null,"Album removed from the database.", "Success !",JOptionPane.PLAIN_MESSAGE);
			  con.close();
			  
		  } 
		  catch (SQLException e1) {
		 	System.out.println("Virhe tietokannan käytössä!");
		 	System.out.println(e1);
		  } // catch
	}
	public void generateTable() {
		String columns[]= {"Artist","Album Name","Release Date"};
		model = new DefaultTableModel(columns, 0);
		contentPane.setLayout(null);
		table = new JTable(model);
		table.setBorder(null);
		table.setDefaultEditor(Object.class, null);
		table.setBounds(0, 0, 388, 295);
		sp = new JScrollPane(table);
		sp.setBorder(null);
		sp.setBounds(0, 0, 434, 209);
		contentPane.add(sp);
		
		 
		  SQL = "SELECT * FROM album;";
		  System.out.print(SQL);
		 
	}
	public void updateTable(DefaultTableModel model, JTable table) {
		 try {			 
			  String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7311114";
			  String USERID = "sql7311114";
			  String PASSWORD = "xi3Lf6E5Iz";
			  
			  SQL = "SELECT * FROM album;";
			
			  Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
			  System.out.println("Yhteys tietokantaan on luotu.");
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(SQL);
			  int x = 0;
			  
			  while (rs.next()) {
				  if (rs.getRow() <= model.getRowCount()) {
					  table.setValueAt(rs.getString(1),x,0);
					  table.setValueAt(rs.getString(2),x,1);
					  table.setValueAt(rs.getString(3),x,2);
					  x++;
				  }
				  else {
					  model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3)});
				  }
			  }
			  con.close();			  
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	}
	public void initiateTable(String SQL, DefaultTableModel model, JTable table) {
		 try {			 
			  String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7311114";
			  String USERID = "sql7311114";
			  String PASSWORD = "xi3Lf6E5Iz";
			
			  Connection con = DriverManager.getConnection(URL, USERID, PASSWORD);
			  System.out.println("Yhteys tietokantaan on luotu.");
			  Statement stmt = con.createStatement();
			  ResultSet rs = stmt.executeQuery(SQL);
			  
			  while (rs.next()) {
				  model.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3)});
			  }
			  con.close();
			  //TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
			  //table.setRowSorter(sorter);
			  	
			  //List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
			  //sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
			  //sorter.setSortKeys(sortKeys);
			  //JÄRJESTYKSEN LISÄÄMINEN RIKKOI POISTOMETODIN JOSTAIN SYYSTÄ
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	}
}
