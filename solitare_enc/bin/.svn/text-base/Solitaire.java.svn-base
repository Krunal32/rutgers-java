import java.io.IOException;
import java.util.Scanner;
import java.util.Random;
import java.util.NoSuchElementException;

/**
 * This class implements a simplified version of Bruce Schneier's Solitaire Encryption algorithm.
 * 
 * @author Steven J. Lu (sjlu@eden.rutgers.edu)
 */
public class Solitaire {
	
	/**
	 * Circular linked list that is the deck of cards for encryption
	 */
	CardNode deckRear;
	
	/**
	 * Makes a shuffled deck of cards for encryption. The deck is stored in a circular
	 * linked list, whose last node is pointed to by the field deckRear
	 */
	public void makeDeck() {
		// start with an array of 1..28 for easy shuffling
		int[] cardValues = new int[28];
		// assign values from 1 to 28
		for (int i=0; i < cardValues.length; i++) {
			cardValues[i] = i+1;
		}
		
		// shuffle the cards
		Random randgen = new Random();
 	        for (int i = 0; i < cardValues.length; i++) {
	            int other = randgen.nextInt(28);
	            int temp = cardValues[i];
	            cardValues[i] = cardValues[other];
	            cardValues[other] = temp;
	        }
	     
	    // create a circular linked list from this deck and make deckRear point to its last node
	    CardNode cn = new CardNode();
	    cn.cardValue = cardValues[0];
	    cn.next = cn;
	    deckRear = cn;
	    for (int i=1; i < cardValues.length; i++) {
	    	cn = new CardNode();
	    	cn.cardValue = cardValues[i];
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
	    }
	}
	
	/**
	 * Makes a circular linked list deck out of values read from scanner.
	 */
	public void makeDeck(Scanner scanner) 
	throws IOException {
		CardNode cn = null;
		if (scanner.hasNextInt()) {
			cn = new CardNode();
		    cn.cardValue = scanner.nextInt();
		    cn.next = cn;
		    deckRear = cn;
		}
		while (scanner.hasNextInt()) {
			cn = new CardNode();
	    	cn.cardValue = scanner.nextInt();
	    	cn.next = deckRear.next;
	    	deckRear.next = cn;
	    	deckRear = cn;
		}
		
		this.printList(deckRear);
	}
	
	private void moveCardBack(int value)
	{
		CardNode ptr = deckRear;
		
		if (ptr.cardValue == value)
		{
			int temp_value = ptr.cardValue;
			ptr.cardValue = ptr.next.cardValue;
			ptr.next.cardValue = temp_value;
			
			return;
		}
		
		ptr = ptr.next;
		
		while (ptr != deckRear)
		{
			if (ptr.cardValue == value)
			{
				int temp_value = ptr.cardValue;
				ptr.cardValue = ptr.next.cardValue;
				ptr.next.cardValue = temp_value;
				
				return;
			}
			ptr = ptr.next;
		}
	}
	
	private void shiftDeckByPoints(int beg, int end)
	{
		// we need to do three operations
		// last/end to rear pointer
		CardNode ptr = deckRear.next;
		CardNode tempRear = null;
		
		while (ptr != deckRear)
		{
			if (ptr.cardValue == end)
			{
				ptr = ptr.next;
				//we are at end+1 position
				
				CardNode cn = null;
				cn = new CardNode();
			    cn.cardValue = ptr.cardValue; // our new card has value
			    cn.next = cn;
			    tempRear = cn;
			    
			    if (ptr == deckRear)
			    {
			    	break;
			    }
			    
			    ptr = ptr.next;
			    
				while (ptr != deckRear)
				{
					cn = new CardNode();
			    	cn.cardValue = ptr.cardValue;
			    	cn.next = tempRear.next;
			    	tempRear.next = cn;
			    	tempRear = cn;
			    	
			    	ptr = ptr.next;
				}
				
				// if while loop breaks, we still need to add the rear.
				cn = new CardNode();
		    	cn.cardValue = ptr.cardValue;
		    	cn.next = tempRear.next;
		    	tempRear.next = cn;
		    	tempRear = cn;
		    	
		    	break;
		    	
			}
			
			ptr = ptr.next;
		} // end while
		
		// now we need to take everything from the beginning to end (mid entries)
		ptr = deckRear.next;
		while (ptr != deckRear)
		{
			if (ptr.cardValue == beg)
			{
				// we want to remain at our beg ptr to take that in
				
				
				CardNode cn = null;
				cn = new CardNode();
			    cn.cardValue = ptr.cardValue; // our new card has value
			    if (tempRear == null)
				{
			    	cn.next = cn;
			    	tempRear = cn;
				}
			    else
			    {
			    	cn.next = tempRear.next;
			    	tempRear.next = cn;
			    	tempRear = cn;
			    }
			    			    
			    ptr = ptr.next;
			    
				while (ptr.cardValue != end)
				{
					cn = new CardNode();
			    	cn.cardValue = ptr.cardValue;
			    	cn.next = tempRear.next;
			    	tempRear.next = cn;
			    	tempRear = cn;
			    	
			    	ptr = ptr.next;
				}
				
				// we also want to take the end card now
				cn = new CardNode();
		    	cn.cardValue = ptr.cardValue;
		    	cn.next = tempRear.next;
		    	tempRear.next = cn;
		    	tempRear = cn;
		    	
		    	break;
			}
			
			ptr = ptr.next;
		} // end while
		
		// now we need to grab everything from deckRear+1 to beg, but emitting beg
		ptr = deckRear.next;
		
		CardNode cn = null;
		while (ptr.cardValue != beg)
		{
			cn = new CardNode();
	    	cn.cardValue = ptr.cardValue;
	    	cn.next = tempRear.next;
	    	tempRear.next = cn;
	    	tempRear = cn;
	    	
	    	ptr = ptr.next;
		}
		
		deckRear = tempRear;
	}
	
