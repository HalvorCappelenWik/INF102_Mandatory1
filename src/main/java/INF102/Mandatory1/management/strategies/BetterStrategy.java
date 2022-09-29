package INF102.Mandatory1.management.strategies;

import java.util.*;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Location;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MySmallestSelector;

public class BetterStrategy extends AbstractStrategy {


	MySmallestSelector mySmallestSelector = new MySmallestSelector();
	//private final List<Location> locationsOfExecutedJobs = new ArrayList<>();
	//Random random = new Random();
	//private Location averageLocation;
	//private int numLocations = 0;


	public BetterStrategy() {
		Comparator<Job> jobComparator = (o1, o2) -> {

			double dist1 = distFromJobToRobots(o1);
			double dist2 = distFromJobToRobots(o2);

			if (dist1 > dist2) return 1;
			if (dist1 < dist2) return -1;
			else return 0;
		};
		backLog = new PriorityQueue<>(jobComparator);
	}


	@Override
	public List<Robot> selectRobots(Job job) {
		int robotsNeeded = job.robotsNeeded;
		if (robotsNeeded > available.size()) {
			return new ArrayList<>();
		}
		robots = mySmallestSelector.selectSmallest(available,robotsNeeded, new ClosestComparator(job));
		//if (robots.size() > 0) {
			//locationsOfExecutedJobs.add(job.location);
			//numLocations ++;
			//addLocation(job.location);
		//}
		return robots;
	}


	/**
	 * Calculate the total distance to a job from all the available robots.
	 * @param job the job to consider.
	 * @return the total distance.
	 */
	private double distFromJobToRobots(Job job) {
		Location jobLocation = job.location;
		double totDist = 0;
		for (Robot robot : available) {
			double dist = robot.getLocation().dist(jobLocation);
			totDist += dist;
		}
		return totDist;


	}


	/*
	@Override
	// Move available bots to the center of known job location to minimize average distance.
	// Move available bots to the known location of where a job has been executed.
	protected void moveFreeRobots() {
		if (locationsOfExecutedJobs.size() == 0)
			return;
		int randomIndex = random.nextInt(locationsOfExecutedJobs.size());

		for (Robot robot : available) {
			//robot.move(averageLocation);
			robot.move((locationsOfExecutedJobs.get(0)));
		}
	}


	/**
	private void addLocation(Location location) {
		var x = location.x;
		var y = location.y;

		if (averageLocation != null)
		{
			x += averageLocation.x * numLocations;
			y += averageLocation.y * numLocations;
		}

		x /= numLocations;
		y /= numLocations;

		averageLocation = new Location(x, y);
	}
	**/

	@Override
	public String getName() {
		return "Better strategy";
	}
}