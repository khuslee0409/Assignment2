package tests.stage1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import doNotModify.*;
import toBeCompleted.stage1.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) 
public class CardTest {
    public static Rank rank1, rank2, rank3, rank4;
    public static Suit suit1, suit2, suit3, suit4;
    public static Card card1, card2, card3, card4;

    @BeforeAll
    public static void init() {        
        rank1 = new Rank("Ace", 'A', 14);
        suit1 = new Suit("Hearts", 'H', "red", 1);
        card1 = new Card(rank1, suit1);

        rank2 = new Rank("Jack", 'J', 11);
        suit2 = new Suit("Diamonds", 'D', "red", 2);
        card2 = new Card(rank2, suit2);
        
        rank3 = new Rank("Ten", 'X', 10);
        suit3 = new Suit("Clubs", 'C', "black", 3);
        card3 = new Card(rank3, suit3);

        rank4 = new Rank("Five", '5', 5);
        suit4 = new Suit("Spades", 'S', "black", 4);
        card4 = new Card(rank4, suit4);;    
    }

    @Test
	@Graded(description = "Card constructor", marks = 10)
	@Order(1)
    public void testCardConstructor() {
        //Card objects already constructed in init(). Check them out

        assertEquals("Ace", card1.rank.name);
        assertEquals("Hearts", card1.suit.name);
        
        assertEquals("Jack", card2.rank.name);
        assertEquals("Diamonds", card2.suit.name);

        assertEquals("Ten", card3.rank.name);
        assertEquals("Clubs", card3.suit.name);

        assertEquals("Five", card4.rank.name);
        assertEquals("Spades", card4.suit.name);

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

	@Test
	@Graded(description = "Card value", marks = 10)
	@Order(2)
    public void testCardValue() {
		assertEquals(141, card1.value());
        assertEquals(112, card2.value());
        assertEquals(103, card3.value());
        assertEquals(54, card4.value());
        
		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	}

	@Test
	@Graded(description = "Card compareTo", marks = 10)
	@Order(3)
    public void testCardCompareTo() {
		assertEquals(29, card1.compareTo(card2));
        assertEquals(-29, card2.compareTo(card1));
        assertEquals(9, card2.compareTo(card3));
        assertEquals(0, card4.compareTo(card4));

        Card card5 = new Card(new Rank("Ace", 'A', 14), new Suit("Diamonds", 'D', "red", 2));
        assertEquals(-1, card1.compareTo(card5));
        assertEquals(1, card5.compareTo(card1));

        Card card6 = new Card(new Rank("Five", '5', 5), new Suit("Diamonds", 'D', "red", 2));
        assertEquals(2, card4.compareTo(card6));
        assertEquals(-2, card6.compareTo(card4));

	    currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	}

    @Test
	@Graded(description = "Card toString", marks = 10)
	@Order(4)
    public void testCardToString() {
		assertEquals("AH", card1.toString());
		assertEquals("JD", card2.toString());
		assertEquals("XC", card3.toString());
		assertEquals("5S", card4.toString());

	    currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    @Test
	@Graded(description = "Card equals", marks = 10)
	@Order(5)
    public void testCardEquals() {
		assertFalse(card1.equals(card2));
        assertFalse(card2.equals(card3));
        assertFalse(card3.equals(card4));
        assertTrue(card4.equals(card4));

        Card card5 = new Card(new Rank("Ace", 'A', 14), new Suit("Diamonds", 'D', "red", 2));
        assertFalse(card1.equals(card5));
        assertFalse(card5.equals(card1));

        Card card6 = new Card(new Rank("Five", '5', 5), new Suit("Diamonds", 'D', "red", 2));
        assertFalse(card1.equals(card6));
        assertFalse(card6.equals(card2));

        Card card7 = new Card(new Rank("Ace", 'A', 14), new Suit("Hearts", 'H', "red", 1));
        assertTrue(card1.equals(card7));
        assertTrue(card7.equals(card1));

	    currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

    public static int score = 0;
	public static String result = "";
	public static String currentMethodName = null;
	ArrayList<String> methodsPassed = new ArrayList<String>();

	@BeforeEach
	public void run() {
		currentMethodName = null;
	}

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
		System.out.println("\nMarks for this file (out of 50): " + score);
	}
}
