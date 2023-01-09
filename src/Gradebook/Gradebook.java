package Gradebook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

public class Gradebook extends JPanel
{
    private static JFrame frame, newClassInfo, newGradeType;
    private static JPanel addOrRemovePanel;
    private static JTextField courseName, courseCredits, gradeTypeName, gradeTypeWeight;
    private static JButton addClass, addGrade, back, viewEnter, editEnter, gradeTypeEnter;
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
        float gpaScore = 0;
        float totalHours = 0;
        for(Class c : classes) {
            if(c.getGrades().size() > 0) {
                gpaScore += c.GPAScore() * c.getCredits();
                totalHours += c.getCredits();
            }
        }
        GPA = new JLabel("Current GPA: %.3f".formatted(gpaScore / totalHours));
        add(GPA);
    }

    private static class GradeView extends JPanel {
        public GradeView() {
            Class c = classes.get(currentClassIndex);
            frame.setTitle(c.getClassName());
            setLayout(new GridLayout(15, 6));
            JTextField gradeName, score, outOf, maxScore;
            JButton remove;
            JComboBox<String> gradeType;
            for(int i = 0; i < c.getGrades().size(); i++) {
                JPanel j = new JPanel();
                j.setLayout(new GridLayout(1, 6));
                gradeName = new JTextField(c.getGrade(i).getName());
                gradeType = new JComboBox<>(c.getGradeTypes());
                gradeType.setSelectedItem(c.getGrade(i).getGradeType());
                score = new JTextField(String.valueOf(c.getGrade(i).getPoints()));
                outOf = new JTextField("out of");
                outOf.setEditable(false);
                maxScore = new JTextField(String.valueOf(c.getGrade(i).getMaxPoints()));
                remove = new JButton("Delete");
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
                add(j);
            }
            if(c.getGradeTypes().length > 0) {
                addGrade = new JButton("Add Grade");
                addGrade.addActionListener(new AddGradeListener());
                add(addGrade);
            }
            JButton addGradeType = new JButton("Add Grade Type");
            addGradeType.addActionListener(new AddGradeTypeListener());
            if(c.getGradeTypes().length > 0) {
                addOrRemovePanel = new JPanel();
                addOrRemovePanel.setLayout(new GridLayout(1, 2));
                JButton removeGradeType = new JButton("Remove Grade Type");
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

    private static void readData(String[] lines) {
        int numClasses = Integer.parseInt(lines[0]);
        IndexStream lineIndex = new IndexStream();
        String[] nameAndCredit, gradeTypeNames, gradeTypeInfo, gradeInfo;
        GradeType[] gradeTypes;
        Grade[] grades, newGrades;
        int index, numGradeTypes, numGrades;
        String readGrade;
        Grade g;
        while(numClasses > 0) {
            nameAndCredit = lines[lineIndex.next()].split("\\|");
            numGradeTypes = Integer.parseInt(lines[lineIndex.next()]);
            gradeTypes = new GradeType[numGradeTypes];
            gradeTypeNames = new String[numGradeTypes];
            for (int i = 0; i < numGradeTypes; i++) {
                gradeTypeInfo = lines[lineIndex.next()].split("/");
                gradeTypes[i] = new GradeType(gradeTypeInfo[0], Float.parseFloat(gradeTypeInfo[1]));
                gradeTypeNames[i] = gradeTypeInfo[0];
            }
            numGrades = Integer.parseInt(lines[lineIndex.next()]);
            grades = new Grade[numGrades];
            for(int j = 0; j < numGrades; j++) {
                readGrade = lines[lineIndex.next()];
                gradeInfo = readGrade.split("/");
                index = listIndex(gradeTypeNames, gradeInfo[0]);
                g = new Grade(gradeTypes[index].getGradeType(), gradeTypes[index].getWeight(), readGrade);
                newGrades = new Grade[grades.length + 1];
                System.arraycopy(grades, 0, newGrades, 0, grades.length);
                newGrades[newGrades.length - 1] = g;
                grades = newGrades;
            }

            Class c = new Class(grades, gradeTypes, nameAndCredit[0], Integer.parseInt(nameAndCredit[1]));
            numClasses--;

            JPanel j = new JPanel();
            j.setLayout(new GridLayout(1, 6));

            JTextField className = new JTextField(c.getClassName());
            classNames.add(className);
            className.setEditable(false);
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
            classes.add(c);
        }
    }

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
            readData(lines);
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
            newClassGUI(-1);
        }
    }

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

    private static class ViewClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentClassIndex = viewButtons.indexOf((JButton) e.getSource());
            frame.setContentPane(new GradeView());
            updateFrame();
        }
    }

    private static class EditClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentClassIndex = editButtons.indexOf((JButton) e.getSource());
            newClassGUI(currentClassIndex);
        }
    }

    private static class RemoveClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = removeButtons.indexOf((JButton) e.getSource());
            frame.remove(addClass);
            frame.remove(GPA);
            classNames.remove(index);
            classCredits.remove(index);
            classGrade.remove(index);
            viewButtons.remove(index);
            editButtons.remove(index);
            removeButtons.remove(index);
            frame.remove(classPanels.get(index));
            classPanels.remove(index);
            frame.add(addClass);
            frame.add(GPA);
            classes.remove(index);
            updateFrame();
        }
    }

    private static void newClassGUI(int classIndex) {
        String name = classIndex < 0 ? "New Class" : classNames.get(classIndex).getText();
        String credits = classIndex < 0 ? "3" : classCredits.get(classIndex).getText();
        newClassInfo = new JFrame("Add New Class");
        newClassInfo.setLayout(new FlowLayout());
        JLabel info = new JLabel(classIndex >= 0 ? "Edit your course information" : "Enter the following to create your new class");
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
        newClassInfo.add(classIndex == -1 ? viewEnter : editEnter);
        newClassInfo.add(cancel);
        newClassInfo.setAlwaysOnTop(true);
        newClassInfo.setSize(classIndex < 0 ? 280 : 250, classIndex < 0 ? 125 : 160);
        newClassInfo.setResizable(false);
        centerFrame(newClassInfo);
        newClassInfo.setVisible(true);
    }

    private static class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < classes.get(currentClassIndex).getGrades().size(); i++) {
                classes.get(currentClassIndex).getGrade(i).setName(gradeNames.get(i).getText());
                classes.get(currentClassIndex).getGrade(i).setGradeType(String.valueOf(gradeTypes.get(i).getSelectedItem()));
                classes.get(currentClassIndex).getGrade(i).setPoints(Float.parseFloat(gradeScores.get(i).getText()));
                classes.get(currentClassIndex).getGrade(i).setMaxPoints(Float.parseFloat(gradeMaxScores.get(i).getText()));
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
            frame.setContentPane(new Gradebook());
            updateFrame();
        }
    }

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

    private static class RemoveGradeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index = gradeRemoveButtons.indexOf((JButton) e.getSource());
            frame.remove(gradeNames.get(index));
            gradeNames.remove(index);
            frame.remove(gradeTypes.get(index));
            gradeTypes.remove(index);
            frame.remove(gradeScores.get(index));
            gradeScores.remove(index);
            frame.remove(gradeMaxScores.get(index));
            gradeMaxScores.remove(index);
            frame.remove(gradeRemoveButtons.get(index));
            gradeRemoveButtons.remove(index);
            frame.remove(gradePanels.get(index));
            gradePanels.remove(index);
            classes.get(currentClassIndex).getGrades().remove(index);
            updateFrame();
        }
    }

    private static class AddGradeTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gradeTypeGUI(1);
        }
    }

    private static class RemoveGradeTypeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gradeTypeGUI(2);
        }
    }

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
        cancel.addActionListener(setting == 1 ? new AddGradeTypeGUIListener() : new RemoveGradeTypeGUIListener());
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

    private static class AddGradeTypeGUIListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == gradeTypeEnter) {
                GradeType newGradeType = new GradeType(gradeTypeName.getText(), Float.parseFloat(gradeTypeWeight.getText()));
                for(JComboBox<String> gradeType : gradeTypes) {
                    gradeType.addItem(newGradeType.getGradeType());
                }
                classes.get(currentClassIndex).addGradeType(newGradeType);
            }
            newGradeType.setVisible(false);
            updateFrame();
        }
    }

    private static class RemoveGradeTypeGUIListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == gradeTypeEnter) {
                String element = String.valueOf(tempGradeType.getSelectedItem());
                for(JComboBox<String> gradeType : gradeTypes) {
                    gradeType.removeItem(element);
                }
                classes.get(currentClassIndex).removeGradeType(element);
                tempGradeType = null;
            }
            newGradeType.setVisible(false);
            updateFrame();
        }
    }

    private static void centerFrame(JFrame f) {
        f.setLocation((screenSize.width / 2) - (f.getWidth() / 2), (screenSize.height / 2) - (f.getHeight() / 2));
    }

    private static void updateFrame() {
        frame.update(frame.getGraphics());
        frame.setSize(frame.getWidth() + 1, frame.getHeight() + 1);
        frame.setSize(frame.getWidth() - 1, frame.getHeight() - 1);
        centerFrame(frame);
    }

    private static int listIndex(String[] list, String str) {
        for(int i = 0; i < list.length; i++) {
            if(str.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    private static void removeAll(ArrayList<JTextField> list) {
        while(list.size() > 0) {
            list.remove(0);
        }
    }

    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
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