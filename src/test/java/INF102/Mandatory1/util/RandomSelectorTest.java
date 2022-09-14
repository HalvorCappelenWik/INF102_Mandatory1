package INF102.Mandatory1.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import INF102.Mandatory1.visualizer.StringListGenerator;

public class RandomSelectorTest {

	IRandomSelector selector;
	ArrayList<String> list;

	@BeforeEach
	void setup() {
		selector = new MyRandomSelector();
		list = StringListGenerator.generateStringList(1000);
	}

	@Test
	public void testElementremovedFromList() {
		int size = list.size();
		List<String> copyList = (List<String>) list.clone();
		String elem = selector.removeRandom(list);
		assertEquals(size - 1, list.size(),
				"when removeing one element list size should decrease by 1");
		assertEquals(Collections.frequency(copyList, elem) - 1, Collections.frequency(list, elem),
				"The element returned was not the same as the element removed");
		for (String str : list) {
			if (str.equals(elem))
				continue;
			assertEquals(Collections.frequency(copyList, str), Collections.frequency(list, str));
		}
	}

	@Test
	public void doNotReturnReferenceToSameList() {
		List<String> selectedElements = selector.removeRandom(list, 10);
		assertNotEquals(list, selectedElements, "Do not return the same list sent as an argument");
	}

	@Test
	public void testKElementsremovedFromList() {
		for(int k=1; k<list.size(); k++) {
			testKElementsremovedFromList(k);
		}
	}

	public void testKElementsremovedFromList(int k) {
		int size = list.size();
		List<String> copyList = (List<String>) list.clone();
		List<String> elem = selector.removeRandom(list,k);
		assertEquals(size - k, list.size(),
				"when removeing "+k+" elements, list size should decrease by "+k);
		for(String s : elem) {
			int countBefore = Collections.frequency(copyList, s);
			int countAfter = Collections.frequency(list, s);
			int countRemoved = Collections.frequency(elem, s);
			assertEquals(countBefore - countRemoved, countAfter,
				"The elements returned was not the same as the elements removed");
		}
		for (String str : list) {
			if (elem.contains(str))
				continue;
			assertEquals(Collections.frequency(copyList, str), Collections.frequency(list, str),
					"Removed element from list that was not returned");

		}		
	}
	
	@Test
	void testDistributionIsRandom() {
		//1000 repeats with probability 1/10 gives expected count of 100
		//Having an expected count of 65 or less happens once in 20000 times
		List<String> list = StringListGenerator.generateStringList(10);
		HashMap<String, Integer> count = new HashMap<>();
		IRandomSelector selector = new MyRandomSelector();
		for(int i=0; i<1000; i++) {
			String rand = selector.removeRandom(new ArrayList<>(list));
			count.put(rand, count.getOrDefault(rand, 0)+1);
		}
		
		for(String s : count.keySet()) {
			assertTrue(count.get(s)>65,"index "+list.indexOf(s)+" was selected too few times");
		}
	}

}
