package INF102.Mandatory1.util;

import java.util.Comparator;
import java.util.List;

public interface ISmallestSelector {

	/**
	 * Select the <code>k</code> smallest elements from <code>list</code>. 
	 * The comparator <code>comp</code> determines the order of the elements in <code>list</code>.
	 * 
	 * If the list contains less than <code>k</code> elements an {@link IllegalArgumentException} 
	 * 
	 * @param <T>
	 * @param list
	 * @param k
	 * @param comp
	 * @return
	 */
	<T> List<T> selectSmallest(List<T> list, int k, Comparator<? super T> comp);
	
}
