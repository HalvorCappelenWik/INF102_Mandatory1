package INF102.Mandatory1.management.strategies;

import java.util.*;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;

public class BetterStrategy extends AbstractStrategy {
	

	protected List<Robot> selectRobots(Job job) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getName() {
		return "Better strategy";
	}

}
