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
        List<Integer> itemsCol = collaborative.calculate(userId,k,Items);
        List<Integer> itemsCb= contentBased.calculate(userId,k,Items);
        return union(itemsCol, itemsCb);
    }
}

