package toBeCompleted.stage3;

import java.util.ArrayList;

import toBeCompleted.stage1.*;
import toBeCompleted.stage2.*;

@SuppressWarnings("unused")
public class Hand {
    public String player;
    public ArrayList<Card> cardsInHand;

    /**
     * Constructor 1 for Hand
     * Initializes the hand with n cards drawn from the deck
     * @param player. Assign "TBD" to instance variable if parameter is null
     * @param n
     * @param deck
     */
    public Hand(String player, int n, Deck deck) {
        this.player = (player != null) ? player : "TBD"; //player not null= true but in condition that it has to be TBD
        this.cardsInHand = new ArrayList<>(); //New arraylist data
        
        for (int i = 0; i < n; i++) {
            Card card = deck.drawCard(); //card reference to function
            if (card != null) {
                cardsInHand.add(card);//array value added
            } else {
                System.out.println("No more cards!");
                break; //when there is no card
            }
        }
    }
    

    /**
     * Constructor 2 for Hand
     * @param player. Assign "TBD" to instance variable if parameter is null
     * @param cards ArrayList of cards to be copied into the instance variable
     */
    public Hand(String player, ArrayList<Card> cards) {
        this.player = (player != null) ? player : "TBD";// same condition as Hand1
        this.cardsInHand = new ArrayList<>(cards);
    }

    /**
     * Add a card to the hand
     * @param card
     * @return false if card is null, true otherwise
     */
    public boolean addCard(Card card) { //same if as Hand1
        if (card != null) {
            cardsInHand.add(card);
            return true;
        } else {
            System.out.println("No more cards!");
            return false;
        }
    }

    /**
     * @param card
     * @return false if card is null or not in the hand, true otherwise
     * IMPORTANT: contains method of ArrayList checks for existence of a reference
     * that refers to the SAME instance as the parameter, but the tests are creating
     * clones, so you will need to figure out how to check the contents, rather than reference.
     * In short, do not use the contains method
     */
    public boolean hasCard(Card key) {
        for (Card card : cardsInHand) {//if same
            if (card.equals(key)) {//if card =key card
                return true;
            }
        }
        return false;
    }
    

    /**
     * Remove a card from the hand
     * @return the card removed, null if the hand is empty
     */
    public Card removeRandomCard() {
        if (cardsInHand.isEmpty()) {//condition of empty
            return null;
        }
        int randomIndex = (int) (Math.random() * cardsInHand.size());//using math function to create index
        return cardsInHand.remove(randomIndex);//remove the index number
    }
    

    /**
     * Steal a card from another hand. Card should be removed from the other hand
     * @param other
     * @return the card stolen, null if the other hand is empty
     */
    public Card stealCardFrom(Hand other) {
        if (other.cardsInHand.isEmpty()) {
            return null;
        }
        int randomIndex = (int) (Math.random() * other.cardsInHand.size());
        Card stolenCard = other.cardsInHand.remove(randomIndex);
        cardsInHand.add(stolenCard);
        return stolenCard;
    }///
    

    /**
     * Remove a card from the hand with a specific suit, if any
     * @param suitName
     * @return the card removed, null if the hand is empty or no card with the suit is found
     */
    public Card removeCardBySuit(String suitName) {
        return 0;
    //todo
    }
    

    /**
     * 
     * @return the maximum number of cards in the hand that have the same suit.
     * For example, if the hand has 5 cards of hearts, 7 of diamonds and 3 of clubs, 
     * the method should return 7.
     * See test cases for examples.
     */
    public int sameSuitCards() {
        return 0; //TODO
    }

    /**
     * 
     * @return true if there are at least 3 cards in the hand and 
     * all cards in the hand have the same suit, false otherwise
     * See test cases for examples.
     */
    public boolean allSameSuit() {
        return false; //TODO
    }

    /**
     * Arrange the cards in the hand in ascending order of their values
     * In case of tie (when two cards have the same values), the card that originally occurred first
     * should occur first after the sorting is done.
     */
    public void arrange() {
        //TODO
    }

