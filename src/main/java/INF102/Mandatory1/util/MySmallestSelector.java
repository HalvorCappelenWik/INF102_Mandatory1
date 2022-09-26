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
        list.sort(comp);  // O(n * log(n))

        for (int i = 0; i < k; i++) {  // O(k)
            kSmallest.add(list.get(i));  // O(1)
        }
        return kSmallest;
    }
}

//  Sorting the list in ascending order, then adding the k first item in our new list.
//  list.sort(comp) = O(n * log (n) * c) where c is the comparator.
//  We use the Compartor.Naturalorder which has O(1) time complexity.