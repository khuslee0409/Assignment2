package toBeCompleted.stage1;

public class Suit {
    public String name;
    public char symbol;
    public String color;
    public int value;

    /**
     * Constructor for Suit
     * @param name
     * @param symbol
     * @param color
     * @param value
     */
    public Suit(String name, char symbol, String color, int value) {
        this.name = name;
        this.symbol = symbol;
        this.color = color;
        this.value = value;
    }

    /**
     * Returns the string representation of the suit.
     * @return name of the suit
     * See test cases for examples.
     */
    public String toString() {
        return this.name;
    }
    
    /**
     * @param other
     * @return the difference between the value of the calling object and the parameter object.
     * See test cases for examples.
     */
    public int compareTo(Suit other) {
        return this.value - other.value;
    }
}
