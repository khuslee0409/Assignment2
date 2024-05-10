package tests.stage4;

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
import toBeCompleted.stage4.*;

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
public class HandNodeATest {
    public static Deck deck;
    public static Hand hand1, hand2, hand3, hand4;
    public static HandNodeA node1, node2, node3, node4;

    @BeforeEach
    public void init() {        
        deck = new Deck();      
        
        ArrayList<Card> cards1 = new ArrayList<Card>();
        cards1.add(new Card(new Rank("Two", '2', 2), new Suit("Hearts", 'H', "red", 1)));
        cards1.add(new Card(new Rank("Four", '4', 4), new Suit("Diamonds", 'D', "red", 2)));
        cards1.add(new Card(new Rank("King", 'K', 13), new Suit("Hearts", 'H', "red", 1)));
        hand1 = new Hand("Amina", cards1);

        ArrayList<Card> cards2 = new ArrayList<Card>();
        cards2.add(new Card(new Rank("Ace", 'A', 14), new Suit("Hearts", 'H', "red", 1)));
        cards2.add(new Card(new Rank("Three", '3', 3), new Suit("Clubs", 'C', "black", 3)));
        cards2.add(new Card(new Rank("Two", '2', 2), new Suit("Spades", 'S', "black", 4)));
        hand2 = new Hand("Biba", cards2);

        ArrayList<Card> cards3 = new ArrayList<Card>();
        cards3.add(new Card(new Rank("Seven", '7', 7), new Suit("Hearts", 'H', "red", 1)));
        cards3.add(new Card(new Rank("Jack", 'J', 11), new Suit("Hearts", 'H', "red", 1)));
        cards3.add(new Card(new Rank("Ten", 'X', 10), new Suit("Hearts", 'H', "red", 1)));
        hand3 = new Hand("Nina", cards3);

        ArrayList<Card> cards4 = new ArrayList<Card>();
        cards4.add(new Card(new Rank("Ace", 'A', 14), new Suit("Diamonds", 'D', "red", 2)));
        cards4.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Diamonds", 'D', "red", 2)));
        cards4.add(new Card(new Rank("King", 'K', 13), new Suit("Diamonds", 'D', "red", 2)));
        hand4 = new Hand("Zoya", cards4);

        node4 = new HandNodeA(hand4, null);
        node3 = new HandNodeA(hand3, node4);
        node2 = new HandNodeA(hand2, node3);
        node1 = new HandNodeA(hand1, node2);

        currentMethodName = null;
    }

