package user;
import java.util.*;
public class driverUserManager {
    public static void main(String[] args){
      userManager manager = null;
      Scanner sc = new Scanner(System.in);
      System.out.println("Instruccions:");
        System.out.println("\t 1- initiate : iniciam el manager");
        System.out.println("\t 2- createUser  <String- user_name> <String- password> <String- confirm password>: crea un nou usuari, que no estigui al manager ja");
        System.out.println("\t 3- existUser  <String- user_name>: diu si el user existeix en el manager");
        System.out.println("\t 4- getUser <String- user_name> : obtenir la instancia d'activeUser");
        System.out.println("\t 5- numReviews <String- user_name> : retorna el numero de reviews fetes per l'usuari");
        System.out.println("\t 6- createReview <String- user_name> <Integer- item_id> <Integer- points> <String- comment> : afegir una review nova");
        System.out.println("\t 7- numUsuaris : retorna el nombre d'usuaris que hi ha");
        System.out.println("\t 8- getUsuaris : retorna la llista de noms dels usuaris que hi ha");
        System.out.println("\t 9- raitingAve <String- user_id> : retorna la puntuacio mitjana del usuari");
        System.out.println("\t 10- getReviews <String- user_id> : retrona els items i la puntuacio que ha donat el user");
        System.out.println("\t 11- getRaiting <String- user_id> <Integer- item_id> : retrona la puntuacio que el user ha donat a l'item");
        System.out.println("\t 11- hasValuated <String- user_id> <Integer-item_id>: Diu si l'usuari ha valorat el item donat");
        System.out.println("\t 12- end : Acabar amb el driver");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if (action.equals("initiate")){
                manager = userManager.getInstance();
                System.out.println("Manager iniciat");
            }
            else if (action.equals("createUser")){
                String user_name,passwd, conf_passwd;
                user_name = sc.next();
                passwd = sc.next();;
                conf_passwd = sc.next();
                if(manager != null){
                    if (!manager.existUser(user_name)){
                        manager.createUser(user_name,passwd,conf_passwd);
                        if (manager.existUser(user_name))System.out.println("User creat");
                    }
                    else System.out.println("L'usuari ja existeix");
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("existUser")){
                String user_name;
                user_name = sc.next();
                if(manager != null) System.out.println(manager.existUser(user_name));
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("getUser")){
                String user_name = sc.next();
                if(manager != null){
                  if (manager.existUser(user_name)){
                      System.out.println("Name: "+ manager.getUser(user_name).getName() + " ,password: "+ manager.getUser(user_name).getPassword());
                  }
                  else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("numReviews")){
                String user_name = sc.next();
                if(manager != null){
                  if (manager.existUser(user_name)){
                      System.out.print("Numero de reviews: " + manager.numReviews(user_name));
                  }
                  else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("createReview")){
                String user_name = sc.next();
                Integer item_id = sc.nextInt();
                Double points = sc.nextDouble();
                String comment = sc.next();
                if(manager != null){
                  if (manager.existUser(user_name)){
                      manager.createReview(user_name, item_id, points,comment);
                  }
                  else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("numUsuaris")){

                if(manager != null){
                 System.out.println("Total usuaris: " + manager.numUsu());
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("getUsuaris")){

                if(manager != null){
                 System.out.println("Usuaris: " + manager.getUsuaris());
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("raitingAve")){
                String user_name = sc.next();
                if(manager != null){
                    if (manager.existUser(user_name)){
                        System.out.print("Mitjana de raiting: " + manager.raiAve(user_name));
                    }
                    else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("getReviews")){
                String user_name = sc.next();
                if(manager != null){
                    if (manager.existUser(user_name)){
                        Map<Integer,Double> revs= manager.getReviewsUsers(user_name);
                        for (Map.Entry<Integer,Double> r : revs.entrySet()){
                            System.out.println("Item:" + r.getKey() + ", valoracio:" + r.getValue());
                        }
                    }
                    else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }

            else if (action.equals("getRaiting")){
                String user_name = sc.next();
                Integer item_id = sc.nextInt();
                if(manager != null){
                    if (manager.existUser(user_name)){
                        if(manager.getUser(user_name).hasValuated(item_id)) System.out.println(manager.getRaiting(user_name,item_id));
                        else System.out.println("L'usuari no ha valorat aquest item");
                    }
                    else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }
            else if (action.equals("hasValuated")){
                String user_name = sc.next();
                Integer item_id = sc.nextInt();
                if(manager != null){
                    if (manager.existUser(user_name)){
                        System.out.println(manager.getUser(user_name).hasValuated(item_id));
                    }
                    else System.out.println("L'usuari no esta al manager");
                }
                else System.out.println("Manager no iniciat");
            }

            action= sc.next();
        }




    }
}
