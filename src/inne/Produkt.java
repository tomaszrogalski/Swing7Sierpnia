package inne;

public class Produkt {

	String nazwa;
	String vat;

	public Produkt(String nazwa, String vat) {
		super();

		this.nazwa = nazwa;
		this.vat = vat;
	}
	public Produkt(String nazwa) {
		super();
		this.nazwa = nazwa;	
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}
}
