package algoritmos;

import java.util.LinkedList;
import java.util.List;

public class HybridApproach implements RecommendationSystem  {
    ContentBasedFiltering contentBased;
    collaborativeFiltering collaborative;

    //complexitat O ( 1 )
    public HybridApproach( ContentBasedFiltering cb, collaborativeFiltering col){
        contentBased = cb;
        collaborative = col;
    }

    // complexitat O (l1.size + l2.size)
    private List<Integer> union(List<Integer> l1, List<Integer> l2) {
        List<Integer> s = new LinkedList<>();
        s.addAll(l1);
        for (Integer item : l2){
            if(! s.contains(item)) s.add(item);
        }

        return s;
    }
    // complexitat O (items.size² * users.size * max(num_reviews_user)  +  CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  +
    //    // s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size)   ))
    public List<Integer> calculate (String userId, int k, List<Integer> Items){
        List<Integer> itemsCol = collaborative.calculate(userId,k,Items);
        List<Integer> itemsCb= contentBased.calculate(userId,k,Items);
        return union(itemsCol, itemsCb);
    }
}

