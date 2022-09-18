package INF102.Mandatory1.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MyRandomSelector implements IRandomSelector {

	Random random = new Random();

	@Override
	public <T> T removeRandom(List<T> list) {
		if (list.size() == 0) throw new IllegalArgumentException("List is empty");
		return list.remove(random.nextInt(list.size()));
	}

	@Override
	public <T> List<T> removeRandom(List<T> list, int k) {
		if (list.size() == 0) throw new IllegalArgumentException("List is empty");
		List<T> removedItems = new LinkedList<T>();

		while(k > 0) {
			if (list.size() < k) throw new IllegalArgumentException("Not enough items in list");
			removedItems.add(removeRandom(list));
			k --;
		}
		list.removeAll(removedItems);
		return removedItems;

	}
	
}
