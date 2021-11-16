package main;

<<<<<<< HEAD
import data.CtrlDades;
package main;

=======


>>>>>>> a4901dc5d357eec7f5d458fe3dba63367e751240
import data.CtrlDades;
import java.util.LinkedList;
import java.util.List;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Objects;
import userManager.userManager;


public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println();
        userManager manager = userManager.getInstance();
        CtrlDades CD = CtrlDades.getInstance();
//        CD.escriureItems();
//        CD.escriureRatings();
        List<String> user_ids= new LinkedList<String>();
        List<Integer> item_ids= new LinkedList<Integer>();
        List<Double> raitings = new LinkedList<Double>();

        CD.obtenir_dades(user_ids, item_ids, raitings);

        System.out.println("Dades obtingudes, rellenam el USER MANAGER");

        for(int i = 0; i < user_ids.size(); ++i){
            String user_name = user_ids.get(i);
            int item_id = item_ids.get(i);
            Double raiting = raitings.get(i);
            if(!(manager.existUser(user_ids.get(i)))) manager.createUser(user_ids.get(i), "",""); //possam les passwords en blanc de moment
            manager.createReview(user_name,item_id,raiting,"");//possam comentari en blanc de moment
        }

        System.out.println("USER MANAGER rellenat");

        System.out.println(manager.raiAve("1625"));








        /* Crea el fitxer users.csv on hi podrem afegir els nous usuaris que es registrin a a la nostra app */
        /* Aquí ha de crear-se la funció de CrearPerfil(signUp), CarregarPerfil(logIn), EsborrarPerfil i
           ModificarPerfil */

        /* DUES FORMES DIFERENTS D'INSERIR INFO EN UN CSV*/
        //1
        FileOutputStream fileOut = new FileOutputStream("DATA/users.csv");
        //Instantiating the DataOutputStream class
        DataOutputStream outputStream = new DataOutputStream(fileOut);
        /* Writing UTF data to the output stream */
        outputStream.writeChars("marta");
        outputStream.flush();

        //2-aquesta millor diria
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("DATA/users2.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String builder = "marta";
        Objects.requireNonNull(pw).write(builder);
        pw.close();

    }
}
