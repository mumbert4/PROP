package algoritmos;
import item.*;
<<<<<<< HEAD

=======
import user.userManager;
import item.ItemManager;
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
import java.util.*;

public class ContentBasedFiltering implements RecommendationSystem {

<<<<<<< HEAD
    public ArrayList<Item> calculate(Map<Integer, Map<Integer ,Double>> mapDistances) {
        return null;
    }

}
=======
    userManager users;
    ItemManager items;

    public ContentBasedFiltering(userManager usrs, ItemManager its){
        users = usrs;
        items = its;
    }

    public void getItemsSemblants(String user_id, Integer k){
        Map<Integer,Double> items_us = users.getUser(user_id).getReviewsUsers(); // aqui tenim els items que mes li han agradat al ususari.
        //Per cada item que li ha agardat, obtenim els k items mes semblants a n'aquest, i els ficam en un map sencer.

        Map<Integer, Map<Integer,Double>> mapa_it= new HashMap<>(); // id item1(un que li ha agradat al user)     //id item2 dist respecte item1
        List <Integer> items_rec= new LinkedList<>();

        //El printa tantes vegades eitems recomanants com ratings ha donat l'usuari
        for (Map.Entry<Integer,Double> e : items_us.entrySet()) {
            Integer item = e.getKey();
            Map<Integer, Double> items_par = items.retornaItemsSemblants(item,k);
            mapa_it.put(item, items_par);
            //System.out.println(item + " item que li agrada, items relascionats: " + items_par);
            Iterator<Map.Entry<Integer, Double>> it = items_par.entrySet().iterator();
            Map.Entry<Integer, Double> entry = it.next();
            if (items_rec.contains(entry.getKey())) entry = it.next();
            items_rec.add(entry.getKey());
        }
        while (items_rec.size() != k) {
            for (Map.Entry<Integer,Double> e : items_us.entrySet()) {
                Integer item = e.getKey();
                Map<Integer, Double> items_par = items.retornaItemsSemblants(item,k);
                mapa_it.put(item, items_par);
                //System.out.println(item + " item que li agrada, items relascionats: " + items_par);
                Iterator<Map.Entry<Integer, Double>> it = items_par.entrySet().iterator();
                Map.Entry<Integer, Double> entry = it.next(); //primer valor
                entry = it.next(); //ignorem primer valor
                if (items_rec.contains(entry.getKey())) entry = it.next();
                items_rec.add(entry.getKey());
                if (items_rec.size() == k) break;
            }
        }
        System.out.println("Items recomanats a l'user "+ user_id + " :" + items_rec);
    }



}
>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
