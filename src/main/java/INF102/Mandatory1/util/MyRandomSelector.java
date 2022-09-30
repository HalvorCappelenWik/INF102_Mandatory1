package INF102.Mandatory1.util;

import java.util.*;

public class MyRandomSelector implements IRandomSelector {

	Random random = new Random();

	@Override
	public <T> T removeRandom(List<T> list) {
		if (list.isEmpty()) throw new IllegalArgumentException("List is empty");
		int size = list.size();
		int randomIndex = random.nextInt(size);

		Collections.swap(list, randomIndex, size-1);

		return list.remove(size-1);
	}


	@Override
	public <T> List<T> removeRandom(List<T> list, int k) {
		if (list.size() < k) throw new IllegalArgumentException("Not enough items in list"); //O(1)
		List<T> removedItems = new ArrayList<>(); //O(1)

		for (int i = 0; i < k; i++) { //O(k)
			removedItems.add(removeRandom(list));   // add method = worst case O(n) if arraylist
													// removeRandom = O(1)
		}
		return removedItems;
	}   // worst case O(nk) if Arraylist, O(k) if linkedList,
}


