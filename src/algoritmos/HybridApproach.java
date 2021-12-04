package algoritmos;

import java.util.LinkedList;
import java.util.List;

public class HybridApproach implements RecommendationSystem  {

    ContentBasedFiltering contentBased;
    collaborativeFiltering collaborative;

    public HybridApproach( ContentBasedFiltering cb, collaborativeFiltering col){
        contentBased = cb;
        collaborative = col;
    }

    private List<Integer> union(List<Integer> l1, List<Integer> l2) {
        List<Integer> s = new LinkedList<>();
        s.addAll(l1);
        for (Integer item : l2){
            if(! s.contains(item)) s.add(item);
        }

        return s;
    }


    public List<Integer> calculate (String userId, int k, List<Integer> Items){
        List<Integer> items_col = collaborative.calculate(userId,3,Items);
        List<Integer> items_cb= contentBased.calculate(userId,3,Items);
        return union(items_col, items_cb);

    }




}

