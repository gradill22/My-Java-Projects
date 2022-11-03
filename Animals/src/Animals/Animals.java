package Animals;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Animals extends JPanel {
    static JFrame frame;
    JLabel name;
    JLabel age;
    JLabel sick;
    JLabel weight;
    JLabel msg;
    JTextField theName;
    JTextField theAge;
    JTextField theSick;
    JTextField theWeight;
    JButton search;
    JButton save;
    JButton add;
    JButton remove;
    JButton print;
    static ArrayList<Elephant> elephants;
    static int jAccessed;

    public Animals() { // structure of GUI
        add(name = new JLabel("Elephant name:"));
        add(theName = new JTextField(20));
        add(age = new JLabel("Elephant age:"));
        add(theAge = new JTextField(20));
        add(sick = new JLabel("Sick?"));
        add(theSick = new JTextField(20));
        add(weight = new JLabel("Elephant weight:"));
        add(theWeight = new JTextField(20));
        add(msg = new JLabel("Message:"));
        add(search = new JButton("Search"));
        search.addActionListener(new SearchListener());
        add(save = new JButton("Save"));
        save.addActionListener(new SaveListener());
        add(add = new JButton("Add"));
        add.addActionListener(new AddListener());
        add(remove = new JButton("Remove"));
        remove.addActionListener(new RemoveListener());
        add(print = new JButton("Print"));
        print.addActionListener(new PrintListener());
    }

    public static void main(String[] args) {
        frame = new JFrame("Elephant Manager");
        frame.setSize(335, 205);
        frame.setLocation(750, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Animals());
        frame.setVisible(true);
        elephants = new ArrayList<>();
        getText();
        jAccessed = 0;
    }

    private class SearchListener implements ActionListener { // searches for specific Elephant object in ArrayList
        public void actionPerformed(ActionEvent e) {
            if(elephants.size() == 0) { // errors if there are no elephants to search
                msg.setText("Message: There are no Elephants saved.");
                frame.setSize(335, 205);
            } else {
                if(!theName.getText().isBlank()) { // runs if there is input
                    for(int i = 0; i < elephants.size(); i++) { // searches through arraylist
                        if(elephants.get(i).getName().equalsIgnoreCase(theName.getText())) {
                            theName.setText(elephants.get(i).getName()); // returns name
                            theAge.setText(String.valueOf(elephants.get(i).getAge())); // returns age
                            theSick.setText(String.valueOf(elephants.get(i).getSick())); // returns sick
                            theWeight.setText(String.valueOf(elephants.get(i).getWeight())); // returns weight
                            jAccessed = i; // marks as last elephant accessed from arraylist
                            i = elephants.size(); // stops loop
                            msg.setText("Message:"); // resets error message
                        } else {
                            msg.setText("Message: Could not find Elephant matching name");
                            frame.setSize(335, 205);
                        }
                    }
                } else { // errors if there is no input
                    msg.setText("Message: Please input name of Elephant to search");
                    frame.setSize(335, 205);
                }
            }
        }
    }

    private class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try { // attempts to execute code
                elephants.get(jAccessed).setName(theName.getText()); // rests name
                elephants.get(jAccessed).setAge(Integer.parseInt(theAge.getText())); // resets age
                elephants.get(jAccessed).setSick(Boolean.parseBoolean(theSick.getText())); // resets sick
                elephants.get(jAccessed).setWeight(Double.parseDouble(theWeight.getText())); // resets weight
                resetTextFields(); // resets input text fields
            } catch(Exception exception) { // errors if non-executable
                msg.setText("Message: Improper input(s)");
                frame.setSize(335, 205);
            }
        }
    }

    private class AddListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(!theName.getText().isBlank() && !theAge.getText().isBlank() && !theSick.getText().isBlank() && !theWeight.getText().isBlank()) { // only adds elephants if text fields are full
                try { // attempts to execute code without error
                    elephants.add(new Elephant(theName.getText(), Integer.parseInt(theAge.getText()), Boolean.parseBoolean(theSick.getText()), Double.parseDouble(theWeight.getText())));
                    msg.setText("Message: Elephant " + theName.getText() + " has been added");
                    resetTextFields();
                } catch (Exception exception) { // prints out code if input is non-executable
                    msg.setText("Message: Improper input(s)");
                }
                frame.setSize(335, 205);
            }
        }
    }

    private class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(elephants.size() == 0) { // errors if there are no elephants saved
                msg.setText("Message: There are no Elephants saved.");
                frame.setSize(335, 205);
            } else { // removes elephant if it exists
                if(!theName.getText().isBlank()) { // only runs if name is inputted
                    for(int i = 0; i < elephants.size(); i++) {
                        if(elephants.get(i).getName().equalsIgnoreCase(theName.getText())) {
                            msg.setText("Message: Elephant " + elephants.get(i).getName() + " has been removed");
                            elephants.remove(i); // removes elephant
                            i = elephants.size(); // stops loop
                            resetTextFields(); // rests input text fields
                            frame.setSize(335, 225);
                            msg.setText("Message:"); // resets error message
                        }
                        else { // errors if elephant(s) don't match input name
                            msg.setText("Message: Could not find Elephant matching name");
                            frame.setSize(335, 205);
                        }
                    }
                } else { // errors if there is no input
                    msg.setText("Message: Please input name of Elephant to remove");
                    frame.setSize(335, 205);
                }
            }
        }
    }

    private static class PrintListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(Elephant elephant : elephants) { // prints out all Elephant objects to console
                System.out.println(elephant.toString());
            }
        }
    }

    private static void getText() { // gets data from text file
        try {
            File file = new File("ElephantData.txt");
            if(file.exists()) {
                Desktop.getDesktop().open(file); // opens data file
            }
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String allElephantData = br.readLine(); // string holds first line of data from ElephantData.txt
            StringBuilder str = new StringBuilder(); // string builder for elephant data input
            String name = null; // null name for elephant data input
            int age = -1; // impossible age for elephant data input
            boolean sick = false; // default sick for elephant data input
            double weight; // null weight for elephant data input
            while(allElephantData != null) { // loops through all data
                for(int i = 0; i < allElephantData.length(); i++) {
                    if(allElephantData.charAt(i) != ',' && allElephantData.charAt(i) != ' ') {
                        str.append(allElephantData.charAt(i));
                    } else {
                        if(name == null) {
                            name = str.toString(); // parses name variable
                        }
                        else if(age == -1) {
                            age = Integer.parseInt(str.toString()); // parses age variable
                        }
                        else if(str.toString().equals("true") || str.toString().equals("false")) {
                            sick = Boolean.parseBoolean(str.toString()); // parses sick variable
                        }
                        str.delete(0, str.length());
                        i++; // skips the ' ' between object variables
                    }
                }
                weight = Double.parseDouble(str.toString()); // weight is rest of string, parses weight variable
                elephants.add(new Elephant(name, age, sick, weight)); // adds new elephant
                str.delete(0, str.length()); // deletes whole string builder
                allElephantData = br.readLine(); // string holds next line of data from ElephantData.txt
                name = null; // resets name
                age = -1; // resets age
                sick = false; // resets sick
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetTextFields() { // resets all user input text fields
        theName.setText("");
        theAge.setText("");
        theSick.setText("");
        theWeight.setText("");
    }
}