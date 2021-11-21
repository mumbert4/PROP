package item;

import java.util.Scanner;

public class driverItem {

    public void main(String[] args){
        Item item = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- createItem <Integer- item_id> <ArrayList<Column>-atributs> : crear un nou item");
        System.out.println("\t 2- getId : obtenir el id de l'item");
        System.out.println("\t 3- sizeCol : obtenir quantitat d'atributs");
        System.out.println("\t 4- getCol <Integer- num_atribut> : obtenir un atribut en concret");
        String action;
        action = sc.next();
        while(!action.equals("end")){
//            if(action.equals("createItem")){
//                Integer item_id = sc.nextInt();
//                item = new Item(item_id,...); // COM CREAR UN VECTOR D'ATRIBUTS?
//            }
            if(action.equals("getId")){
                if(item!= null) System.out.println("Id del item: " + item.getId());
                else  System.out.println("Item no iniciat");
            }
            else if(action.equals("sizeCol")){
                if(item!= null) System.out.println("Num atributs: " + item.getSizeAttributes());
                else  System.out.println("Item no iniciat");
            }
            else if(action.equals("getCol")){
                Integer i = sc.nextInt();
                if(item!= null) System.out.println("Atribut: " + item.getColumn(i));
                else  System.out.println("Item no iniciat");
            }
            action = sc.next();
        }
    }

}
