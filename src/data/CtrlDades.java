package data;

import user.userManager;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import user.userManager;

/**
 * @author Marta Granero I Martí
 */


public class CtrlDades {

    /* Inicialitzem controlador de dades */
    private static CtrlItemsFitxer CIF = CtrlItemsFitxer.getInstance();
    private static CtrlRatingsFitxer CRF = CtrlRatingsFitxer.getInstance();
    //private static CtrlUsersFitxer CUF = CtrlUsersFitxer.getInstance();

    private static CtrlDades singletonO;

    public static CtrlDades getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDades() {};
        } return singletonO;
    }

    public void escriureItems() throws FileNotFoundException {
        System.out.println(CIF.getAll("items.csv"));
    }
    public void escriureRatings() throws FileNotFoundException {

//        System.out.println(CRF.getAll("ratings.db.csv")); MARTA
        List<String> rai = CRF.getAll("ratings.db.csv");

        List<String> User_ids = new LinkedList<String>();
        List<String> Item_ids = new LinkedList<String>();
//        for(int i =0; i < rai.size(); ++i){
//            ids.add(getUID(rai.get(i)));
//        }
//        System.out.println(ids.get(1));
//        System.out.println(ids);


    }

//    public void escriureUsuaris() throws  FileNotFoundException{
//        System.out.println(CUF.getAll("users.csv"));
//    }

    public List<String> getItems() throws FileNotFoundException {
        return CIF.getAll("items.csv");
    }

<<<<<<< HEAD

=======



>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
    public void obtenir_dades(userManager manager) throws FileNotFoundException {
        List<String> rai = CRF.getAll("ratings.test.known.csv");
        String aux = "";
        int col_us=0;
        int col_it=0;
        int col_rai=0;
        int col_act=0;
        for(int i = 0; i < rai.get(0).length(); ++i){
            if(rai.get(0).charAt(i)==',' || i == rai.get(0).length()-1){

                if(aux.equals("userId")) col_us=col_act;
                else if (aux.equals("itemId")) col_it = col_act;
                else col_rai = col_act;
                aux = "";
                ++col_act;
            }
            else aux += rai.get(0).charAt(i);
        }
//        System.out.println("Columna usuari: " + col_us + ", columna item: " + col_it + ", columna raitings: "+ col_rai);
        String user="";
        int item=0;
        double raiting=0;
        for(int i = 1; i < rai.size(); ++i){
            aux ="";
            col_act = 0;
            for(int j = 0; j < rai.get(i).length(); ++j){
                if(rai.get(i).charAt(j)== ',' || j == rai.get(i).length()-1){
<<<<<<< HEAD
                    if(col_act == col_us) user = aux;
                    else if(col_act == col_it) item = Integer.valueOf(aux);
                    else raiting = Double.valueOf(aux);
                    ++col_act;
                    aux ="";
                }
                else aux += rai.get(i).charAt(j);
            }
=======
                  if(col_act == col_us) user = aux;
                  else if(col_act == col_it) item = Integer.valueOf(aux);
                  else raiting = Double.valueOf(aux);
                  ++col_act;
                  aux ="";
                }
                else aux += rai.get(i).charAt(j);
            }

>>>>>>> 8e6bb2a2937e57d4461b4ca570f5dea4ee4fe835
            if(!(manager.existUser(user))) manager.createUser(user,"","");
            manager.createReview(user,item,raiting,"");
        }
    }


}
