package tests.stage1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import doNotModify.*;
import toBeCompleted.stage1.Suit;

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
public class SuitTest {
    @Test
	@Graded(description = "Suit constructor", marks = 10)
	@Order(1)
    public void testSuitConstructor() {
        Suit suit = new Suit("Hearts", 'H', "red", 1);
        assertEquals("Hearts", suit.name);
        assertEquals('H', suit.symbol);
        assertEquals("red", suit.color);
        assertEquals(1, suit.value);

        suit = new Suit("Diamonds", 'D', "red", 2);
        assertEquals("Diamonds", suit.name);
        assertEquals('D', suit.symbol);
        assertEquals("red", suit.color);
        assertEquals(2, suit.value);

        suit = new Suit("Clubs", 'C', "black", 3);
        assertEquals("Clubs", suit.name);
        assertEquals('C', suit.symbol);
        assertEquals("black", suit.color);
        assertEquals(3, suit.value);
        
        suit = new Suit("Spades", 'S', "black", 4);
        assertEquals("Spades", suit.name);
        assertEquals('S', suit.symbol);
        assertEquals("black", suit.color);
        assertEquals(4, suit.value);

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

	@Test
	@Graded(description = "Suit toString", marks = 10)
	@Order(2)
    public void testSuitToString() {
		Suit suit = new Suit("Hearts", 'H', "red", 1);
        assertEquals("Hearts", suit.toString());

        suit = new Suit("Diamonds", 'D', "red", 2);
        assertEquals("Diamonds", suit.toString());

        suit = new Suit("Clubs", 'C', "black", 3);
        assertEquals("Clubs", suit.toString());
        
        suit = new Suit("Spades", 'S', "black", 4);
        assertEquals("Spades", suit.toString());

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	}

	@Test
	@Graded(description = "Suit compareTo", marks = 10)
	@Order(3)
    public void testSuitCompareTo() {
		Suit suit1 = new Suit("Hearts", 'H', "red", 1);
        Suit suit2 = new Suit("Diamonds", 'D', "red", 2);
        assertEquals(-1, suit1.compareTo(suit2));

        suit1 = new Suit("Clubs", 'C', "black", 3);
        assertEquals(1, suit1.compareTo(suit2));

        suit1 = new Suit("Spades", 'S', "black", 4);
        assertEquals(2, suit1.compareTo(suit2));
        assertEquals(-2, suit2.compareTo(suit1));
        
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
