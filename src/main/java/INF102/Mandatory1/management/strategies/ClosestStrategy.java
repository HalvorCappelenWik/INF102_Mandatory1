package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MySmallestSelector;

public class ClosestStrategy extends AbstractStrategy {

	@Override
	public List<Robot> selectRobots(Job job) {
		MySmallestSelector mySmallestSelector = new MySmallestSelector();
		int robotsNeeded = job.robotsNeeded;

		if (robotsNeeded > getAvailableRobots().size()) {
			return new LinkedList<>();
		}

		return mySmallestSelector.selectSmallest(getAvailableRobots(),robotsNeeded, new ClosestComparator(job));
	}

	@Override
	public String getName() {
		return "Closest strategy";
	}

}
