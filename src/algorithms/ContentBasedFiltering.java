package algorithms;

import item.ItemManager;
import user.UserManager;

import java.util.*;


/**
 * Classe per a l'algoritme Content Based, aquest dona una recomanacio dels items, basant-se en items semblants a items que han agradat a l'usuari
 * Nomes conte el userManager i el itemManager en que es basa
 */
public class ContentBasedFiltering implements RecommendationSystem {
    UserManager users;
    ItemManager items;


    /**
     * Constructora de l'algoritme
     * @param usrs UserManager al que fara referencia
     * @param its ItemManager al que fara referencia
     * Complexitat O(1)
     */
    public ContentBasedFiltering(UserManager usrs, ItemManager its){
        users = usrs;
        items = its;
    }


    /**
     * Retorna els K items que mes se li recomanen a l'usuari
     * @param userId Identificador de l'usuari al que volem fer la recomanacio
     * @param k Nombre de items que li recomanarem a l'usuari
     * @param Items LLista de possibles items que li podem recomanar a l'usuari
     * @return LLista amb els k items que li recomanam a l'usuari
     * Complexitat O( numReviewsUser * items.size )
     */
    public List<Integer> calculate(String userId, int k, List<Integer> Items){
        Map<Integer,Double> itemsUs = users.getUser(userId).getReviewsUsers(k); // aqui tenim els k items que li han agradat mes
        //Per cada item que li ha agardat, obtenim els k items mes semblants a n'aquest, i els ficam en un map sencer.

        Map<Integer, List<Integer>> mapaIt= new HashMap<>(); // id item1(un que li ha agradat al user)     //id item2 dist respecte item1
        List <Integer> itemsRec= new LinkedList<>();

        for (Map.Entry<Integer,Double> e : itemsUs.entrySet()) { //obtenim els k items mes semblants de cada item que li ha agradat a l'usuari
            Integer item = e.getKey();
            mapaIt.put(item, items.returnSimilarItems(item,k)); // retornam els k items mes semblants a l'item passat per parametre

        }


        while (itemsRec.size()!= k){
            for (Integer item : mapaIt.keySet()){
                Integer itemsSemblant = mapaIt.get(item).iterator().next();// primer item mes semblant que tenim al mapaIt
                if(!itemsRec.contains(itemsSemblant))itemsRec.add(itemsSemblant);
                mapaIt.get(item).remove(itemsSemblant);
            }
        }
        return itemsRec;
    }
}
