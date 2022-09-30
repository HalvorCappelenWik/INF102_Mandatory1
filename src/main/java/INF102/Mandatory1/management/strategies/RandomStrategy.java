package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MyRandomSelector;

public class RandomStrategy extends AbstractStrategy {

	MyRandomSelector myRandomSelector = new MyRandomSelector();


	@Override
	public List<Robot> selectRobots(Job job) {
		if (job.robotsNeeded > available.size()) return new ArrayList<>(0);

		return myRandomSelector.removeRandom(available, job.robotsNeeded); // worst case o(nk)
	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
