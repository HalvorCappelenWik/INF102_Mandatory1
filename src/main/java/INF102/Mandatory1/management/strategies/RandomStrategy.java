package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;

public class RandomStrategy extends AbstractStrategy {
	
	@Override
	protected List<Robot> selectRobots(Job job) {
		int robotsNeeded = job.robotsNeeded;
		List<Robot> selected = new ArrayList<>();
		if (robotsNeeded > available.size())
			return selected;

		return selectRandom(available, robotsNeeded);
	}

	/**
	 * Remove <code>k</code> random element from <code>list</code>.
	 * @param <T>
	 * @param list
	 * @return list of all randomly removed elements
	 */
	private <T> List<T> selectRandom(List<T> list, int k) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
