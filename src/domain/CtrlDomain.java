package domain;

import algorithms.*;
import data.CtrlData;
import item.*;
import estructures.Pair;
import review.Review;
import review.ReviewList;
import user.ActiveUser;
import user.UserManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



//Fa queries al CtrlDades i carrega les dades que es necessiten
public class CtrlDomain {
    private static CtrlData CData;
    private static CtrlDomain singletonO;

    public static int valorK = 3;

    ItemManager items;
    UserManager manager;
    ReviewList reviewList;
    CollaborativeFiltering col;
    ContentBasedFiltering cb;
    Item item;
    Kmeans km;
    Avaluator av;
    HybridApproach hb;

    // complexitat O (1)
    private CtrlDomain() {
        initializeCtrlDomain();
    }

    /* Inicialitzem el controlador del domini*/
    // complexitat O (1)
    private void initializeCtrlDomain() {
        CData = CtrlData.getInstance();
        items = new ItemManager();
        manager = UserManager.getInstance();
        reviewList = new ReviewList();
        item = new Item();
        km = new Kmeans();
        av = new Avaluator();
        col = new CollaborativeFiltering(manager,km);
        cb = new ContentBasedFiltering(manager,items);
        hb = new HybridApproach(cb,col);
    }

    // complexitat O (1)
    public static CtrlDomain getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDomain() {};
        } return singletonO;
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public void obtainData(UserManager manager) throws FileNotFoundException {
        CData.obtainData(manager);
    }

    // complexitat O (items.csv.size -> mida fitxer)
    public List<String> getItems() throws IOException {
        return CData.getItems();
    }

    public List<String> getPonderacions() throws  IOException{
        return CData.getPonderacions();
    }

    public void createItemDomain(int id, ArrayList<Pair<String,String>> attributtes) {
        ArrayList<Pair<String,Column>> atts = new ArrayList<>();
        for (int i = 0; i < attributtes.size(); ++i) {
            if (isInt(attributtes.get(i).getSecond())) {
                int inputInt = Integer.parseInt(attributtes.get(i).getSecond());
                ColumnInteger c = new ColumnInteger(inputInt);
                atts.add(new Pair<>(attributtes.get(i).getFirst(), c));
            }
            else if (isB(attributtes.get(i).getSecond())) {
                boolean inputB = Boolean.parseBoolean(attributtes.get(i).getSecond());
                ColumnBool c = new ColumnBool(inputB);
                atts.add(new Pair<>(attributtes.get(i).getFirst(), c));
            }
            else if (isDbl(attributtes.get(i).getSecond())) {
                Double inputDbl = Double.parseDouble(attributtes.get(i).getSecond());
                ColumnDouble c = new ColumnDouble(inputDbl);
                atts.add(new Pair<>(attributtes.get(i).getFirst(), c));
            }
            else {
                ColumnString c = new ColumnString(attributtes.get(i).getSecond());
                atts.add(new Pair<>(attributtes.get(i).getFirst(), c));
            }
            items.createItem(id,atts);
        }
    }

    public boolean isInt(String input) {
        try{
            int inputDbl = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    public boolean isDbl(String input) {
        try{
            double inputDbl = Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    public boolean isFlt(String input) {
        try{
            Float.parseFloat(input);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }

    public boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }

    public ArrayList<Pair<String,String>> getTitleTypeColumnsDomain() {

        CData.initializeCtrlData();
        System.out.println(CData.getItems().get(0));
        ArrayList<Pair<String, String>> cols = new ArrayList<>();
        List<String> columnes = new LinkedList<>(); //guardem els noms de les columnes
        List<String> tipus = new LinkedList<>(); //tenim en format string el valor dels atrtibuts del 1r item
        List<String> tipusEnString = new LinkedList<>();
        int j = 0;
        int k = 0;
        String aux = "";
        String auxT = "";

        while (j < CData.getItems().get(0).length()) {
            if ((CData.getItems().get(0).charAt(j) == ',' || j == CData.getItems().get(0).length() - 1)) {
                columnes.add(aux);
                //System.out.println(aux);
                aux = "";
            } else {
                aux += CData.getItems().get(0).charAt(j);
            }
            ++j;
        }
        while (k < CData.getItems().get(1).length()) {
            if(CData.getItems().get(1).charAt(k) == '"'){
                ++k;
                auxT = "";
                while (!(CData.getItems().get(1).charAt(k) == '"' && CData.getItems().get(1).charAt(k + 1) == ',' && CData.getItems().get(1).charAt(k + 2) != ' ')){
                    aux += CData.getItems().get(1).charAt(k);
                    ++k;
                }
                ++k;
                tipus.add(aux);
                aux = "";
            }
            else if ((CData.getItems().get(1).charAt(k) == ',' || k == CData.getItems().get(1).length() - 1)) {
                tipus.add(auxT);
                //System.out.println(auxT);
                auxT = "";
            } else {
                auxT += CData.getItems().get(1).charAt(k);
            }
            ++k;
        }


        System.out.println("ESCRIVINT LLISTA TIPUS");
        for (int p = 0; p < tipus.size(); ++p) {
            if (isInt(tipus.get(p))) {
                System.out.println(tipus.get(p) + " " + "Integer");
                tipusEnString.add("Integer");
            } else if (isDbl(tipus.get(p)) || isFlt(tipus.get(p))) {
                System.out.println(tipus.get(p) + " " + "Double");
                tipusEnString.add("Double");
            } else if (isB(tipus.get(p))) {
                System.out.println(tipus.get(p) + " " + "Boolean");
                tipusEnString.add("Boolean");
            } else {
                System.out.println(tipus.get(p) + " " + "String");
                tipusEnString.add("String");
            }
        }
        System.out.println("HEM ESCRIT LLISTA TIPUS");

        for (int m = 0, n = 0; m < columnes.size() && n < tipusEnString.size(); ++m, ++n) {
            System.out.println("La columna: " + columnes.get(m) + " es de tipus: " + tipusEnString.get(n));
            if (!columnes.get(m).equals("id")) cols.add(new Pair<>(columnes.get(m), tipusEnString.get(n)));
        }
        System.out.println("ESCRIVINT COLUMNES");
        for (int i = 0; i < cols.size(); ++i) {
            System.out.println(cols.get(i).getFirst() + " " + cols.get(i).getSecond());
        }
        System.out.println("HEM ESCRIT COLUMNES");
        return cols;
    }

    public void settingAllData() {
        initializeCtrlDomain();
    }

    public boolean existUserDomain(String id) {
        return manager.existUser(id);
    }

    public void createUserDomain(String id, String password){
        manager.createUser(id, password);
    }

    public boolean existItemDomain(int id) {
        return items.existItem(id);
    }

    /** id de l'usuari + id de l'item **/
    public boolean existReviewDomain(String user, int id) {
        ActiveUser us = manager.getUser(user);
        return us.hasValuated(id);
    }

    public void addReviewDomain(int id, double points, String comment, String userName) {
        Review r = new Review(points,comment);
        manager.getUser(userName).addReview(id,r);

    }

    public String getPasswordDomain(String id) {
        return manager.getUser(id).getPassword();
    }

    /** Faltaria passar l'id de l'usuari al controlador de domini*/
    public void setPasswordDomain(String id, String pass) {
        manager.getUser(id).changePassword(pass);
    }

    public void deleteItemDomain(int id) {
        items.deleteItem(id);
    }

    public ArrayList<String> getAttributesItemDomain(int id) {
        ArrayList<String> atributs = new ArrayList<>();
        for (int i = 0; i < items.getItem(id).getAttributes().size(); ++i) {
            atributs.add(items.getItem(id).getAttributes().get(i).getFirst());
        }
        return atributs;
    }

    public void deleteUserDomain(String id) {
        manager.deleteUser(id);
    }

    public void updateKmeans() {
        km.kmeans(manager,items.getItems(),valorK);
    }

    /** Crec que estaria bé així */
    public void updateMapDistances() throws IOException {

        items.fillMapDistances(singletonO.getItems());
    }
    /** Faltaria passar el userId */
    public double evaluation(List<Integer> recommendation, String type, String userId) throws IOException {
        return av.evaluate(recommendation, type, singletonO.getRatings(userId));
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public Map<Integer,Double> getRatings(String userId) throws IOException {
        return CData.getUnknown(userId);
    }

    public List<Integer> collaborative(String id) {
        return col.calculate(id, valorK, items.getItems());
    }

    public List<Integer> content(String id) {
        return cb.calculate(id, valorK, items.getItems());
    }

    public List<Integer> hybrid(String id) {
        return hb.calculate(id, valorK, items.getItems());
    }

    public void initializeData() throws IOException {

        obtainData(manager);
        List<String> users = manager.getUsers();
        List<Integer> usersInt = new LinkedList<>();
        for(String s : users) usersInt.add(Integer.parseInt(s));
        Collections.sort(users);
        Collections.sort(usersInt);
        items.createColumns(singletonO.getItems());
        items.fillPonderacions(singletonO.getPonderacions());
        updateMapDistances();
        updateKmeans();
    }


}
