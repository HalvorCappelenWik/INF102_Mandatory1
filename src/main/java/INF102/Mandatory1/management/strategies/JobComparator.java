package INF102.Mandatory1.management.strategies;

import INF102.Mandatory1.management.Job;
import INF102.Mandatory1.management.Robot;

import java.util.Comparator;

public class JobComparator implements Comparator<Job> {


    @Override
    public int compare(Job o1, Job o2) {

        double time1 = o1.time;
        double time2 = o2.time;

        return Double.compare(time1, time2);
    }
}
