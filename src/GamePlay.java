import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class GamePlay {
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	private ArrayList<String> cardHand = new ArrayList<String>();
	private int cardIndex;
	private int score;
	private int cardScore;
	private boolean playing;
	
	GamePlay() {
		cardDeckGenerator(); 
	}
	
	public void play() {
		printDeck();
		
		playing = true;
		System.out.println("Welkom bij Blackjack. Typ (q) om te stoppen met spelen.");
		
		startGame();
		continueGame();
	}
	
	private void suitGenerator(String suit) {
		for (int i=2; i<=10; i++) {
			Card card = new Card(suit + i, i);
			cardDeck.add(card);
		}
		
		Card cardJack = new Card(suit + "Boer", 10);
		cardDeck.add(cardJack);
		Card cardQueen = new Card(suit + "Vrouw", 10);
		cardDeck.add(cardQueen);
		Card cardKing = new Card(suit + "Heer", 10);
		cardDeck.add(cardKing);
		Card cardAce = new Card(suit + "Aas", 11, 1);
		cardDeck.add(cardAce);		
	}
	
	private ArrayList<Card> cardDeckGenerator() {
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
	
	private void printDeck() {
		for (int i=0; i<cardDeck.size(); i++) {
			Card drawnCard = cardDeck.get(i);
			System.out.print(drawnCard.getName() + ". ");
		}
		System.out.println(" ");
		
		System.out.println("----------------------------------");
	}
	
	private void startGame() {
		
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
		
	}
	
	private void continueGame() {
		
		while (playing == true) {
			@SuppressWarnings("resource")
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
					System.out.println("Wilt u verder spelen (s) of stoppen (q)?");
				} else {
					System.out.println("Helaas, u heeft verloren");
					System.out.println("Uw kaarten waren: " + cardHand);
					System.out.println("Wilt u verder spelen (s) of stoppen (q)?");
				}
				
				break;
				
			case "p":
				System.out.println("Uw kaarten waren: " + cardHand);
				System.out.println("Uw score was " + score + ", probeer het nog een keer.");
				score = 0;
				cardHand.clear();
				startGame();
				break;
					
			case "s":
				score = 0;
				cardHand.clear();
				startGame();
				break;
				
			case "q":
				playing = false;
				break;
				
			}
			
		
	
			
		}
	}
	

	

}


