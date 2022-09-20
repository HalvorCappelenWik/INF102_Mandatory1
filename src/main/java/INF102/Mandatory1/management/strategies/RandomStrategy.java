package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MyRandomSelector;

public class RandomStrategy extends AbstractStrategy {
	
	@Override
	public List<Robot> selectRobots(Job job) {
		int robotsNeeded = job.robotsNeeded;
		List<Robot> robotsForJob = new LinkedList<>();

		if (robotsNeeded > getAvailableRobots().size()) {
			return robotsForJob;
		}

		MyRandomSelector myRandomSelector = new MyRandomSelector();
		return myRandomSelector.removeRandom(getAvailableRobots(),robotsNeeded);
	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
