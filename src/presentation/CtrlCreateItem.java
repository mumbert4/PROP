package presentation;

import estructures.Pair;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CtrlCreateItem extends CtrlItemManagement implements ActionListener {

    int cont = 0;
    CtrlPresentation cp;
    JLabel columnTitle;
    JTextField columnTitleText;
    JButton acceptColumn;
    ArrayList<Pair<String, String>> conjTitleType = new ArrayList<>();
    ArrayList<Pair<String, String>> values = new ArrayList<>();

    public CtrlCreateItem(JPanel createItem){
        cp = CtrlPresentation.getInstance();
        createItem.setLayout(null);

        idItem = new JLabel("Identificador Item:");
        idItemText = new JTextField();
        readItem = new JButton("Accept Id");
        columnTitle = new JLabel("Títol Atribut:");
        columnTitleText = new JTextField("Valor Atribut");
        acceptColumn = new JButton(("Accept Column"));
        error = new JLabel();
        exit = new JButton("Back");

        idItem.setBounds(10, 30, 150, 25);
        idItemText.setBounds(160, 30, 150, 25);
        readItem.setBounds(160, 60, 130, 25);
        columnTitle.setBounds(10, 90, 150, 25);
        columnTitleText.setBounds(160, 90, 150, 25);
        acceptColumn.setBounds(160, 120, 130, 25);
        error.setBounds(80, 150, 300, 25);
        exit.setBounds(290, 180, 100, 25);

        readItem.addActionListener(this);
        acceptColumn.addActionListener(this);
        exit.addActionListener(this);

        createItem.add(idItem);
        createItem.add(idItemText);
        createItem.add(readItem);
        createItem.add(columnTitle);
        createItem.add(columnTitleText);
        createItem.add(acceptColumn);
        createItem.add(error);
        createItem.add(exit);
    }


    public boolean isInt(String input) {
        try{
            int inputInt = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    public boolean isDbl(String input) {
        try{
            double inputDbl = Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    public boolean isB(String input) {
        return input.equals("True") || input.equals("False") || input.equals("true") || input.equals("false") || input.equals("TRUE") || input.equals("FALSE");
    }


    @Override
    public void actionPerformed (ActionEvent actionEvent) {
        if(actionEvent.getSource() == readItem){
            error.setText("");
            cont = 0;
            values.clear();

            String item = idItemText.getText();
            if (isInt(item)){

                int itemId = Integer.parseInt(idItemText.getText());

                if (!cp.existItem(itemId)){
                    conjTitleType = cp.getTitleTypeColumns();
                    Pair<String, String> initialPair = conjTitleType.get(0);

                    columnTitle.setText(initialPair.getFirst());
                    columnTitleText.setText(initialPair.getSecond());
                }

                else {
                    idItemText.setText("");
                    error.setText("<html><font color='red'>This item already exists</font></html>");
                }
            }

            else {
                idItemText.setText("");
                error.setText("<html><font color='red'>Invalid item id, must type an integer</font></html>");
            }
        }

        else if(actionEvent.getSource() == acceptColumn){

            error.setText("");

            if (cont == 0) values.clear();;

            if (!conjTitleType.isEmpty()) {
                Pair<String, String> actPair = conjTitleType.get(cont);
                String type = actPair.getSecond();

                String col = columnTitleText.getText();

                if (type.equals("Integer")) {

                    if (isInt(col)) {

                        Pair<String,String> actValues = new Pair<>(actPair.getFirst(),col);
                        values.add(actValues);

                        if(cont != conjTitleType.size() - 1) {
                            ++cont;
                            Pair<String, String> nextPair = conjTitleType.get(cont);
                            columnTitle.setText(nextPair.getFirst());
                            columnTitleText.setText(nextPair.getSecond());
                        }

                        else {

                            int itemId = Integer.parseInt(idItemText.getText());

                            cp.createItem(itemId,values);

                            cont = 0;
                            values.clear();
                            idItemText.setText("");
                            columnTitle.setText("Títol Atribut");
                            columnTitleText.setText("Valor Atribut");
                            error.setText("<html><font color='green'>Item created Succesfully</font></html>");

                        }
                    }

                    else {
                        columnTitleText.setText(actPair.getSecond());
                        error.setText("<html><font color='red'>Type of the attribute is not correct</font></html>");
                    }

                } else if (type.equals("Double")) {

                    if (isDbl(col)) {

                        Pair<String,String> actValues = new Pair<>(actPair.getFirst(),col);
                        values.add(actValues);

                        if(cont != conjTitleType.size() - 1) {
                            ++cont;
                            Pair<String, String> nextPair = conjTitleType.get(cont);
                            columnTitle.setText(nextPair.getFirst());
                            columnTitleText.setText(nextPair.getSecond());
                        }

                        else {

                            int itemId = Integer.parseInt(idItemText.getText());

                            cp.createItem(itemId,values);

                            cont = 0;
                            values.clear();
                            idItemText.setText("");
                            columnTitle.setText("Títol Atribut");
                            columnTitleText.setText("Valor Atribut");
                            error.setText("<html><font color='green'>Item created succesfully</font></html>");

                        }
                    }

                    else {
                        columnTitleText.setText(actPair.getSecond());
                        error.setText("<html><font color='red'>Type of the attribute is not correct</font></html>");
                    }

                } else if (type.equals("Boolean")) {

                    if (isB(col)) {

                        Pair<String,String> actValues = new Pair<>(actPair.getFirst(),col);
                        values.add(actValues);

                        if(cont != conjTitleType.size() - 1) {
                            ++cont;
                            Pair<String, String> nextPair = conjTitleType.get(cont);
                            columnTitle.setText(nextPair.getFirst());
                            columnTitleText.setText(nextPair.getSecond());
                        }

                        else {

                            int itemId = Integer.parseInt(idItemText.getText());

                            cp.createItem(itemId,values);

                            cont = 0;
                            values.clear();
                            idItemText.setText("");
                            columnTitle.setText("Títol Atribut");
                            columnTitleText.setText("Valor Atribut");
                            error.setText("<html><font color='green'>Item created succesfully</font></html>");

                        }
                    }

                    else {
                        columnTitleText.setText(actPair.getSecond());
                        error.setText("<html><font color='red'>Type of the attribute is not correct</font></html>");
                    }

                } else if (type.equals("String")) {

                    Pair<String,String> actValues = new Pair<>(actPair.getFirst(),col);
                    values.add(actValues);

                    if(cont != conjTitleType.size() - 1) {
                        ++cont;
                        Pair<String, String> nextPair = conjTitleType.get(cont);
                        columnTitle.setText(nextPair.getFirst());
                        columnTitleText.setText(nextPair.getSecond());
                    }

                    else {

                        int itemId = Integer.parseInt(idItemText.getText());

                        cp.createItem(itemId,values);

                        cont = 0;
                        values.clear();
                        idItemText.setText("");
                        columnTitle.setText("Títol Atribut");
                        columnTitleText.setText("Valor Atribut");
                        error.setText("<html><font color='green'>Item created succesfully</font></html>");

                    }
                }
            }

            else {
                error.setText("<html><font color='red'>Non item selected</font></html>");
            }
        }

        else if (actionEvent.getSource() == exit){

            cont = 0;
            values.clear();
            idItemText.setText("");
            columnTitle.setText("Títol Atribut");
            columnTitleText.setText("Valor Atribut");
            error.setText("");
            cp.setPanel(4);
        }
    }
}