    @Test
	@Graded(description = "HandNodeA constructor", marks = 10)
	@Order(1)
    public void testHandNodeAConstructor() {
        //HandNodeA objects already constructed and linked in init(). Check them out

        assertNotNull(node1);
        assertEquals(hand1, node1.hand);

        assertNotNull(node1.next);
        assertEquals(hand2, node1.next.hand);

        assertNotNull(node1.next.next);
        assertEquals(hand3, node1.next.next.hand);

        assertNotNull(node1.next.next.next);
        assertEquals(hand4, node1.next.next.next.hand);

        assertNull(node1.next.next.next.next);

        assertEquals(node2, node1.next);
        assertEquals(node3, node1.next.next);
        assertEquals(node4, node1.next.next.next);

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
    @Graded(description = "HandNodeA size", marks = 10)
    @Order(2)
    public void testHandNodeASize() {
        assertEquals(4, node1.size());
        assertEquals(3, node2.size());
        assertEquals(2, node3.size());
        assertEquals(1, node4.size());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeA get", marks = 10)
	@Order(3)
    public void testHandNodeAGet() {
        assertNotNull(node1.get(0));
        assertEquals(hand1, node1.get(0));
        
        assertNotNull(node1.get(1));
        assertEquals(hand2, node1.get(1));
        
        assertNotNull(node1.get(2));
        assertEquals(hand3, node1.get(2));

        assertNotNull(node1.get(3));
        assertEquals(hand4, node1.get(3));

        assertNull(node1.get(4));

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeA winner", marks = 10)
	@Order(4)
    public void testHandNodeAWinner() {
        assertNotNull(node1.winner());
        assertEquals(hand2, node1.winner());

        assertNotNull(node3.winner());
        assertEquals(hand3, node3.winner());

        assertNotNull(node4.winner());
        assertEquals(hand4, node4.winner());

        node1.next = null;
        assertNull(node1.winner());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeA winnerAdvanced", marks = 10)
	@Order(5)
    public void testHandNodeAWinnerAdvanced() {
        /* 
         * hand1, A = 2H, 4D, KH
         * hand2, B = AH, 2C, 3S, sequence
         * hand3: N = 7H, 10H, JH, same suit
         * hand4: Zo = QD, KD, AD, sequence && same suit
         * hand5: Zu = 5D, 7D, 8D, same suit
         */
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand4, node1.winnerAdvanced());

        ArrayList<Card> cards5 = new ArrayList<Card>();
        cards5.add(new Card(new Rank("Eight", '8', 8), new Suit("Diamonds", 'D', "red", 2)));
        cards5.add(new Card(new Rank("Five", '5', 5), new Suit("Diamonds", 'D', "red", 2)));
        cards5.add(new Card(new Rank("Seven", '7', 7), new Suit("Diamonds", 'D', "red", 2)));
        Hand hand5 = new Hand("Zulu", cards5);
        HandNodeA node5 = new HandNodeA(hand5, null);
        node4.next = node5;

        assertEquals(hand4, node1.winnerAdvanced());
        //only one hand in linked list
        assertEquals(hand5, node5.winnerAdvanced());

        //tests for value tiebreaker (same suit)
        ArrayList<Card> cards6 = new ArrayList<Card>();
        cards6.add(new Card(new Rank("Five", '5', 5), new Suit("Diamonds", 'D', "red", 2)));
        cards6.add(new Card(new Rank("Six", '6', 6), new Suit("Diamonds", 'D', "red", 2)));
        cards6.add(new Card(new Rank("Seven", '7', 7), new Suit("Diamonds", 'D', "red", 2)));
        Hand hand6 = new Hand("Player6", cards6);
        HandNodeA node6 = new HandNodeA(hand6, null);
        node5.next = node6;
        assertEquals(hand4, node1.winnerAdvanced());

        //tests for value tiebreaker (same rank)
        ArrayList<Card> cards7 = new ArrayList<Card>();
        cards7.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Spades", 'S', "black", 4)));
        cards7.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards7.add(new Card(new Rank("Ace", 'A', 14), new Suit("Spades", 'S', "black", 4)));
        Hand hand7 = new Hand("Player7", cards7);
        HandNodeA node7 = new HandNodeA(hand7, null);
        node6.next = node7;
        assertEquals(hand7, node1.winnerAdvanced());

        //tests for occurs same value, occurs first tiebreaker
        Hand hand8 = new Hand("Player8", cards7);
        HandNodeA node8 = new HandNodeA(hand8, null);
        node7.next = node8;
        assertEquals(hand7, node1.winnerAdvanced());

        /*
         * these test cases were added since previously the winners were always going to be sequence + same suit
         * hence the winning order cannot be tested accurately if there is no hand with sequennce + same suit
         */
        
        //test same suit trump sequence
        node3.next = null; //node4 is sequence + same suit
        assertEquals(hand3, node1.winnerAdvanced());

        //test sequence trump value
        node2.next = null;
        assertEquals(hand2, node1.winnerAdvanced());

        //test same suit trump sequence, different order
        node1.next = node3;
        node3.next = node2;
        assertEquals(hand3, node3.winnerAdvanced());
        assertEquals(hand2, node2.winnerAdvanced());

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
			score = Math.min(score, 100);
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
		System.out.println("\nMarks for this file (out of 50): " + score);
	}
}
