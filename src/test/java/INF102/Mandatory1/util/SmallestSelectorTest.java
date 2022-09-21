package INF102.Mandatory1.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import INF102.Mandatory1.visualizer.StringListGenerator;


public class SmallestSelectorTest {

	ISmallestSelector selector;
	ArrayList<Integer> list;
	int k = 10;
	int n = 1000;

	@BeforeEach
	void setup() {
		selector = new MySmallestSelector();
		list = makeRandomList(n, 2000);
	}

	@Test
	public void testSelection() {
		List<Integer> best = selector.selectSmallest(list, k, Comparator.naturalOrder());
		int limit = Collections.max(best);
		int countSmaller = 0;
		int countEqual = 0;
		for (int num : list) {
			if (num < limit)
				countSmaller++;
			if (num == limit)
				countEqual++;
		}
		assertEquals(k, best.size(), "Did not select the right number of elements");
		assertEquals(n, list.size(), "number of elements in the original list has changed");
		assertTrue(countSmaller < k, "Elements with too high value were selected");
		for (int a : best) {
			assertTrue(Collections.frequency(best, a) <= Collections.frequency(list, a),
					"A element were selected more times than it appear in the list");
		}
		assertTrue(countSmaller + countEqual >= k, "Too few elements were selected");
	}

	@Test
	public void doNotReturnReferenceToSameList() {
		List<Integer> smallestElements = selector.selectSmallest(list, 10, Comparator.naturalOrder());
		smallestElements.set(0, 1); // Place the element 1 on index 0
		System.out.println(list.get(0));
		System.out.println(smallestElements.get(0));
		assertNotEquals(list.get(0), smallestElements.get(0));
	}

	public ArrayList<Integer> makeRandomList(int n, int bound) {
		Random rand = new Random();
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			numbers.add(rand.nextInt(bound));
		}
		return numbers;
	}
	
	@Test
	void testNotEnoughElements() {
		assertThrows(IllegalArgumentException.class, () -> selector.selectSmallest(new ArrayList<String>(),1,Comparator.naturalOrder()));

		List<String> tenStrings = StringListGenerator.generateStringList(10);
		assertThrows(IllegalArgumentException.class, () -> selector.selectSmallest(tenStrings, 11,Comparator.naturalOrder()));
	}
}
