package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlChangePassword implements ActionListener {

    CtrlPresentation cp;
    private JLabel password;
    private JPasswordField passwordText;
    private JLabel newPassword;
    private JPasswordField newPasswordText;
    private JLabel confirmPassword;
    private JPasswordField confirmPasswordText;
    private JButton apply;
    private JLabel error;
    private JButton exit;

    public CtrlChangePassword(JPanel changePwd) {
        cp = CtrlPresentation.getInstance();
        changePwd.setLayout(null);

        password = new JLabel("Actual Password:");
        passwordText = new JPasswordField();
        newPassword = new JLabel("New Password:");
        newPasswordText = new JPasswordField();
        confirmPassword = new JLabel("Confirm Password:");
        confirmPasswordText = new JPasswordField();
        apply = new JButton("Apply");
        error = new JLabel();
        exit = new JButton("Back");

        password.setBounds(20, 30, 150, 25);
        passwordText.setBounds(160, 30, 150, 25);
        newPassword.setBounds(33, 60, 150, 25);
        newPasswordText.setBounds(160, 60, 150, 25);
        confirmPassword.setBounds(10, 90, 150, 25);
        confirmPasswordText.setBounds(160, 90, 150, 25);
        apply.setBounds(160, 120, 100, 25);
        error.setBounds(50, 150, 350, 25);
        exit.setBounds(290, 180, 100, 25);

        apply.addActionListener(this);
        exit.addActionListener(this);

        changePwd.add(password);
        changePwd.add(passwordText);
        changePwd.add(newPassword);
        changePwd.add(newPasswordText);
        changePwd.add(confirmPassword);
        changePwd.add(confirmPasswordText);
        changePwd.add(apply);
        changePwd.add(error);
        changePwd.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == apply) {
            String id = cp.getIdUserAux();
            String pass = cp.getPassword(id);
            String pwd = passwordText.getText();

            if (!pass.equals(pwd)) {
                error.setText("<html><font color='red'>Wrong actual password, please try again.</font></html>");
                passwordText.setText("");
                newPasswordText.setText("");
                confirmPasswordText.setText("");
            }

            else {
                String pass1 = newPasswordText.getText();
                String pass2 = confirmPasswordText.getText();

                if (!pass1.equals(pass2)) {
                    error.setText("<html><font color='red'>The new password does not match.</font></html>");
                    confirmPasswordText.setText("");
                }

                else {
                    cp.changePassword(pass1);
                    error.setText("<html><font color='green'>Your password has been successfully changed.</font></html>");
                    passwordText.setText("");
                    newPasswordText.setText("");
                    confirmPasswordText.setText("");
                }

            }
        }

        else if (actionEvent.getSource() == exit) {
            passwordText.setText("");
            newPasswordText.setText("");
            confirmPasswordText.setText("");
            error.setText("");
            String usr = cp.getIdUser();
            String usrAux = cp.getIdUserAux();
            if (usr.equals(usrAux)) cp.setPanel(3);
            else cp.setPanel(12);
        }

    }

}
