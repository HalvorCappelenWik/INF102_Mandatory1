package INF102.Mandatory1.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MySmallestSelector implements ISmallestSelector {

    @Override
    public <T> List<T> selectSmallest(List<T> list, int k, Comparator<? super T> comp) {
        if (list.size() < k) throw new IllegalArgumentException("List is to small");

        List<T> kSmallest = new LinkedList<>();
        list.sort(comp);

        for (int i = 0; i < k; i++) {
            kSmallest.add(list.get(i));
        }
        return kSmallest;
    }
    // Bobble sort = O(n*k)
    // Use temporary array = O((n-k)*k)
    // Use sorting = O(n*log(n))         = implies that logn operations will occure n times.
    // Using quick sort partitioning algorithm = worst O(2n) on average O(n) ||||||| O(nlogn)
}