	/**
	 * Implements Step 1 - Joker A - on the deck.
	 */
	// Joker A = 27
	void jokerA() {
		this.moveCardBack(27);
	}
	
	/**
	 * Implements Step 2 - Joker B - on the deck.
	 */
	void jokerB() {
	    this.moveCardBack(28);
	    this.moveCardBack(28);
	}
	
	/**
	 * Implements Step 3 - Triple Cut - on the deck.
	 */
	void tripleCut() {
		CardNode ptr = deckRear.next;
		while(ptr != deckRear)
		{
			if (ptr.cardValue == 27)
			{
				this.shiftDeckByPoints(27, 28);
				break;
			}
			else if (ptr.cardValue == 28)
			{
				this.shiftDeckByPoints(28, 27);
				break;
			}
			
			ptr = ptr.next;
		}
	}
	
	/**
	 * Implements Step 4 - Count Cut - on the deck.
	 */
	void countCut() {
		CardNode ptr = deckRear;
		int value = deckRear.cardValue;
		
		if (value == 28)
		{
			value = 27;
		}
		
		for (int i = 0; i < value; i++)
		{
			ptr = ptr.next;
		}
		
		if (deckRear.cardValue == 1)
		{
			this.moveCardBack(1);
		}
		else
		{
			// selects the first card in the deck, and the value of the card of the position number of the last
			this.shiftDeckByPoints(deckRear.next.cardValue, ptr.cardValue); 
		}
		
		ptr = deckRear.next;
		CardNode ptr_prev = deckRear;
		
		while (ptr != deckRear)
		{
			if (ptr.cardValue == value)
			{
				ptr_prev.next = ptr.next;
				break;
			}
			
			ptr_prev = ptr;
			ptr = ptr.next;
		}
		
		CardNode last = new CardNode();
		last.cardValue = value;
		last.next = deckRear.next;
		deckRear.next = last;
		deckRear = last;
		// COMPLETE THIS METHOD
	}
	
	/**
	 * Utility method that prints a circular linked list, given its rear pointer
	 * 
	 * @param rear Rear pointer
	 */
	private static void printList(CardNode rear) {
		if (rear == null) { 
			return;
		}
		System.out.print(rear.next.cardValue);
		CardNode ptr = rear.next;
		do {
			ptr = ptr.next;
			System.out.print("," + ptr.cardValue);
		} while (ptr != rear);
		System.out.println("\n");
	}

	/**
	 * Implements Step 5 - key = INCLUDING repeating the whole process if the key turns
	 * out to be a joker.
	 * 
	 * @return Key between 1 and 26
	 */
	int getKey() {
		int card_number = deckRear.next.cardValue;
		if (card_number == 28)
		{
			card_number = 27;
		}
		
		CardNode ptr = deckRear;

		for (int i = 0; i < card_number; i++)
		{
			ptr = ptr.next;
		}
		// COMPLETE THIS METHOD
		// THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return ptr.next.cardValue;
	}
	
	private int computeKey()
	{
		while (true)
		{
			this.jokerA();
			this.jokerB();
			this.tripleCut();
			this.countCut();
			if (this.getKey() != 27 && this.getKey() != 28)
			{
				return this.getKey();
			}
		}
	}
	
	/**
	 * Encrypts a message, ignores all characters except upper case letters
	 * 
	 * @param message Message to be encrypted
	 * @return Encrypted message, a sequence of upper case letters only
	 */
	public String encrypt(String message) {
		String output = "";
		message = message.toUpperCase();
		for (int i = 0; i < message.length(); i++)
		{
			int term1 = ((int) message.charAt(i)) - ((int) 'A') + 1;
			if (term1 < 1 || term1 > 26)
			{
				continue;
			}
			int term2 = this.computeKey();
			
			int enc_term = term1+term2;
			if (enc_term > 26)
			{
				enc_term = enc_term-26;
			}
			
			output = output + (char) (enc_term-1+'A'); //(char)(c-1+'A'); 
		}
			
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return output;
	}
	
	/**
	 * Decrypts a message, which consists of upper case letters only
	 * 
	 * @param message Message to be decrypted
	 * @return Decrypted message, a sequence of upper case letters only
	 */
	public String decrypt(String message) {	
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
		String output = "";
		message = message.toUpperCase();
		for (int i = 0; i < message.length(); i++)
		{
			int term1 = ((int) message.charAt(i)) - ((int) 'A') + 1;
			if (term1 < 1 || term1 > 26)
			{
				continue;
			}
			int term2 = this.computeKey();
			
			int enc_term = term1-term2;
			if (enc_term < 1)
			{
				enc_term = enc_term+26;
			}
			
			output = output + (char) (enc_term-1+'A'); //(char)(c-1+'A'); 
		}
			
		// COMPLETE THIS METHOD
	    // THE FOLLOWING LINE HAS BEEN ADDED TO MAKE THE METHOD COMPILE
	    return output;
	}
}