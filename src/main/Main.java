package main;

import userManager.userManager;
import activeUser.activeUser;

public class Main {
    public static void main(String[] args) {
        userManager manager = userManager.getInstance();
        activeUser user = new activeUser("Miquel", "1234");
        manager.addUser(user);
        manager.createReview("Miquel",16, 5.5, "Ha estat molt be");
        manager.createReview("Miquel",16, 5.5, "Ha estat molt be");
        System.out.println("Usuari Miquel ha fet: " + manager.numReviews("Miquel")+ " reviews en total");
    }
}
