import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class GamePlay {
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	private ArrayList<Card> cardHand = new ArrayList<Card>();
	private int cardIndex;
	private boolean playing;
	
	
	public GamePlay() {
		cardDeckGenerator(); 
	}
	
	public void play() {
		printDeck();
		
		playing = true;
		System.out.println("Welkom bij Blackjack. Typ (q) om te stoppen met spelen.");
		
		startGame();
		continueGame();
	}
	
	// making and printing the card deck
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
	
	private void printDeck() {
		for (int i=0; i<cardDeck.size(); i++) {
			Card drawnCard = cardDeck.get(i);
			System.out.print(drawnCard.getName() + ". ");
		}
		System.out.println(" ");
		
		System.out.println("----------------------------------");
	}
	
	//making and printing (the cards in) the hand
	private Card getCard() {
		Card drawnCard = cardDeck.get(cardIndex);
		cardHand.add(drawnCard);
		
		cardIndex++;
		
		// when not enough cards in cardDeck, shuffle, print and keep playing
		if (cardIndex >= cardDeck.size()) {
			Collections.shuffle(cardDeck);
			printDeck();
			cardIndex = 0;
		}
		
		return drawnCard;
	}
	
	private void displayHand() {
		String cardHandNames = "";
		for (int i = 0; i <cardHand.size(); i++) {
			
			if(i != 0) {
				cardHandNames += ", ";
			}
			
			cardHandNames += cardHand.get(i).getName();
		}
		System.out.println("Hand: " + cardHandNames);
	}
	
	// calculating score
	private int getScore() {
		int score = 0;
		for (int i=0; i < cardHand.size(); i++) {
			score = score + cardHand.get(i).getValue(); 
		}
		if (score > 21) {
			return getAltScore();
		}
		return score;
	}
	
	private int getAltScore() {
		int score = 0;
		for (int i=0; i < cardHand.size(); i++) {
			score = score + cardHand.get(i).getAltValue(); 
		}
		return score;
	}
	

	// show score
	private void displayScore() {
		System.out.println("Score: " + getScore());
	}
	
	// create and print card
	private void getAndPrintCard() {
		Card drawnCard = getCard();
		System.out.println("Getrokken kaart: " + drawnCard.getName());
	}
	
	// gameplay start
	private void startGame() {
		
		getCard();
		getCard();
		
		displayHand();
		displayScore();
		
		if (getScore() == 21) {
			System.out.println("Gefeliciteerd, u heeft nu al gewonnen!");
			System.out.println("Wilt u nog een keer spelen (s) of stoppen (q)?");
		} else {
			System.out.println("Koop een kaart (k) of pas (p)");
		}
		
	}
	
	// gameplay continued
	private void continueGame() {
		
		while (playing == true) {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			String input = scanner.next();
	
			
			switch(input) {
			case "k":
				getAndPrintCard();
				displayHand();
				int score = getScore();
				
				if (score < 21) {
					displayScore();
					System.out.println("Wilt u nog een kaart kopen (k) of passen (p)?"); 
				} else if (score == 21) {
					displayScore();
					System.out.println("Gefeliciteerd, u heeft gewonnen!");
					System.out.println("Wilt u nog een keer spelen (s) of stoppen (q)?");
				} else {
						displayScore();
						System.out.println("Helaas, u heeft verloren");
						System.out.println("Wilt u nog een keer spelen (s) of stoppen (q)?");
					
				}		
				break;
				
			case "p":
				System.out.println("Uitslag:");
				displayHand();
				System.out.println(" ");
				System.out.println("Nieuw spel:");
				cardHand.clear();
				startGame();
				break;
					
			case "s":
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


