package tests.stage2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import doNotModify.*;
import toBeCompleted.stage1.*;
import toBeCompleted.stage2.*;

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
public class DeckTest {
    public static Deck deck;
    public static ArrayList<Card> cards;

    @BeforeAll
    public static void init() {        
        deck = new Deck();      
        
        cards = new ArrayList<Card>();
        ArrayList<Suit> suits = new ArrayList<Suit>();
        String[] suitNames = CSVReader.read("data/suits.csv");
        for(String suitName : suitNames) {
            String[] suitDetails = suitName.split(",");
            suits.add(new Suit(suitDetails[0], suitDetails[1].charAt(0), suitDetails[2], Integer.parseInt(suitDetails[3])));
        }

        ArrayList<Rank> ranks = new ArrayList<Rank>();

        String[] rankNames = CSVReader.read("data/ranks.csv");

        for(String rankName : rankNames) {
            String[] rankDetails = rankName.split(",");
            ranks.add(new Rank(rankDetails[0], rankDetails[1].charAt(0), Integer.parseInt(rankDetails[2])));
        }

        for(Suit suit : suits) {
            for(Rank rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    @Test
	@Graded(description = "Deck constructor", marks = 10)
	@Order(1)
    public void testDeckConstructor() {
        //Deck object already constructed in init(). Check them out

        assertEquals(cards.size(), deck.cards.size());
        for(int i = 0; i < cards.size(); i++) {
            assertTrue(cards.get(i).equals(deck.cards.get(i)));
        }

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

	@Test
	@Graded(description = "Deck shuffle", marks = 10)
	@Order(2)
    public void testDeckShuffle() {
		ArrayList<Integer> freq = new ArrayList<Integer>();
        for(int i=0; i < deck.cards.size(); i++) {
            freq.add(0);
        }

        ArrayList<String> arrangements = new ArrayList<String>();
        int count = 0;
        for(int i=0; i < 20000; i++) {
            deck.shuffle();
            arrangements.add(deck.toString());
        }
        assertTrue(allUnique(arrangements));
        
		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	}

    private boolean allUnique(ArrayList<String> arrangements) {
        for(int i=0; i < arrangements.size(); i++) {
            for(int j=i+1; j < arrangements.size(); j++) {
                if(arrangements.get(i).equals(arrangements.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
	@Graded(description = "Deck drawCard", marks = 10)
	@Order(3)
    public void testDeckDrawCard() {
        deck = new Deck();
        assertEquals(52, deck.cards.size());

        for(int i=0; i < 52; i++) {
            int sizeBefore = deck.cards.size();
            Card card = deck.drawCard();
            int sizeAfter = deck.cards.size();
            
            assertNotNull(card);
            assertEquals(sizeBefore - 1, sizeAfter);
        }

        assertNull(deck.drawCard()); //no more cards left
        assertEquals(0, deck.cards.size());

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
		System.out.println("\nMarks for this file (out of 30): " + score);
	}
}
