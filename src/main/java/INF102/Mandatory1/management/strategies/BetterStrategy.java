package INF102.Mandatory1.management.strategies;

import java.util.*;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Location;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MySmallestSelector;

public class BetterStrategy extends AbstractStrategy {

	MySmallestSelector mySmallestSelector = new MySmallestSelector();
	private Comparator<Job> jobComparator = (o1, o2) -> {
		double dist1 = jobDistanceToRobots(o1);
		double dist2 = jobDistanceToRobots(o2);

		if (dist1 > dist2) return 1;
		if (dist1 < dist2) return -1;
		else return 0;
	};

	public BetterStrategy() {
		backLog = new PriorityQueue<>(jobComparator);
	}

	@Override
	public List<Robot> selectRobots(Job job) {
		int robotsNeeded = job.robotsNeeded;
		if (robotsNeeded > available.size()) {
			return new ArrayList<>();
		}
		List<Robot> robots = mySmallestSelector.selectSmallest(available,robotsNeeded, new ClosestComparator(job));
		return robots;
	}

	/**
	 * Calculate the average distance to a job from all the available robots.
	 * @param job the job to consider.
	 * @return the mean distance.
	 */
	private double jobDistanceToRobots(Job job) {
		Location jobLocation = job.location;
		double mean = 0;
		for (Robot robots : available) {
			double dist = robots.getLocation().dist(jobLocation);
			mean += dist;
		}
		mean = mean / available.size();
		return mean;
	}

	@Override
	public String getName() {
		return "Better strategy";
	}

}