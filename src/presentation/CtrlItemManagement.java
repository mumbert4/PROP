package presentation;

import javax.swing.*;




public class CtrlItemManagement {

    protected JLabel idItem;
    protected JTextField idItemText;
    protected JButton readItem;
    protected JLabel error;
    protected JButton exit;


    public boolean isInt(String input) {
        try{
            int inputDbl = Integer.parseInt(input);
            return true;
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
    }
}
