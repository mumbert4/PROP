package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CtrlGetRecommendationActiveUserMode implements ActionListener {

    protected CtrlPresentation cp;
    protected JLabel choose;
    protected JButton collaborative;
    protected JButton contentB;
    protected JButton hybrid;
    protected JLabel result;
    protected JLabel rec_items;
    protected JButton exit;
    protected List<Integer> items_rec;
    protected String type;

    public CtrlGetRecommendationActiveUserMode(JPanel recommendation) {
        cp = CtrlPresentation.getInstance();
        recommendation.setLayout(null);

        choose = new JLabel("Chose one of the algorithms");
        collaborative = new JButton("CollaborativeFiltering");
        contentB = new JButton("ContentBasedFiltering");
        hybrid = new JButton("HybridApproach");
        result = new JLabel();
        rec_items = new JLabel();
        exit = new JButton("Back");

        choose.setBounds(20, 20, 250, 25);
        collaborative.setBounds(20, 55, 200, 25);
        contentB.setBounds(20, 90, 200, 25);
        hybrid.setBounds(20, 125, 200, 25);
        result.setBounds(20, 155, 300, 25);
        rec_items.setBounds(20, 180, 300, 25);
        exit.setBounds(290, 180, 100, 25);

        collaborative.addActionListener(this);
        contentB.addActionListener(this);
        hybrid.addActionListener(this);
        exit.addActionListener(this);

        recommendation.add(choose);
        recommendation.add(collaborative);
        recommendation.add(contentB);
        recommendation.add(hybrid);
        recommendation.add(result);
        recommendation.add(rec_items);
        recommendation.add(exit);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == contentB) {
            type = "con";
            items_rec = cp.getRecommendation("cb");
            result.setText("The recommended items are:");
            rec_items.setText("" + items_rec);
        }

        else if (actionEvent.getSource() == collaborative) {
            type = "col";
            items_rec = cp.getRecommendation("col");
            result.setText("The recommended items are:");
            rec_items.setText("" + items_rec);
        }

        else if (actionEvent.getSource() == hybrid) {
            items_rec = cp.getRecommendation("hyb");
            result.setText("The recommended items are:");
            rec_items.setText("" + items_rec);
        }

        else if (actionEvent.getSource() == exit) {
            cp.setPanel(3);
        }

    }

}

