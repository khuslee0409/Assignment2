package toBeCompleted.stage1;

public class Rank {
    public String name;
    public char symbol;
    public int value;

    /**
     * Constructor for Rank
     * @param name
     * @param symbol
     * @param value
     */
    public Rank(String name, char symbol, int value) {
        this.name = name;
        this.symbol = symbol;
        this.value = value;
    }

    /**
     * Returns the string representation of the rank.
     * @return name of the rank
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
    public int compareTo(Rank other) {
        return this.value - other.value;
    }
}
