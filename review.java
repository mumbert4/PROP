import java.util.*;

public class review{
    private int points;
    private String comment;
    //user_manager manager;



    active_user(String name, String passwd){
        username = name;
        password = passwd;
        //manager = mana;
    }



    void update_profile(String new_name, String new_passwd){
        if (new_name != null) username = new_name;
        if (new_passwd != null) password = new_passwd;
    }

    String get_name(){
        return username;
    }

    void add_review() {
        System.out.println("To add a review insert the item id, the score and the comment")
        Scanner sc = new Scanner(System.in); // forma de input
        int item_id = sc.nextInt();
        if( !(manager.exist_item(item_id)) ) System.out.println("This item does not exist");
        else {
            int score = sc.nextInt();
            String comment = sc.nextLine();
            System.out.println("You are reviewing intem: " + item_id + " with a score of: " + score + " points with the comment: " + comment);
            manager.add_review(username, item_id, comment);
            // aqui li passariem tot al user_manager, comprobar si els valors han d'estar dins un domini? (que existesqui item_id, que la puntuacio sigui entre 0 i 10...)
        }

    }


}