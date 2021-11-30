package algoritmos;
import item.*;
<<<<<<< HEAD
=======
import algoritmos.*;
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835

import java.util.*;

public class HybridApproach implements RecommendationSystem {
<<<<<<< HEAD
    public ArrayList<Item> calculate(String s) {
        return null;
    }
}
=======

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
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
