package toBeCompleted.stage5;

import toBeCompleted.stage2.*;
import toBeCompleted.stage3.*;

public class HandNodeBClient {
    public static void main(String[] args) {
        Deck d = new Deck();
        d.shuffle();

        int nCards = 4;

        Hand h1 = new Hand("Andy", nCards, d);
        Hand h2 = new Hand("Bing", nCards, d);
        Hand h3 = new Hand("Cindy", nCards, d);
        Hand h4 = new Hand("David", nCards, d);
        Hand h5 = new Hand("Eve", nCards, d);
        Hand h6 = new Hand("Frank", nCards, d);
        
        HandNodeB hn6 = new HandNodeB(h6, null, null);
        HandNodeB hn5 = new HandNodeB(h5, null, hn6);
        HandNodeB hn4 = new HandNodeB(h4, null, hn5);
        HandNodeB hn3 = new HandNodeB(h3, null, hn4);
        HandNodeB hn2 = new HandNodeB(h2, null, hn3);
        HandNodeB hn1 = new HandNodeB(h1, null, hn2);

        System.out.println("Original list: \n"+hn3.toStringCompact());
        hn4.playerQuits(2); //Cindy should be removed
        System.out.println("After removing item at index 2: \n"+hn2.toStringCompact());

        int nRounds = 0;
        Hand winner = hn1.winnerAdvanced();
        while(winner==null || (winner.allSameSuit()==false && winner.sequence()==false)) {
            nRounds++;
            System.out.println("Round "+nRounds);
            for(int k=0; k < hn1.size(); k++) {
                HandNodeB currentNode = hn1.get(k);
                HandNodeB nextNode = currentNode.next;
                if(nextNode == null) {
                    nextNode = hn1.get(0); 
                }
                Hand current = currentNode.hand; 
                Hand next = nextNode.hand;

                int idx = (int)(Math.random() * current.cardsInHand.size());
                current.swap(idx, next);
                System.out.println(hn1.toStringCompact());
            }
            winner = hn1.winnerAdvanced();
        }
        winner.arrange();
        System.out.println("\nWinner decided in "+nRounds+" rounds:\n"+winner.toStringCompact());
    }
}
