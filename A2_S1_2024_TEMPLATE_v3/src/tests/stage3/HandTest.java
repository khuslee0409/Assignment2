package tests.stage3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import doNotModify.*;
import toBeCompleted.stage1.*;
import toBeCompleted.stage2.*;
import toBeCompleted.stage3.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class HandTest {
    public static Deck deck;
    public static Hand hand1, hand2, hand3, hand4;

    @BeforeEach
    public void init() {        
        deck = new Deck();      
        
        hand1 = new Hand("Amina", 5, deck);
        hand2 = new Hand("Jin", 5, deck);
        hand3 = new Hand("Nina", 5, deck);
        hand4 = new Hand("Zoya", 5, deck);

        currentMethodName = null;
    }

    @Test
	@Graded(description = "Hand constructor 1", marks = 10)
	@Order(1)
    public void testHandConstructor1() {
        //Hand objects already constructed in init(). Check them out

        assertEquals("Amina", hand1.player);
        assertEquals("Jin", hand2.player);
        assertEquals("Nina", hand3.player);
        assertEquals("Zoya", hand4.player);

        assertEquals(5, hand1.cardsInHand.size());
        assertEquals(5, hand2.cardsInHand.size());
        assertEquals(5, hand3.cardsInHand.size());
        assertEquals(5, hand4.cardsInHand.size());

        deck = new Deck();
        hand1 = new Hand("Amina", 5, deck);
        assertEquals(47, deck.cards.size());
        for(int i=0; i < deck.cards.size(); i++) {
            for(int k=0; k < hand1.cardsInHand.size(); k++) {
                assertFalse(deck.cards.get(i).equals(hand1.cardsInHand.get(k)));
            }
        }

        hand2 = new Hand("Jin", 5, deck);
        assertEquals(42, deck.cards.size());
        for(int i=0; i < deck.cards.size(); i++) {
            for(int k=0; k < hand2.cardsInHand.size(); k++) {
                assertFalse(deck.cards.get(i).equals(hand2.cardsInHand.get(k)));
            }
        }

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand constructor 2", marks = 10)
	@Order(2)
    public void testHandConstructor2() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));

        Hand hand = new Hand("Amina", cards);
        assertEquals("Amina", hand.player);
        assertNotNull(hand.cardsInHand);
        assertEquals(6, hand.cardsInHand.size());
        for(int i=0; i < cards.size(); i++) {
            assertNotNull(hand.cardsInHand.get(i));
            assertTrue(hand.cardsInHand.contains(cards.get(i)));
            assertEquals(cards.get(i), hand.cardsInHand.get(i));
        }

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

        /**
     * This test checks for whether or not the card is valid (which doesn't seem to be the case when implemented below)
     */
    @Test
    @Graded(description = "Hand addCard", marks = 10)
    @Order(3)
    public void testHandAddCard() {
        Hand h1 = new Hand("p1", 5, deck);
        assertFalse(h1.addCard(null));
        assertEquals(h1.cardsInHand.size(), 5); //cannot add null card
        
        Card c = new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1));
        assertTrue(h1.addCard(c));
        assertEquals(h1.cardsInHand.size(), 6);

        assertFalse(h1.addCard(null));
        assertTrue(h1.addCard(c));

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
    @Graded(description = "Hand hasCard", marks = 10)
    @Order(4)
    public void testHandHasCard() {
        deck = new Deck();
        hand1 = new Hand("Amina", 5, deck);
        for(Card c: hand1.cardsInHand) {
            Card copy = new Card(c.rank, c.suit); //a new instance of the card
            assertTrue(hand1.hasCard(copy));
        }
        for(Card c: deck.cards) {
            Card copy = new Card(c.rank, c.suit); //a new instance of the card
            assertFalse(hand1.hasCard(copy));
        }

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand removeRandomCard", marks = 10)
	@Order(5)
    public void testHandRemoveRandomCard() {
        deck = new Deck();
        hand1 = new Hand("Amina", 5, deck);

        for(int i=0; i < 5; i++) {
            Card removed = hand1.removeRandomCard();
            assertNotNull(removed);
            assertFalse(hand1.hasCard(removed));
        }
        
        assertNull(hand1.removeRandomCard());
        assertEquals(0, hand1.cardsInHand.size());
        
        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand stealCardFrom", marks = 10)
	@Order(6)
    public void testHandStealCardFrom() {
        deck = new Deck();

        hand1 = new Hand("Amina", 5, deck);
        hand2 = new Hand("Jin", 5, deck);

        for(int i=0; i < 5; i++) {
            int h1before = hand1.cardsInHand.size();
            int h2before = hand2.cardsInHand.size();
            Card removed = hand1.stealCardFrom(hand2);
            assertNotNull(removed);
            assertFalse(hand2.hasCard(removed));
            assertTrue(hand1.hasCard(removed));
            assertEquals(h1before + 1, hand1.cardsInHand.size());
            assertEquals(h2before - 1, hand2.cardsInHand.size());
        }

        int h1before = hand1.cardsInHand.size();
        int h2before = hand2.cardsInHand.size();
        assertNull(hand1.stealCardFrom(null));
        assertEquals(h1before, hand1.cardsInHand.size());
        assertEquals(h2before, hand2.cardsInHand.size());

        hand2.addCard(null);
        hand1.stealCardFrom(hand1);
        assertEquals(hand1.cardsInHand.size(), 10);

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand removeCardBySuit", marks = 10)
	@Order(7)
    public void testHandRemoveCardBySuit() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        Hand h1 = new Hand(null, cards);
        h1.removeCardBySuit("Hearts");
        assertEquals(h1.cardsInHand.size(), 5);
        Card c = h1.removeCardBySuit("Diamonds");
        assertNull(c);
        assertEquals(h1.cardsInHand.size(), 5);
        c = h1.removeCardBySuit("Clubs");
        assertNotNull(c);
        assertTrue(c.equals(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3))));
        assertEquals(h1.cardsInHand.size(), 4);
        h1.cardsInHand.clear();
        assertNull(h1.removeCardBySuit("Hearts"));
        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand sameSuitCards", marks = 10)
	@Order(8)
    public void testHandSameSuitCards() {
        deck = new Deck();
        deck.shuffle();

        hand1 = new Hand("Amina", 52, deck);
        
        assertEquals(13, hand1.sameSuitCards());
        
        ArrayList<Integer> remainingCount = new ArrayList<Integer>(Arrays.asList(13, 13, 13, 13));
        ArrayList<String> suitNames = new ArrayList<String>(Arrays.asList("Hearts", "Diamonds", "Clubs", "Spades"));
        
        while(hand1.cardsInHand.size() > 0) {
            //following statement randomizes which suit to remove
            Card c = hand1.cardsInHand.remove((int)(Math.random() * hand1.cardsInHand.size()));
            if(c != null) { //only if successfully removed
                int idx = c.suit.value - 1;
                remainingCount.set(idx, remainingCount.get(idx) - 1);
                
                //the following statement calculates the maximum number of cards of the same suit left
                int maxRemaining = remainingCount.stream().mapToInt(i -> i).max().getAsInt();
                
                assertEquals(maxRemaining, hand1.sameSuitCards());
            }
        }

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand allSameSuit", marks = 10)
	@Order(9)
    public void testHandAllSameSuit() {
        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        hand1 = new Hand("Jin", cards);
        assertFalse(hand1.allSameSuit()); //need three cards at least
       
        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        hand1 = new Hand("Jin", cards);
        assertFalse(hand1.allSameSuit()); //need three cards at least
   
        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        hand1 = new Hand("Jin", cards);
        assertTrue(hand1.allSameSuit()); //three cards, same suit
      
        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Clubs", 'C', "black", 3)));
        hand1 = new Hand("Jin", cards);
        assertFalse(hand1.allSameSuit()); //three cards, but not same suit
      
        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Spades", 'S', "black", 4)));

        hand2 = new Hand("Jin", cards);
        assertTrue(hand2.allSameSuit()); 

        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Spades", 'S', "black", 4)));
        
        hand2 = new Hand("Poh", cards);
        assertFalse(hand2.allSameSuit()); 
    
        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand sequenceLength", marks = 10)
	@Order(10)
    public void testHandSequenceLength() {
        deck = new Deck();
        hand1 = new Hand("Amina", 1, deck);
        assertEquals(1, hand1.sequenceLength());
        
        deck = new Deck();
        hand1 = new Hand("Nina", 52, deck);
        assertEquals(13, hand1.sequenceLength());

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        
        hand1  = new Hand("Amina", cards);
        assertEquals(3, hand1.sequenceLength());

        cards.add(new Card(new Rank("Ace", 'A', 14), new Suit("Clubs", 'C', "Black", 1)));
        hand1  = new Hand("Amina", cards);
        assertEquals(4, hand1.sequenceLength());

        cards.add(new Card(new Rank("Jack", 'J', 11), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Ten", 'X', 10), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Nine", '9', 9), new Suit("Hearts", 'H', "red", 1)));

        hand1  = new Hand("Amina", cards);
        assertEquals(7, hand1.sequenceLength());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand sequence", marks = 10)
	@Order(11)
    public void testHandSequence() {
        deck = new Deck();
        hand1 = new Hand("Amina", 1, deck);
        assertFalse(hand1.sequence());
        
        deck = new Deck();
        hand1 = new Hand("Nina", 52, deck);
        assertFalse(hand1.sequence());

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        
        hand1  = new Hand("Amina", cards);
        assertFalse(hand1.sequence());

        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));

        hand1  = new Hand("Amina", cards);
        assertFalse(hand1.sequence()); //must be at least 3 cards for a sequence

        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Six", '6', 6), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Five", '5', 5), new Suit("Hearts", 'H', "red", 1)));
        
        hand1  = new Hand("Amina", cards);
        assertFalse(hand1.sequence());

        cards.add(new Card(new Rank("Seven", '7', 7), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);

        assertTrue(hand1.sequence());

        //test for ace FIRST
        cards.clear();
        cards.add(new Card(new Rank("Ace", 'A', 14), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Jack", 'J', 11), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));

        hand1 = new Hand("Amina", cards);
        assertTrue(hand1.sequence());

        //test for all ranks in the same card
        cards.add(new Card(new Rank("Ten", 'X', 10), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Nine", '9', 9), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Seven", '7', 7), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Six", '6', 6), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Five", '5', 5), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));

        hand1 = new Hand("Amina", cards);
        assertTrue(hand1.sequence());

        //test for ace LAST
        cards.clear();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Ace", 'A', 14), new Suit("Hearts", 'H', "red", 1)));
        hand1 = new Hand("Amina", cards);
        assertTrue(hand1.sequence());

        cards.clear();
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Ace", 'A', 14), new Suit("Hearts", 'H', "red", 1)));
        hand1 = new Hand("Amina", cards);
        assertFalse(hand1.sequence());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand agreeToSwap", marks = 10)
	@Order(12)
    public void testHandAgreeToSwap() {
        //FIRST SEQUENCE CHECK

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);
        
        assertTrue(hand1.agreeToSwap());

        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));

        hand1  = new Hand("Amina", cards);
        assertTrue(hand1.agreeToSwap());

        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Six", '6', 6), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Five", '5', 5), new Suit("Hearts", 'H', "red", 1)));
        
        assertTrue(hand1.agreeToSwap());

        cards.add(new Card(new Rank("Seven", '7', 7), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);

        assertFalse(hand1.agreeToSwap());

        //NEXT SUIT CHECK

        cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        
        hand1  = new Hand("Amina", cards);
        assertTrue(hand1.agreeToSwap()); //no sequence and no same suit as they need at least 3 cards

        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);
        assertFalse(hand1.agreeToSwap()); //suit
    
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);
        assertFalse(hand1.agreeToSwap()); //suit
    
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Clubs", 'C', "black", 3)));
        hand1  = new Hand("Amina", cards);
        assertTrue(hand1.agreeToSwap()); //suit
    
        hand1  = new Hand("Amina", cards);
        assertTrue(hand1.agreeToSwap());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand swap", marks = 10)
	@Order(13)
    public void testHandSwap() {
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("Three", '3', 3), new Suit("Hearts", 'H', "red", 1)));
        
        ArrayList<Card> cards2 = new ArrayList<Card>();
        cards2.add(new Card(new Rank("Two", '2', 2), new Suit("Diamonds", 'D', "red", 2)));
        cards2.add(new Card(new Rank("Nine", '9', 9), new Suit("Clubs", 'C', "black", 3)));
        cards2.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards2.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Spades", 'S', "black", 4)));

        hand1  = new Hand("Amina", cards1);
        hand2  = new Hand("Nina", cards2);

        String before1 = hand1.toString();
        String before2 = hand2.toString();

        assertFalse(hand1.swap(2, hand2));
        assertEquals(3, hand1.cardsInHand.size());
        assertEquals(4, hand2.cardsInHand.size());
        assertEquals(before1, hand1.toString());
        assertEquals(before2, hand2.toString());
        
        cards1 = new ArrayList<Card>();
        cards1.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards1.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards1);

        cards2 = new ArrayList<Card>();
        cards2.add(new Card(new Rank("Two", '2', 2), new Suit("Diamonds", 'D', "red", 2)));
        cards2.add(new Card(new Rank("Nine", '9', 9), new Suit("Clubs", 'C', "black", 3)));
        cards2.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards2.add(new Card(new Rank("Jack", 'J', 11), new Suit("Hearts", 'H', "red", 1)));

        hand2  = new Hand("Nina", cards2);
        assertEquals(6, hand1.cardsInHand.size());
        assertEquals(4, hand2.cardsInHand.size());


        hand1.swap(2, hand2);

        assertEquals(6, hand1.cardsInHand.size());
        assertEquals(4, hand2.cardsInHand.size());

        assertFalse(hand1.hasCard(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1))));
        assertTrue(hand2.hasCard(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1))));
        
        int count = 0;
        if(hand1.hasCard(new Card(new Rank("Two", '2', 2), new Suit("Diamonds", 'D', "red", 2)))) {
            count++;
        }
        if(hand1.hasCard(new Card(new Rank("Nine", '9', 9), new Suit("Clubs", 'C', "black", 3)))) {
            count++;
        }
        if(hand1.hasCard(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)))) {
            count++;
        }
        if(hand1.hasCard(new Card(new Rank("Jack", 'J', 11), new Suit("Hearts", 'H', "red", 1)))) {
            count++;
        }
        assertEquals(1, count);

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand value", marks = 10)
	@Order(14)
    public void testHandValue() {
        deck = new Deck();
        hand1 = new Hand("Amina", 52, deck);
        assertEquals(4290, hand1.value());

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Diamonds", 'D', "red", 2)));
        hand1  = new Hand("Amina", cards);
        assertEquals(22, hand1.value());

        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);
        assertEquals(432, hand1.value());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Hand arrange", marks = 10)
	@Order(15)
    public void testHandArrange() {
        hand1.arrange();
        assertEquals(5, hand1.cardsInHand.size());
        for(int i=0; i < hand1.cardsInHand.size()-1; i++) {
            assertTrue(hand1.cardsInHand.get(i).compareTo(hand1.cardsInHand.get(i+1)) <= 0);
        }

        deck = new Deck();
        hand1 = new Hand("Amina", 52, deck);
        hand1.arrange();
        
        assertEquals(52, hand1.cardsInHand.size());
        for(int i=0; i < hand1.cardsInHand.size()-1; i++) {
            assertTrue(hand1.cardsInHand.get(i).compareTo(hand1.cardsInHand.get(i+1)) <= 0);
        }

        ArrayList<Card> cards = new ArrayList<Card>();
        cards.add(new Card(new Rank("Two", '2', 2), new Suit("Diamonds", 'D', "red", 2)));
        cards.add(new Card(new Rank("Four", '4', 4), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards.add(new Card(new Rank("Eight", '8', 8), new Suit("Hearts", 'H', "red", 1)));
        cards.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Hearts", 'H', "red", 1)));
        hand1  = new Hand("Amina", cards);
        hand1.arrange();

        assertEquals(6, hand1.cardsInHand.size());
        for(int i=0; i < hand1.cardsInHand.size()-1; i++) {
            assertTrue(hand1.cardsInHand.get(i).compareTo(hand1.cardsInHand.get(i+1)) <= 0);
        }

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    public static int score = 0;
	public static String result = "";
	public static String currentMethodName = null;
	ArrayList<String> methodsPassed = new ArrayList<String>();

    @AfterEach
	public void logSuccess() throws NoSuchMethodException, SecurityException {
		if (currentMethodName != null
				&& !methodsPassed.contains(currentMethodName)) {
			methodsPassed.add(currentMethodName);
			Method method = getClass().getMethod(currentMethodName);
			Graded graded = method.getAnnotation(Graded.class);
			score += graded.marks();
			result += graded.description() + " passed. Marks awarded: "
					+ graded.marks() + "\n";
			doNotModify.CSV.write(this.getClass().toString(), currentMethodName,
					graded.marks());
		}
	}

    @AfterAll
	public static void wrapUp() throws IOException {
		if (result.length() != 0) {
			result = result.substring(0, result.length() - 1); // remove the
																// last "\n"
		}
		System.out.println(result);
		System.out.println("\nMarks for this file (out of 150): " + score);
	}
}
