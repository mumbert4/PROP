package algorithms;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe que implementa l'algoritme Hybrid Approach on es basa en la unio de les llistes dels items recomanats que donen els algoritmes de Content Based i Collaborative Filtering
 */

public class HybridApproach implements RecommendationSystem  {
    ContentBasedFiltering contentBased;
    CollaborativeFiltering collaborative;


    /**
     * Constructora de la classe
     * @param cb Instancia de l'algoritme Content Based
     * @param col Instancia de l'algoritme Collaborative Filtering
     * Complexitat O(1)
     */
    public HybridApproach( ContentBasedFiltering cb, CollaborativeFiltering col){
        contentBased = cb;
        collaborative = col;
    }

    /**
     * Realitza la unio de les llistes donades, sense elements repetits
     * @param l1 Primera llista d'elements
     * @param l2 Segona llista d'elements
     * @return Unio de les llistes sense elements repetits
     * Complexitat O( l1.size + l2.size )
     */
    private List<Integer> union(List<Integer> l1, List<Integer> l2) {
        List<Integer> s = new LinkedList<>();
        s.addAll(l1);
        for (Integer item : l2){
            if(! s.contains(item)) s.add(item);
        }

        return s;
    }
    //    //

    /**
     * Dona una llista de items recomanats a l'usuari, feta per la unio de les recomanacions fetes pel Collaborative Filtering i el Content Based
     * @param userId Identificador de l'usuari al que volem fer la recomanacio
     * @param k K items que ens donara cada algorisme
     * @param Items LLista de possibles items recomanables
     * @return LLista dels items que ens recomanen els dos algoritmes, sense elements repetits
     * Complexitat O (items.size² * users.size * max(num_reviews_user)  +  CjtClusters.size * max(UsersCjt.size)) + (users.size * max(num_reviews_usuari²) )  +   (items.size * num_reviews_user)  +
     * s.size * (itemsVal.size * users.size * max(num_reviews_user) + itemsBons.size * (mapIt1.size + mapIt2.size))  +  (m.size)   ))
     */
    public List<Integer> calculate(String userId, int k, List<Integer> Items){
        List<Integer> itemsCol = collaborative.calculate(userId,k,Items);
        List<Integer> itemsCb= contentBased.calculate(userId,k,Items);
        // complexitat O
        return union(itemsCol, itemsCb);
    }
}

