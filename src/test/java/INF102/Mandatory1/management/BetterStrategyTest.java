package INF102.Mandatory1.management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import INF102.Mandatory1.management.strategies.BetterStrategy;
import INF102.Mandatory1.management.strategies.ClosestStrategy;
import INF102.Mandatory1.management.strategies.IStrategy;
import INF102.Mandatory1.management.strategies.RandomStrategy;
import INF102.Mandatory1.system.Model;

/**
 * Tests for which test cases your strategy beats both random and closest
 */
public class BetterStrategyTest {
	RandomStrategy random = new RandomStrategy();
	ClosestStrategy closest = new ClosestStrategy();
	BetterStrategy better = new BetterStrategy();

	@BeforeEach
	public void setUp() throws Exception {
		random = new RandomStrategy();
		closest = new ClosestStrategy();
		better = new BetterStrategy();
	}

	public Model make10Job1RobotModel() {
		List<Location> startLocation = new ArrayList<>();
		List<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		for (int i = 0; i < 10; i++)
			jobs.add(new Job(new Location(i + 1, i + 1), i, 1e-10 * i, 1));
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
		better.registerRobots(robots);
		List<Job> jobs = model.getJobs();
		selectRobotsTest(jobs, robots);
	}

	@Test
	public void returnEmptyListWhenNotEnoughRobots() {
		Model model = make4Job4RobotModel();
		List<Robot> robots = model.listRobots();
		better.registerRobots(robots);
		List<Job> jobs = model.getJobs();
		selectRobotsTest(jobs, robots);
	}

	public void selectRobotsTest(List<Job> jobs, List<Robot> robots) {
		for (Job job : jobs) {
			int robotsNeeded = job.robotsNeeded;
			int robotsAvailable = robots.size();
			List<Robot> selected = better.selectRobots(job);
			better.getAvailableRobots().removeAll(selected);
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
	public void testBetterOnInput1() throws Exception {
		testStrategy("input/01.in");
	}

	@Test
	public void testBetterOnInput2() throws Exception {
		testStrategy("input/02.in");
	}

	@Test
	public void testBetterOnInput3() throws Exception {
		testStrategy("input/03.in");
	}

	@Test
	public void testBetterOnInput4() throws Exception {
		testStrategy("input/04.in");
	}

	@Test
	public void testBetterOnInput5() throws Exception {
		testStrategy("input/05.in");
	}

	@Test
	public void testBetterOnInput6() throws Exception {
		testStrategy("input/06.in");
	}

	public void testStrategy(String inputFile) throws Exception {
		Model rmodel = runSimulation(inputFile, random);
		Model cmodel = runSimulation(inputFile, closest);
		Model bmodel = runSimulation(inputFile, better);
		assertBetterScore(rmodel.score(), cmodel.score(), bmodel.score(), inputFile);
	}

	private static Model runSimulation(String inputFile, IStrategy strategy) throws Exception {
		Model model = new Model(inputFile, strategy);
		strategy.registerRobots(model.listRobots());
		model.runSimulation();
		return model;
	}

	private void assertBetterScore(double randomScore, double closestScore, double betterScore, String file) {
		assertTrue(randomScore > betterScore, "random strategy beats best strategy on " + file);
		assertTrue(closestScore > betterScore, "closest strategy beats best strategy on " + file);
	}
}