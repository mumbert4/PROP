package domain;

import data.CtrlData;
import user.UserManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;


//Fa queries al CtrlDades i carrega les dades que es necessiten
public class CtrlDomain {
    private static CtrlData CData;
    private static CtrlDomain singletonO;

    // complexitat O (1)
    private CtrlDomain() {
        initializeCtrlDomain();
    }

    /* Inicialitzem el controlador del domini*/
    // complexitat O (1)
    private void initializeCtrlDomain() {
        CData = CtrlData.getInstance();
    }

    // complexitat O (1)
    public static CtrlDomain getInstance(){
        if(singletonO == null) {
            singletonO = new CtrlDomain() {};
        } return singletonO;
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public void obtainData(UserManager manager) throws FileNotFoundException {
        CData.obtainData(manager);
    }

    // complexitat O (items.csv.size -> mida fitxer)
    public List<String> getItems() throws IOException {
        return CData.getItems();
    }

    public List<String> getPonderacions() throws  IOException{
        return CData.getPonderacions();
    }

    // complexitat O ( rai.size * max(rai.get(i).size) )
    public Map<Integer,Double> getRatings(String userId) throws IOException {
        return CData.getUnknown(userId);
    }
}
