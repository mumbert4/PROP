package presentation;

import domain.CtrlDomain;
import estructures.Pair;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CtrlPresentation {

    private static Boolean firstScreen;
    private String idUser;
    private static JFrame frame;
    private static CtrlDomain cDomain;
    private static CtrlPresentation singletonO;
    private String idUserAux;
    private static JPanel welcomeScreen;
    private static JPanel createProfile;
    private static JPanel loginProfile;
    private static JPanel menuUser;
    private static JPanel menuUserManager;
    private static JPanel getRecommendationUserManager;
    private static JPanel getRecommendationActiveUser;
    private static JPanel changePassword;
    private static JPanel addReview;
    private static JPanel createItem;
    private static JPanel modifyItem;
    private static JPanel deleteItem;
    private static JPanel getUser;
    private static JPanel actual;

    private CtrlPresentation() {}

    public static void main(String[] args) throws Exception{
        singletonO = new CtrlPresentation();
        initializeCtrlPresentation();
    }

    public boolean existUser(String id) {
        return cDomain.existUserDomain(id);
    }

    public void createUser(String id, String password) {
        cDomain.createUserDomain(id, password);
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String id) {
        this.idUser = id;
    }

    public List<Integer> getRecommendation(String typeRecommendation) {
        List<Integer> result;
        if (typeRecommendation.equals("col")) result = cDomain.collaborative(idUserAux);
        else if (typeRecommendation.equals("cb")) result = cDomain.content(idUserAux);
        else result = cDomain.hybrid(idUserAux);
        return result;
    }

    public Boolean existItem(int id) {
        return cDomain.existItemDomain(id);
    }

    public void createItem(int id, ArrayList<Pair<String,String>> attributes) {
        cDomain.createItemDomain(id, attributes);
    }

    public Boolean existReview(int idItem) {
        return cDomain.existReviewDomain(idUserAux, idItem);
    }

    public void addReview(int idItem, double points, String comment) {
        cDomain.addReviewDomain(idItem, points, comment, idUserAux);
    }

    public String getPassword(String id) {
        return cDomain.getPasswordDomain(id);
    }

    public void changePassword(String password) {
        cDomain.setPasswordDomain(idUserAux, password);
    }

    public void deleteItem(int id) {
        cDomain.deleteItemDomain(id);
    }

    public ArrayList<String> getAttributesItem(int id) {
        return cDomain.getAttributesItemDomain(id);
    }

    public ArrayList<Pair<String, String>> getTitleTypeColumns() {
        return cDomain.getTitleTypeColumnsDomain();
    }

    public static CtrlPresentation getInstance(){
        return singletonO;
    }

    private static void initializeCtrlPresentation() throws IOException {
        firstScreen = true;
        cDomain = CtrlDomain.getInstance();

        cDomain.initializeData();
        frame = new JFrame();
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeScreen = new JPanel();
        createProfile = new JPanel();
        loginProfile = new JPanel();
        menuUser = new JPanel();
        menuUserManager = new JPanel();
        getRecommendationUserManager = new JPanel();
        getRecommendationActiveUser = new JPanel();
        changePassword = new JPanel();
        addReview = new JPanel();
        createItem = new JPanel();
        modifyItem = new JPanel();
        deleteItem = new JPanel();
        getUser = new JPanel();
        new CtrlWelcomeScreen(welcomeScreen);
        new CtrlCreateProfile(createProfile);
        new CtrlLoginProfile(loginProfile);
        new CtrlMenuUser(menuUser);
        new CtrlMenuUserManager(menuUserManager);

        new CtrlGetRecommendationUserManagerMode(getRecommendationUserManager);
        new CtrlGetRecommendationActiveUserMode(getRecommendationActiveUser);
        new CtrlChangePassword(changePassword);

        new CtrlAddReview(addReview);
        new CtrlCreateItem(createItem);
        //new CtrlModifyItem(modifyItem);
        new CtrlDeleteItem(deleteItem);
        new CtrlGetUser(getUser);

        setPanel(0);
    }

    public void deleteUser() {
        cDomain.deleteUserDomain(idUserAux);
    }

    public String getIdUserAux() {
        return idUserAux;
    }

    public void setIdUserAux(String id) {
        idUserAux = id;
    }

    public void updateKmeans() {
        cDomain.updateKmeans();
    }

    public void updateMapDistances() throws IOException {
        cDomain.updateMapDistances();
    }

    public Double evaluation(List<Integer> recommendation, String type) throws IOException {
        return cDomain.evaluation(recommendation, type, idUserAux);
    }

    public static void setPanel(int i) {
        if (firstScreen) {
            firstScreen = false;
            actual = welcomeScreen;
        }

        JPanel fin = null;
        if (i == 0) {
            fin = welcomeScreen;
            frame.setTitle("Welcome Screen");
        }
        else if (i == 1)  {
            fin = createProfile;
            frame.setTitle("Create Profile");
        }
        else if (i == 2) {
            fin = loginProfile;
            frame.setTitle("Login Profile");
        }
        else if (i == 3) {
            fin = menuUser;
            frame.setTitle("Menu User");
        }
        else if (i == 4) {
            fin = menuUserManager;
            frame.setTitle("Menu User Manager");
        }
        else if (i == 5) {
            fin = getRecommendationUserManager;
            frame.setTitle("Recommendation User Manager");
        }
        else if (i == 6) {
            fin = getRecommendationActiveUser;
            frame.setTitle("Recommendation Active User");
        }
        else if (i == 7) {
            fin = changePassword;
            frame.setTitle("Change Password");
        }
        else if (i == 8) {
            fin = addReview;
            frame.setTitle("Add Review");
        }
        else if (i == 9) {
            fin = createItem;
            frame.setTitle("Create Item");
        }
        else if (i == 10) {
            fin = modifyItem;
            frame.setTitle("Modify Item");
        }
        else if (i == 11) {
            fin = deleteItem;
            frame.setTitle("Delete Item");
        }
        else if (i == 12) {
            fin = getUser;
            frame.setTitle("Get User");
        }

        frame.remove(actual);
        frame.repaint();
        frame.add(fin);
        frame.setVisible(true);
        actual = fin;
    }

}
