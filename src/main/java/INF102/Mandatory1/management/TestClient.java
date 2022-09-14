package INF102.Mandatory1.management;

import java.util.ArrayList;
import java.util.List;

import INF102.Mandatory1.management.strategies.BetterStrategy;
import INF102.Mandatory1.management.strategies.ClosestStrategy;
import INF102.Mandatory1.management.strategies.IStrategy;
import INF102.Mandatory1.management.strategies.RandomStrategy;
import INF102.Mandatory1.system.Model;

/**
 * @author Olav Bakken og Martin Vatshelle
 */
public class TestClient {
	
	static List<String> getTestFiles(){
		ArrayList<String> files = new ArrayList<>();
		for (int i = 1; i <= 6; i++) {
			files.add(String.format("input/%02d.in", i));
		}
		return files;
	}
	
	/**
	 * Returns a strategy to be tested
	 * @return IStragegy
	 */
	static List<IStrategy> getStrategies() {
		// TODO: Enter the strategy you want to test here
		ArrayList<IStrategy> strategies = new ArrayList<>();
		strategies.add(new RandomStrategy());
		strategies.add(new ClosestStrategy());
		strategies.add(new BetterStrategy());
		return strategies;
	}
	
	/**
	 *  Runs the simulation with the provided strategy for each of the
	 *  inputs input/01.in-...-06.in, and prints the score obtained,
	 *  lower is better
	 * @param args
	 */
	public static void main(String args[]) throws Exception{
		for (IStrategy strategy: getStrategies()) {
			System.out.println("\n-----Scores for "+strategy.getName() + "-----");
			for(String file:getTestFiles()) {
				try { 
					Model model = new Model(file, strategy);
					strategy.registerRobots(model.listRobots());
					model.runSimulation();
					System.out.printf("Score %s: %.0f\n", file, model.score()); 
				}
				catch (IllegalStateException e) {
					System.out.printf("Score on %s: %s\n", file, e.getMessage());
				}
				catch (UnsupportedOperationException e) {
					System.out.println(strategy.getName() + " is not implemented.");
					break;
				}
			}
		}
	}
}
