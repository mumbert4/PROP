package user;

import review.Review;

import java.util.*;

public class driverActiveUser {
    public static void main(String args[]){
        activeUser usr = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- createUser <String-user_name> <String-password>: crear un usuari nou");
        System.out.println("\t 2- updateName  <String- new user_name>: modificar el nom d'usuari actual");
        System.out.println("\t 3- updatePasswd  <String- new password>: modificar el password actual");
        System.out.println("\t 4- getName : obtenir el nom actual");
        System.out.println("\t 5- getPasswd : obtenir el password actual");
        System.out.println("\t 6- addReview <Integer- item_id> <Integer- points> <String- comment> : afegir una review nova");
        System.out.println("\t 7- numReviews : obtenir el nombre de reviews realitzades");
        System.out.println("\t 8- setPoints <Integer-item_id> <Integer- new points> : canviar la puntuacio d'una review feta");
        System.out.println("\t 9- setComment <Integer-item_id> <String- new comment> : canviar el comentari d'una review feta");
        System.out.println("\t 10- raitingAverage : mitjana de puntuacions realitzades");
        System.out.println("\t 11- getReviws : retorna els items que el user ha valorat amb la seva puntuacio");
        System.out.println("\t 11- hasValuated <Integer-item_id>: Diu si l'usuari ha valorat el item donat");
        System.out.println("\t 12- end : Acabar amb el driver");
        String action;
        action = sc.next();
        while(!action.equals("end")){

            if(action.equals("createUser")){
                String user_name = sc.next();
                String passwd = sc.next();
                usr = new activeUser(user_name, passwd);
                System.out.println("Usuari creat");
            }
            else if(action.equals("updateName")){
                String user_name = sc.next();
                if(usr != null){
                    usr.updateName(user_name);
                    System.out.println("Nom usuari actualitzat");
                }
                else System.out.println("No hi ha usuari creat");
            }

            else if(action.equals("updatePasswd")){
                String passwd = sc.next();
                if(usr != null){
                    usr.updatePasswd(passwd);
                    System.out.println("Password actualitzat");
                }
                else System.out.println("No hi ha usuari creat");
            }

            else if(action.equals("getName")){
                if(usr != null) System.out.println("Nom usuari: " + usr.getName());
                else System.out.println("No hi ha usuari creat");
            }

            else if(action.equals("getPasswd")){
                if(usr != null) System.out.println("Password usuari: " + usr.getPassword());
                else System.out.println("No hi ha usuari creat");
            }
            else if(action.equals("addReview")){
                Integer item_id, points;
                String comment;
                item_id = sc.nextInt();
                points = sc.nextInt();
                comment = sc.next();
                if(usr != null){
                    if(usr.hasValuated(item_id)) System.out.println("L'usuari ja ha valorat aquest item, utilitzeu les funcions de settejar atributs d'una review");
                    else{
                        Review r = new Review(points, comment);
                        usr.addReview(item_id, r);
                        System.out.println("Review afegida");
                    }
                }
                else System.out.println("No hi ha usuari creat");
            }
            else if(action.equals("numReviews")){
                if(usr != null){
                    System.out.println("Reviews usuari: " + usr.numReviews());
                }
                else System.out.println("No hi ha usuari creat");
            }
            else if(action.equals("setPoints")){
                Integer item_id, points;
                item_id = sc.nextInt();
                points = sc.nextInt();
                if(usr != null){
                    if(usr.hasValuated(item_id)){
                        usr.setPoints(item_id, points);
                        System.out.println("Puntuacio actualitzada");
                    }
                    else System.out.println("L'usuari no ha valorat aquest item");
                }
                else System.out.println("No hi ha usuari creat");
            }
            else if(action.equals("setComment")){
                Integer item_id = sc.nextInt();
                String comment= sc.next();

                if(usr != null){
                    if(usr.hasValuated(item_id)){
                        usr.setComment(item_id,comment);
                        System.out.println("Comentari actualitzada");
                    }
                    else System.out.println("L'usuari no ha valorat aquest item");
                }
                else System.out.println("No hi ha usuari creat");
            }

            else if(action.equals("raitingAverage")){
                if(usr != null) System.out.println("Mitjana puntuacions: " + usr.raiAve());
                else System.out.println("No hi ha usuari creat");
            }

            else if(action.equals("getReviews")){
                if(usr != null){
                    Map<Integer,Double> revs = usr.getReviewsUsers();
                    for(Map.Entry<Integer,Double> e : revs.entrySet() ){
                        System.out.println("Item:" + e.getKey() + " Puntuacio:"+e.getValue());
                    }
                }
                else System.out.println("No hi ha usuari creat");
            }
            else if(action.equals("hasValuated")){
                Integer item_id = sc.nextInt();
                if(usr != null) System.out.println(usr.hasValuated(item_id));
                else System.out.println("No hi ha usuari creat");
            }
            action = sc.next();

        }

    }
}
