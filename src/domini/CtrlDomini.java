package domini;

import data.CtrlDades;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Marta Granero I Martí
 */


//Fa queries al CtrlDades i carrega les dades que es necessiten
public class CtrlDomini {

    private static CtrlDomini singletonO;

    public static CtrlDomini getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDomini() {};
        } return singletonO;
    }

    public CtrlDomini() {}

    public void carregarDadesItems() throws IOException {
       //
    }

    public void carregarDadesRatings(CtrlDades cd) throws FileNotFoundException {
        //
    }
}
