package item;

import java.util.ArrayList;
import java.util.Scanner;

public class driverItem {
    public static Scanner sc = new Scanner(System.in);
    static ArrayList<Pair<String,Column>> getAtributes(){
        ArrayList<Pair<String,Column>> atributes = new ArrayList<>();
        System.out.println("Insereix l'atribut que vols declarar, o escriu f si has acabat");
        System.out.println("Si es una descripcio, s'ha de possar com desc descripcio");
        String atribut = sc.next();
        System.out.println(atribut);
        while(!atribut.equals("f")){
            if(atribut.equals("desc")){
                String valor = sc.next();
                System.out.println(valor);
                ColumnString actItem = new ColumnString(valor);
                atributes.add(new Pair<>(atribut,actItem));
            }
            else {
                System.out.println("Ara insereix el valor de l'atribut: ");
                String valor = sc.next();
                if (isInt(valor)) {
//                     System.out.println("Atribut " + atribut + " afegit com Integer");
                    ColumnInteger actItem = new ColumnInteger(Integer.parseInt(valor));
                    atributes.add(new Pair<>(atribut,actItem));
                    //System.out.println(Integer.parseInt(aux));
                } else if (isB(valor)) {
//                     System.out.println("Atribut " + atribut + " afegit com Boolean");
                    ColumnBool actItem = new ColumnBool(Boolean.parseBoolean(valor));
                    atributes.add(new Pair<>(atribut,actItem));
                    //System.out.println(Boolean.parseBoolean(aux));
                } else if (isDbl(valor)) {
//                     System.out.println("Atribut " + atribut + " afegit com Double");
                    ColumnDouble actItem = new ColumnDouble(Double.parseDouble(valor));
                    atributes.add(new Pair<>(atribut,actItem));
                    //System.out.println(Double.parseDouble(aux));
                } else{
//                     System.out.println("Atribut " + atribut + " afegit com String");
                    ColumnString actItem = new ColumnString(valor);
                    atributes.add(new Pair<>(atribut,actItem));
                }
            }
            System.out.println("Insereix l'atribut que vols declarar, o escriu f si has acabat");
            atribut = sc.next();
        }

        return atributes;
    }

    public static void main(String[] args){
        Item item = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- createItem <Integer- itemId> <String-atributs> : crear un nou item");
        System.out.println("\t 2- getId : obtenir el id de l'item");
        System.out.println("\t 3- sizeCol : obtenir quantitat d'atributs");
        System.out.println("\t 4- getCol <Integer- numAtribut> : obtenir un atribut en concret");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if(action.equals("createItem")){
                String p = sc.next();
                Integer itemId = Integer.parseInt(p);
                System.out.println(itemId);
                //item = new Item(itemId,getAtributes());
            }
            if(action.equals("getId")){
                if(item!= null) System.out.println("Id del item: " + item.getId());
                else  System.out.println("Item no iniciat");
            }
            else if(action.equals("sizeCol")){
                if(item!= null) System.out.println("Num atributs: " + item.getSizeAttributes());
                else  System.out.println("Item no iniciat");
            }
            else if(action.equals("getCol")){
                String p = sc.next();
                Integer i = Integer.parseInt(p);
                System.out.println(i);
                if(item!= null) System.out.println("Atribut: " + item.getColumn(i));
                else  System.out.println("Item no iniciat");
            }
            action = sc.next();
        }
    }
    private static boolean isInt(String input) {
        try{
            int inputDbl = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private static boolean isDbl(String input) {
        try{
            double inputDbl = Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private static boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }
}