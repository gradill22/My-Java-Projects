package Quadratic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuadSolverGUI extends JPanel {
    private static JFrame frame;
    private final JTextField a, b, c;
    private final JLabel yInt, xInts, axisOfSymmetry, vertex, domain, range, minOrMax, factoredQuad;
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public QuadSolverGUI() {
        setLayout(new FlowLayout());
        add(new JLabel("Input quadratic values"));
        add(a = new JTextField(10));
        add(b = new JTextField(10));
        add(c = new JTextField(10));
        JButton button;
        add(button = new JButton("Calculate"));
        add(yInt = new JLabel());
        add(xInts = new JLabel());
        add(axisOfSymmetry = new JLabel());
        add(vertex = new JLabel());
        add(domain = new JLabel());
        add(range = new JLabel());
        add(minOrMax = new JLabel());
        add(new JLabel("Factored Quadratic:"));
        add(factoredQuad = new JLabel());
        button.addActionListener(new Listener());
    }

    public static void main(String[] args) {
        frame = new JFrame("Quadratic Solver");
        frame.setSize(150, 175);
        frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new QuadSolverGUI());
        frame.setVisible(true);
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Quadratic quad = new Quadratic(Double.parseDouble(a.getText()), Double.parseDouble(b.getText()), Double.parseDouble(c.getText()));
            if(quad.getA() != 0) {
                yInt.setText("Y-Intercept = (0, " + quad.getYInt() + ")");
                if(quad.getPosInt() != 0 && quad.getNegInt() != 0) {
                    if(quad.getPosInt() == quad.getNegInt()) {
                        xInts.setText("X-Intercept = (" + Quadratic.formNum(quad.getPosInt()) + ", 0)");
                    } else {
                        xInts.setText("X-Intercepts: (" + Quadratic.formNum(quad.getPosInt()) + ", 0), (" + Quadratic.formNum(quad.getNegInt()) + ", 0)");
                    }
                } else if (quad.getPosInt() == 0 && quad.getNegInt() != 0) {
                    xInts.setText("X-Intercept = (" + Quadratic.formNum(quad.getNegInt()) + ", 0)");
                } else if (quad.getPosInt() != 0) {
                    xInts.setText("X-Intercept = (" + Quadratic.formNum(quad.getPosInt()) + ", 0)");
                } else {
                    xInts.setText("X-Intercepts: None");
                }
                axisOfSymmetry.setText("Axis of Symmetry: x = " + Quadratic.formNum(quad.getAxisOfSymmetry()));
                vertex.setText("Vertex: (" + Quadratic.formNum(quad.getAxisOfSymmetry()) + ", " + Quadratic.formNum(quad.getVertex()) + ")");
                domain.setText("Domain: {x | x ∈ ℝ)");
                if(quad.getA() > 0) {
                    range.setText("Range: {y | y ≥ " + Quadratic.formNum(quad.getVertex()) + "}");
                    minOrMax.setText("Min or Max: Minimum");
                } else if(quad.getA() < 0) {
                    range.setText("Range: {y | y ≤ " + Quadratic.formNum(quad.getVertex()) + "}");
                    minOrMax.setText("Min or Max: Maximum");
                }
                if(Factors.psFactors(quad).length() > 0) {
                    factoredQuad.setText(Factors.psFactors(quad));
                } else if(Factors.factors(quad).length() > 0) {
                    factoredQuad.setText(Factors.factors(quad));
                } else {
                    factoredQuad.setText("Imaginary solutions");
                }
                frame.setSize(195, 365);
                frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
            } else {
                a.setText("");
                b.setText("");
                c.setText("");
                yInt.setText("ERROR: a = 0, please try again");
            }
        }
    }
}