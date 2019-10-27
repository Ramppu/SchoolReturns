import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.chrono.JapaneseChronology;
import java.util.InputMismatchException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI_Automaatti extends JFrame {

	// Luokkamuuttujat
	// Esitell‰‰n t‰‰ll‰ jotta komponentteihin voidaan viitata mist‰ tahansa luokan
	// sis‰lt‰

	JPanel contentPane;
	private JMenuItem mntmTallennaAutomaatinTila;
	private JMenuItem mntmLataaAutomaatti;
	private JTextField txtKaakao;
	private JTextField txtTee;
	private JTextField txtKahvi;
	private JTextField txtKahvia;
	private JTextField txtTeeta;
	private JTextField txtKaakaota;

	/**
	 * Main-metodi, joka k‰ynnist‰‰ sovelluksen
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Luodaan ensmin uusi JuomaAutomaatti-olio
					JuomaAutomaatti ja = new JuomaAutomaatti();

					// K‰yttˆliittym‰ saa parametrina olion, jonka tiedot se n‰ytt‰‰
					GUI_Automaatti frame = new GUI_Automaatti(ja);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Konstruktorissa rakennetaan k‰yttˆliittym‰. Huomaa, ett‰ otetaan parametrina
	 * vastaan alussa luotu juoma-automaatti. T‰m‰ siksi, ett‰ voidaan n‰ytt‰‰ sen
	 * tiedot GUI:ssa
	 */
	public GUI_Automaatti(JuomaAutomaatti ja) {

		// Ikkunan otsikko ja koko

		setTitle("Kahviautomaatti GUI v. 1.21");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 705);

	 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 449, 22);
		contentPane.add(menuBar);
		
		// Menu items
		JMenu mnYllpito = new JMenu("Yll‰pito");
		menuBar.add(mnYllpito);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Aseta kahvin m‰‰r‰");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kahvi = JOptionPane.showInputDialog(null,"Aseta kahvin m‰‰r‰");
				try {
					int m‰‰r‰ = Integer.parseInt(kahvi);
					if (m‰‰r‰ > 100) {
						ja.setKahvi(100);
					}
					else if (m‰‰r‰ < 0) {	
						ja.setKahvi(0);
					}
					else {
						ja.setKahvi(m‰‰r‰);
					}
					
					txtKahvia.setText("Kahvia: " + ja.getKahvi());
					kahviClr(txtKahvia, ja);
					System.out.println("Kahvin uusi m‰‰r‰ asetettu.");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Aseta arvoksi Kokonaisluku!", "Virhe!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnYllpito.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Aseta teen m‰‰r‰");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tee = JOptionPane.showInputDialog(null,"Aseta teen m‰‰r‰");
				try {
					int m‰‰r‰ = Integer.parseInt(tee);
					if (m‰‰r‰ > 100) {
						ja.setTee(100);
					}
					else if (m‰‰r‰ < 0) {
						ja.setTee(0);
					}
					else {
						ja.setTee(m‰‰r‰);
					}
					txtTeeta.setText("Teet‰: " + ja.getTee());
					teeClr(txtTeeta, ja);
					System.out.println("Teen uusi m‰‰r‰ asetettu.");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Aseta arvoksi Kokonaisluku!", "Virhe!", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		mnYllpito.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Aseta kaakaon m‰‰r‰");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String kaakao = JOptionPane.showInputDialog(null,"Aseta kaakaon m‰‰r‰");
				try {
					int m‰‰r‰ = Integer.parseInt(kaakao);
					if(m‰‰r‰ > 100) {
						ja.setKaakao(100);
					}
					else if (m‰‰r‰ < 0) {
						ja.setKaakao(0);
					}
					else {
						ja.setKaakao(m‰‰r‰);
					}
					txtKaakaota.setText("Kaakaota: " + ja.getKaakao());
					kaakaoClr(txtKaakaota, ja);
					System.out.println("Kaakaon uusi m‰‰r‰ asetettu.");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,"Aseta arvoksi Kokonaisluku!", "Virhe!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mnYllpito.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Tallenna automaatin tila");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				if(ja.getKaakao() < 10 || ja.getKahvi() < 10 || ja.getTee() < 10) {
					JOptionPane.showMessageDialog(null,"Virhe!\nEt voi tallentaa automaattia tilaan, jossa Kahvin, Teen tai Kaakaon arvo on alle 10!", "Virhe!", JOptionPane.ERROR_MESSAGE);
				}
				else {
					try {
						Sarjallistamista.kirjoitaTiedostoon(ja);
						System.out.println("Automaatin tila tallennettu.");
					} 
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mnYllpito.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Lataa automaatti");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JuomaAutomaatti uusi = Sarjallistamista.lueTiedostosta();
					ja.setKahvi(uusi.getKahvi());
					ja.setTee(uusi.getTee());
					ja.setKaakao(uusi.getKaakao());
					txtKahvia.setText("Kahvia: " + ja.getKahvi());
					txtTeeta.setText("Teet‰: " + ja.getTee());
					txtKaakaota.setText("Kaakaota: " + ja.getKaakao());
					System.out.println("Automaatin tila ladattu.");
					txtKahvia.setDisabledTextColor(Color.BLACK);
					txtTeeta.setDisabledTextColor(Color.BLACK);
					txtKaakaota.setDisabledTextColor(Color.BLACK);
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnYllpito.add(mntmNewMenuItem_4);
		
		JMenuItem mntmLopeta = new JMenuItem("Lopeta");
		mntmLopeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnYllpito.add(mntmLopeta);
		
		JMenu mnTietojaOhjelmasta = new JMenu("Tietoja Ohjelmasta");
		menuBar.add(mnTietojaOhjelmasta);
		
		JMenuItem mntmVersiotiedot = new JMenuItem("Versiotiedot");
		mntmVersiotiedot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Versiotiedot f = new Versiotiedot();
				f.setVisible(true);
			}	
		});
		mnTietojaOhjelmasta.add(mntmVersiotiedot);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaKahvi();
				txtKahvia.setText("Kahvia: " + ja.getKahvi());
				kahviClr(txtKahvia,ja);
			}
		});
		button.setIcon(new ImageIcon(GUI_Automaatti.class.getResource("/resources/coffee.jpg")));
		button.setBounds(21, 62, 121, 112);
		contentPane.add(button);
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaTee();
				txtTeeta.setText("Teet‰: " + ja.getTee());
				teeClr(txtTeeta, ja);
				
			}
		});
		button_1.setIcon(new ImageIcon(GUI_Automaatti.class.getResource("/resources/tea.jpg")));
		button_1.setBounds(21, 269, 121, 112);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.setIcon(new ImageIcon(GUI_Automaatti.class.getResource("/resources/cocoa.jpg")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ja.valmistaKaakao();
				txtKaakaota.setText("Kaakaota: " + ja.getKaakao());
				kaakaoClr(txtKaakaota, ja);
			}
		});
		button_2.setBounds(21, 483, 121, 112);
		contentPane.add(button_2);
		
		txtKaakao = new JTextField();
		txtKaakao.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		txtKaakao.setBorder(null);
		txtKaakao.setEditable(false);
		txtKaakao.setDisabledTextColor(Color.BLACK);
		txtKaakao.setHorizontalAlignment(SwingConstants.CENTER);
		txtKaakao.setOpaque(false);
		txtKaakao.setText("Kaakao");
		txtKaakao.setBounds(31, 614, 96, 20);
		contentPane.add(txtKaakao);
		txtKaakao.setColumns(10);
		
		txtTee = new JTextField();
		txtTee.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		txtTee.setEditable(false);
		txtTee.setBorder(null);
		txtTee.setDisabledTextColor(Color.BLACK);
		txtTee.setOpaque(false);
		txtTee.setHorizontalAlignment(SwingConstants.CENTER);
		txtTee.setText("Tee");
		txtTee.setBounds(31, 392, 96, 20);
		contentPane.add(txtTee);
		txtTee.setColumns(10);
		
		txtKahvi = new JTextField();
		txtKahvi.setFont(new Font("Myanmar Text", Font.BOLD, 13));
		txtKahvi.setEditable(false);
		txtKahvi.setDisabledTextColor(Color.BLACK);
		txtKahvi.setBorder(null);
		txtKahvi.setEnabled(false);
		txtKahvi.setOpaque(false);
		txtKahvi.setHorizontalAlignment(SwingConstants.CENTER);
		txtKahvi.setText("Kahvi");
		txtKahvi.setBounds(31, 185, 96, 20);
		contentPane.add(txtKahvi);
		txtKahvi.setColumns(10);
		
		txtKahvia = new JTextField();
		txtKahvia.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 14));
		txtKahvia.setBorder(null);
		txtKahvia.setDisabledTextColor(Color.BLACK);
		txtKahvia.setEnabled(false);
		txtKahvia.setOpaque(false);
		txtKahvia.setText("Kahvia: " + ja.getKahvi());
		txtKahvia.setBounds(209, 103, 121, 20);
		contentPane.add(txtKahvia);
		txtKahvia.setColumns(10);
		
		txtTeeta = new JTextField();
		txtTeeta.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 14));
		txtTeeta.setEnabled(false);
		txtTeeta.setOpaque(false);
		txtTeeta.setDisabledTextColor(Color.BLACK);
		txtTeeta.setEditable(false);
		txtTeeta.setBorder(null);
		txtTeeta.setText("Teet‰: " + ja.getTee());
		txtTeeta.setBounds(209, 309, 96, 20);
		contentPane.add(txtTeeta);
		txtTeeta.setColumns(10);
		
		txtKaakaota = new JTextField();
		txtKaakaota.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 14));
		txtKaakaota.setEnabled(false);
		txtKaakaota.setBorder(null);
		txtKaakaota.setEditable(false);
		txtKaakaota.setDisabledTextColor(Color.BLACK);
		txtKaakaota.setOpaque(false);
		txtKaakaota.setText("Kaakaota: " + ja.getKaakao());
		txtKaakaota.setBounds(210, 533, 137, 20);
		contentPane.add(txtKaakaota);
		txtKaakaota.setColumns(10);
	}
	public static void kahviClr(JTextField txtKahvia, JuomaAutomaatti ja) {
		if(ja.getKahvi() < 10) {
			txtKahvia.setDisabledTextColor(Color.RED);
		}//like the blood of my victims S
		else {
			txtKahvia.setDisabledTextColor(Color.BLACK);
		}
	}
	public static void teeClr(JTextField txtTeeta, JuomaAutomaatti ja) {
		if(ja.getTee() < 10) {
			txtTeeta.setDisabledTextColor(Color.RED);
		}
		else {
			txtTeeta.setDisabledTextColor(Color.BLACK);
		}
	}
	public static void kaakaoClr(JTextField txtKaakaota, JuomaAutomaatti ja) {
		if(ja.getKaakao() < 10) {
			txtKaakaota.setDisabledTextColor(Color.RED);
		}
		else {
			txtKaakaota.setDisabledTextColor(Color.BLACK);
		}
	}
}
