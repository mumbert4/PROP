package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlCreateProfile extends CtrlProfile implements ActionListener {

    CtrlPresentation cp;
    private JLabel confirmPassword;
    private JPasswordField confirmPasswordText;
    private JButton ok;

    public CtrlCreateProfile(JPanel create) {
        cp = CtrlPresentation.getInstance();
        create.setLayout(null);

        username = new JLabel("Username:");
        userText = new JTextField();
        password = new JLabel("Password:");
        passwordText = new JPasswordField();
        confirmPassword = new JLabel("Confirm Password:");
        confirmPasswordText = new JPasswordField();
        ok = new JButton("Sign up");
        error = new JLabel();
        exit = new JButton("Back");


        username.setBounds(70, 30, 100, 25);
        userText.setBounds(160, 30, 150, 25);
        password.setBounds(70, 60, 100, 25);
        passwordText.setBounds(160, 60, 150, 25);
        confirmPassword.setBounds(10, 90, 150, 25);
        confirmPasswordText.setBounds(160, 90, 150, 25);
        ok.setBounds(160, 120, 100, 25);
        error.setBounds(80, 150, 250, 25);
        exit.setBounds(290, 180, 100, 25);

        ok.addActionListener(this);
        exit.addActionListener(this);

        create.add(username);
        create.add(userText);
        create.add(password);
        create.add(passwordText);
        create.add(confirmPassword);
        create.add(confirmPasswordText);
        create.add(ok);
        create.add(error);
        create.add(exit);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == ok) {
            String user = userText.getText();

            if (cp.existUser(user) || user.equals("admin") || user.equals("-1")) {
                error.setText("<html><font color='red'>The user already exists.</font></html>");
                userText.setText("");
                passwordText.setText("");
                confirmPasswordText.setText("");
            }

            else {
                String pass1 = passwordText.getText();
                String pass2 = confirmPasswordText.getText();

                if (!pass1.equals(pass2)) {
                    error.setText("<html><font color='red'>The password does not match.</font></html>");
                    confirmPasswordText.setText("");
                }

                else {
                    cp.createUser(user, pass1);
                    cp.updateKmeans();
                    error.setText("<html><font color='green'>User created.</font></html>");
                    userText.setText("");
                    passwordText.setText("");
                    confirmPasswordText.setText("");
                }

            }

        }

        else if (actionEvent.getSource() == exit) {
            userText.setText("");
            passwordText.setText("");
            confirmPasswordText.setText("");
            error.setText("");
            cp.setPanel(0);
        }

    }
}

