package tests.stage1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import doNotModify.*;
import toBeCompleted.stage1.Rank;

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
public class RankTest {
    @Test
	@Graded(description = "Rank constructor", marks = 10)
	@Order(1)
    public void testRankConstructor() {
		Rank rank = new Rank("Ace", 'A', 14);
		assertEquals("Ace", rank.name);
		assertEquals('A', rank.symbol);
		assertEquals(14, rank.value);

		rank = new Rank("King", 'K', 13);
		assertEquals("King", rank.name);
		assertEquals('K', rank.symbol);

		rank = new Rank("Queen", 'Q', 12);
		assertEquals("Queen", rank.name);
		assertEquals('Q', rank.symbol);

		rank = new Rank("Jack", 'J', 11);
		assertEquals("Jack", rank.name);
		assertEquals('J', rank.symbol);

		rank = new Rank("Ten", 'X', 10);
		assertEquals("Ten", rank.name);
		assertEquals('X', rank.symbol);
		assertEquals(10, rank.value);

		rank = new Rank("Two", '2', 2);
		assertEquals("Two", rank.name);
		assertEquals('2', rank.symbol);
		assertEquals(2, rank.value);

		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
    }

	@Test
	@Graded(description = "Rank toString", marks = 10)
	@Order(2)
    public void testRankToString() {
		Rank rank = new Rank("Ace", 'A', 14);
		assertEquals("Ace", rank.toString());

		rank = new Rank("King", 'K', 13);
		assertEquals("King", rank.toString());

		rank = new Rank("Two", '2', 2);
		assertEquals("Two", rank.toString());
		
		currentMethodName = new Object(){}.getClass().getEnclosingMethod().getName();
	}

	@Test
	@Graded(description = "Rank compareTo", marks = 10)
	@Order(3)
    public void testRankCompareTo() {
		Rank rank1 = new Rank("Ace", 'A', 14);
		Rank rank2 = new Rank("King", 'K', 13);
		assertEquals(1, rank1.compareTo(rank2));

		rank1 = new Rank("King", 'K', 13);
		rank2 = new Rank("Three", '3', 3);

		assertEquals(10, rank1.compareTo(rank2));
		assertEquals(-10, rank2.compareTo(rank1));

		rank1 = new Rank("Two", '2', 2);
		rank2 = new Rank("Two", '2', 2);

		assertEquals(0, rank1.compareTo(rank2));

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
