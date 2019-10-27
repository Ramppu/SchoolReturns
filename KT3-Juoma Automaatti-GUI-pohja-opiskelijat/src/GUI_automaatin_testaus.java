
public class GUI_automaatin_testaus {

	public static void main(String[] args) {
		
		// Huomaa, että Juoma-automaatti-luokkaa voidaan käyttää myös toisesta luokasta käsin
		
		JuomaAutomaatti ja = new JuomaAutomaatti();
		GUI_Automaatti x = new GUI_Automaatti( ja );
		x.setVisible(true);
		

	}

}
