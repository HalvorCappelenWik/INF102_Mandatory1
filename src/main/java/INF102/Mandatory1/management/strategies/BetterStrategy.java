package INF102.Mandatory1.management.strategies;

import java.util.*;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Location;
import INF102.Mandatory1.management.Robot;
import INF102.Mandatory1.util.MySmallestSelector;

public class BetterStrategy extends AbstractStrategy {


	MySmallestSelector mySmallestSelector = new MySmallestSelector();
	private final List<Location> locationsOfExecutedJobs = new ArrayList<>();


	public BetterStrategy() {
		Comparator<Job> jobComparator = (o1, o2) -> {

			double dist1 = distFromJobToRobots(o1);
			double dist2 = distFromJobToRobots(o2);

			return Double.compare(dist1, dist2);
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
		if (robots.size() > 0) {
			locationsOfExecutedJobs.add(job.location);
		}
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



	@Override
	//Move free robots to the average location of the executed jobs or to a random location.
	protected void moveFreeRobots() {
		if (locationsOfExecutedJobs.size() == 0) {
			return;
		}
		//Location averageLocation = calculateAvgLocation(locationsOfExecutedJobs);
		for (Robot robot : available) {
			//robot.move(averageLocation);
			robot.move(locationsOfExecutedJobs.get(new Random().nextInt(locationsOfExecutedJobs.size())));
		}
	}



	private Location calculateAvgLocation(List<Location> locations) {
		double x = 0;
		double y = 0;
		for (Location location : locations) {
			x += location.x;
			y += location.y;
		}
		return new Location(x / locations.size(), y / locations.size());
	}


	@Override
	public String getName() {
		return "Better strategy";
	}
}