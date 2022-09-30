package INF102.Mandatory1.util;

import java.util.*;

public class MyRandomSelector implements IRandomSelector {

	Random random = new Random();

	@Override
	public <T> T removeRandom(List<T> list) {
		if (list.isEmpty()) throw new IllegalArgumentException("List is empty");
		int size = list.size();
		int randomIndex = random.nextInt(size);
		return list.remove(randomIndex);
	}   //O(n)


	@Override
	public <T> List<T> removeRandom(List<T> list, int k) {
		if (list.size() < k) throw new IllegalArgumentException("Not enough items in list"); //O(1)
		List<T> removedItems = new ArrayList<>();
		for (int i = 0; i < k; i++) { //O(k)
			removedItems.add(removeRandom(list));  //O(n)
		}
		return removedItems;
	}   // O(nk)
}


