package Tracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Main extends JPanel implements KeyListener {
    private static JLabel output, total;
    private static JTextField price;
    private static JButton enter, undo;
    private static ArrayList<Float> prices;

    public Main() {
        add(new JLabel("Price:"));
        add(price = new JTextField(8));
        price.addKeyListener(this);
        add(output = new JLabel(""));
        add(enter = new JButton("Enter"));
        enter.addActionListener(new EnterListener());
        enter.setVisible(false);
        add(undo = new JButton("Undo"));
        undo.addActionListener(new UndoListener());
        undo.setVisible(false);
        add(total = new JLabel());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("McDonald's");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.addWindowListener(new Close());
        frame.setSize(275, 100);
        frame.setContentPane(new Main());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
        frame.setVisible(true);
        prices = new ArrayList<>();
        try {
            File file = new File("McDonald's_Tracker_Data.txt");
            if(file.createNewFile()) {
                System.out.println("File created!");
            } else {
                System.out.println("File already exists!");
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while(line != null) {
                prices.add(Float.parseFloat(line));
                line = br.readLine();
            }
            total.setText("Total spent on McDonald's: " + NumberFormat.getCurrencyInstance().format(sumOfArrayList(prices)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class EnterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isFloat(price.getText())) {
                prices.add(Float.parseFloat(price.getText()));
                output.setText("Added " + NumberFormat.getCurrencyInstance().format(Float.parseFloat(price.getText())));
                price.setText("");
                total.setText("Total spent on McDonald's: " + NumberFormat.getCurrencyInstance().format(sumOfArrayList(prices)));
            } else {
                output.setText("Please enter proper dollar value");
            }
        }
    }

    private static class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(prices.size() > 0) {
                price.setText(String.valueOf(prices.get(prices.size() - 1)));
                output.setText("Removed " + NumberFormat.getCurrencyInstance().format(prices.get(prices.size() - 1)));
                prices.remove(prices.size() - 1);
                total.setText("Total spent on McDonald's: " + NumberFormat.getCurrencyInstance().format(sumOfArrayList(prices)));
            }
        }
    }

    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            try {
                File file = new File("McDonald's_Tracker_Data.txt");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                StringBuilder str = new StringBuilder();
                for(Float f : prices) {
                    str.append(f).append("\n");
                }
                bw.write(str.toString());
                bw.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        public void windowClosed(WindowEvent e) {

        }

        public void windowIconified(WindowEvent e) {

        }

        public void windowDeiconified(WindowEvent e) {

        }

        public void windowActivated(WindowEvent e) {

        }

        public void windowDeactivated(WindowEvent e) {

        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {
            enter.doClick();
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            undo.doClick();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static float sumOfArrayList(ArrayList<Float> arr) {
        float sum = 0;
        for(float f : arr) {
            sum += f;
        }
        return sum;
    }
}