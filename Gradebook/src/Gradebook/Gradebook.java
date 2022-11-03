package Gradebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Gradebook extends JPanel {
    private static JFrame frame;
    private static JButton addClass, addGrade, back;
    private static final ArrayList<Class> classes = new ArrayList<>();
    private static final ArrayList<JTextField> classNames = new ArrayList<>();
    private static final ArrayList<JSpinner> classCredits = new ArrayList<>();
    private static final ArrayList<JTextField> classGrade = new ArrayList<>();
    private static final ArrayList<JButton> viewButtons = new ArrayList<>();
    private static final ArrayList<JButton> removeButtons = new ArrayList<>();
    private static final File file = new File("gradebook.txt");
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Gradebook() {
        frame.setTitle("Gradebook");
        setLayout(new GridLayout(9, 5));
        for(Class c: classes) {
            JPanel j = new JPanel();
            j.setLayout(new GridLayout(1, 6));
            JTextField className = new JTextField(c.getClassName());
            JSpinner classCreds = new JSpinner(new SpinnerNumberModel(c.getCredits(), 0, 4, 1));
            JTextField grade = new JTextField(c.letterGrade() + " (" + String.format("%.2f", c.numberGrade()) + ")");
            grade.setEditable(false);
            classGrade.add(grade);
            String substring = c.toString().substring(0, c.toString().length() - 1);

            JButton viewClass = new JButton("View " + c.getClassName());
            viewClass.addActionListener(new ViewClassListener(c));
            viewButtons.add(viewClass);
            System.out.println("JUST PASSED " + substring + " INTO new ViewClassListener");

            JButton removeClass = new JButton("Delete");
            removeClass.addActionListener(new RemoveClassListener(c));
            removeButtons.add(removeClass);
            System.out.println("JUST PASSED " + substring + " INTO new RemoveClassListener");

            j.add(className);
            j.add(classCreds);
            j.add(grade);
            j.add(viewClass);
            j.add(removeClass);
            classNames.add(className);
            classCredits.add(classCreds);
            add(j);
        }
        addClass = new JButton("Add Class");
        addClass.addActionListener(new AddClassListener());
        add(addClass);
    }

    private static class ClassView extends JPanel {
        public ClassView(Class c) {
            frame.setTitle(c.getClassName());
            setLayout(new GridLayout(8, 6));
            for(int i = 0; i < c.getGrades().size(); i++) {
                JPanel j = new JPanel();
                j.setLayout(new GridLayout(1, 5));
                JTextArea gradeName = new JTextArea(c.getGrade(i).getName());
                JSpinner gradeType = new JSpinner(new SpinnerListModel(c.gradeNames()));
                gradeType.setValue(c.getGradeName(c.getGrade(i)));
                JTextField score = new JTextField(String.valueOf(c.getGrade(i).getGrade(i)));
                JTextArea outOf = new JTextArea("out of");
                outOf.setEditable(false);
                JTextField maxScore = new JTextField(String.valueOf(c.getGrade(i).getMaxPoints(i)));
                j.add(gradeName);
                j.add(gradeType);
                j.add(score);
                j.add(outOf);
                j.add(maxScore);
                add(j);
            }
            back = new JButton("Back");
            back.addActionListener(new BackListener());
            updateFrame();
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Gradebook");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            System.out.println("INPUT");
            while (line != null) {
                System.out.println(line);
                classes.add(new Class(line));
                line = br.readLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        frame.setSize(1500, 800);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Gradebook());
        frame.addWindowListener(new Close());
        frame.setVisible(true);
        centerFrame(frame);
    }

    private static class AddClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JPanel j = new JPanel();
            Class c = new Class();
            j.setLayout(new GridLayout(1, 6));
            JTextField className = new JTextField(c.getClassName());
            classNames.add(className);
            JSpinner classCreds = new JSpinner(new SpinnerNumberModel(3, 0, 4, 1));
            classCredits.add(classCreds);
            JTextField grade = new JTextField(c.letterGrade() + " (" + c.numberGrade() + ")");
            grade.setEditable(false);
            classGrade.add(grade);
            JButton removeClass = new JButton("Delete");
            removeClass.addActionListener(new RemoveClassListener(c));
            removeButtons.add(removeClass);
            JButton viewClass = new JButton("View " + c.getClassName());
            viewClass.addActionListener(new ViewClassListener(c));
            viewButtons.add(viewClass);
            j.add(className);
            j.add(classCreds);
            j.add(grade);
            j.add(viewClass);
            j.add(removeClass);
            classes.add(c);
            frame.remove(addClass);
            frame.add(j);
            frame.add(addClass);
            updateFrame();
        }
    }

    private static class RemoveClassListener implements ActionListener {
        private static Class aClass;

        public RemoveClassListener(Class i) {
            aClass = i;
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("REMOVING " + aClass.toString() + "BUTTON " + e.getActionCommand());
            int index = classes.indexOf(aClass);
            frame.remove(addClass);
            frame.remove(classNames.get(index));
            frame.remove(classCredits.get(index));
            frame.remove(classGrade.get(index));
            frame.remove(viewButtons.get(index));
            frame.remove(removeButtons.get(index));
            frame.add(addClass);
            classes.remove(aClass);
            updateFrame();
        }
    }

    private static class ViewClassListener implements ActionListener {
        private static Class aClass;

        public ViewClassListener(Class i) {
            aClass = i;
        }

        public void actionPerformed(ActionEvent e) {
            System.out.println("VIEWING " + aClass + "BUTTON " + e.getActionCommand());
            frame.setContentPane(new ClassView(aClass));
            frame.update(frame.getGraphics());
        }
    }

    private static class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setContentPane(new Gradebook());
            updateFrame();
        }
    }

    private static void centerFrame(JFrame f) {
        f.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
    }

    private static void updateFrame() {
        frame.setSize(frame.getWidth() + 1, frame.getHeight());
        frame.setSize(frame.getWidth() - 1, frame.getHeight());
    }

    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            for(int i = 0; i < classes.size(); i++) {
                classes.get(i).setClassName(classNames.get(i).getText());
                classes.get(i).setCredits((int) classCredits.get(i).getValue());
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                System.out.println("OUTPUT");
                for(Class c: classes) {
                    System.out.println(c.toString());
                    bw.write(c.toString());
                }
                bw.close();
            } catch(FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
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
}