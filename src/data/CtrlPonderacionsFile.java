package data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class CtrlPonderacionsFile {

    private static CtrlPonderacionsFile singletonO;


    public static CtrlPonderacionsFile getInstance(){
        if(singletonO == null){
            singletonO = new CtrlPonderacionsFile();
        }
        return singletonO;
    }

    private CtrlPonderacionsFile(){}

    public List<String> getAll(String filename) throws FileNotFoundException{
        LinkedList<String> ponderacions = new LinkedList<>();
        FileReader fr = new FileReader("PLANTILLES/"+filename);
        Scanner scan = new Scanner(fr);
        while (scan.hasNextLine()){
            ponderacions.add(scan.nextLine() + "\n");
        }
        return ponderacions;
    }





}
