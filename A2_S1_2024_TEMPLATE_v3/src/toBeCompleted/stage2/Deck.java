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
     * name = n"7", symbol = '7', value = 7
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
        this.cards = new ArrayList<Card>();
        // Define suits
        Suit[] suits = {
            new Suit("Hearts", '♥', "red", 1),
            new Suit("Diamonds", '♦', "red", 2),
            new Suit("Clubs", '♣', "black", 3),
            new Suit("Spades", '♠', "black", 4)
        };
    
        // Define ranks
        Rank[] ranks = {
            new Rank("2", '2', 2),
            new Rank("3", '3', 3),
            new Rank("4", '4', 4),
            new Rank("5", '5', 5),
            new Rank("6", '6', 6),
            new Rank("7", '7', 7),
            new Rank("8", '8', 8),
            new Rank("9", '9', 9),
            new Rank("10", 'X', 10),
            new Rank("Jack", 'J', 11),
            new Rank("Queen", 'Q', 12),
            new Rank("King", 'K', 13),
            new Rank("Ace", 'A', 14)
        };
    
        // Add all combinations of suits and ranks to the deck
        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                Card card = new Card(rank, suit);
                cards.add(card);
            }
        }

        
    }
    

    /**
     * Shuffles the deck. If you are not passing the tests, 
     * it is most likely because you are either not swapping cards correctly, 
     * or not making sufficient swaps.
     */
    public void shuffle() {
        
        for(int i = 0; i < 20; i++){
            int random = (int)(Math.random() * (51) + 0);
            Card temp = cards.get(i);
            cards.set(i, cards.get(random));
            cards.set(random, temp);
        }
        
    }

    /**
     * Draws a card from the deck. 
     * @return Card drawn from the deck, null if deck is empty
     */
    public Card drawCard() {

        if(this.cards.size() == 0){
            return null;
        }

        int rand = (int)(Math.random()* this.cards.size());
        return this.cards.remove(rand);
 
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
