package BinaryTranslatorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BinaryTranslatorGUI extends JPanel {
    static JFrame frame;
    JLabel binary, text;
    JTextField binaryString, textField;
    JButton button;

    public BinaryTranslatorGUI() {
        add(binary = new JLabel("Binary:"));
        add(binaryString = new JTextField(20));
        add(text = new JLabel("Non-binary:"));
        add(textField = new JTextField(20));
        button = new JButton("Translate");
        button.addActionListener(new Listener());
        add(button);
    }

    public static void main(String[] args) {
        frame = new JFrame("Binary Translator");
        frame.setSize(325, 125);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new BinaryTranslatorGUI());
        frame.setVisible(true);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(binaryString.getText().isBlank() && !textField.getText().isBlank()) {
                if(isInteger(textField.getText())) {
                    binaryString.setText(Binary.toBinary(Integer.parseInt(textField.getText())));
                } else {
                    binaryString.setText(Binary.toBinary(textField.getText()));
                }
            } else if(!binaryString.getText().isBlank() && textField.getText().isBlank()) {
                textField.setText(Binary.toText(binaryString.getText()));
            }
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}