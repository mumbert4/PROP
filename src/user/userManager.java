package user;

import item.ItemManager;
import review.Review;

import java.util.*;


public class userManager {
    public Map<String, activeUser> users;
    ItemManager items;
    private static userManager manager;

    private userManager(){
        users = new HashMap<String, activeUser>();
    }

    public static userManager getInstance(){
        if(manager == null) manager = new userManager();
        return manager;
    }

    public void setItemMan(ItemManager itemMan){
        items = itemMan;
    }
    public boolean existUser(String user_name){
        return users.containsKey(user_name);
    }

    public void createUser(String user_name, String password, String confirm_password){
        if(existUser(user_name)) System.out.println("The user with name: " +user_name+" already exists, please choose another name");
        else if (!(password.equals(confirm_password))) System.out.println("Passwords do not match, try again");
        else {
            activeUser user = new activeUser(user_name, password);
            users.put(user_name, user);
//            System.out.println("User successfully created");
        }
    }

    public void addUser(activeUser user){
        String user_name = user.getName();
        if (existUser(user_name)) System.out.println("This user already exists");
        else{
            users.put(user_name, user);
//            System.out.println("User added");
        }
    }

    public activeUser getUser(String user_name){
        return users.get(user_name);
    }

    public void createReview(String user_name, int item_id, double points, String comment){
        Review r = new Review(points, comment);
        activeUser user = getUser(user_name);
        user.addReview(item_id, r);
//        System.out.println("Review afegida amb exit");
    }

    public int numReviews(String user_name){
        return users.get(user_name).numReviews();
    }

    public int numUsu(){ //NOMBRE D'USUARIS QUE HI HA
        return users.size();
    }

    public List<String> getUsuaris(){ //LISTA DE TOTS ELS USUARIS
        List<String> ids = new LinkedList<String>();
        //mapa users ordenat per clau
        Map<String, activeUser> sortedMap = new TreeMap<String, activeUser>(users);
        for(String user_id : sortedMap.keySet()){
            ids.add(user_id);
        }
        return ids;
    }

    public double raiAve(String user_id){
        return users.get(user_id).raiAve();
    }

    public Map<Integer,Double> getReviewsUsers(String user_id) { //clau:id_item, valor:rating de l'usuari a l'ítem
        return users.get(user_id).getReviewsUsers();
    }




    public void getItemsSemblants(String user_id, Integer k){
        Map<Integer,Double> items_us = users.get(user_id).getReviewsUsers(); // aqui tenim els items que mes li han agradat al ususari.
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

    public List<String> getUsers_items(Integer item1, Integer item2){
        List<String> usrs = new LinkedList<>();
        for(Map.Entry<String, activeUser> en: users.entrySet()){
            activeUser act = en.getValue();
            String user_name = en.getKey();
            if(act.hasValuated(item1) && act.hasValuated(item2)) usrs.add(user_name);
        }
        return usrs;
    }

    public Double getRaiting(String user_name, Integer item_id){
        return users.get(user_name).getReview(item_id).getPoints();
    }

    public List<Integer> getNoVal(String user_id, List<Integer> items){
        List<Integer> ret = new LinkedList<>();
        for(Integer item : items){
            if(!users.get(user_id).hasValuated(item)) ret.add(item);
        }
        return ret;
    }

    public List<Integer> getVal(String user_id, List<Integer> items){
        List<Integer> ret = new LinkedList<>();
        for(Integer item : items){
            if(users.get(user_id).hasValuated(item)){
                ret.add(item);
            }
        }
        return ret;
    }

    public Set<Integer> itemsNoVal(String user_id, List<String> users){
        Set<Integer> s = new HashSet<>();
        for(String user : users){
            Map<Integer,Double> items_val = manager.getReviewsUsers(user);
            for(Map.Entry<Integer,Double> e : items_val.entrySet()) {
                if (!manager.getUser(user_id).hasValuated(e.getKey())) s.add(e.getKey());
            }
        }
        return s;
    }

}