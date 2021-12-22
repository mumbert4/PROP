package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CtrlGetRecommendationUserManagerMode extends CtrlGetRecommendationActiveUserMode implements ActionListener {

    private JButton evaluate;
    private JLabel evaluationResult;

    public CtrlGetRecommendationUserManagerMode(JPanel recommendation) {
        super(recommendation);
        evaluate = new JButton("evaluate");
        evaluationResult = new JLabel();

        evaluate.setBounds(230, 55, 150, 25);
        evaluationResult.setBounds(230, 90, 200, 25);

        evaluate.addActionListener(this);

        recommendation.add(evaluate);
        recommendation.add(evaluationResult);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if (actionEvent.getSource() == evaluate) {

            Double d = 0.;

            try {
                d = cp.evaluation(items_rec, type);
            } catch (IOException e) {
                e.printStackTrace();
            }

            evaluationResult.setText("" + d);
        }

        else if (actionEvent.getSource() == exit) {
            evaluationResult.setText("");
            cp.setPanel(3);
        }

        else {
            super.actionPerformed(actionEvent);
        }

    }

}

