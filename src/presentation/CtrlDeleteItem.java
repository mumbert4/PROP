package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CtrlDeleteItem extends CtrlItemManagement implements ActionListener{

    CtrlPresentation cp;



    public CtrlDeleteItem(JPanel deleteItem){
        cp = CtrlPresentation.getInstance();
        deleteItem.setLayout(null);

        idItem = new JLabel("Identificador Item:");
        idItemText = new JTextField();
        readItem = new JButton("Apply");
        error = new JLabel();
        exit = new JButton("Back");

        idItem.setBounds(10, 30, 150, 25);
        idItemText.setBounds(160, 30, 150, 25);
        readItem.setBounds(160, 90, 100, 25);
        error.setBounds(80, 120, 300, 25);
        exit.setBounds(290, 180, 100, 25);

        readItem.addActionListener(this);
        exit.addActionListener(this);

        deleteItem.add(idItem);
        deleteItem.add(idItemText);
        deleteItem.add(readItem);
        deleteItem.add(error);
        deleteItem.add(exit);
    }

    @Override
    public void actionPerformed (ActionEvent actionEvent) {
        if(actionEvent.getSource() == readItem){
            String item = idItemText.getText();
            if (isInt(item)){

                int itemId = Integer.parseInt(idItemText.getText());

                if (cp.existItem(itemId)){
                    cp.deleteItem(itemId);
                    try {
                        cp.updateMapDistances();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    idItemText.setText("");
                    error.setText("<html><font color='green'>Item deleted successful</font></html>");
                }

                else {
                    idItemText.setText("");
                    error.setText("<html><font color='red'>This item does not exist</font></html>");
                }
            }

            else {
                idItemText.setText("");
                error.setText("<html><font color='red'>Invalid item id, must type an integer.</font></html>");
            }
        }

        else if (actionEvent.getSource() == exit){
            idItemText.setText("");
            error.setText("");
            cp.setPanel(4);
        }

    }
}
