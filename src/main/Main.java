package main;

import data.CtrlDades;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println();
        CtrlDades CD = CtrlDades.getInstance();
        CD.escriureItems();
        CD.escriureRatings();
    }
}