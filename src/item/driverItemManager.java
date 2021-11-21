package item;

import java.util.Scanner;

public class driverItemManager {
    public void main(String args[]){
        ItemManager manager = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.print("\t 1- initiate: iniciam el item manager");
        System.out.print("\t 2- createItem <Integer- item_id> <ArrayList<Column> - atributs>: cream un item amb els seus atributs");
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
                //LES DADES DE COLUMNA
//                if(manager != null){
//                    if(!manager.existItem(item_id)) manager.createItem(item_id, ....);
//                    else System.out.println("Ja existeix aquestv item al manager");
//                }
//                else System.out.println("Manager no iniciat");
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
}
