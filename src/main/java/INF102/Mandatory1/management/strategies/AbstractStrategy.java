package INF102.Mandatory1.management.strategies;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;

public abstract class AbstractStrategy implements IStrategy {

	/**
	 * List of all robots, both available and occupied
	 */
	protected List<Robot> robots;
	protected List<Robot> available;
	/**
	 * List of jobs not yet executed
	 */
	protected Queue<Job> backLog;

	public AbstractStrategy() {
		backLog = new LinkedList<Job>();
	}

	@Override
	public void registerRobots(List<Robot> robots) {
		this.robots = new ArrayList<Robot>(robots); // O(n)
		this.available = new ArrayList<>(robots);   // O(n)
	}  //O(2n) -> O(n)

	@Override
	public void registerNewJob(Job job) {
		backLog.add(job);  //O(1)
		doJobs(); //O(mkn)
	} //O(mkn)

	@Override
	public void registerJobAsFulfilled(Job job, List<Robot> robots) {
		available.addAll(robots);  //O(n)
		backLog.peek(); //O(1)
		doJobs(); //O(mkn)
	} //O(mkn)

	/**
	 * Finds jobs in backLog and assigns robots
	 */
	protected void doJobs() {
		while (!backLog.isEmpty()) { //O(m)
			Job job = selectJob(); //O(1)
			List<Robot> selected = selectRobots(job); // Closest: O(n * log(n))
													  // Random: O(nk)

			if (assignRobots(selected, job)) //O(k*n)
				removeJob(job); // O(n)
			else
				break;
		}
		if (backLog.isEmpty())
			moveFreeRobots();
	} // Random: O(m) * (O(kn) + O(kn) + O(n)) ->  O(mkn)
	  // Closest: O(m) * (O(n * log(n)) + O(k*n) + O(n)) ->  worst case O(mkn) / O(m(nlogn)

	/**
	 * Selects a Job from the list of available jobs
	 * Currently selects the job at the top of the list
	 * 
	 * @return most appropriate job
	 */
	protected Job selectJob() {
		return backLog.peek();
	} //O(1)

	protected void removeJob(Job job) {
		if (backLog.peek().equals(job))  //O(1)
			backLog.poll(); //O(1)
		else
			backLog.remove(job);  //O(n)
	} //O(1) + O(n) -> O(n)

	/**
	 * Select robots for the job. Should select robots most appropriate for the job.
	 * 
	 * @param job       - The job to select robots for
	 * @param available - The Robots to select among
	 * @return return list of selected robots if the job can be executed, else
	 *         return empty list
	 */
	protected abstract List<Robot> selectRobots(Job job);

	/**
	 * When a Robot is not assigned to a Job it is just waiting
	 * We can then position these robots such that when a new job comes in
	 * the robots are already close to the job.
	 */
	protected void moveFreeRobots() {
		// TODO: This method could be suited for task 3
	}

	/**
	 * Sends the selected Robots to their Job
	 * 
	 * @return true if robots assigned to job, false if not
	 */
	boolean assignRobots(List<Robot> selected, Job job) {
		if (selected == null)
			return false;
		if (selected.isEmpty())
			return false;

		boolean canDo = selected.size() >= job.robotsNeeded;
		for (Robot r : selected) {
			if (r.isBusy()) {
				System.out.println("You selected a robot that was busy.");
				canDo = false;
			}
		}
		if (canDo) {
			for (Robot robot : selected) {
				robot.move(job);
				available.remove(robot);
			} // O(k) * (O(log(m) + O(n)) -> O(k*n)
		} else {
			for (Robot r : selected) {
				if (!r.isBusy()) {
					r.move(job.location);
				} // O(k) * O(log(m)
			}
		}
		return canDo;
	} // O(k) * (O(log(m) + O(n)) + (O(k) * O(log m)) -> O(k * n)

	/**
	 * Returns list of free robots
	 * 
	 * @return list of all available robots
	 */
	public List<Robot> getAvailableRobots() {
		return available;
	}

}
