package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlAddReview implements ActionListener{

    CtrlPresentation cp;

    public JLabel rating, comment, item, errorItemOrRaiting;
    public JTextField raitingText, commentText, itemText;
    public JButton send, exit;


    public CtrlAddReview(JPanel addReview){
        addReview.setLayout(null);
        cp = CtrlPresentation.getInstance();

        send = new JButton("Send");
        exit = new JButton("Back");
        rating = new JLabel("Rating");
        comment = new JLabel("Comment");
        errorItemOrRaiting = new JLabel("");
        item = new JLabel("Item");
        raitingText = new JTextField();
        commentText = new JTextField();
        itemText = new JTextField();

        send.setBounds(70,180,100,25);
        exit.setBounds( 290,180,100,25);
        errorItemOrRaiting.setBounds(50, 150, 350, 25);;

        item.setBounds(70,30,100,25);
        itemText.setBounds(160, 30, 150, 25);

        rating.setBounds(70,90,100,25);
        raitingText.setBounds(160, 90, 150, 25);

        comment.setBounds(70,60,100,25);
        commentText.setBounds(160,60,150,25);

        send.addActionListener(this);
        exit.addActionListener(this);

        addReview.add(errorItemOrRaiting);
        addReview.add(send);
        addReview.add(exit);
        addReview.add(item);
        addReview.add(itemText);
        addReview.add(rating);
        addReview.add(raitingText);
        addReview.add(comment);
        addReview.add(commentText);

    }

    private boolean isDbl(String input) {
        try{
            double inputDbl = Double.parseDouble(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    private boolean isInt(String input) {
        try{
            int inputDbl = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == send){
            Integer itemId;
            String commentari =  commentText.getText();
            Double rai;

            if (!isInt(itemText.getText())) {
                errorItemOrRaiting.setText("<html><font color='red'>Invalid item id, must type an integer.</font></html>");
                itemText.setText("");
                commentText.setText("");
                raitingText.setText("");
            }
            else {
                itemId = Integer.parseInt(itemText.getText());
                if (!cp.existItem(itemId)) {
                    errorItemOrRaiting.setText("<html><font color='red'>This item does not exist</font></html>");
                    itemText.setText("");
                    commentText.setText("");
                    raitingText.setText("");
                } else if (cp.existReview(itemId)) {
                    errorItemOrRaiting.setText("<html><font color='red'>This user has already evaluated this item</font></html>");
                    commentText.setText("");
                    raitingText.setText("");
                } else {
                    if (isDbl(raitingText.getText())) {
                        rai = Double.parseDouble(raitingText.getText());
                        if (rai >= 0 && rai <= 5) {
                            cp.addReview(itemId, rai, commentari);
                            errorItemOrRaiting.setText("<html><font color='green'>Review added.</font></html>");
                            itemText.setText("");
                            raitingText.setText("");
                            commentText.setText("");
                        } else {
                            errorItemOrRaiting.setText("<html><font color='red'>Invalid raiting, must be between 0 and 5.</font></html>");
                            raitingText.setText("");
                        }
                    } else {
                        errorItemOrRaiting.setText("<html><font color='red'>Invalid raiting, must type a decimal number.</font></html>");
                        raitingText.setText("");
                    }

                }
            }
        }

        else if (actionEvent.getSource() == exit){
            errorItemOrRaiting.setText("");
            itemText.setText("");
            raitingText.setText("");
            commentText.setText("");
            cp.setPanel(3);
        }
    }

}
