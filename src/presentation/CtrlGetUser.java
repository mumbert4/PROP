package presentation;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlGetUser implements ActionListener {

    CtrlPresentation cp;
    JLabel username;
    JTextField userText;
    JList<String> profile;
    JButton getRecommendation;
    JLabel error;
    JButton exit;

    public CtrlGetUser(JPanel getUser) {

        cp = CtrlPresentation.getInstance();
        getUser.setLayout(null);

        String[] options = new String[2];
        options[0] = "Modify Profile";
        options[1] = "Delete Profile";

        username = new JLabel("Username:");
        userText = new JTextField();
        profile = new JList(options);
        getRecommendation = new JButton("Get Recommendation");
        error = new JLabel();
        exit = new JButton("Back");

        profile.setVisibleRowCount(1);
        JScrollPane jcp = new JScrollPane(profile);

        username.setBounds(30, 50, 190, 25);
        userText.setBounds(30, 70, 190, 25);
        getRecommendation.setBounds(30, 100, 190, 25);
        jcp.setBounds(250, 50, 125, 23);
        error.setBounds(80, 120, 300, 25);
        exit.setBounds(290, 180, 100, 25);

        getRecommendation.addActionListener(this);
        exit.addActionListener(this);

        getUser.add(username);
        getUser.add(userText);
        getUser.add(getRecommendation);
        getUser.add(jcp);
        getUser.add(error);
        getUser.add(exit);

        profile.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                String selected = profile.getSelectedValue();
                String user = userText.getText();

                if (cp.existUser(user)) {
                    cp.setIdUserAux(user);
                    if (selected.equals("Modify Profile")) {
                        username.setText("");
                        error.setText("");
                        cp.setPanel(7);
                    } else if (selected.equals("Delete Profile")) {
                        cp.deleteUser();
                        cp.updateKmeans();
                        username.setText("");
                        error.setText("");
                        cp.setPanel(4);
                    }
                } else {
                    error.setText("<html><font color='red'>This user does not exist</font></html>");
                    userText.setText("");
                }
            }

        });
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == getRecommendation) {
            String user = userText.getText();

            if (cp.existUser(user)) {
                cp.setIdUserAux(user);
                username.setText("");
                error.setText("");
                cp.setPanel(5);
            } else {
                error.setText("<html><font color='red'>This user does not exist</font></html>");
                userText.setText("");
            }
        } else if (actionEvent.getSource() == exit) {
            cp.setPanel(4);
        }

    }

}