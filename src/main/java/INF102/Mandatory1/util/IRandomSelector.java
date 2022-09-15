package INF102.Mandatory1.util;

import java.util.List;

public interface IRandomSelector {

	/**
	 * Remove a random element from <code>list</code>.
	 * The element should be chosen with uniform probability,
	 * that means that every element in the list should have 
	 * the same probability to be chosen.
	 * 
	 * The chosen element should be removed from the list
	 * But there are no requirement that one does not change the order
	 * of the elements left in the list.
	 * 
	 * If the list provided an {@link IllegalArgumentException} should be thrown.
	 * 
	 * @param <T>
	 * @param list
	 * @return the randomly removed element
	 */
	<T> T removeRandom(List<T> list);
	
	/**
	 * Remove <code>k</code> random element from <code>list</code>.
	 * Each element should be chosen with uniform probability,
	 * that means that every element in the list should have 
	 * the same probability to be chosen.
	 * No element should be chosen twice.
	 * 
	 * The chosen elements should be removed from the list
	 * But there are no requirement that one does not change the order
	 * of the elements left in the list.
	 * 
	 * If there is not enough elements in the list an {@link IllegalArgumentException} should be thrown
	 * 
	 * @param <T>
	 * @param list
	 * @return list of all randomly removed elements
	 */
	<T> List<T> removeRandom(List<T> list, int k);
}
