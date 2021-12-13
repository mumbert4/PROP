package item;

import java.util.Scanner;

public class driverColumn {
    public static void main(String[] args){
        Column c = null;
        Column.ColumnString cs = null;
        Column.ColumnInteger ci = null;
        Column.ColumnDouble cd = null;
        Column.ColumnBool cb = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Instruccions:");
        System.out.println("\t 2- createColumn: crear una columna");
        System.out.println("\t 2- createColumnString: crear una columna d'string");
        System.out.println("\t 3- getValueString: crear una columna d'string");
        System.out.println("\t 4- createColumnInteger: crear una columna d'string");
        System.out.println("\t 5- getValueInteger: crear una columna d'string");
        System.out.println("\t 6- createColumnDouble: crear una columna d'string");
        System.out.println("\t 7- getValueDouble: crear una columna d'string");
        System.out.println("\t 8- createColumnBool: crear una columna d'string");
        System.out.println("\t 9- getValueBool: crear una columna d'string");
        String action;
        action = sc.next();
        while(!action.equals("end")){
            if (action.equals("createColumn")) {
                c = new Column();
                if (c != null) System.out.println("S'ha creat correctament la columna");
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("createColumnString")) {
                if (c != null) {
                    System.out.print("Introdueix un valor string per la columna : ");
                    String s = sc.next();
                    cs = new Column.ColumnString(s);
                    if (cs != null) {
                        System.out.println("S'ha creat correcatment la columna d'un string");
                    }
                    else System.out.println("No iniciada la columna d'un string");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("getValueString")) {
                if (c != null) {
                    if (cs != null) {
                        if (cs.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un string i és " + cs.getValue());
                        else System.out.println("No s'ha obtingut correctament el valor de la columna d'un string");
                    }
                    else System.out.println("No iniciada la columna d'un string");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("createColumnInteger")) {
                if (c != null) {
                    System.out.print("Introdueix un valor integer per la columna : ");
                    int i = sc.nextInt();
                    ci = new Column.ColumnInteger(i);
                    if (ci != null) {
                        System.out.println("S'ha creat correcatment la columna d'un integer");
                    }
                    else System.out.println("No iniciada la columna d'un integer");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("getValueInteger")) {
                if (c != null) {
                    if (ci != null) {
                        if (ci.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un integer i és " + ci.getValue());
                        else System.out.println("No s'ha obtingut correctament el valor de la columna d'un integer");
                    }
                    else System.out.println("No iniciada la columna d'un integer");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("createColumnDouble")) {
                if (c != null) {
                    System.out.print("Introdueix un valor double per la columna : ");
                    Double d = sc.nextDouble();
                    cd = new Column.ColumnDouble(d);
                    if (cd != null) {
                        System.out.println("S'ha creat correcatment la columna d'un double");
                    }
                    else System.out.println("No iniciada la columna d'un double");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("getValueDouble")) {
                if (c != null) {
                    if (cd != null) {
                        if (cd.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un double i és " + cd.getValue());
                        else System.out.println("No s'ha obtingut correctament el valor de la columna d'un double");
                    }
                    else System.out.println("No iniciada la columna d'un double");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("createColumnBool")) {
                if (c != null) {
                    System.out.print("Introdueix un valor integer per la columna : ");
                    boolean b = sc.nextBoolean();
                    cb = new Column.ColumnBool(b);
                    if (cb != null) {
                        System.out.println("S'ha creat correcatment la columna d'un bool");
                    }
                    else System.out.println("No iniciada la columna d'un bool");
                }
                else System.out.println("Columna no iniciada");
            }
            if (action.equals("getValueBool")) {
                if (c != null) {
                    if (cb != null) {
                        if (cb.getValue() != null) System.out.println("S'ha obtingut correctament el valor de la columna d'un bool i és " + cb.getValue());
                        else System.out.println("No s'ha obtingut correctament el valor de la columna d'un bool");
                    }
                    else System.out.println("No iniciada la columna d'un bool");
                }
                else System.out.println("Columna no iniciada");
            }
            action = sc.next();
        }
    }
}
