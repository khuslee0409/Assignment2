package tests.stage5;

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
import toBeCompleted.stage4.HandNodeA;
import toBeCompleted.stage5.*;

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
public class HandNodeBTest {
    public static Deck deck;
    public static Hand hand1, hand2, hand3, hand4;
    public static HandNodeB node1, node2, node3, node4;

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
        cards4.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Diamonds", 'D', "red", 2)));
        cards4.add(new Card(new Rank("Jack", 'J', 11), new Suit("Diamonds", 'D', "red", 2)));
        cards4.add(new Card(new Rank("King", 'K', 13), new Suit("Diamonds", 'D', "red", 2)));
        hand4 = new Hand("Zoya", cards4);

        node4 = new HandNodeB(hand4, null, null);
        node3 = new HandNodeB(hand3, null, node4);
        node2 = new HandNodeB(hand2, null, node3);
        node1 = new HandNodeB(hand1, null, node2);
        
        node4.previous = node3;
        node3.previous = node2;
        node2.previous = node1;

        currentMethodName = null;
    }

    @Test
	@Graded(description = "HandNodeB constructor", marks = 5)
	@Order(1)
    public void testHandNodeBConstructor() {
        //HandNodeB objects already constructed and linked in init(). Check them out

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

        assertEquals(node1, node2.previous);
        assertEquals(node2, node3.previous);
        assertEquals(node3, node4.previous);

        assertEquals(node1, node3.previous.previous);
        assertEquals(node2, node4.previous.previous);

        assertEquals(node1, node4.previous.previous.previous);

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
    @Graded(description = "HandNodeB size", marks = 5)
    @Order(2)
    public void testHandNodeBSize() {
        assertEquals(4, node1.size());
        assertEquals(4, node2.size());
        assertEquals(4, node3.size());
        assertEquals(4, node4.size());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB get", marks = 5)
	@Order(3)
    public void testHandNodeBGet() {
        assertNotNull(node1.get(0));
        assertEquals(hand1, node1.get(0).hand);
        
        assertNotNull(node1.get(1));
        assertEquals(hand2, node1.get(1).hand);
        
        assertNotNull(node1.get(2));
        assertEquals(hand3, node1.get(2).hand);

        assertNotNull(node1.get(3));
        assertEquals(hand4, node1.get(3).hand);

        assertNotNull(node2.get(0));
        assertEquals(hand1, node1.get(0).hand);
        
        assertNotNull(node2.get(1));
        assertEquals(hand2, node2.get(1).hand);
        
        assertNotNull(node2.get(2));
        assertEquals(hand3, node2.get(2).hand);

        assertNotNull(node2.get(3));
        assertEquals(hand4, node2.get(3).hand);

        assertNull(node1.get(4));
        assertNull(node2.get(4));
        assertNull(node3.get(4));
        assertNull(node4.get(4));

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB toStringCompact", marks = 5)
	@Order(4)
    public void testHandNodeBToStringCompact() {
        String s1 = node1.toStringCompact().trim();
        String s2 = node2.toStringCompact().trim();
        String s3 = node3.toStringCompact().trim();
        String s4 = node4.toStringCompact().trim();

        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(s4);

        assertEquals("Amina: 2H4DKH (194) -> Biba: SEQUENCE AH3C2S (198) -> Nina: SAME SUIT 7HJHXH (283) -> Zoya: SAME SUIT SEQUENCE QDJDKD (366)", s1);
        assertEquals("Amina: 2H4DKH (194) -> Biba: SEQUENCE AH3C2S (198) -> Nina: SAME SUIT 7HJHXH (283) -> Zoya: SAME SUIT SEQUENCE QDJDKD (366)", s2);
        assertEquals("Amina: 2H4DKH (194) -> Biba: SEQUENCE AH3C2S (198) -> Nina: SAME SUIT 7HJHXH (283) -> Zoya: SAME SUIT SEQUENCE QDJDKD (366)", s3);
        assertEquals("Amina: 2H4DKH (194) -> Biba: SEQUENCE AH3C2S (198) -> Nina: SAME SUIT 7HJHXH (283) -> Zoya: SAME SUIT SEQUENCE QDJDKD (366)", s4);

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB toString", marks = 5)
	@Order(5)
    public void testHandNodeBToString() {
        String s1 = node1.toString().trim();
        String s2 = node2.toString().trim();
        String s3 = node3.toString().trim();
        String s4 = node4.toString().trim();

        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(s4);

        assertEquals(s1, s2);
        assertEquals(s1, s3);
        assertEquals(s1, s4);
        
        assertEquals("===================\n" + //
                        "Amina's 3-card hand:\n" + //
                        "2H, 4D, KH\n" + //
                        "Total value: 194\n" + //
                        "===================\n" + //
                        " -> \n" + //
                        "===================\n" + //
                        "Biba's 3-card hand:\n" + //
                        "Sequence\n" + //
                        "AH, 3C, 2S\n" + //
                        "Total value: 198\n" + //
                        "===================\n" + //
                        " -> \n" + //
                        "===================\n" + //
                        "Nina's 3-card hand:\n" + //
                        "Same suit\n" + //
                        "7H, JH, XH\n" + //
                        "Total value: 283\n" + //
                        "===================\n" + //
                        " -> \n" + //
                        "===================\n" + //
                        "Zoya's 3-card hand:\n" + //
                        "Same suit sequence\n" + //
                        "QD, JD, KD\n" + //
                        "Total value: 366\n" + //
                        "===================", s1);

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB toStringReverse", marks = 5)
	@Order(6)
    public void testHandNodeBToStringReverse() {
        String s1 = node1.toStringReverse().trim();
        String s2 = node2.toStringReverse().trim();
        String s3 = node3.toStringReverse().trim();
        String s4 = node4.toStringReverse().trim();

        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);
        assertNotNull(s4);

        assertEquals(s1, s2);
        assertEquals(s1, s3);
        assertEquals(s1, s4);

        assertEquals("===================\n" + //
                        "Zoya's 3-card hand:\n" + //
                        "Same suit sequence\n" + //
                        "QD, JD, KD\n" + //
                        "Total value: 366\n" + //
                        "===================\n" + //
                        " <- \n" + //
                        "===================\n" + //
                        "Nina's 3-card hand:\n" + //
                        "Same suit\n" + //
                        "7H, JH, XH\n" + //
                        "Total value: 283\n" + //
                        "===================\n" + //
                        " <- \n" + //
                        "===================\n" + //
                        "Biba's 3-card hand:\n" + //
                        "Sequence\n" + //
                        "AH, 3C, 2S\n" + //
                        "Total value: 198\n" + //
                        "===================\n" + //
                        " <- \n" + //
                        "===================\n" + //
                        "Amina's 3-card hand:\n" + //
                        "2H, 4D, KH\n" + //
                        "Total value: 194\n" + //
                        "===================", s1);
        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB playerQuits", marks = 5)
	@Order(7)
    public void testHandNodeBPlayerQuits() {
        System.out.println(node1.toStringReverse());
        node1.playerQuits(2);
        System.out.println(node1.toStringReverse());
        assertEquals(node2, node1.next);
        assertEquals(node4, node2.next);
        assertNull(node4.next);
        assertEquals(node1, node2.previous);
        assertEquals(node2, node4.previous);
        assertNull(node1.previous); 
        
        //need to test whether or not references from node3 are removed
        //(or else you haven't actually removed it from the list, becomes a binary tree like structure)
        assertNull(node3.previous);
        assertNull(node3.next);   
        
        node1.playerQuits(1);
        assertEquals(node4, node1.next);
        assertNull(node4.next);
        assertEquals(node1, node4.previous);
        assertNull(node1.previous); 

        //added test case
        assertNull(node2.previous);
        assertNull(node2.next); 
        
        node1.playerQuits(0);
        assertNull(node4.next);
        assertNull(node4.previous); 
        assertNull(node1.next); 
        
        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB winner", marks = 5)
	@Order(8)
    public void testHandNodeBWinner() {
        assertNotNull(node1.winner());
        assertEquals(hand2, node1.winner());

        assertNotNull(node3.winner());
        assertEquals(hand2, node3.winner());

        assertNotNull(node3.winner());
        assertEquals(hand2, node3.winner());

        assertNotNull(node4.winner());
        assertEquals(hand2, node4.winner());

        //edge case: no hand with same sequence or same suit
        node1.next = null;
        node2.previous = null;
        assertNull(node1.winner());

        currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "HandNodeB winnerAdvanced", marks = 5)
	@Order(9)
    public void testHandNodeBWinnerAdvanced() {
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand4, node1.winnerAdvanced());

        assertNotNull(node2.winnerAdvanced());
        assertEquals(hand4, node2.winnerAdvanced());

        assertNotNull(node3.winnerAdvanced());
        assertEquals(hand4, node3.winnerAdvanced());

        assertNotNull(node4.winnerAdvanced());
        assertEquals(hand4, node4.winnerAdvanced());

        ArrayList<Card> cards5 = new ArrayList<Card>();
        cards5.add(new Card(new Rank("Ace", 'A', 14), new Suit("Diamonds", 'D', "red", 2)));
        cards5.add(new Card(new Rank("King", 'K', 13), new Suit("Diamonds", 'D', "red", 2)));
        cards5.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Diamonds", 'D', "red", 2)));
        Hand hand5 = new Hand("Zulu", cards5);
        
        HandNodeB node5 = new HandNodeB(hand5, node2, node3); //placing it between node2 and node3
        //if so, wouldn't node2.next and node3.previous need to be changed? or else you can't really access node5
        node2.next = node5;
        node3.previous = node5;
        
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand5, node1.winnerAdvanced());

        assertNotNull(node2.winnerAdvanced());
        assertEquals(hand5, node2.winnerAdvanced());

        assertNotNull(node3.winnerAdvanced());
        assertEquals(hand5, node3.winnerAdvanced());

        assertNotNull(node4.winnerAdvanced());
        assertEquals(hand5, node4.winnerAdvanced());

        assertNotNull(node5.winnerAdvanced());
        assertEquals(hand5, node5.winnerAdvanced());

        //tests for value tiebreaker (same rank)
        ArrayList<Card> cards6 = new ArrayList<Card>();
        cards6.add(new Card(new Rank("Queen", 'Q', 12), new Suit("Spades", 'S', "black", 4)));
        cards6.add(new Card(new Rank("King", 'K', 13), new Suit("Spades", 'S', "black", 4)));
        cards6.add(new Card(new Rank("Ace", 'A', 14), new Suit("Spades", 'S', "black", 4)));
        Hand hand6 = new Hand("Player6", cards6);
        HandNodeB node6 = new HandNodeB(hand6, node4, null); //node5 in between node2 and node3
        node4.next = node6;
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand6, node1.winnerAdvanced());
        assertNotNull(node5.winnerAdvanced());
        assertEquals(hand6, node5.winnerAdvanced());

        //tests for occurs same value, occurs first tiebreaker
        Hand hand7 = new Hand("Player7", cards6);
        HandNodeB node7 = new HandNodeB(hand7, node6, null);
        node6.next = node7;
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand6, node1.winnerAdvanced());
        assertNotNull(node7.winnerAdvanced());
        assertEquals(hand6, node7.winnerAdvanced());

        /*
         * these test cases were added since previously the winners were always going to be sequence + same suit
         * hence the winning order cannot be tested accurately if there is no hand with sequence + same suit
         */
        node2.next = node3; //get rid of node5 and node4, as both are same suit + sequence
        node3.previous = node2;
        node5.previous = null;
        node5.next = null;
        node3.next = null;
        node4.previous = null;

        //test same suit trump sequence
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand3, node1.winnerAdvanced());
        assertNotNull(node2.winnerAdvanced());
        assertEquals(hand3, node2.winnerAdvanced());
        assertNotNull(node3.winnerAdvanced());
        assertEquals(hand3, node3.winnerAdvanced());

        //test sequence trump value
        node2.next = null;
        node3.previous = null;
        assertNotNull(node1.winnerAdvanced());
        assertEquals(hand2, node1.winnerAdvanced());
        assertNotNull(node2.winnerAdvanced());
        assertEquals(hand2, node2.winnerAdvanced());

        //test same suit trump sequence, different order
        node1.next = node3;
        node3.previous = node1;
        node3.next = node2;
        node2.previous = node3;
        assertNotNull(node3.winnerAdvanced());
        assertEquals(hand3, node3.winnerAdvanced());
        assertNotNull(node2.winnerAdvanced());
        assertEquals(hand3, node2.winnerAdvanced());

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
		System.out.println("\nMarks for this file (out of 45): " + score);
	}
}
