package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlLoginProfile extends CtrlProfile implements ActionListener {

    CtrlPresentation cp;
    JButton loginOk;

    public CtrlLoginProfile(JPanel login) {
        cp = CtrlPresentation.getInstance();
        login.setLayout(null);

        username = new JLabel("Username:");
        userText = new JTextField();
        password = new JLabel("Password:");
        passwordText = new JPasswordField();
        loginOk = new JButton("Log in");
        error = new JLabel();
        exit = new JButton("Back");

        username.setBounds(70, 30, 100, 25);
        userText.setBounds(160, 30, 150, 25);
        password.setBounds(70, 60, 100, 25);
        passwordText.setBounds(160, 60, 150, 25);
        loginOk.setBounds(160, 90, 100, 25);
        error.setBounds(80, 120, 300, 25);
        exit.setBounds(290, 180, 100, 25);

        loginOk.addActionListener(this);
        exit.addActionListener(this);

        login.add(username);
        login.add(userText);
        login.add(password);
        login.add(passwordText);
        login.add(loginOk);
        login.add(error);
        login.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == loginOk) {
            String user = userText.getText();
            String entered = passwordText.getText();

            if (!cp.existUser(user) && !user.equals("admin")) {
                error.setText("<html><font color='red'>The user does not exist.</font></html>");
                userText.setText("");
                passwordText.setText("");
            }

            else {

                if (!user.equals("admin")) {
                    String pass = cp.getPassword(user);
                    if (!entered.equals(pass)) {
                        error.setText("<html><font color='red'>Wrong password, please try again.</font></html>");
                        passwordText.setText("");
                    } else {
                        userText.setText("");
                        passwordText.setText("");
                        error.setText("");
                        cp.setIdUser(user);
                        cp.setIdUserAux(user);
                        cp.setPanel(3);
                    }
                }

                else {
                    if (!entered.equals("admin")) {
                        error.setText("<html><font color='red'>Wrong password, please try again.</font></html>");
                        passwordText.setText("");
                    } else {
                        userText.setText("");
                        passwordText.setText("");
                        error.setText("");
                        cp.setIdUser("-1");
                        cp.setIdUserAux("-1");
                        cp.setPanel(4);
                    }
                }

            }

        }

        else if (actionEvent.getSource() == exit) {
            userText.setText("");
            passwordText.setText("");
            error.setText("");
            cp.setPanel(0);
        }

    }
}
