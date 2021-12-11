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

    public void setItemMan(ItemManager itemMan) {
        items = itemMan;
    }

    public boolean existUser(String userId) {
        return users.containsKey(userId);
    }

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

    public void addUser(activeUser user) {
        String userId = user.getName();
        if (existUser(userId)) System.out.println("This user already exists");
        else {
            users.put(userId, user);
//            System.out.println("User added");
        }
    }

    public activeUser getUser(String userId) {
        return users.get(userId);
    }

    public void createReview(String userId, int itemId, double points, String comment) {
        Review r = new Review(points, comment);
        activeUser user = getUser(userId);
        user.addReview(itemId, r);
//        System.out.println("Review afegida amb exit");
    }

    public int numReviews(String userId) {
        return users.get(userId).numReviews();
    }

    public int numUsu() { //NOMBRE D'USUARIS QUE HI HA
        return users.size();
    }

    public List<String> getUsuaris() { //LISTA DE TOTS ELS USUARIS
        List<String> ids = new LinkedList<String>();
        //mapa users ordenat per clau
        Map<String, activeUser> sortedMap = new TreeMap<String, activeUser>(users);
        for (String userId : sortedMap.keySet()) {
            ids.add(userId);
        }
        return ids;
    }

    public double raiAve(String userId) {
        return users.get(userId).raiAve();
    }

    public Map<Integer, Double> getReviewsUsers(String userId, int k) { //clau:idItem, valor:rating de l'usuari a l'ítem
        return users.get(userId).getReviewsUsers(k);
    }





    public List<String> getUsersItems(Integer item1, Integer item2) {
        List<String> usrs = new LinkedList<>();
        for (Map.Entry<String, activeUser> en : users.entrySet()) {
            activeUser act = en.getValue();
            String userId = en.getKey();
            if (act.hasValuated(item1) && act.hasValuated(item2)) usrs.add(userId);
        }
        return usrs;
    }

    public Double getRaiting(String userId, Integer itemId) {
        return users.get(userId).getReview(itemId).getPoints();
    }

    public List<Integer> getNoVal(String userId, List<Integer> items) {
        List<Integer> ret = new LinkedList<>();
        for (Integer item : items) {
            if (!users.get(userId).hasValuated(item)) ret.add(item);
        }
        return ret;
    }

    public List<Integer> getVal(String userId, List<Integer> items) {
        List<Integer> ret = new LinkedList<>();
        for (Integer item : items) {
            if (users.get(userId).hasValuated(item)) {
                ret.add(item);
            }
        }
        return ret;
    }

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