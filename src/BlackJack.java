import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class BlackJack {


	public static void main(String[] args) {
		System.out.println("Welkom bij Blackjack. Typ (q) om te stoppen met spelen");
		GamePlay gamePlay = new GamePlay();
		ArrayList<Kaart> cardDeck = new ArrayList<Kaart>();
		cardDeck.addAll(gamePlay.cardDeckGenerator());
		for (int i=0; i<cardDeck.size(); i++) {
			Kaart getrokkenKaart = cardDeck.get(i);
			System.out.print(getrokkenKaart.getNaam() + ". ");
		}
		System.out.println(" ");
		gamePlay.play();
	}

	
	
}

class GamePlay {
	private ArrayList<Kaart> cardDeck = new ArrayList<Kaart>();
	ArrayList<String> getrokkenKaarten = new ArrayList<String>();
	int kaartIndex = 0;
	int score = 0;
	
	
	private void kleurGenerator(String kleur) {
		for (int i=2; i<=10; i++) {
			Kaart kaart = new Kaart(kleur + i, i);
			cardDeck.add(kaart);
		}
		Kaart kaart = new Kaart(kleur + "Boer", 10);
		cardDeck.add(kaart);
		Kaart kaart2 = new Kaart(kleur + "Vrouw", 10);
		cardDeck.add(kaart2);
		Kaart kaart3 = new Kaart(kleur + "Heer", 10);
		cardDeck.add(kaart3);
		Kaart kaart4 = new Kaart(kleur + "Aas", 11);
		cardDeck.add(kaart4);		
	}
	
	public ArrayList<Kaart> cardDeckGenerator() {
		kleurGenerator("Schoppen ");
		kleurGenerator("Harten ");
		kleurGenerator("Ruiten ");
		kleurGenerator("Klaveren ");
		Collections.shuffle(cardDeck);
		return cardDeck;
	}
	
	private Kaart getCardAddScore() {
		Kaart getrokkenKaart = cardDeck.get(kaartIndex);
		getrokkenKaarten.add(getrokkenKaart.getNaam());
		score = score + getrokkenKaart.getWaarde();
		kaartIndex++;
		return getrokkenKaart;
	}
	
	
	public void play() {
		
		boolean playing = true;
		
		getCardAddScore();
		getCardAddScore();
		System.out.println("Startkaarten: " + getrokkenKaarten);
		System.out.println("Uw score is: " + score);
		if (score == 21) {
			System.out.println("Gefeliciteerd, u heeft nu al gewonnen!");
			playing = false;
		} else {
			System.out.println("Koop een kaart (k) of pas (p)");
		}
		
		
		while (playing == true) {
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
	
			switch(input) {
			case "k":
				Kaart getrokkenKaart = getCardAddScore();
				System.out.println(getrokkenKaart.getNaam() + ", waarde: " + getrokkenKaart.getWaarde());
				System.out.println("Uw score is: " + score);
				
				if (score < 21) {
					System.out.println("Wilt u nog een kaart kopen (k) of passen (p)?"); 
				} else if (score == 21) {
					System.out.println("Gefeliciteerd, u heeft gewonnen!");
					System.out.println("Uw kaarten waren: " + getrokkenKaarten);
					playing = false;
				} else {
					System.out.println("Helaas, u heeft verloren");
					System.out.println("Uw kaarten waren: " + getrokkenKaarten);
					playing = false;
				}
				
				
				break;
			case "p":
				System.out.println("Uw kaarten waren: " + getrokkenKaarten);
				System.out.println("Uw score was " + score + ", probeer het nog een keer.");
				score = 0;
				getrokkenKaarten.clear();
				System.out.println("Koop een kaart (k)");
				break;
			case "q":
				playing = false;
				break;
			}
		}
	}
	

}

class Kaart {
	private String naam;
	private int waarde;
	Kaart(String naam, int waarde) {
		this.naam = naam;
		this.waarde = waarde;
	}
	public String getNaam() {
		return this.naam;
	}
	public int getWaarde() {
		return this.waarde;
	}
}

