package INF102.Mandatory1.management.strategies;

import java.util.*;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Location;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MySmallestSelector;

public class BetterStrategy extends AbstractStrategy {


	public BetterStrategy() {
		Comparator<Job> jobComp = new Comparator<Job>() {

			@Override
			public int compare(Job o1, Job o2) {
				double dist1 = jobDistanceToRobots(o1);
				double dist2 = jobDistanceToRobots(o2);

				if (dist1 > dist2) return 1;
				if (dist1 < dist2) return -1;
				else return 0;
			}
		};

		super.backLog = new PriorityQueue<>(jobComp);
	}

	@Override
	public List<Robot> selectRobots(Job job) {
		Comparator<Robot> comp = new ClosestComparator(job);
		MySmallestSelector mySmallestSelector = new MySmallestSelector();
		int robotsNeeded = job.robotsNeeded;

		if (robotsNeeded > getAvailableRobots().size()) {
			return new LinkedList<>();
		}

		return mySmallestSelector.selectSmallest(getAvailableRobots(),robotsNeeded, comp);
	}

	private double jobDistanceToRobots(Job job) {
		Location jobLocation = job.location;
		double mean = 0;
		for (Robot robots : getAvailableRobots()) {
			double dist = robots.getLocation().dist(jobLocation);
			mean += dist;
		}
		mean = mean / getAvailableRobots().size();
		return mean;
	}


	@Override
	public String getName() {
		return "Better strategy";
	}

}
