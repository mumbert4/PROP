package item;

/**
 * Classe que representa les columnes (característiques) que tenen els items
 */
public class Column {
    /**
     * Subclasse que representa una columna de tipus string
     */
    public static class ColumnString extends Column{
        String valueS;

        /**
         * Constructora de la subclasse
         * @param s Valor que tendra la instància
         * Complexitat O(1)
         */
        public ColumnString(String s){
            valueS = s;
        }



        /**
         * Obtenir el valor de la instància
         * @return Valor del string de la columna
         * Complexitat O(1)
         */
        public String getValue(){
            return valueS;
        }
    }

    public static class ColumnInteger extends Column{
        int valueInt;

        /**
         * Constructora de la subclasse
         * @param i Valor que tendra la instància
         * Complexitat O (1)
         */
        public ColumnInteger(int i){
            valueInt = i;
        }



        /**
         * Obtenir el valor de la instància
         * @return Valor del Int de la columna
         * Complexitat O(1)
         */
        public Integer getValue(){
            return valueInt;
        }
    }

    public static class ColumnDouble extends Column{
        double valueD;

        /**
         * Constructora de la subclasse
         * @param d Valor que tendra la instància
         * Complexitat O (1)
         */
        public ColumnDouble(double d){
            valueD = d;
        }

        /**
         * Obtenir el valor de la instància
         * @return Valor del Double de la columna
         * Complexitat O(1)
         */
        public Double getValue(){
            return valueD;
        }
    }

    public static class ColumnBool extends Column{
        boolean valueB;

        /**
         * Constructora de la subclasse
         * @param b Valor que tendra la instància
         * Complexitat O (1)
         */
        public ColumnBool(boolean b){
            valueB = b;
        }

        /**
         * Obtenir el valor de la instància
         * @return Valor del Boolean de la columna
         * Complexitat O(1)
         */
        public Boolean getValue(){
            return valueB;
        }
    }
}
