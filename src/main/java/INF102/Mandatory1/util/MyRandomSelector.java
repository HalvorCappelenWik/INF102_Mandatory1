package INF102.Mandatory1.util;

import java.util.*;

public class MyRandomSelector implements IRandomSelector {

	Random random = new Random();


	// This runtime of this function is not dependant on either n or k.
	// The function only removes one random element, hence k is always 1, and
	// no operations are dependent on k.
	// Furthermore, no operations are dependent on n, the number of element in the list.
	// We do not iterate tru the list, we are generating a random index based on the size of the list.
	// And then swaps the generated index with the last item in the list, and then removes the last item.
	// This gives us amortized constant time O(1).
	@Override
	public <T> T removeRandom(List<T> list) {
		if (list.isEmpty()) throw new IllegalArgumentException("List is empty");  //O(1)
		int listSize = list.size();  //O(1)

		return list.remove(random.nextInt(listSize)); //O(n)
	} //O(n)



	@Override
	public <T> List<T> removeRandom(List<T> list, int k) {
		if (list.size() < k) throw new IllegalArgumentException("Not enough items in list");
		List<T> removedItems = new ArrayList<>(k); //O(1)

		for (int i = 0; i < k; i++) { //O(k)
			removedItems.add(removeRandom(list)); //O(n)
		}
		return removedItems;
	} //O(n*k)
}

//O(n), uses the .contains method.
// which has O(n) runtime. Since the time we spend to find a specific object
// depends on the number of items we have in the array.
