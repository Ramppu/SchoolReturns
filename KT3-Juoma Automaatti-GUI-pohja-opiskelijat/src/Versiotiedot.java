import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class Versiotiedot extends JFrame {

	private JPanel contentPane;
	private JTextField txtJuomaautomaattiV;
	private JTextField txtByRammu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Versiotiedot frame = new Versiotiedot();
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
	public Versiotiedot() {
		setTitle("Versiotiedot");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtJuomaautomaattiV = new JTextField();
		txtJuomaautomaattiV.setBorder(null);
		txtJuomaautomaattiV.setFont(new Font("Perpetua Titling MT", Font.ITALIC, 31));
		txtJuomaautomaattiV.setForeground(new Color(0, 0, 0));
		txtJuomaautomaattiV.setDisabledTextColor(Color.BLACK);
		txtJuomaautomaattiV.setEditable(false);
		txtJuomaautomaattiV.setEnabled(false);
		txtJuomaautomaattiV.setText("JUOMA-AUTOMAATTI V.1.21");
		txtJuomaautomaattiV.setBounds(10, 11, 424, 83);
		contentPane.add(txtJuomaautomaattiV);
		txtJuomaautomaattiV.setColumns(10);
		
		txtByRammu = new JTextField();
		txtByRammu.setBorder(null);
		txtByRammu.setDisabledTextColor(Color.BLACK);
		txtByRammu.setFont(new Font("Perpetua Titling MT", Font.ITALIC, 32));
		txtByRammu.setHorizontalAlignment(SwingConstants.CENTER);
		txtByRammu.setEditable(false);
		txtByRammu.setEnabled(false);
		txtByRammu.setText("BY: RAMMU");
		txtByRammu.setBounds(49, 121, 327, 110);
		contentPane.add(txtByRammu);
		txtByRammu.setColumns(10);
	}
}
