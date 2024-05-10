package toBeCompleted.stage3;

import toBeCompleted.stage2.*;

public class HandClient {
    public static void main(String[] args) {
        Deck deck = new Deck();
        deck.shuffle();
        Hand player1hand = new Hand("Andy", 5, deck);
        Hand player2hand = new Hand("Bing", 5, deck);

        player1hand.arrange();
        player2hand.arrange();

        System.out.println(player1hand);
        System.out.println(player2hand);
    }
}
