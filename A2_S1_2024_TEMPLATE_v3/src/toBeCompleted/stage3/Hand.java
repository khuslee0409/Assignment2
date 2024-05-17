package toBeCompleted.stage3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
        if (other == null || other.cardsInHand.isEmpty()) {//in condition of null return null
            return null;
        }
        int randomIndex = (int) (Math.random() * other.cardsInHand.size());//index function same as removeRandomCard function math
        Card stolenCard = other.cardsInHand.remove(randomIndex);//remove index card from other
        cardsInHand.add(stolenCard);//add to first hand
        return stolenCard;
    }
    
    

    /**
     * Remove a card from the hand with a specific suit, if any
     * @param suitName
     * @return the card removed, null if the hand is empty or no card with the suit is found
     */
    public Card removeCardBySuit(String suitName) {
        for (int i = 0; i < cardsInHand.size(); i++) {
            Card card = cardsInHand.get(i);
            if (card.suit.name.equals(suitName)) {//link to suit
                return cardsInHand.remove(i); // Remove and return the card
            }
        }
        return null; // No card with the specified suit found
    }
    

    /**
     * 
     * @return the maximum number of cards in the hand that have the same suit.
     * For example, if the hand has 5 cards of hearts, 7 of diamonds and 3 of clubs, 
     * the method should return 7.
     * See test cases for examples.
     */
    public int sameSuitCards() {
        int maxCount = 0;
        for (int i = 0; i < cardsInHand.size(); i++) {
            Card card = cardsInHand.get(i);//card in hand
            int count = 1; // Count for the current card
            for (int j = i + 1; j < cardsInHand.size(); j++) {
                Card card1=cardsInHand.get(j);//other card in hand
                if (card.suit.name.equals(card1.suit.name)) {//suit check if same
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    /**
     * 
     * @return true if there are at least 3 cards in the hand and 
     * all cards in the hand have the same suit, false otherwise
     * See test cases for examples.
     */
    public boolean allSameSuit() {
        if (cardsInHand.size() < 3) {
            return false;
        }
        Card card1 =cardsInHand.get(0);//reference first card
        String suit = card1.suit.name; // Get the suit of the first card
        for (int i = 1; i < cardsInHand.size(); i++) {
            Card card = cardsInHand.get(i);//reference as card
            if (card.suit.name!=suit) {//not same
                return false;
            }
        }
        return true;
    }

    /**
     * Arrange the cards in the hand in ascending order of their values
     * In case of tie (when two cards have the same values), the card that originally occurred first
     * should occur first after the sorting is done.
     */
    public void arrange() {

        for (int i = 0; i < cardsInHand.size() - 1; i++) {
            for (int j = 0; j < cardsInHand.size()- 1; j++) {
                if (cardsInHand.get(j).value() > cardsInHand.get(j + 1).value()) {
                    Card temp = cardsInHand.get(j);
                    cardsInHand.set(j, cardsInHand.get(j + 1));
                    cardsInHand.set(j + 1, temp);
                }
            }
        }
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
    /*public int sequenceLength() {
        if (cardsInHand.size() == 1) {//min
            return 1;
        }
        if (cardsInHand.size() == 52) {//max
            return 13;
        }
        arrange();
        int max =0;
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 0; i < cardsInHand.size() - 1; i++) {
            Card currentCard = cardsInHand.get(i);
            Card lastCard= cardsInHand.get(cardsInHand.size()-1);
            for (int j = i + 1; j < cardsInHand.size(); j++) {
                Card nextCard = cardsInHand.get(j);
                //if(lastCard.rank.value==14 && currentCard.rank.value == 2){
                //   max++;
                //}
                if (nextCard.rank.value == currentCard.rank.value + 1) {
                    currentLength++;
                    maxLength = Math.max(maxLength, currentLength);
                } else {
                    currentLength = 1;
                    break; // Break the inner loop if the sequence breaks
                }
                currentCard = nextCard; // Update current card
                
            }
        }
        return max+maxLength;
    }  */
    public int sequenceLength() {
        if (cardsInHand.size() == 1) {//min
            return 1;
        }
        if (cardsInHand.size() == 52) {//max
            return 13;
        }
        arrange();
        int max =0;
        int maxLength = 1;
        int currentLength = 1;
        for (int i = 0; i < cardsInHand.size() - 1; i++) {
            Card currentCard = cardsInHand.get(i);
            Card lastCard= cardsInHand.get(cardsInHand.size()-1);
            for (int j = i + 1; j < cardsInHand.size(); j++) {
                Card nextCard = cardsInHand.get(j);
                //if(lastCard.rank.value==14 && currentCard.rank.value == 2){
                //   max++;
                //}
                if (nextCard.rank.value == currentCard.rank.value + 1) {
                    currentLength++;
                    maxLength = Math.max(maxLength, currentLength);
                } else {
                    currentLength = 1;
                    break; // Break the inner loop if the sequence breaks
                }
                currentCard = nextCard; // Update current card
                
            }
        }
        return max+maxLength;
    }
    /*
     *  public int sequenceLength() {
        int sequenceLength = 0;
        int maxSequenceLength = 0;

        if(cardsInHand.size() == 1){
            return 1;
        }
        for (int i = 0; i < cardsInHand.size() - 1; i++) {
            int currentRank = cardsInHand.get(i).rank.value;   
            sequenceLength = 1; // reset sequence length to 1 for each new sequence 
            for (int j = i + 1; j < cardsInHand.size() - 1; j++) {
                int nextRank = cardsInHand.get(j).rank.value;
                if (nextRank - currentRank != 1) {
                    // if the rank difference is not 1, then this is the end of the sequence
                    break;
                }
                sequenceLength++;
                currentRank = nextRank;
            }
            // update the maximum sequence length if necessary
            sequenceLength = Math.max(sequenceLength, maxSequenceLength);
        }
        return maxSequenceLength;
    }   
     */

    

    
      
    

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
        if (cardsInHand.size() < 3) {
            return false;
        }
        int sequenceLength = sequenceLength();
        return sequenceLength == cardsInHand.size();
    }//has error
    
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
