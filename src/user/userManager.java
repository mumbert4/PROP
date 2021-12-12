package user;

import item.ItemManager;
import review.Review;

import java.util.*;


public class userManager {
    public Map<String, activeUser> users;
    ItemManager items;
    private static userManager manager;

    private userManager() {
        users = new HashMap<String, activeUser>();
    }

    public static userManager getInstance() {
        if (manager == null) manager = new userManager();
        return manager;
    }


    //complexitat O ( 1 )
    public void setItemMan(ItemManager itemMan) {
        items = itemMan;
    }

    //complexitat O ( users.size )
    public boolean existUser(String userId) {
        return users.containsKey(userId);
    }


    //complexitat O ( users.size )
    public void createUser(String userId, String password, String confirmPassword) {
        if (existUser(userId))
            System.out.println("The user with name: " + userId + " already exists, please choose another name");
        else if (!(password.equals(confirmPassword))) System.out.println("Passwords do not match, try again");
        else {
            activeUser user = new activeUser(userId, password);
            users.put(userId, user);
//            System.out.println("User successfully created");
        }
    }

    //complexitat O ( users.size )
    public void addUser(activeUser user) {
        String userId = user.getName();
        if (existUser(userId)) System.out.println("This user already exists");
        else {
            users.put(userId, user);
//            System.out.println("User added");
        }
    }

    //complexitat O ( 1 )
    public activeUser getUser(String userId) {
        return users.get(userId);
    }

    //complexitat O ( numReviewsUsuari )
    public void createReview(String userId, int itemId, double points, String comment) {
        Review r = new Review(points, comment);
        activeUser user = getUser(userId);
        user.addReview(itemId, r);
//        System.out.println("Review afegida amb exit");
    }

    //complexitat O ( 1 )
    public int numReviews(String userId) {
        return users.get(userId).numReviews();
    }

    //complexitat O ( 1 )
    public int numUsu() { //NOMBRE D'USUARIS QUE HI HA
        return users.size();
    }


    //complexitat O ( users.size )
    public List<String> getUsuaris() { //LISTA DE TOTS ELS USUARIS
        List<String> ids = new LinkedList<String>();
        //mapa users ordenat per clau
        Map<String, activeUser> sortedMap = new TreeMap<String, activeUser>(users);
        for (String userId : sortedMap.keySet()) {
            ids.add(userId);
        }
        return ids;
    }

    //complexitat O ( numReviewsUsuari )
    public double raiAve(String userId) {
        return users.get(userId).raiAve();
    }


    //complexitat O ( numReviewsUsuari )  -----------  O (K) espacial
    public Map<Integer, Double> getReviewsUsers(String userId, int k) { //clau:idItem, valor:rating de l'usuari a l'ítem
        return users.get(userId).getReviewsUsers(k);
    }




    //complexitat O (users.size * max(num_reviews_user))
    public List<String> getUsersItems(Integer item1, Integer item2) {
        List<String> usrs = new LinkedList<>();
        for (Map.Entry<String, activeUser> en : users.entrySet()) {
            activeUser act = en.getValue();
            String userId = en.getKey();
            if (act.hasValuated(item1) && act.hasValuated(item2)) usrs.add(userId);
        }
        return usrs;
    }

    //complexitat  O(1)
    public Double getRaiting(String userId, Integer itemId) {
        return users.get(userId).getReview(itemId).getPoints();
    }

    //complexitat O (items.size * num_reviews_user)
    public List<Integer> getNoVal(String userId, List<Integer> items) {
        List<Integer> ret = new LinkedList<>();
        for (Integer item : items) {
            if (!users.get(userId).hasValuated(item)) ret.add(item);
        }
        return ret;
    }

    //complexitat O (items.size * num_reviews_user)
    public List<Integer> getVal(String userId, List<Integer> items) {
        List<Integer> ret = new LinkedList<>();
        for (Integer item : items) {
            if (users.get(userId).hasValuated(item)) {
                ret.add(item);
            }
        }
        return ret;
    }

    // compleixtat (users.size * max(num_reviews_usuari²) )
    public Set<Integer> itemsNoVal(String userId, List<String> users) {
        Set<Integer> s = new HashSet<>();
        for (String user : users) {
            Map<Integer, Double> itemsVal = manager.getReviewsUsers(user, numReviews(userId));
            for (Map.Entry<Integer, Double> e : itemsVal.entrySet()) {
                if (!manager.getUser(userId).hasValuated(e.getKey())) s.add(e.getKey());
            }
        }
        return s;
    }
}