package algorithms;

import java.util.List;
import java.util.Map;

/**
 * Classe que implementa el metode de l'avaluador dels algorismes de Collaborative Filtering i Content Based
 */

public class Avaluator {

    /**
     * Constructora de la classe
     */
    public Avaluator(){}



    /**
     * Calcul del Discounted Cumulative Gain
     * @param l LLista dels items recomanats per un dels dos algorismes
     * @param m Mapa de l'identificador dels items i la puntuacio que l'usuari dona segons el fitxer raiting.test.unknown.csv
     * @return Fiabilitat de la prediccio del resultat dels nostres algorismes
     * Complexitat O( l.size * m.size )
     */
    public double DCG(List<Integer> l, Map<Integer,Double> m){
        double dcg = 0;
        for(int i = 0 ; i < l.size(); ++i){
            Integer item = l.get(i);
            Double rel;
            if(!m.containsKey(item)) rel = 0.0;
            else rel = m.get(item);
            dcg += (Math.pow(2.0, rel) - 1.0) / (Math.log((i+1) + 1) / Math.log(2));
        }
        return dcg;
    }



    /**
     * Evalua la fiabilitat de la prediccio dels nostres algorismes
     * @param col Solucio de l'algorisme Collaborative Filtering
     * @param cb Solucio de l'algorisme Content Based
     * @param unknown Mapa de l'identificador dels items i la puntuacio que l'usuari dona segons el fitxer raiting.test.unknown.csv
     * Complexitat ( col.size * unknown.size + cb.size * unknown.size )
     */
    public void evaluate(List<Integer> col, List<Integer> cb, Map<Integer,Double> unknown){
        System.out.println ("Collaborative: "+ DCG(col,unknown));
        System.out.println ("Content Based: "+ DCG(cb,unknown));
    }
}
