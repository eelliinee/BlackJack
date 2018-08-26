
public class Kaart {
	private String naam;
	private int waarde;
	private int altWaarde;
	
	Kaart(String naam, int waarde) {
		this(naam, waarde, waarde);
		this.naam = naam;
		this.waarde = waarde;
	}
	
	Kaart(String naam, int waarde, int altWaarde) {
		this.naam = naam;
		this.waarde = waarde;
		this.altWaarde = altWaarde;
	}
	
	// getters
	public String getNaam() {
		return this.naam;
	}
	
	public int getWaarde() {
		return this.waarde;
	}
	
	public int getAltWaarde() {
		return this.altWaarde;
	}
}
