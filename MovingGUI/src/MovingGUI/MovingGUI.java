package MovingGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovingGUI extends JPanel {
    static JFrame frame;
    JButton button;
    static Datapoint points;
    static int i;
    static Dimension screenSize;

    public MovingGUI() {
        setLayout(new FlowLayout());
        button = new JButton("Press the button");
        add(button);
        button.addActionListener(new Listener());
    }

    public static void main(String[] args) {
        frame = new JFrame();
        frame.setSize(75, 75);
        frame.setResizable(false);
        frame.setLocation(900, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new MovingGUI());
        frame.setVisible(true);
        points = new Datapoint();
        i = 0;
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int x = (int) (Math.random() * (screenSize.width - 200) + 100);
            int y = (int) (Math.random() * (screenSize.height - 50) + 25);
            frame.setLocation(x, y);
            button.setText(x + ", " + y);
            points.add(x, y);
            System.out.println(i + 1 + ": " + points.toString(i));
            i++;
            if(i % 100 != 0) {
                button.doClick(10);
            }
        }
    }
}