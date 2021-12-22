package presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMenuUser  implements ActionListener {

    CtrlPresentation cp;
    JButton getRecommendation;
    JButton addReview;
    JList<String> profile;
    JButton signOut;

    public CtrlMenuUser(JPanel menuUser) {
        cp = CtrlPresentation.getInstance();
        menuUser.setLayout(null);

        String[] options = new String[2];
        options[0] = "Modify Profile";
        options[1] = "Delete Profile";

        getRecommendation = new JButton("Get Recommendation");
        addReview = new JButton("Add Review");
        profile = new JList(options);
        signOut = new JButton("Sign Out");

        profile.setVisibleRowCount(1);
        JScrollPane jcp = new JScrollPane(profile);

        getRecommendation.setBounds(30, 50, 190, 25);
        addReview.setBounds(30, 90, 190, 25);
        jcp.setBounds(250, 50, 125, 23);
        signOut.setBounds(290, 180, 100, 25);

        getRecommendation.addActionListener(this);
        addReview.addActionListener(this);
        signOut.addActionListener(this);

        menuUser.add(getRecommendation);
        menuUser.add(addReview);
        menuUser.add(jcp);
        menuUser.add(signOut);

        profile.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String selected = profile.getSelectedValue();
                if (selected.equals("Modify Profile")) {
                    cp.setPanel(7);
                }
                else if (selected.equals("Delete Profile")) {
                    cp.deleteUser();
                    cp.updateKmeans();
                    cp.setPanel(0);
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == getRecommendation) {
            cp.setPanel(6);
        }
        else if (actionEvent.getSource() == addReview) {
            cp.setPanel(8);
        }
        else if (actionEvent.getSource() == signOut) {
            cp.setPanel(2);
        }
    }

}

