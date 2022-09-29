package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MySmallestSelector;

public class ClosestStrategy extends AbstractStrategy {

	MySmallestSelector mySmallestSelector = new MySmallestSelector();

	@Override
	public List<Robot> selectRobots(Job job) {
		int robotsNeeded = job.robotsNeeded;

		if (robotsNeeded > available.size()) {
			return new ArrayList<>(0);
		}

		return mySmallestSelector.selectSmallest(available,robotsNeeded, new ClosestComparator(job));
	} // O(n * log(n))


	@Override
	public String getName() {
		return "Closest strategy";
	}
}
