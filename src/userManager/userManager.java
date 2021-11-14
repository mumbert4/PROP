package userManager;

import activeUser.activeUser;
import java.util.*;

public class userManager {
    Map<String, activeUser> users;


    public userManager(){
        users = new HashMap<String, activeUser>();
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
            System.out.println("User successfully created");
        }
    }

    public void addUser(activeUser user){
        String user_name = user.getName();
        if (existUser(user_name)) System.out.println("This user already exists");
        else{
            users.put(user_name, user);
            System.out.println("User added");
        }
    }

    public activeUser getUser(String user_name){
        return users.get(user_name);
    }
}
