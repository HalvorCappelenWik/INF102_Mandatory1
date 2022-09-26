package INF102.Mandatory1.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MySmallestSelector implements ISmallestSelector {

    @Override
    public <T> List<T> selectSmallest(List<T> list, int k, Comparator<? super T> comp) {
        if (list.size() < k) throw new IllegalArgumentException("List is to small"); // O(1)
        List<T> kSmallest = new LinkedList<>();  // O(1)

        list.sort(comp);  // O(n * log(n) * c) where c = Comparator.NaturalOrder = O(1)

        for (int i = 0; i < k; i++) {  // O(k)
            kSmallest.add(list.get(i));  // best case: O(1)  worst case: O(n)
                                         // List.get(i) if Arraylist = O(1), if linkedList = O(n)
        }
        return kSmallest;
    } // O(n * log(n))
}
