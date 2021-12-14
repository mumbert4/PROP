package user;

import item.ItemManager;
import review.Review;

import java.util.*;


public class UserManager {
    public Map<String, ActiveUser> users;
    ItemManager items;
    private static UserManager manager;

    //
    private UserManager() {
        users = new HashMap<String, ActiveUser>();
    }

    //
    public static UserManager getInstance() {
        if (manager == null) manager = new UserManager();
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
            ActiveUser user = new ActiveUser(userId, password);
            users.put(userId, user);
//            System.out.println("User successfully created");
        }
    }



    /**
     * Afegeix un usuari no existent als usuaris
     * @param user activeUser que volem afegir
     * Complexitat O (users.size)
     */
    public void addUser(ActiveUser user) {
        String userId = user.getName();
        if (existUser(userId)) System.out.println("This user already exists");
        else {
            users.put(userId, user);
        }
    }


    /**
     * Obtenir la instància d'un usuari
     * @param userId Identificador de l'usuari del que volem obtenir la instància
     * @return Instància de l'activeUser
     * Complxitat O (1)
     */
    public ActiveUser getUser(String userId) {
        return users.get(userId);
    }

    /**
     * Crea una review donat un usuari, una puntuacio, i un comentari i l'afegeix a les reviews realitzades per l'usuari
     * @param userId User que realitza la review
     * @param itemId Item que estam valorant
     * @param points Puntuació que esteim donant a l'item
     * @param comment Comentari que dona l'usuari a la review
     * Complexitat O(numReviewsUsuari)
     */
    public void createReview(String userId, int itemId, double points, String comment) {
        Review r = new Review(points, comment);
        ActiveUser user = getUser(userId);
        user.addReview(itemId, r);
//        System.out.println("Review afegida amb exit");
    }

    /**
     * Dona el nombre de reviews que ha fet un usuari
     * @param userId Usuari del que volem saber el nombre de reviews
     * @return Nombre de reviews fetes
     * Complexitat O (1)
     */
    public int numReviews(String userId) {
        return users.get(userId).numReviews();
    }

    /**
     * Retorna el nombre d'usuaris totals
     * @return Nombre usuaris totals
     * Complexitat O (1)
     */
    public int numUsu() {
        return users.size();
    }


    /**
     * Retorna la llista dels identificadors dels usuaris
     * @return Identificadors de tots els usuaris ordenats per clau
     * Complexitat O(users.size)
     */
    public List<String> getUsers() {
        List<String> ids = new LinkedList<String>();

        Map<String, ActiveUser> sortedMap = new TreeMap<String, ActiveUser>(users);
        for (String userId : sortedMap.keySet()) {
            ids.add(userId);
        }
        return ids;
    }

    //complexitat O ( numReviewsUsuari )

    /**
     *
     * @param userId
     * @return
     */
    public double raiAve(String userId) {
        return users.get(userId).raiAve();
    }

    //complexitat O ( numReviewsUsuari )  -----------  O (K) espacial
    public Map<Integer, Double> getReviewsUsers(String userId, int k) { //clau:idItem, valor:rating de l'usuari a l'ítem
        return users.get(userId).getReviewsUsers(k);
    }

    //complexitat O ( users.size * max(num_reviews_user) )
    public List<String> getUsersItems(Integer item1, Integer item2) {
        List<String> usrs = new LinkedList<>();
        for (Map.Entry<String, ActiveUser> en : users.entrySet()) {
            ActiveUser act = en.getValue();
            String userId = en.getKey();
            if (act.hasValuated(item1) && act.hasValuated(item2)) usrs.add(userId);
        }
        return usrs;
    }


    /**
     * Retorna la puntuació que ha donat un usuari a un item ,els dos han d'existir i l'usuari l'ha d'haver valorat
     * @param userId Usuari que ha donat la puntuació
     * @param itemId Item del que volem la puntuació donada per l'usuari
     * @return Puntuació donada a l'item per l'usuari
     * Complexitat O(1)
     */
    public Double getRaiting(String userId, Integer itemId) {
        return users.get(userId).getReview(itemId).getPoints();
    }


    /**
     * Donat un usuari i una llista de items, ens retorna els items que l'usuari no ha valorat de la llista
     * @param userId Usuari que volem
     * @param items LLista de items
     * @return Items de la llista donada que l'usuari no ha valorat
     * Complexitat O(items.size * num_reviews_user)
     */
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