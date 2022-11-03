package RegressionCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegressionCalculator extends JPanel {
    static JFrame frame;
    JLabel msg, lin, quad;
    JTextField x, y;
    JButton button;
    static Regression r;

    public RegressionCalculator() {
        setLayout(new FlowLayout());
        add(msg = new JLabel("Input x and y data"));
        add(x = new JTextField(20));
        add(y = new JTextField(20));
        button = new JButton("Calculate");
        add(button);
        button.addActionListener(new Listener());
        add(lin = new JLabel());
        add(quad = new JLabel());
    }

    public static void main(String[] args) {
        frame = new JFrame("Regression Calculator");
        frame.setSize(240, 200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new RegressionCalculator());
        frame.setVisible(true);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!x.getText().isEmpty() && !y.getText().isEmpty()) {
                if(r == null) {
                    r = new Regression();
                }
                r.addX(Double.parseDouble(x.getText()));
                r.addY(Double.parseDouble(y.getText()));
                msg.setText("Input x and y data (" + Regression.xData.size() + ")");
                lin.setText(r.toLinString());
                quad.setText(r.toQuadString());
                if(r.toQuadString().length() >= 42) {
                    frame.setSize(260, 200);
                }
                x.setText("");
                y.setText("");
            }
        }
    }
}