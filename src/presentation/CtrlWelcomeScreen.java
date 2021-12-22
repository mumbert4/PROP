package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlWelcomeScreen implements ActionListener {

    CtrlPresentation cp;
    JButton createProfile;
    JButton login;
    JButton exit;

    public CtrlWelcomeScreen(JPanel welcome) {
        cp = CtrlPresentation.getInstance();
        welcome.setLayout(null);

        createProfile = new JButton("Sign up");
        createProfile.setBounds(70, 90, 100, 25);
        createProfile.addActionListener(this);
        welcome.add(createProfile);

        login = new JButton("Login");
        login.setBounds(210, 90, 100, 25);
        login.addActionListener(this);
        welcome.add(login);

        exit = new JButton("Exit");
        exit.setBounds(290, 180, 100, 25);
        exit.addActionListener(this);
        welcome.add(exit);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == createProfile) {
            cp.setPanel(1);
        }
        else if (actionEvent.getSource() == login) {
            cp.setPanel(2);
        }
        else if (actionEvent.getSource() == exit) {
            System.exit(0);
        }
    }
}
