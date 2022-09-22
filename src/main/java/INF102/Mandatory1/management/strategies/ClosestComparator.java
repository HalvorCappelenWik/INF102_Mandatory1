package INF102.Mandatory1.management.strategies;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Location;
import INF102.Mandatory1.management.Robot;

import java.util.Comparator;

public class ClosestComparator implements Comparator<Robot> {

    private Job job;

    public ClosestComparator(Job job) {
        this.job = job;
    }

    @Override
    public int compare(Robot o1, Robot o2) {
        Location robot1 = o1.getLocation();
        Location robot2 = o2.getLocation();

        double distToJob1 = job.location.dist(robot1);
        double distToJob2 = job.location.dist(robot2);

        if (distToJob1 > distToJob2)  {return  1;}
        else if (distToJob1 < distToJob2) {return -1;}
        return  0;
    }

}
