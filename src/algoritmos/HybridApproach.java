package algoritmos;
import item.*;
import algoritmos.*;

import java.util.*;

public class HybridApproach implements RecommendationSystem {

    private ArrayList<Item> union(ArrayList<Item> l1, ArrayList<Item> l2) {
        Set<Item> s = new HashSet<>();
        s.addAll(l1);
        s.addAll(l2);
        return new ArrayList<>(s);
    }

    public ArrayList<Item> calculate() {
        collaborativeFiltering cf = new collaborativeFiltering();
        ContentBasedFiltering cbf = new ContentBasedFiltering();
        ArrayList<Item> l1 = cf.calculate();
        ArrayList<Item> l2 = cbf.calculate();
        return union(l1, l2);
    }
}
