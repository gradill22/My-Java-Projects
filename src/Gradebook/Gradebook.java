package Gradebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Gradebook extends JPanel
{
    // all instance variables for the gradebook
    private static JFrame frame, newClassInfo, newGradeType;
    private static JPanel addOrRemovePanel;
    private static JTextField courseName, courseCredits, gradeTypeName, gradeTypeWeight;
    private static JButton addClass, addGrade, back, viewEnter, editEnter, gradeTypeEnter, addGradeType, removeGradeType;
    private static JComboBox<String> tempGradeType;
    private static JLabel GPA;
    private static final ArrayList<Class> classes = new ArrayList<>();
    private static final ArrayList<JPanel> classPanels = new ArrayList<>();
    private static final ArrayList<JTextField> classNames = new ArrayList<>();
    private static final ArrayList<JTextField> classCredits = new ArrayList<>();
    private static final ArrayList<JTextField> classGrade = new ArrayList<>();
    private static final ArrayList<JPanel> gradePanels = new ArrayList<>();
    private static final ArrayList<JTextField> gradeNames = new ArrayList<>();
    private static final ArrayList<JComboBox<String>> gradeTypes = new ArrayList<>();
    private static final ArrayList<JTextField> gradeScores = new ArrayList<>();
    private static final ArrayList<JTextField> gradeMaxScores = new ArrayList<>();
    private static final ArrayList<JButton> gradeRemoveButtons = new ArrayList<>();
    private static final ArrayList<JButton> viewButtons = new ArrayList<>();
    private static final ArrayList<JButton> editButtons = new ArrayList<>();
    private static final ArrayList<JButton> removeButtons = new ArrayList<>();
    private static final File file = new File("gradebook.txt");
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int currentClassIndex;

    /*
    This is the main function of the program. It utilizes java swing's GUI features to take user input and display
    its output. It shows individual grades and overall unweighted GPA.
    */
    public static void main(String[] args) {
        frame = new JFrame("Gradebook");
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String[] lines = new String[0];
            String line = br.readLine();
            while(line != null) {
                String[] newLines = new String[lines.length + 1];
                System.arraycopy(lines, 0, newLines, 0, lines.length);
                newLines[newLines.length - 1] = line;
                lines = newLines;
                line = br.readLine();
            }
            parseData(lines);
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

    /*
    This function parses all the data from the text file database (gradebook.txt). This comes from the data saved
    after every use of the program. On program closing, it writes to the gradebook.txt all the classes, gradeTypes,
    and grades. Refer to windowClosing() in private static class Close.
    */
    private static void parseData(String[] lines) {
        if(lines.length == 0) {  // if the file is fresh, there aren't any classes, so stop
            return;
        }
        // all function variables for parsing data from program text file (gradebook.txt)
        int numClasses = Integer.parseInt(lines[0]);  // first line is always the number of classes
        IndexStream lineIndex = new IndexStream();  // personal stream to increment the index with every call
        String[] nameAndCredit, gradeTypeNames, gradeTypeInfo, gradeInfo;  // all class data necessary to create new class
        GradeType[] gradeTypes;
        Grade[] grades, newGrades;
        int index, numGradeTypes, numGrades;
        String readGrade;
        Grade g;
        while(numClasses > 0) {
            nameAndCredit = lines[lineIndex.next()].split("\\|"); // name and number of credits for a class
            numGradeTypes = Integer.parseInt(lines[lineIndex.next()]);  // the number of gradeTypes in the class
            gradeTypes = new GradeType[numGradeTypes];  // this list will be passed into the new Class object
            gradeTypeNames = new String[numGradeTypes]; // this list will be passed into the new Class object
            for(int i = 0; i < numGradeTypes; i++) {
                gradeTypeInfo = lines[lineIndex.next()].split("/");
                gradeTypes[i] = new GradeType(gradeTypeInfo[0], Float.parseFloat(gradeTypeInfo[1]));
                gradeTypeNames[i] = gradeTypeInfo[0];
            }
            numGrades = Integer.parseInt(lines[lineIndex.next()]); // the number of grades in the class
            grades = new Grade[numGrades];  // this list will be passed into the new Class object
            for(int i = 0; i < numGrades; i++) {
                readGrade = lines[lineIndex.next()];  // the grade info (name, gradeType, points, maxPoints)
                gradeInfo = readGrade.split("/");  // splits all info into individual pieces
                index = listIndex(gradeTypeNames, gradeInfo[0]);  // used to get gradeType info from single gradeType
                                                                 // at a specified index (refer to next line)
                g = new Grade(gradeTypes[index].getGradeType(), gradeTypes[index].getWeight(), readGrade);
                newGrades = new Grade[grades.length + 1];  // list to append the new grade to the end of the list
                System.arraycopy(grades, 0, newGrades, 0, grades.length);  // copy arrays
                newGrades[newGrades.length - 1] = g;  // add new grade to the end
                grades = newGrades;  // set the original list to the new one with the appended grade
            }
            // creates all the panels necessary for the GUI
            newClassPanels(new Class(grades, gradeTypes, nameAndCredit[0], Integer.parseInt(nameAndCredit[1])));
            numClasses--;
        }
    }

    /*
    This creates all the panels, fields, and buttons necessary for the GUI. It allows the program to keep track of all
    the user data based off of their input to display their class grades and unweighted GPA.
    */
    private static void newClassPanels(Class c) {
        JPanel j = new JPanel();
        j.setLayout(new GridLayout(1, 6));

        JTextField className = new JTextField(c.getClassName());
        className.setEditable(false);
        classNames.add(className);
        j.add(className);

        JTextField classCredit = new JTextField(String.valueOf(c.getCredits()));
        classCredit.setEditable(false);
        classCredits.add(classCredit);
        j.add(classCredit);

        JTextField grade = new JTextField(c.formattedGrade());
        grade.setEditable(false);
        classGrade.add(grade);
        j.add(grade);

        JButton viewClass = new JButton("View");
        viewClass.addActionListener(new ViewClassListener());
        viewButtons.add(viewClass);
        j.add(viewClass);

        JButton editClass = new JButton("Edit");
        editClass.addActionListener(new EditClassListener());
        editButtons.add(editClass);
        j.add(editClass);

        JButton removeClass = new JButton("Delete");
        removeClass.addActionListener(new RemoveClassListener());
        removeButtons.add(removeClass);
        j.add(removeClass);

        classPanels.add(j);
        classes.add(c);  // adds the newly created Class to the class list
    }

    // default constructor for the JFrame Gradebook
    public Gradebook() {
        frame.setTitle("Gradebook");
        setLayout(new GridLayout(9, 5));
        for(int i = 0; i < classes.size(); i++) {
            Class c = classes.get(i);
            classGrade.get(i).setText(c.formattedGrade());
            add(classPanels.get(i));
        }
        addClass = new JButton("Add Class");
        addClass.addActionListener(new AddClassListener());
        add(addClass);
        GPA = new JLabel(GPAScoreString());
        add(GPA);
    }

    /*
    The GUI display for viewing grades in a class. It shows all individual grades and allows the user to add and remove
    gradeTypes. The user can edit and remove grades as well. All panels and buttons are stored in their unique lists
    */
    private static class GradeView extends JPanel {
        public GradeView() {
            Class c = classes.get(currentClassIndex);
            frame.setTitle(c.getClassName());
            setLayout(new GridLayout(15, 6));
            for(int i = 0; i < c.getGrades().size(); i++) {
                add(newGradePanel(c, c.getGrade(i)));
            }
            if(c.getGradeTypes().length > 0) {
                addGrade = new JButton("Add Grade");
                addGrade.addActionListener(new AddGradeListener());
                add(addGrade);
            }
            addGradeType = new JButton("Add Grade Type");
            addGradeType.addActionListener(new AddGradeTypeListener());
            if(c.getGradeTypes().length > 0) {
                addOrRemovePanel = new JPanel();
                addOrRemovePanel.setLayout(new GridLayout(1, 2));
                removeGradeType = new JButton("Remove Grade Type");
                removeGradeType.addActionListener(new RemoveGradeTypeListener());
                addOrRemovePanel.add(addGradeType);
                addOrRemovePanel.add(removeGradeType);
                add(addOrRemovePanel);
            } else {
                add(addGradeType);
            }
            back = new JButton("Back");
            back.addActionListener(new BackListener());
            add(back);
            updateFrame();
        }
    }

    /*
    This creates the JPanel interface for the grade to be displayed. It includes the grades name, type, score, and
    maxScore. The option to delete is added at the end with a JButton.
    */
    private static JPanel newGradePanel(Class c, Grade g) {
        JPanel j = new JPanel();
        j.setLayout(new GridLayout(1, 6));
        JTextField gradeName = new JTextField(g.getName());
        JComboBox<String> gradeType = new JComboBox<>(c.getGradeTypes());
        gradeType.setSelectedItem(g.getGradeType());
        JTextField score = new JTextField(String.valueOf(g.getPoints()));
        JTextField outOf = new JTextField("out of");
        outOf.setEditable(false);
        JTextField maxScore = new JTextField(String.valueOf(g.getMaxPoints()));
        JButton remove = new JButton("Delete");
        remove.addActionListener(new RemoveGradeListener());
        j.add(gradeName);
        j.add(gradeType);
        j.add(score);
        j.add(outOf);
        j.add(maxScore);
        j.add(remove);
        gradeNames.add(gradeName);
        gradeTypes.add(gradeType);
        gradeScores.add(score);
        gradeMaxScores.add(maxScore);
        gradeRemoveButtons.add(remove);
        gradePanels.add(j);
        return j;
    }

    // This is the button listener that allows the user to add a new class
    private static class AddClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            newClassGUI(-1);
        }
    }

    /*
    This is the secondary GUI listener that prompts a user to either enter a new class or to edit a current class. The
    option depends on the source of the action.
    */
    private static class NewClassGUIListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == viewEnter) {
                Class c = new Class(courseName.getText(), Integer.parseInt(courseCredits.getText()));
                classes.add(c);
                JPanel j = new JPanel();
                j.setLayout(new GridLayout(1, 6));
                JTextField className = new JTextField(c.getClassName());
                className.setEditable(false);
                classNames.add(className);
                JTextField courseCredits = new JTextField(String.valueOf(c.getCredits()));
                courseCredits.setEditable(false);
                classCredits.add(courseCredits);
                JTextField grade = new JTextField(c.formattedGrade());
                grade.setEditable(false);
                classGrade.add(grade);
                JButton viewClass = new JButton("View");
                viewClass.addActionListener(new ViewClassListener());
                viewButtons.add(viewClass);
                JButton editClass = new JButton("Edit");
                editClass.addActionListener(new EditClassListener());
                editButtons.add(editClass);
                JButton removeClass = new JButton("Delete");
                removeClass.addActionListener(new RemoveClassListener());
                removeButtons.add(removeClass);
                j.add(className);
                j.add(courseCredits);
                j.add(grade);
                j.add(viewClass);
                j.add(editClass);
                j.add(removeClass);
                classPanels.add(j);
                frame.remove(addClass);
                frame.remove(GPA);
                frame.add(j);
                frame.add(addClass);
                frame.add(GPA);
                updateFrame();
            } else if(e.getSource() == editEnter) {
                classes.get(currentClassIndex).setClassName(courseName.getText());
                classes.get(currentClassIndex).setCredits(Integer.parseInt(courseCredits.getText()));
                classNames.get(currentClassIndex).setText(classes.get(currentClassIndex).getClassName());
                classCredits.get(currentClassIndex).setText(String.valueOf(classes.get(currentClassIndex).getCredits()));
                updateFrame();
            }
            newClassInfo.setVisible(false);
        }
    }

    // This is the listener that allows a user to view the grades in a class.
    private static class ViewClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentClassIndex = viewButtons.indexOf((JButton) e.getSource());
            frame.setContentPane(new GradeView());
            updateFrame();
        }
    }

    // This is the listener that allows a user to edit a current class' information (name and credits)
    private static class EditClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentClassIndex = editButtons.indexOf((JButton) e.getSource());
            newClassGUI(currentClassIndex);
        }
    }

    // This is the listener that allows a user to delete a certain class
    private static class RemoveClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int classIndex = removeButtons.indexOf((JButton) e.getSource());
            frame.remove(addClass);
            frame.remove(GPA);
            classNames.remove(classIndex);
            classCredits.remove(classIndex);
            classGrade.remove(classIndex);
            viewButtons.remove(classIndex);
            editButtons.remove(classIndex);
            removeButtons.remove(classIndex);
            frame.remove(classPanels.get(classIndex));
            classPanels.remove(classIndex);
            frame.add(addClass);
            frame.add(GPA);
            classes.remove(classIndex);
            GPA.setText(GPAScoreString());
            updateFrame();
        }
    }

    // This displays the secondary GUI that allows the user to create a new class or edit a current one.
    private static void newClassGUI(int classIndex) {
        String name = classIndex < 0 ? "New Class" : classNames.get(classIndex).getText();
        String credits = classIndex < 0 ? "3" : classCredits.get(classIndex).getText();
        newClassInfo = new JFrame("Add New Class");
        newClassInfo.setLayout(new FlowLayout());
        JLabel info = new JLabel(classIndex < 0 ? "Enter the following to create your new class" : "Edit your course information");
        JLabel className = new JLabel("Course name:");
        courseName = new JTextField(name, 12);
        JLabel classCredits = new JLabel("Credits:");
        courseCredits = new JTextField(credits, 5);
        viewEnter = new JButton("Enter");
        viewEnter.addActionListener(new NewClassGUIListener());
        editEnter = new JButton("Enter");
        editEnter.addActionListener(new NewClassGUIListener());
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(new NewClassGUIListener());
        newClassInfo.add(info);
        newClassInfo.add(className);
        newClassInfo.add(courseName);
        newClassInfo.add(classCredits);
        newClassInfo.add(courseCredits);
        newClassInfo.add(classIndex < 0 ? viewEnter : editEnter);
        newClassInfo.add(cancel);
        newClassInfo.setAlwaysOnTop(true);
        newClassInfo.setSize(classIndex < 0 ? 280 : 250, classIndex < 0 ? 125 : 160);
        newClassInfo.setResizable(false);
        centerFrame(newClassInfo);
        newClassInfo.setVisible(true);
    }

    // This is the listener that allows the user to go back to the main screen after viewing their grades in a class
    private static class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Class currentClass = classes.get(currentClassIndex);
            for(int i = 0; i < currentClass.getGrades().size(); i++) {  // saves all current grades
                currentClass.getGrade(i).setName(gradeNames.get(i).getText());
                currentClass.getGrade(i).setGradeType(currentClass.getGradeType(gradeTypes.get(i).getSelectedIndex()).getGradeType());
                currentClass.getGrade(i).setPoints(Float.parseFloat(gradeScores.get(i).getText()));
                currentClass.getGrade(i).setMaxPoints(Float.parseFloat(gradeMaxScores.get(i).getText()));
            }
            removeAll(gradeNames);
            while(gradeTypes.size() > 0) {
                gradeTypes.remove(0);
            }
            removeAll(gradeScores);
            removeAll(gradeMaxScores);
            while(gradeRemoveButtons.size() > 0) {
                gradeRemoveButtons.remove(0);
            }
            while(gradePanels.size() > 0) {
                gradePanels.remove(0);
            }
            frame.setContentPane(new Gradebook());  // sets the frame to the main screen
            updateFrame();
        }
    }

    // This is the listener that allows a user to add a new class grade
    private static class AddGradeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Class c = classes.get(currentClassIndex);
            Grade g = new Grade("New Grade", c.getGradeType(0).getGradeType(), c.getGradeType(0).getWeight());
            c.addGrade(g);
            JPanel j = new JPanel();
            j.setLayout(new GridLayout(1, 6));
            JTextField gradeName = new JTextField(g.getName());
            gradeNames.add(gradeName);
            JComboBox<String> gradeType = new JComboBox<>(classes.get(currentClassIndex).getGradeTypes());
            gradeTypes.add(gradeType);
            JTextField gradeScore = new JTextField(String.valueOf(g.getPoints()));
            gradeScores.add(gradeScore);
            JTextField outOf = new JTextField("out of");
            outOf.setEditable(false);
            JTextField gradeMaxScore = new JTextField(String.valueOf(g.getMaxPoints()));
            gradeMaxScores.add(gradeMaxScore);
            JButton removeGrade = new JButton("Delete");
            removeGrade.addActionListener(new RemoveGradeListener());
            gradeRemoveButtons.add(removeGrade);
            j.add(gradeName);
            j.add(gradeType);
            j.add(gradeScore);
            j.add(outOf);
            j.add(gradeMaxScore);
            j.add(removeGrade);
            gradePanels.add(j);
            frame.remove(addGrade);
            frame.remove(addOrRemovePanel);
            frame.remove(back);
            frame.add(j);
            frame.add(addGrade);
            frame.add(addOrRemovePanel);
            frame.add(back);
            updateFrame();
        }
    }

    // This is the listener that allows the user to remove a grade from a class
    private static class RemoveGradeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int gradeIndex = gradeRemoveButtons.indexOf((JButton) e.getSource());
            frame.remove(gradeNames.get(gradeIndex));
            gradeNames.remove(gradeIndex);
            frame.remove(gradeTypes.get(gradeIndex));
            gradeTypes.remove(gradeIndex);
            frame.remove(gradeScores.get(gradeIndex));
            gradeScores.remove(gradeIndex);
            frame.remove(gradeMaxScores.get(gradeIndex));
            gradeMaxScores.remove(gradeIndex);
            frame.remove(gradeRemoveButtons.get(gradeIndex));
            gradeRemoveButtons.remove(gradeIndex);
            frame.remove(gradePanels.get(gradeIndex));
            gradePanels.remove(gradeIndex);
            classes.get(currentClassIndex).getGrades().remove(gradeIndex);
            updateFrame();
        }
    }

    // This listener allows a user to add a new gradeType to a class
    private static class AddGradeTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gradeTypeGUI(1);
        }
    }

    // This listener allows a user to delete an existing gradeType in a class
    private static class RemoveGradeTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Remove the gradeType only if there is a gradeType to remove
            if(classes.get(currentClassIndex).getGradeTypes().length > 0) {
                gradeTypeGUI(2);
            }
        }
    }

    /* This is the addGradeTypeListener and removeGradeTypeListener helper method to display a secondary GUI for the
    user to add or remove a gradeType.
    */
    private static void gradeTypeGUI(int setting) {
        newGradeType = new JFrame(setting == 1 ? "Add New Grade Type" : "Remove Grade Type");
        newGradeType.setLayout(new FlowLayout());
        String addInfoSetting = "Please enter the following information for your new Grade Type";
        String removeInfoSetting = "Select the Grade Type you would like to remove";
        JLabel info = new JLabel(setting == 1 ? addInfoSetting : removeInfoSetting);
        JLabel gtn = new JLabel("Name:");
        gradeTypeName = new JTextField(10);
        JLabel gtw = new JLabel("Weight:");
        gradeTypeWeight = new JTextField(5);
        gradeTypeEnter = new JButton("Enter");
        gradeTypeEnter.addActionListener(setting == 1 ? new AddGradeTypeGUIListener() : new RemoveGradeTypeGUIListener());
        JButton cancel = new JButton("Cancel");
        cancel.addActionListener(gradeTypeEnter.getActionListeners()[0]);
        newGradeType.add(info);
        newGradeType.add(gtn);
        if(setting == 1) {
            newGradeType.add(gradeTypeName);
        } else {
            tempGradeType = new JComboBox<>(classes.get(currentClassIndex).getGradeTypes());
            newGradeType.add(tempGradeType);
        }
        if(setting == 1) {
            newGradeType.add(gtw);
            newGradeType.add(gradeTypeWeight);
        }
        newGradeType.add(gradeTypeEnter);
        newGradeType.add(cancel);
        newGradeType.setAlwaysOnTop(true);
        newGradeType.setSize(setting == 1 ? 380 : 275, 125);
        newGradeType.setResizable(false);
        centerFrame(newGradeType);
        newGradeType.setVisible(true);
    }

    // This is the listener for the secondary GUI that allows a user to input a new gradeType
    private static class AddGradeTypeGUIListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == gradeTypeEnter) {
                GradeType newGradeType = new GradeType(gradeTypeName.getText(), Float.parseFloat(gradeTypeWeight.getText()));
                for(JComboBox<String> gradeType : gradeTypes) {
                    gradeType.addItem(newGradeType.formattedGradeType());
                }
                classes.get(currentClassIndex).addGradeType(newGradeType);
                if(classes.get(currentClassIndex).getGradeTypes().length == 1) {
                    frame.remove(addGradeType);
                    frame.remove(back);
                    addOrRemovePanel = new JPanel(new GridLayout(1, 2));
                    removeGradeType = new JButton("Remove Grade Type");
                    removeGradeType.addActionListener(new RemoveGradeTypeListener());
                    addOrRemovePanel.add(addGradeType);
                    addOrRemovePanel.add(removeGradeType);
                    addGrade = new JButton("Add Grade");
                    addGrade.addActionListener(new AddGradeListener());
                    frame.add(addGrade);
                    frame.add(addOrRemovePanel);
                    back.addActionListener(new BackListener());
                    frame.add(back);
                }
            }
            newGradeType.setVisible(false);
            updateFrame();
        }
    }

    // This is the listener for the secondary GUI that allows a user to input the gradeType they want to remove
    private static class RemoveGradeTypeGUIListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == gradeTypeEnter) {
                String element = String.valueOf(tempGradeType.getSelectedItem());
                for(JComboBox<String> gradeType : gradeTypes) {
                    gradeType.removeItem(element);
                }
                classes.get(currentClassIndex).removeGradeType(tempGradeType.getSelectedIndex());
                tempGradeType = null;
                if(classes.get(currentClassIndex).getGradeTypes().length == 0) {
                    frame.remove(addGrade);
                    frame.remove(addOrRemovePanel);
                    addOrRemovePanel = null;
                    frame.remove(back);
                    frame.add(addGradeType);
                    frame.add(back);
                }
            }
            newGradeType.setVisible(false);
            updateFrame();
        }
    }

    // This centers the frame of a given frame to the center of a screen
    private static void centerFrame(JFrame f) {
        f.setLocation((screenSize.width / 2) - (f.getWidth() / 2), (screenSize.height / 2) - (f.getHeight() / 2));
    }

    // This updates the main frame's contents
    private static void updateFrame() {
        frame.update(frame.getGraphics());
        frame.setSize(frame.getWidth() + 1, frame.getHeight() + 1);
        frame.setSize(frame.getWidth() - 1, frame.getHeight() - 1);
        centerFrame(frame);
    }

    // Provides the list index in a given String list for String str
    private static int listIndex(String[] list, String str) {
        for(int i = 0; i < list.length; i++) {
            if(str.equals(list[i])) {
                return i;
            }
        }
        return -1;  // in case str isn't in list
    }

    // Removes all items from a JTextField ArrayList
    private static void removeAll(ArrayList<JTextField> list) {
        while(list.size() > 0) {
            list.remove(0);
        }
    }

    // Calculates the unweighted GPA of the user based off of class grades and credits
    private static String GPAScoreString() {
        float gpaScore = 0;
        float totalHours = 0;
        for(Class c : classes) {
            if(c.getGrades().size() > 0) {
                gpaScore += c.GPAScore() * c.getCredits();
                totalHours += c.getCredits();
            }
        }
        return String.format("Current GPA: %.3f", totalHours > 0 ? gpaScore / totalHours : 0);
    }

    // The frame listener that saves all the user's data once they close the program
    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {  // saves data to program text file (gradebook.txt)
            for(int i = 0; i < classes.size(); i++) {
                classes.get(i).setClassName(classNames.get(i).getText());
                classes.get(i).setCredits(Integer.parseInt(classCredits.get(i).getText()));
            }
            try {
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(classes.size() + "\n");
                for(Class c: classes) {
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