package toBeCompleted.stage2;

import java.util.ArrayList;
import toBeCompleted.stage1.*;

public class Deck {
    public ArrayList<Card> cards;

    /**
     * Constructor for Deck
     * Initializes the deck with 52 cards.
     * 13 ranks and 4 suits.
     * 
     * The four suits are:
     * Hearts (color = "red", symbol = '♥', value = 1),
     * Diamonds (color = "red", symbol = '♦', value = 2),
     * Clubs (color = "black", symbol = '♣', value = 3),
     * Spades (color = "black", symbol = '♠', value = 4)
     * 
     * The 13 ranks are:
     * 
     * name = "2", symbol = '2', value = 2
     * name = "3", symbol = '3', value = 3
     * name = "4", symbol = '4', value = 4
     * name = "5", symbol = '5', value = 5
     * name = "6", symbol = '6', value = 6
     * name = "7", symbol = '7', value = 7
     * name = "8", symbol = '8', value = 8
     * name = "9", symbol = '9', value = 9
     * name = "10", symbol = 'X', value = 10
     * name = "Jack", symbol = 'J', value = 11
     * name = "Queen", symbol = 'Q', value = 12
     * name = "King", symbol = 'K', value = 13
     * name = "Ace", symbol = 'A', value = 14
     * 
     * Besides ArrayList and the classes from stage 1,
     * the constructor should not import any other library/class,
     * even from within the project.
     */
    public Deck() {
        
    }

    /**
     * Shuffles the deck. If you are not passing the tests, 
     * it is most likely because you are either not swapping cards correctly, 
     * or not making sufficient swaps.
     */
    public void shuffle() {
        //TODO
    }

    /**
     * Draws a card from the deck. 
     * @return Card drawn from the deck, null if deck is empty
     */
    public Card drawCard() {
        return null; //TODO
    }

    //DO NOT MODIFY
    public String toString() {
        String result = "[";
        for(Card card : cards) {
            result += card + ", ";
        }
        return result.substring(0, result.length() - 2)+"]";
    }
}
