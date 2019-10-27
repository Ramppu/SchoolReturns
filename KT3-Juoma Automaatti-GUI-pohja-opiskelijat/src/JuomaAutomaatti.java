// Esimerkkiratkaisu



public class JuomaAutomaatti {

	private int tee;
	private int kahvi;
	private int kaakao;

	public JuomaAutomaatti() {
		this.tee = 50;
		this.kahvi = 50;
		this.kaakao = 50;
	}



	public int getTee() {
		return tee;
	}

	public void setTee(int tee) {
		this.tee = tee;
	}

	public int getKahvi() {
		return kahvi;
	}

	public void setKahvi(int kahvi) {
		this.kahvi = kahvi;
	}

	public int getKaakao() {
		return kaakao;
	}

	public void setKaakao(int kaakao) {
		this.kaakao = kaakao;
	}

	public void valmistaKahvi() {
		System.out.println("Odota hetki, kahvisi valmistuu. ");
		if (this.kahvi - 10 < 0) {
			this.kahvi = 0;
			System.out.println("kahvia ei ole enää jäljellä! Täytä säiliö.");
		}

		else
			this.kahvi -= 10;

		this.toString();
	}
 

	public void valmistaTee() {
		System.out.println("Odota hetki, teesi valmistuu. ");
		if (this.tee - 10 < 0) {
			this.tee = 0;
			System.out.println("teetä ei ole enää jäljellä! Täytä säiliö.");
		}

		else
			this.tee -= 10;

		this.toString();

	}

 

	public void valmistaKaakao() {
		System.out.println("Odota hetki, kaakaosi valmistuu. ");
		if (this.kaakao - 10 < 0) {
			this.kaakao = 0;
			System.out.println("kaakaota ei ole enää jäljellä! Täytä säiliö.");
		}

		else
			this.kaakao -= 10;

		this.toString();
	}

	@Override
	public String toString() {
		return "JuomaAutomaatti [tee=" + tee + ", kahvi=" + kahvi + ", kaakao=" + kaakao + "]";
	}
}