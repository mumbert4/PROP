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