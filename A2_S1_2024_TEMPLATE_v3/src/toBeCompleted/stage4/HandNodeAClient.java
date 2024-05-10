package toBeCompleted.stage4;

import toBeCompleted.stage2.*;
import toBeCompleted.stage3.*;

public class HandNodeAClient {
    public static void main(String[] args) {
        Deck d = new Deck();
        d.shuffle();

        int nCards = 5;

        Hand h1 = new Hand("Andy", nCards, d);
        Hand h2 = new Hand("Bing", nCards, d);
        Hand h3 = new Hand("Cindy", nCards, d);
        Hand h4 = new Hand("David", nCards, d);

        HandNodeA hn4 = new HandNodeA(h4, null);
        HandNodeA hn3 = new HandNodeA(h3, hn4);
        HandNodeA hn2 = new HandNodeA(h2, hn3);
        HandNodeA hn1 = new HandNodeA(h1, hn2);
        System.out.println(hn1);

        int nRounds = 0;
        Hand winner = null;
        while(winner==null) {
            nRounds++;
            System.out.println("Round "+nRounds);
            for(int k=0; k < hn1.size(); k++) {
                Hand current = hn1.get(k);
                Hand next = hn1.get((k+1)%hn1.size());
                int idx = (int)(Math.random() * current.cardsInHand.size());
                current.swap(idx, next);
                winner = hn1.winner();
            }
            System.out.println(hn1.toStringCompact());
        }
        winner.arrange();
        System.out.println("Winner decided in "+nRounds+" rounds:\n"+winner.toStringCompact());
    }
}
