package INF102.Mandatory1.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MySmallestSelector implements ISmallestSelector {

    @Override
    public <T> List<T> selectSmallest(List<T> list, int k, Comparator<? super T> comp) {
        if (list.size() < k) throw new IllegalArgumentException("List is to small"); // O(1)

        List<T> kSmallest = new ArrayList<>(list);  // O(n)

        kSmallest.sort(comp);  // O(n * log(n) )

        return kSmallest.subList(0, k); // O(k)

    } // O(n * log(n) + k) -> O(n * log(n)
}
