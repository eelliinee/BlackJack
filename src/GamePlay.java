import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class GamePlay {
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	ArrayList<String> cardHand = new ArrayList<String>();
	int cardIndex = 0;
	int score = 0;
	private int cardScore;
	
	
	private void suitGenerator(String suit) {
		for (int i=2; i<=10; i++) {
			Card card = new Card(suit + i, i);
			cardDeck.add(card);
		}
		Card card = new Card(suit + "Boer", 10);
		cardDeck.add(card);
		Card card2 = new Card(suit + "Vrouw", 10);
		cardDeck.add(card2);
		Card card3 = new Card(suit + "Heer", 10);
		cardDeck.add(card3);
		Card card4 = new Card(suit + "Aas", 11, 1);
		cardDeck.add(card4);		
	}
	
	public ArrayList<Card> cardDeckGenerator() {
		suitGenerator("Schoppen ");
		suitGenerator("Harten ");
		suitGenerator("Ruiten ");
		suitGenerator("Klaveren ");
		Collections.shuffle(cardDeck);
		return cardDeck;
	}
	
	private Card getCardAddScore() {
		Card drawnCard = cardDeck.get(cardIndex);
		cardHand.add(drawnCard.getName());
		if (score + drawnCard.getValue() > 21) {
			score = score + drawnCard.getAltValue();
			cardScore = drawnCard.getAltValue();
		} else { 
			score = score + drawnCard.getValue();
			cardScore = drawnCard.getValue();
		}
		
		cardIndex++;
		return drawnCard;
	}
	
	
	
	public void play() {
		System.out.println("Welkom bij Blackjack. Typ (q) om te stoppen met spelen");
		ArrayList<Card> cardDeck = new ArrayList<Card>();
		cardDeck.addAll(cardDeckGenerator());
		for (int i=0; i<cardDeck.size(); i++) {
			Card drawnCard = cardDeck.get(i);
			System.out.print(drawnCard.getName() + ". ");
		}
		System.out.println(" ");
		
		boolean playing = true;
		
		getCardAddScore();
		getCardAddScore();
		System.out.println("Startkaarten: " + cardHand);
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
				Card drawnCard = getCardAddScore();
				System.out.println(drawnCard.getName() + ", waarde: " + cardScore);
				System.out.println("Uw score is: " + score);
				
				if (score < 21) {
					System.out.println("Wilt u nog een kaart kopen (k) of passen (p)?"); 
				} else if (score == 21) {
					System.out.println("Gefeliciteerd, u heeft gewonnen!");
					System.out.println("Uw kaarten waren: " + cardHand);
					playing = false;
				} else {
					System.out.println("Helaas, u heeft verloren");
					System.out.println("Uw kaarten waren: " + cardHand);
					playing = false;
				}
				
				
				break;
			case "p":
				System.out.println("Uw kaarten waren: " + cardHand);
				System.out.println("Uw score was " + score + ", probeer het nog een keer.");
				score = 0;
				cardHand.clear();
				System.out.println("Koop een kaart (k)");
				break;
			case "q":
				playing = false;
				break;
			}
		}
	}
	

}


