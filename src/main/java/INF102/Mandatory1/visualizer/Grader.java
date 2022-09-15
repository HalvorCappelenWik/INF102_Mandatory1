package INF102.Mandatory1.visualizer;

import java.io.PrintWriter;

import INF102.Mandatory1.management.strategies.BetterStrategy;
import INF102.Mandatory1.management.strategies.IStrategy;
import INF102.Mandatory1.system.Model;

public class Grader {

    static IStrategy getStrategy() {
        return new BetterStrategy();
    }

    /**
     * Runs the simulation with the provided strategy for each of the
     * inputs input/01.in-...-06.in, and prints the score obtained,
     * lower is better
     * 
     * @param args
     */
    public static void main(String args[]) throws Exception {
        PrintWriter writer = new PrintWriter("betterStrategyScore.txt", "UTF-8");
        for (int i = 1; i <= 6; i++) {
            if (i > 1) {
                writer.print("\t");
                System.out.print("\t");
            }
            IStrategy strategy = getStrategy();
            try {
                Model model = new Model(String.format("input/grade%02d.in", i), strategy);
                strategy.registerRobots(model.listRobots());
                model.runSimulation();
                writer.printf("%.0f", model.score());
                System.out.printf("%.0f", model.score());
            } catch (IllegalStateException e) {
                System.out.print("NaN");
            }

        }
        System.out.println("\nDone grading.");
        writer.close();        
    }
}
