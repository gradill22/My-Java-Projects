package HelloNamePanel2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloNamePanel2 extends JPanel {
    JLabel msg;
    JTextField box;
    JButton button;

    String[] names = new String[10];
    static int i = 0;

    public HelloNamePanel2() {
        setLayout(new FlowLayout());

        msg = new JLabel("Type your name, then press Enter. Enter -1 when finished");
        add(msg);

        box = new JTextField(20);
        add(box);

        button = new JButton("Enter");
        add(button);
        button.addActionListener(new Listener());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello World Panel");
        frame.setSize(250, 100);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HelloNamePanel2());
        frame.setVisible(true);
    }

    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(i >= 10) {
                int j = 0;
                while(j < i) {
                    System.out.println(names[j]);
                    j++;
                }
                System.exit(0);
            } else if(box.getText().equals("-1")) {
                for(int n = 0; n < i; n++) {
                    System.out.println(names[n]);
                }
                System.exit(0);
            } else if(box.getText().isEmpty()) {
                msg.setText("Please enter your name");
            } else {
                msg.setText("Hello " + box.getText());
                names[i] = box.getText();
                i++;
            }
            box.setText("");
        }
    }
}