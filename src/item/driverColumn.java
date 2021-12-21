package item;

import java.util.Scanner;

public class driverColumn {
    public static void main(String[] args){
        ColumnString cs = null;
        ColumnInteger ci = null;
        ColumnDouble cd = null;
        ColumnBool cb = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 1- createColumnString: crear una columna d'string");
        System.out.println("\t 2- getValueString: obtenir valor string");
        System.out.println("\t 3- createColumnInteger: crear una columna d'integer");
        System.out.println("\t 4- getValueInteger: obtenir valor integer");
        System.out.println("\t 5- createColumnDouble: crear una columna double");
        System.out.println("\t 6- getValueDouble: obtenir valor double");
        System.out.println("\t 7- createColumnBool: crear una columna de boolean");
        System.out.println("\t 8- getValueBool: obtenir valor bool");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if (action.equals("createColumnString")) {
                System.out.print("Introdueix un valor string per la columna : ");
                String sf = sc.next();
                cs = new ColumnString(sf);
                if (cs != null) System.out.println("S'ha creat correcatment la columna d'un string");
                else System.out.println("No iniciada la columna d'un string");
            }
            if (action.equals("getValueString")) {
                if (cs != null) {
                    if (cs.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un string i és " + cs.getValue());
                    else System.out.println("No s'ha obtingut correctament el valor de la columna d'un string");
                }
                else System.out.println("No iniciada la columna d'un string");
            }
            if (action.equals("createColumnInteger")) {
                System.out.print("Introdueix un valor integer per la columna : ");
                String p = sc.next();
                int i = Integer.parseInt(p);
                ci = new ColumnInteger(i);
                if (ci != null) {
                    System.out.println("S'ha creat correcatment la columna d'un integer");
                }
                else System.out.println("No iniciada la columna d'un integer");
            }
            if (action.equals("getValueInteger")) {
                if (ci != null) {
                    if (ci.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un integer i és " + ci.getValue());
                    else System.out.println("No s'ha obtingut correctament el valor de la columna d'un integer");
                }
                else System.out.println("No iniciada la columna d'un integer");
            }
            if (action.equals("createColumnDouble")) {
                System.out.print("Introdueix un valor double per la columna : ");
                String p = sc.next();
                Double d = Double.parseDouble(p);
                cd = new ColumnDouble(d);
                if (cd != null) System.out.println("S'ha creat correcatment la columna d'un double");
                else System.out.println("No iniciada la columna d'un double");
            }
        }
        if (action.equals("getValueDouble")) {
            if (cd != null) {
                if (cd.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un double i és " + cd.getValue());
                else System.out.println("No s'ha obtingut correctament el valor de la columna d'un double");
            }
            else System.out.println("No iniciada la columna d'un double");
        }
        if (action.equals("createColumnBool")) {
            System.out.print("Introdueix un valor integer per la columna : ");
            String p = sc.next();
            boolean b = Boolean.parseBoolean(p);
            cb = new ColumnBool(b);
            if (cb != null) {
                System.out.println("S'ha creat correcatment la columna d'un bool");
            } else System.out.println("No iniciada la columna d'un bool");
        }
        if (action.equals("getValueBool")) {
            if (cb != null) {
                if (cb.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un bool i és " + cb.getValue());
                else System.out.println("No s'ha obtingut correctament el valor de la columna d'un bool");
            }
            else System.out.println("No iniciada la columna d'un bool");
        }
        action = sc.next();
    }
}