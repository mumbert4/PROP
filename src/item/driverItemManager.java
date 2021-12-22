package item;

import java.util.ArrayList;
import java.util.Scanner;
import estructures.Pair;

public class driverItemManager {

    static ArrayList<Pair<String,Column>> getAtributes(){

        ArrayList<Pair<String,Column>> atributes = new ArrayList<>();
        System.out.println("Insereix l'atribut que vols declarar, o escriu f si has acabat");
        System.out.println("Si es una descripcio, s'ha de possar com desc descripcio");
        Scanner sc = new Scanner(System.in);
        String atribut = sc.next();
        while(!atribut.equals("f")){
            if(atribut.equals("desc")){

                String valor = sc.nextLine();
//                 System.out.println(valor);
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

    public static void main(String args[]){
        ItemManager manager = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.print("\t 1- initiate: iniciam el item manager");
        System.out.print("\t 2- createItem <Integer- item_id> <String- atributs>: cream un item amb els seus atributs");
        System.out.print("\t 3- existItem <Integer- item_id> : retorna si el manager conte el item");
        System.out.print("\t 4- getItems : retrona els items que conte el manager");
        System.out.print("\t 5- end : acaba amb el driver");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if(action.equals("initiate")){
                manager = new ItemManager();
                System.out.println("Manager iniciat");
            }
            else if(action.equals("createItem")){
                Integer item_id = sc.nextInt();
                if(manager != null){
                    //if(!manager.existItem(item_id)) manager.createItem(item_id, getAtributes());
                    //else System.out.println("Ja existeix aquestv item al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if(action.equals("existItem")){
                Integer item_id = sc.nextInt();
                if (manager!= null) System.out.println(manager.existItem(item_id));
                else System.out.println("Manager no iniciat");
            }
            else if(action.equals("getItems")){
                if(manager!=null){
                    System.out.println(manager.getItems());
                }
                else System.out.println("Manager no iniciat");
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