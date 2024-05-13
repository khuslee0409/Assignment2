package toBeCompleted.stage1;

public class Card {
    public Suit suit;
    public Rank rank;

    /**
     * Constructor for Card
     * @param ranks
     * @param suits
     */
    public Card(Rank ranks, Suit suits) {
        this.rank = ranks;
        this.suit = suits;
    }

    /**
     * Returns the string representation of the card as a combination of rank and suit.
     * See test cases for examples.
     * 
     */
    public String toString() {
        String r = Character.toString(this.rank.symbol);
        String s = Character.toString(this.suit.symbol);
        return r + s;
    }

    /**
     * Returns the value of the card as an integer.
     * @return value of the card as calculated by the formula (rank.value * 10 + suit.value)
     */
    public int value() {
        return rank.value*10 + suit.value;
    } 

    /**
     * @param other
     * @return the difference between the value of the calling object and the parameter object.
     * See test cases for examples.
     */
    public int compareTo(Card other) {
        return this.value() - other.value();
    }

    /**
     * @param other
     * @return true if the value of the calling object is equal to the value of the parameter object.
     * See test cases for examples.
     */
    public boolean equals(Card other) {
        return this.value() == other.value();
    }
}