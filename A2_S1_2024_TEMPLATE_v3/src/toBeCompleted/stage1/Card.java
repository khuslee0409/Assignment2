package toBeCompleted.stage1;

public class Card {
    public Suit suit;
    public Rank rank;

    /**
     * Constructor for Card
     * @param rank
     * @param suit
     */
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    /**
     * Returns the string representation of the card as a combination of rank and suit.
     * See test cases for examples.
     */
    public String toString() {
        if(rank.value < 10){
            return this.rank.value + this.suit.name.substring(0, 1);
        }
        return this.rank.name.substring(0, 1) + this.suit.name.substring(0,1) ;
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