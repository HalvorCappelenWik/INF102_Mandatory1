package INF102.Mandatory1.util;

import java.util.*;

public class MyRandomSelector implements IRandomSelector {

	Random random = new Random();

	@Override
	public <T> T removeRandom(List<T> list) {
		if (list.isEmpty()) throw new IllegalArgumentException("List is empty");  //O(1)
		int size = list.size();  //O(1)
		int randomIndex = random.nextInt(size); //O(1)

		Collections.swap(list, randomIndex, size-1);  //O(1)

		return list.remove(size-1); //O(1)
	} //O(1)



	@Override
	public <T> List<T> removeRandom(List<T> list, int k) {
		if (list.size() < k) throw new IllegalArgumentException("Not enough items in list"); //O(1)
		List<T> removedItems = new LinkedList<>(); //O(1)

		for (int i = 0; i < k; i++) { //O(k)
			removedItems.add(removeRandom(list));   // add method = O(1) since it only appends to the end of the list
													// removeRandom = O(1)
		}
		return removedItems;
	} //O(k)
}









// This runtime of this function is not dependant on either n or k.
// The function only removes one random element, hence k is always 1, and
// no operations are dependent on k.
// Furthermore, no operations are dependent on n, the number of element in the list.
// We do not iterate tru the list, we are generating a random index based on the size of the list.
// And then swaps the generated index with the last item in the list, and then removes the last item.
// This gives us amortized constant time O(1).
