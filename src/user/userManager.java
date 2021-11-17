package user;

import review.Review;
import java.util.*;



public class userManager {
    Map<String, activeUser> users;

    private static userManager manager;

    private userManager(){
        users = new HashMap<String, activeUser>();
    }

    public static userManager getInstance(){
        if(manager == null) manager = new userManager();
        return manager;
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
        for(String user_id : users.keySet()){
            ids.add(user_id);
        }
        return ids;
    }

    public double raiAve(String user_id){
        return users.get(user_id).raiAve();
    }

    public void getReviewsUsers(String user_id) {
        users.get(user_id).getReviewsUsers();
    }
}

