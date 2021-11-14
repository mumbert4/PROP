package data;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marta Granero I Martí
 */


public class CtrlDades {

    /* Inicialitzem controlador de dades */
    private static CtrlItemsFitxer CIF = CtrlItemsFitxer.getInstance();
    private static CtrlRatingsFitxer CRF = CtrlRatingsFitxer.getInstance();
    private static CtrlUsersFitxer CUF = CtrlUsersFitxer.getInstance();

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
        
        List<String> ids = new LinkedList<String>();
        for(int i =0; i < rai.size(); ++i){
            ids.add(getUID(rai.get(i)));
        }
        System.out.println(ids.get(1));
        System.out.println(ids);


    }

    public void escriureUsuaris() throws  FileNotFoundException{
        System.out.println(CUF.getAll("users.csv"));
    }

    public String getUID(String s){
        String id="";
        Boolean primer = false;
        for(int i =0; i < s.length() ; ++i ){
            if(primer && s.charAt(i) == ',') i = s.length();
            else if(primer) id+= s.charAt(i);
            else if(s.charAt(i) == ',') primer = true;
        }
        return id;
    }
}
