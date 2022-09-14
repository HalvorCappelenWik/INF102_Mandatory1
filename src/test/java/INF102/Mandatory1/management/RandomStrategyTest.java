package INF102.Mandatory1.management;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import INF102.Mandatory1.management.strategies.IStrategy;
import INF102.Mandatory1.management.strategies.RandomStrategy;
import INF102.Mandatory1.system.Model;

public class RandomStrategyTest {

	static IStrategy random;

	@BeforeAll
	public static void setUp() throws Exception {
		random = new RandomStrategy();
	}

	public Model make2Job1RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		jobs.add(new Job(new Location(1, 1), 0, 1d, 1));
		jobs.add(new Job(new Location(2, 2), 1, 10d, 1));
		return new Model(startLocation, jobs, random);
	}

	public Model make1DoubleJob2RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		startLocation.add(new Location(1, 1));
		jobs.add(new Job(new Location(2, 2), 0, 1d, 2));
		return new Model(startLocation, jobs, random);
	}

	public Model make2DoubleJob2RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 1));
		startLocation.add(new Location(10, 0));
		jobs.add(new Job(new Location(10, 1), 0, 1d, 2));
		jobs.add(new Job(new Location(12, 1), 1, 5d, 2));
		return new Model(startLocation, jobs, random);
	}

	public Model make10Job1RobotModel() {
		ArrayList<Location> startLocation = new ArrayList<>();
		ArrayList<Job> jobs = new ArrayList<>();
		startLocation.add(new Location(0, 0));
		for (int i = 0; i < 10; i++)
			jobs.add(new Job(new Location(i + 1, i + 1), i, 1e-10 * i, 1));
		return new Model(startLocation, jobs, random);
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
		return new Model(startLocation, jobs, random);
	}

	@Test
	public void testRandomStrategyOn2Job1RobotModel() {
		Model model = make2Job1RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(2.828, model.score(), 0.001);
	}

	@Test
	public void testRandomStrategyOn1DoubleJob2RobotModel() {
		Model model = make1DoubleJob2RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(2.828, model.score(), 0.001);
	}

	@Test
	public void testRandomStrategyOn2DoubleJob2RobotModel() {
		Model model = make2DoubleJob2RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		assertEquals(18d, model.score(), 0.001);
	}

	@Test
	public void testRandomStrategyOn10Job1RobotModel() {
		Model model = make10Job1RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		model.score(); // verify all jobs completed
	}

	@Test
	public void testRandomStrategyOn4Job4RobotModel() {
		Model model = make4Job4RobotModel();
		random.registerRobots(model.listRobots());
		model.runSimulation();
		model.score(); // verify all jobs completed
	}

	public static void main(String[] args) {
		RandomStrategyTest test = new RandomStrategyTest();
		try {
			test.setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 1000; i++) {
			test.testRandomStrategyOn4Job4RobotModel();
			test.testRandomStrategyOn10Job1RobotModel();
			test.testRandomStrategyOn2DoubleJob2RobotModel();
			test.testRandomStrategyOn1DoubleJob2RobotModel();
			test.testRandomStrategyOn2Job1RobotModel();
		}
	}
}
