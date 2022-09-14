package INF102.Mandatory1.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import INF102.Mandatory1.management.strategies.ClosestStrategy;
import INF102.Mandatory1.system.Model;

public class ClosestStrategyTest {

	static ClosestStrategy closest;

	@BeforeAll
	public static void setUp() throws Exception {
		closest = new ClosestStrategy();
	}

	public Model make10Job1RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		for (int i = 0; i < 10; i++)
			jobs.add(new Job(new Location(i + 1, i + 1), i, 1e-10 * i, 1));
		return new Model(startLocation, jobs, closest);
	}

	public Model make2Job1RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		jobs.add(new Job(new Location(1, 1), 0, 1d, 1));
		jobs.add(new Job(new Location(2, 2), 1, 10d, 1));
		return new Model(startLocation, jobs, closest);
	}

	public Model make1DoubleJob2RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		startLocation.add(new Location(1, 1));
		jobs.add(new Job(new Location(2, 2), 0, 1d, 2));
		return new Model(startLocation, jobs, closest);
	}

	public Model make2DoubleJob2RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 1));
		startLocation.add(new Location(10, 0));
		jobs.add(new Job(new Location(10, 1), 0, 1d, 2));
		jobs.add(new Job(new Location(12, 1), 1, 5d, 2));
		return new Model(startLocation, jobs, closest);
	}

	public Model make4Job4RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(1, 1));
		startLocation.add(new Location(1, 2));
		startLocation.add(new Location(2, 1));
		startLocation.add(new Location(2, 2));
		jobs.add(new Job(new Location(0, 0), 0, 1d, 1));
		jobs.add(new Job(new Location(3, 0), 1, 1d, 1));
		jobs.add(new Job(new Location(0, 3), 2, 1d, 1));
		jobs.add(new Job(new Location(3, 3), 3, 1d, 1));
		return new Model(startLocation, jobs, closest);
	}

	@Test
	public void selectCorrectAmountOfRobots() {
		Model model = make10Job1RobotModel();
		List<Robot> robots = model.listRobots();
		closest.registerRobots(robots);
		List<Job> jobs = model.getJobs();
		selectRobotsTest(jobs, robots);
	}

	@Test
	public void returnEmptyListWhenNotEnoughRobots() {
		Model model = make4Job4RobotModel();
		List<Robot> robots = model.listRobots();
		closest.registerRobots(robots);
		List<Job> jobs = model.getJobs();
		selectRobotsTest(jobs, robots);
	}

	public void selectRobotsTest(List<Job> jobs, List<Robot> robots) {
		for (Job job : jobs) {
			int robotsNeeded = job.robotsNeeded;
			int robotsAvailable = robots.size();
			List<Robot> selected = closest.selectRobots(job);
			closest.getAvailableRobots().removeAll(selected);
			robots.removeAll(selected);
			if (robotsNeeded > robotsAvailable)
				assertTrue(selected.isEmpty(),
						"When there are not enough robots for the job selectRobots should return an empty list.");
			else
				assertEquals(robotsNeeded, selected.size(), "Number of selected robots (" + selected.size()
						+ ") was not equal to the number of robots needed for the job (" + robotsNeeded + ").");
		}
	}

	@Test
	public void testClosestStrategyOn10Job1RobotModel() {
		Model model = make10Job1RobotModel();
		closest.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(Math.sqrt(2) * ((10) * (11) / 2), model.score(), 0.001);
	}

	@Test
	public void testClosestStrategyOn2Job1RobotModel() {
		Model model = make2Job1RobotModel();
		closest.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(2.828, model.score(), 0.001);
	}

	@Test
	public void testClosestStrategyOn1DoubleJob2RobotModel() {
		Model model = make1DoubleJob2RobotModel();
		closest.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(2.828, model.score(), 0.001);
	}

	@Test
	public void testClosestStrategyOn2DoubleJob2RobotModel() {
		Model model = make2DoubleJob2RobotModel();
		closest.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(18d, model.score(), 0.001);
	}

	@Test
	public void testClosestStrategyOn4Job4RobotModel() {
		Model model = make4Job4RobotModel();
		closest.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(4 * 1.414, model.score(), 0.001);
	}

}