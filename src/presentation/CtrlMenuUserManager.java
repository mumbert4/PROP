package presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMenuUserManager implements ActionListener{

    CtrlPresentation cp;
    JButton getUser;
    JList<String> itemManagement;
    JButton signOut;

    public CtrlMenuUserManager (JPanel menuUserManager) {

        cp = CtrlPresentation.getInstance();
        menuUserManager.setLayout(null);

        String[] options = new String[3];
        options[0] = "Create Item";
        options[1] = "Modify Item";
        options[2] = "Delete Item";

        itemManagement= new JList(options);
        getUser = new JButton("Get User");
        signOut = new JButton("Sign Out");

        itemManagement.setVisibleRowCount(1);
        JScrollPane jcp = new JScrollPane(itemManagement);

        getUser.setBounds(30, 50, 190, 25);
        jcp.setBounds(250, 50, 125, 23);
        signOut.setBounds(290, 180, 100, 25);

        getUser.addActionListener(this);
        signOut.addActionListener(this);

        menuUserManager.add(getUser);
        menuUserManager.add(jcp);
        menuUserManager.add(signOut);

        itemManagement.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String selected = itemManagement.getSelectedValue();
                if (selected.equals("Create Item")) {
                    cp.setPanel(9);
                }
                else if (selected.equals("Modify Item")) {
                    cp.setPanel(10);
                }

                else if (selected.equals("Delete Item")){
                    cp.setPanel(11);
                }
            }

        });

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == getUser) {
            cp.setPanel(12);
        }

        else if (actionEvent.getSource() == signOut) {
            cp.setPanel(2);
        }
    }
}

