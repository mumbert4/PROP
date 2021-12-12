package domini;

import data.CtrlDades;
import user.userManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//Fa queries al CtrlDades i carrega les dades que es necessiten
public class CtrlDomini {
    private static CtrlDades CDades;
    private static CtrlDomini singletonO;

    // complexitat O (1)
    public CtrlDomini() {
        inicialitzarCtrlDomini();
    }

    /* Inicialitzem el controlador del domini*/
    // complexitat O (1)
    private void inicialitzarCtrlDomini() {
        CDades = CtrlDades.getInstance();
    }

    // complexitat O (1)
    public static CtrlDomini getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDomini() {};
        } return singletonO;
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public void obtenirDades(userManager manager) throws FileNotFoundException {
        CDades.obtenirDades(manager);
    }

    // complexitat O (items.csv.size -> mida fitxer)
    public List<String> getItems() throws IOException {
        return CDades.getItems();
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public Map<Integer,Double> getRatings(String userId) throws IOException {
        return CDades.getUnknown(userId);
    }
}