    /**
    * A sequence is defined as a series of cards, each with rank one more than the previous one.
    * A sequence length is the length of the longest sequence that exists in the hand.
    * IMPORTANT: Ace can be treated as rank 1 or 14 for this purpose.
    * For example,
    * 
    * For a full deck, the sequence length will be 13 as it contains cards of all ranks from 2 to 14 (or 1 to 13).
    * For a deck that is missing all four Kings, the sequence length will be 12.
    * 
    * 5 of hearts, 3 of diamonds, 4 of clubs, 2 of spades, 5 of spades, 6 of hearts is a sequence of length 5 (even though not arranged properly)
    * Jack of hearts, Jack of diamonds, Queen of hearts, King of hearts, Ace of hearts is a sequence of length 4
    * 2 of hearts, 3 of diamonds, 4 of clubs, 5 of spades, 6 of hearts, Jack of spades, Ace of diamonds has a sequence of length 6
    * (since Ace can be considered as 1 or 14, and then 1, 2, 3, 4, 5, 6 is a sequence of length 6)
    * 10 of hearts, Jack of hearts, King of hearts has a sequence of length 2 (only 10 and Jack, but not King)
    * Two of hearts, Ace of clubs, King of hearts is a sequence of length 2 ((Ace and Two) OR (King and Ace))
    * @return the length of the longest "sequence" of cards in the hand.
    */
    public int sequenceLength() {
        return 0; //TODO
    }

    /**
     * 
     * @return true if the hand has at least 3 cards, and all cards form a complete sequence, false otherwise.
     * IMPORTANT: the cards don't need to be arranged in order to be considered a sequence.
     * Ace can be treated as rank 1 or 14 for this purpose.
     * For example, 7 of hearts, 3 of diamonds, 6 of clubs, 4 of spades, 5 of hearts is a sequence of length 5.
     * Four of hearts, 2 of hearts, Jack of spades, Three of hearts, Ace of clubs is a sequence of length 4 (A, 2, 3, 4)
     * 
     */
    public boolean sequence() {
        return false; //TODO
    }
    
    /**
     * 
     * @return true if the calling object has neither all same suit cards, 
     * nor a perfect sequence of cards; false otherwise
     */    
    public boolean agreeToSwap() {
        return false; //TODO
    }

     /**
     * Swap a card from this hand with a random card from another hand, if both parties agree
     * (determined using agreeToSwap method)
     * @param idx index of the card in this hand to be swapped
     * @param other the other hand
     * @return true if the swap is successful, false otherwise
     */
    public boolean swap(int idx, Hand other) {
        return false; //TODO
    }

    /**
     * 
     * @return the total value of the cards in the hand.
     */
    public int value() {
        return 0; //TODO
    }

    //DO NOT MODIFY
    public String toString() {
        if(cardsInHand.size() == 0) {
            return "Empty hand";
        }
        String special = "";
        if(allSameSuit() && sequence()) {
            special = "Same suit sequence";
        }
        else if(allSameSuit()) {
            special = "Same suit";
        }
        else if(sequence()) {
            special = "Sequence";
        }
        String result = "===================\n"+player+"'s "+cardsInHand.size()+"-card hand:\n";
        if(!special.isEmpty()) {
            result+=special+"\n";
        }
        for(Card card : cardsInHand) {
            result += card + ", ";
        }
        result = result.substring(0, result.length() - 2);
        result+="\nTotal value: "+value();
        return result+"\n===================\n";
    }

    //DO NOT MODIFY
    public String toStringCompact() {
        String s = this.player + ": ";
        if(allSameSuit()) {
            s += "SAME SUIT ";
        }
        if(sequence()) {
            s += "SEQUENCE ";
        }
        for(Card c: cardsInHand) {
            s += c.rank.symbol +"" + c.suit.symbol;
        }
        return s + " ("+value()+")";
    }
}
