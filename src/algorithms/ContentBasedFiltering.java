package algorithms;

import item.ItemManager;
import user.UserManager;

import java.util.*;

public class ContentBasedFiltering implements RecommendationSystem {
    UserManager users;
    ItemManager items;

    // complexitat O ( 1 )
    public ContentBasedFiltering(UserManager usrs, ItemManager its){
        users = usrs;
        items = its;
    }

    // complexitat O ( num_reviewws_user * items.size )
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
