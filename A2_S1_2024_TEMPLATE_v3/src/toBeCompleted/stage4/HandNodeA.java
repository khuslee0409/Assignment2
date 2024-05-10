package toBeCompleted.stage4;
 
import toBeCompleted.stage3.*;

public class HandNodeA {
    public Hand hand;
    public HandNodeA next;

    /**
     * Constructor for HandNodeA
     * @param hand
     * @param next
     */
    public HandNodeA(Hand hand, HandNodeA next) {
        //TODO
    }

    /**
     * @return the number of hands in the linked list.
     */
    public int size() {
        return 0; //TODO
    }

    /**
     * 
     * @param idx
     * @return the hand at the given index in the linked list, null if index is out of bounds.
     * (first hand is at index 0)
     */
    public Hand get(int idx) {
        return null; //TODO
    }

    /**
     * 
     * @return the FIRST hand starting at the calling object
     * that is a "sequence" OR has all cards of the same suit.
     * NOTE: This is a simplified implementation, and not really
     * a fair evaluation of who should actually win - that is an advanced 
     * algorithm, tackled by winnerAdvanced.
     */
    public Hand winner() {
        return null; //TODO
    }

    /**
     * 
     * @return the correct winner according to following rules:
     * 
     * 1. Sequence + Same Suit trumps Same Suit
     * 2. Same Suit trumps Sequence
     * 3. Sequence trumps value
     * 
     * In case of tie, use value as a resolver.
     * If there is still a tie, the hand that appears first in the list is the winner.
     */
    public Hand winnerAdvanced() {
        return null; //TODO
    }

    //DO NOT MODIFY
    public String toString() {
        if(next == null) {
            return hand.toString();
        }
        return hand.toString() + " \n" + next.toString();
    }

    //DO NOT MODIFY
    public String toStringCompact() {
        if(next == null) {
            return hand.toStringCompact();
        }
        return hand.toStringCompact() + " -> " + next.toStringCompact();
    }
}
