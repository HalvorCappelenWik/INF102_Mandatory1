package INF102.Mandatory1.util;

import java.util.List;

public interface IRandomSelector {

	/**
	 * Remove a random element from <code>list</code>.
	 * @param <T>
	 * @param list
	 * @return the randomly removed element
	 */
	<T> T removeRandom(List<T> list);
	
	/**
	 * Remove <code>k</code> random element from <code>list</code>.
	 * @param <T>
	 * @param list
	 * @return list of all randomly removed elements
	 */
	<T> List<T> removeRandom(List<T> list, int k);
}
