package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;

public class ClosestStrategy extends AbstractStrategy {

	@Override
	protected List<Robot> selectRobots(Job job) {
		int robotsNeeded = job.robotsNeeded;
		if (available.size() < robotsNeeded)
			return new ArrayList<Robot>();
		List<Robot> selected = selectSmallest(robotsNeeded, available, Comparator.naturalOrder());
		return selected;
	}

	/**
	 * Find the <code>k</code> smallest elements in <code>list</code>.
	 * If the list contains fewer than <code>k</code> elements then return an empty
	 * list.
	 * 
	 * @param <T>
	 * @param k
	 * @param list
	 * @param comp
	 * @return list <code>k</code> of smallest elements
	 */
	private <T> List<T> selectSmallest(int k, List<T> list, Comparator<T> comp) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return "Closest strategy";
	}

}
