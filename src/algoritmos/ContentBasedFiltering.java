package algoritmos;

import item.ItemManager;
import user.userManager;

import java.util.*;

public class ContentBasedFiltering implements RecommendationSystem {

    userManager users;
    ItemManager items;

    public ContentBasedFiltering(userManager usrs, ItemManager its){
        users = usrs;
        items = its;
    }

    public List<Integer> calculate(String userId, int k, List<Integer> Items){
        Map<Integer,Double> items_us = users.getUser(userId).getReviewsUsers(k); // aqui tenim els k items que li han agradat mes
        //Per cada item que li ha agardat, obtenim els k items mes semblants a n'aquest, i els ficam en un map sencer.

        Map<Integer, Set<Integer>> mapa_it= new HashMap<>(); // id item1(un que li ha agradat al user)     //id item2 dist respecte item1
        List <Integer> items_rec= new LinkedList<>();


        for (Map.Entry<Integer,Double> e : items_us.entrySet()) { //obtenim els k items mes semblants de cada item que li ha agradat a l'usuari
            Integer item = e.getKey();
            Map<Integer, Double> items_par = items.retornaItemsSemblants(item,k); // retornam els k items mes semblants a l'item passat per parametre
            mapa_it.put(item, items_par.keySet());
        }

        System.out.println(mapa_it);

        while (items_rec.size()!= k){
            for (Integer item : mapa_it.keySet()){
                Integer itemsSemblant = mapa_it.get(item).iterator().next();// primer item mes semblant que tenim al mapa_it
                System.out.println(itemsSemblant);
                if(!items_rec.contains(itemsSemblant))items_rec.add(itemsSemblant);
                mapa_it.get(item).remove(itemsSemblant);
            }
        }

//        while (items_rec.size() != k) {
//            for (Map.Entry<Integer,Double> e : items_us.entrySet()) {
//                Integer item = e.getKey();
//                Map<Integer, Double> items_par = items.retornaItemsSemblants(item,k);
//                mapa_it.put(item, items_par);
//                Iterator<Map.Entry<Integer, Double>> it = items_par.entrySet().iterator();
//                Map.Entry<Integer, Double> entry = it.next(); //primer valor
//                entry = it.next(); //ignorem primer valor
//                while(items_rec.contains(entry.getKey())) entry = it.next();
//                items_rec.add(entry.getKey());
//                if (items_rec.size() == k) break;
//            }
//        }
        return items_rec;

    }
}
