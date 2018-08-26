
public class Card {
	private String name;
	private int value;
	private int altValue;
	
	Card(String name, int value) {
		this(name, value, value);
		this.name = name;
		this.value = value;
	}
	
	Card(String name, int value, int altValue) {
		this.name = name;
		this.value = value;
		this.altValue = altValue;
	}
	
	// getters
	public String getName() {
		return this.name;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public int getAltValue() {
		return this.altValue;
	}
}
