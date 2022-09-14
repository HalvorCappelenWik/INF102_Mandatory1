package INF102.Mandatory1.management.strategies;

import java.util.List;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;

public class RandomStrategy extends AbstractStrategy {
	
	@Override
	public List<Robot> selectRobots(Job job) {
		throw new UnsupportedOperationException();

	}

	@Override
	public String getName() {
		return "Random strategy";
	}
}
