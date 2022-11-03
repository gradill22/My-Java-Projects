package PitchCharting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;


public class Main extends JPanel {
    private static JFrame frame, abResult, selectNewPitcher;
    private static JTextArea pitch;
    private static JLabel inningLabel, countLabel, outsLabel;
    private static JTextField newPitcherName, newPitcherNumber, newPitcherAge, newPitcherPitches;
    private static JButton enter, resultEnter, editPitcher, newPitcherCancel;
    private static JComboBox<String> pitchBox, pitcherSelection, atBatEnd, pathOfBall;
    private static JCheckBox first, second, third, lefty, foulBall, lastPitch;
    private static final String[] endOfAtBats = {"Foul Ball", "Single", "Double", "Triple", "Homerun", "Strikeout", "Out", "Double Play", "Triple Play", "Error", "HBP"};
    private static final String[] ballPath = {"None", "Groundball", "Line drive", "Flyball"};
    private static ArrayList<Pitcher> pitchersList;
    private static final ArrayList<Button> strikeZone = new ArrayList<>();
    private static final File pitchers = new File("Pitchers.txt");
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Pitcher currentPitcher;
    public static int inning = 1;
    public static int outs = 0;
    private static float inningStarted = 0;
    private static boolean inputPageShown = false;

    public static class InputPage extends JPanel {
        public InputPage() {
            setLayout(new GridLayout(7, 5));
            add(pitch = new JTextArea());
            pitch.setEditable(false);
            pitchBox = new JComboBox<>();
            if(pitchersList != null && pitchersList.size() > 0) {
                for(int i = 0; i < currentPitcher.getPitchArsenalCount(); i++) {
                    pitchBox.addItem(currentPitcher.getPitchType(i));
                }
            }
            JPanel inningsAndOuts = new JPanel(new GridLayout(2, 1));
            inningsAndOuts.add(inningLabel = new JLabel("Inning: " + inning));
            inningsAndOuts.add(outsLabel = new JLabel("Outs: " + outs));
            add(inningsAndOuts);
            add(countLabel = new JLabel("Count: " + currentPitcher.getCurrentAB().getCount()));
            JPanel bases = new JPanel(new GridLayout(4, 1));
            bases.add(new JLabel("Occupied bases:"));
            bases.add(first = new JCheckBox("First base"));
            bases.add(second = new JCheckBox("Second base"));
            bases.add(third = new JCheckBox("Third base"));
            add(bases);
            add(pitchBox);
            if(!inputPageShown) {
                for(int i = 0; i < 6; i++) {
                    Button ball = new Button("Ball");
                    ball.setBackground(Color.BLUE);
                    ball.setForeground(Color.WHITE);
                    ball.addActionListener(new AddPitchListener(strikeZone.size()));
                    strikeZone.add(ball);
                    add(ball);
                }
                for(int i = 0; i < 2; i++) {
                    for(int j = 0; j < 3; j++) {
                        Button strike = new Button("STRIKE");
                        strike.setBackground(Color.RED);
                        strike.addActionListener(new AddPitchListener(strikeZone.size()));
                        strikeZone.add(strike);
                        add(strike);
                    }
                    for(int j = 0; j < 2; j++) {
                        Button ball = new Button("Ball");
                        ball.setBackground(Color.BLUE);
                        ball.setForeground(Color.WHITE);
                        ball.addActionListener(new AddPitchListener(strikeZone.size()));
                        strikeZone.add(ball);
                        add(ball);
                    }
                }
                for(int i = 0; i < 3; i++) {
                    Button strike = new Button("STRIKE");
                    strike.setBackground(Color.RED);
                    strike.addActionListener(new AddPitchListener(strikeZone.size()));
                    strikeZone.add(strike);
                    add(strike);
                }
                for(int i = 0; i < 6; i++) {
                    Button ball = new Button("Ball");
                    ball.setBackground(Color.BLUE);
                    ball.setForeground(Color.WHITE);
                    ball.addActionListener(new AddPitchListener(strikeZone.size()));
                    strikeZone.add(ball);
                    add(ball);
                }
            } else {
                for(Button button : strikeZone) {
                    add(button);
                }
            }
            editPitcher = new JButton("Edit " + currentPitcher.getName());
            editPitcher.addActionListener(new EditPitcherListener());
            add(editPitcher);
            JButton changePitcher = new JButton("Change Pitcher");
            changePitcher.addActionListener(new ChangePitcherListener());
            add(changePitcher);
            JButton undo = new JButton("Undo");
            undo.addActionListener(new UndoPitchListener());
            add(undo);
            JButton export = new JButton("Export");
            export.addActionListener(new ExportListener());
            add(export);
            JPanel radioButtons = new JPanel();
            foulBall = new JCheckBox("Foul ball");
            lastPitch = new JCheckBox("Last pitch of at-bat");
            lefty = new JCheckBox("Lefty batter");
            radioButtons.add(foulBall);
            radioButtons.add(lastPitch);
            radioButtons.add(lefty);
            add(radioButtons);
            frame.setAlwaysOnTop(false);
            frame.setResizable(true);
            frame.setTitle(currentPitcher.getName());
            frame.setSize(750, 750);
            centerFrame(frame);
        }
    }

    private static class PitcherSelection extends JPanel {
        public PitcherSelection() {
            frame.setTitle("Select Pitcher");
            pitcherSelection = new JComboBox<>();
            add(new JLabel("Select pitcher:"));
            for(Pitcher pitcher : pitchersList) {
                pitcherSelection.addItem(pitcher.getName());
            }
            pitcherSelection.addItem("Add new pitcher");
            add(pitcherSelection);
            add(enter);
            frame.setSize(200, 150);
            frame.setResizable(false);
            centerFrame(frame);
        }
    }

    private static class NewPitcher extends JPanel {
        public NewPitcher() {
            frame.setTitle("Add New Pitcher");
            add(new JLabel("Name:"));
            add(newPitcherName = new JTextField(10));
            add(new JLabel("Number:"));
            add(newPitcherNumber = new JTextField(2));
            add(new JLabel("Age:"));
            add(newPitcherAge = new JTextField(2));
            add(new JLabel("Pitch Arsenal:"));
            add(newPitcherPitches = new JTextField(10));
            add(enter);
            if(inputPageShown) {
                newPitcherCancel = new JButton("Cancel");
                newPitcherCancel.addActionListener(new PitcherSelectionListener());
                add(newPitcherCancel);
            }
            frame.setSize(225, 150);
            frame.setResizable(false);
            centerFrame(frame);
            if(selectNewPitcher != null) {
                selectNewPitcher.setVisible(false);
            }
        }
    }

    private static class PitcherSelectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == enter) {
                if(newPitcherName != null && !newPitcherName.getText().isBlank() && isInteger(newPitcherNumber.getText()) && isInteger(newPitcherAge.getText()) && !newPitcherPitches.getText().isBlank()) {
                    String newName = newPitcherName.getText();
                    String newNumber = newPitcherNumber.getText();
                    String newAge = newPitcherAge.getText();
                    String newArsenal = newPitcherPitches.getText();
                    Pitcher newPitcher = Pitcher.buildPitcher(newName + "|" + newNumber + "|" + newAge + "|" + newArsenal);
                    pitchersList.add(newPitcher);
                    currentPitcher = newPitcher;
                    frame.setContentPane(new InputPage());
                    inputPageShown = true;
                } else if(pitcherSelection != null && pitcherSelection.getSelectedItem() == "Add new pitcher") {
                    frame.setContentPane(new NewPitcher());
                } else {
                    float currentInning = (inning - 1) + ((float) outs / 3);
                    if(currentPitcher != null) {
                        currentPitcher.setInningsPitched(currentInning);
                    }
                    if(pitcherSelection != null) {
                        currentPitcher = pitchersList.get(pitcherSelection.getSelectedIndex());
                    }
                    inningStarted = currentInning;
                    frame.setContentPane(new InputPage());
                    if(selectNewPitcher != null && selectNewPitcher.isShowing()) {
                        selectNewPitcher.setVisible(false);
                    }
                    inputPageShown = true;
                }
                frame.setSize(frame.getWidth(), frame.getHeight() + 1);
                frame.setSize(frame.getWidth(), frame.getHeight() - 1);
            } else if(e.getSource() == newPitcherCancel){
                frame.setContentPane(new InputPage());
            } else {
                if(selectNewPitcher != null && selectNewPitcher.isShowing()) {
                    selectNewPitcher.setVisible(false);
                }
            }
        }
    }

    private static class EditPitcher extends JPanel {
        public EditPitcher() {
            frame.setTitle("Edit " + currentPitcher.getName());
            add(new JLabel("Name:"));
            add(newPitcherName = new JTextField(10));
            add(new JLabel("Number:"));
            add(newPitcherNumber = new JTextField(2));
            add(new JLabel("Age:"));
            add(newPitcherAge = new JTextField(2));
            add(new JLabel("Pitch Arsenal:"));
            add(newPitcherPitches = new JTextField(10));
            newPitcherName.setText(currentPitcher.getName());
            newPitcherNumber.setText(String.valueOf(currentPitcher.getNumber()));
            newPitcherAge.setText(String.valueOf(currentPitcher.getAge()));
            String arsenal = String.valueOf(currentPitcher.getPitchArsenal());
            newPitcherPitches.setText(arsenal.substring(1, arsenal.length() - 1));
            JButton button = new JButton("Save");
            button.addActionListener(new EditPitcherListener());
            add(button);
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new EditPitcherListener());
            add(cancel);
            frame.setSize(225, 150);
            frame.setResizable(false);
            centerFrame(frame);
        }
    }

    private static class EditPitcherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == editPitcher) {
                frame.setContentPane(new EditPitcher());
            } else {
                if(newPitcherName != null && newPitcherName.isShowing() && isInteger(newPitcherNumber.getText()) && isInteger(newPitcherAge.getText())) {
                    currentPitcher.setName(newPitcherName.getText());
                    currentPitcher.setNumber(Integer.parseInt(newPitcherNumber.getText()));
                    currentPitcher.setAge(Integer.parseInt(newPitcherAge.getText()));
                    ArrayList<String> newPitches = new ArrayList<>(Arrays.asList(newPitcherPitches.getText().split(", ")));
                    currentPitcher.setArsenal(newPitches);
                }
                frame.setContentPane(new InputPage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        pitchersList = new ArrayList<>();
        if(pitchers.createNewFile()) {
            System.out.println("New pitchers file created!");
        }
        try {
            FileInputStream fis = new FileInputStream(pitchers);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while(line != null) {
                pitchersList.add(Pitcher.buildPitcher(line));
                line = br.readLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        enter = new JButton("Enter");
        enter.addActionListener(new PitcherSelectionListener());
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        if(pitchersList.size() > 0) {
            frame.setContentPane(new PitcherSelection());
        } else {
            frame.setContentPane(new NewPitcher());
        }
        frame.addWindowListener(new Close());
        frame.setVisible(true);
    }

    private static class UndoPitchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentPitcher.removeLastPitch();
            String pitchText = "No pitches thrown";
            if(currentPitcher.getLastPitch() != null) {
                Pitch currentLastPitch = currentPitcher.getLastPitch();
                lastPitch.setSelected(currentLastPitch.isLastPitch());
                foulBall.setSelected(currentLastPitch.isFoulBall());
                first.setSelected(currentLastPitch.firstBase());
                second.setSelected(currentLastPitch.secondBase());
                third.setSelected(currentLastPitch.thirdBase());
                lefty.setSelected(currentLastPitch.isLefty());
                String[] pitchSplit = currentLastPitch.toString(currentPitcher.getPitchArsenal().get(currentLastPitch.getPitchType())).split("for");
                pitchText = pitchSplit[0] + "\nfor" + pitchSplit[1];
                if(!currentPitcher.getCurrentAB().getResult().equals("No result")) {
                    String[] resultSplit = currentPitcher.getCurrentAB().getResult().split(", ");
                    pitchText += "\n\n" + resultSplit[0];
                    if(!resultSplit[1].equals("None")) {
                        pitchText += "\n" + resultSplit[1];
                    }
                }
            }
            pitch.setText(pitchText);
            inningLabel.setText("Inning: " + inning);
            countLabel.setText("Count: " + currentPitcher.getCurrentAB().getCount());
            outsLabel.setText("Outs: " + outs);
        }
    }

    private static class ChangePitcherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            selectNewPitcher = new JFrame("Select New Pitcher");
            selectNewPitcher.setLayout(new FlowLayout());
            selectNewPitcher.add(new JLabel("Select a new pitcher:"));
            pitcherSelection = new JComboBox<>();
            for(Pitcher p : pitchersList) {
                pitcherSelection.addItem(p.getName());
            }
            pitcherSelection.addItem("Add new pitcher");
            selectNewPitcher.add(pitcherSelection);
            selectNewPitcher.add(enter);
            JButton cancel = new JButton("Cancel");
            cancel.addActionListener(new PitcherSelectionListener());
            selectNewPitcher.add(cancel);
            selectNewPitcher.setSize(200, 150);
            centerFrame(selectNewPitcher);
            selectNewPitcher.setResizable(false);
            selectNewPitcher.setVisible(true);
        }
    }

    private static class AddPitchListener implements ActionListener {
        private final int index;
        public AddPitchListener(int i) {
            index = i;
        }

        public void actionPerformed(ActionEvent e) {
            boolean isStrike = (index >= 6 && index <= 8) || (index >= 11 && index <= 13) || (index >= 16 && index <= 18);
            if(lastPitch.isSelected() || (isStrike && countLabel.getText().contains("-2") && !foulBall.isSelected()) || (!isStrike && countLabel.getText().contains("3-"))) {
                getABResult(index);
            } else {
                currentPitcher.addPitch(new Pitch(index, first.isSelected(), second.isSelected(), third.isSelected(),
                        pitchBox.getSelectedIndex(), lefty.isSelected(), foulBall.isSelected(), lastPitch.isSelected()));
                String[] pitchSplit = currentPitcher.getLastPitch().toString(currentPitcher.getPitchType(pitchBox.getSelectedIndex())).split("for");
                pitch.setText(pitchSplit[0] + "\nfor" + pitchSplit[1]);
                lastPitch.setSelected(false);
                foulBall.setSelected(false);
                countLabel.setText("Count: " + currentPitcher.getCurrentAB().getCount());
                inningLabel.setText("Inning: " + inning);
                outsLabel.setText("Outs: " + outs);
                pitchBox.setSelectedIndex(0);
            }
        }
    }

    private static class ResultListener implements ActionListener {
        private final int index;
        public ResultListener(int i) {
            index = i;
        }

        public void actionPerformed(ActionEvent e) {
            boolean closable = false;
            if(e.getSource() == resultEnter) {
                boolean isStrike = (index >= 6 && index <= 8) || (index >= 11 && index <= 13) || (index >= 16 && index <= 18);
                boolean case1 = pathOfBall.getSelectedItem() == "None" && atBatEnd.getSelectedItem() == "HBP";
                boolean case2 = !(pathOfBall.getSelectedItem() == "None") && !(atBatEnd.getSelectedItem() == "HBP");
                boolean case3 = !isStrike && countLabel.getText().contains("3-");
                if((case1 || case2) && !case3) {
                    boolean lastPitchSelected = lastPitch.isSelected();
                    AtBat ab = currentPitcher.getCurrentAB();
                    String end = String.valueOf(atBatEnd.getSelectedItem());
                    switch (end) {
                        case "Foul Ball" -> {
                            pathOfBall.setSelectedIndex(0);
                            lastPitch.setSelected(false);
                            foulBall.setSelected(true);
                            closable = true;
                        }
                        case "Strikeout" -> {
                            pathOfBall.setSelectedIndex(0);
                            lastPitch.setSelected(true);
                            outs++;
                            ab.setResult(atBatEnd.getSelectedItem() + ", " + pathOfBall.getSelectedItem());
                            closable = true;
                        }
                        case "Error", "Single", "Double", "Triple", "Homerun" -> {
                            if(pathOfBall.getSelectedIndex() > 0) {
                                lastPitch.setSelected(true);
                                ab.setResult(atBatEnd.getSelectedItem() + ", " + pathOfBall.getSelectedItem());
                            }
                            closable = pathOfBall.getSelectedIndex() > 0;
                        }
                        case "Out" -> {
                            if(pathOfBall.getSelectedIndex() > 0) {
                                outs++;
                                lastPitch.setSelected(true);
                                ab.setResult(atBatEnd.getSelectedItem() + ", " + pathOfBall.getSelectedItem());
                            }
                            closable = pathOfBall.getSelectedIndex() > 0;
                        }
                        case "Double play" -> {
                            if(pathOfBall.getSelectedIndex() > 0) {
                                outs += 2;
                                lastPitch.setSelected(true);
                                ab.setResult(atBatEnd.getSelectedItem() + ", " + pathOfBall.getSelectedItem());
                            }
                            closable = pathOfBall.getSelectedIndex() > 0;
                        }
                        case "Triple play" -> {
                            if(pathOfBall.getSelectedIndex() > 0) {
                                outs += 3;
                                lastPitch.setSelected(true);
                                ab.setResult(atBatEnd.getSelectedItem() + ", " + pathOfBall.getSelectedItem());
                            }
                            closable = pathOfBall.getSelectedIndex() > 0;
                        }
                        case "HBP" -> {
                            if(pathOfBall.getSelectedIndex() == 0) {
                                lastPitch.setSelected(true);
                                ab.setResult(atBatEnd.getSelectedItem() + ", " + pathOfBall.getSelectedItem());
                            }
                            closable = pathOfBall.getSelectedIndex() == 0;
                        }
                    }
                    currentPitcher.addPitch(new Pitch(index, first.isSelected(), second.isSelected(), third.isSelected(),
                            pitchBox.getSelectedIndex(), lefty.isSelected(), foulBall.isSelected(), lastPitch.isSelected()));
                    String[] pitchSplit = currentPitcher.getLastPitch().toString(currentPitcher.getPitchType(pitchBox.getSelectedIndex())).split("for");
                    String pitchText = pitchSplit[0] + "\nfor" + pitchSplit[1];
                    if(!ab.getResult().isBlank() && !end.equals("Foul Ball")) {
                        String[] resultSplit = ab.getResult().split(", ");
                        pitchText +=  "\n\n" + resultSplit[0];
                        if(!resultSplit[1].equals("None")) {
                            pitchText += "\n" + resultSplit[1];
                        }
                    } else if(end.equals("Foul Ball")) {
                        pitchText += "\n\nFoul Ball";
                    }
                    pitch.setText(pitchText);
                    if(outs == 3) {
                        inning++;
                        outs = 0;
                        first.setSelected(false);
                        second.setSelected(false);
                        third.setSelected(false);
                    }
                    if(lastPitchSelected) {
                        atBatEnd.removeAllItems();
                        for(String s : endOfAtBats) {
                            atBatEnd.addItem(s);
                        }
                    }
                } else if(case3) {
                    lastPitch.setSelected(true);
                    currentPitcher.getCurrentAB().setResult("Walk, None");
                    currentPitcher.addPitch(new Pitch(index, first.isSelected(), second.isSelected(), third.isSelected(),
                            pitchBox.getSelectedIndex(), lefty.isSelected(), foulBall.isSelected(), lastPitch.isSelected()));
                    String[] pitchSplit = currentPitcher.getLastPitch().toString(currentPitcher.getPitchType(pitchBox.getSelectedIndex())).split("for");
                    pitch.setText(pitchSplit[0] + "\nfor" + pitchSplit[1] + "\n\n" + currentPitcher.getLastAtBat().getResult().split(", ")[0]);
                    if(first.isSelected()) {
                        if(second.isSelected()) {
                            third.setSelected(true);
                        } else {
                            second.setSelected(true);
                        }
                    } else {
                        first.setSelected(true);
                    }
                }
            } else {
                closable = true;
            }
            if(lastPitch.isSelected()) {
                lefty.setSelected(false);
            }
            lastPitch.setSelected(false);
            foulBall.setSelected(false);
            countLabel.setText("Count: " + currentPitcher.getCurrentAB().getCount());
            inningLabel.setText("Inning: " + inning);
            outsLabel.setText("Outs: " + outs);
            pitchBox.setSelectedIndex(0);
            abResult.setVisible(!closable);
        }
    }

    private static void getABResult(int index) {
        boolean isStrike = (index >= 6 && index <= 8) || (index >= 11 && index <= 13) || (index >= 16 && index <= 18);
        abResult = new JFrame("Result of At-Bat");
        abResult.setLayout(new FlowLayout());
        atBatEnd = new JComboBox<>();
        pathOfBall = new JComboBox<>();
        for(String s : endOfAtBats) {
            atBatEnd.addItem(s);
        }
        for(String s : ballPath) {
            pathOfBall.addItem(s);
        }
        if(lastPitch.isSelected()) {
            atBatEnd.removeItem("Foul Ball");
        }
        resultEnter = new JButton("Enter");
        resultEnter.addActionListener(new ResultListener(index));
        JButton resultCancel = new JButton("Cancel");
        resultCancel.addActionListener(new ResultListener(index));
        abResult.add(new JLabel("Result of at-bat:"));
        abResult.add(atBatEnd);
        abResult.add(new JLabel("Ball path:"));
        abResult.add(pathOfBall);
        abResult.add(resultEnter);
        abResult.add(resultCancel);
        abResult.setSize(225, 150);
        abResult.setResizable(false);
        if(countLabel.getText().contains("3-") && !isStrike) {
            resultEnter.doClick();
        } else {
            abResult.setVisible(true);
            centerFrame(abResult);
        }
    }

    private static class ExportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            currentPitcher.setInningsPitched(((inning - 1) + ((float) outs / 3)) - inningStarted);
            try {
                File file = new File(currentPitcher.getName() + ", " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, LLL d, yyyy")) + ".txt");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                bw.write(currentPitcher.export());
                bw.close();
                Desktop.getDesktop().open(file);
            } catch(Exception f) {
                f.printStackTrace();
            }
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void centerFrame(JFrame f) {
        f.setLocation((screenSize.width / 2) - (frame.getWidth() / 2), (screenSize.height / 2) - (frame.getHeight() / 2));
    }

    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            if(currentPitcher != null) {
                currentPitcher.setInningsPitched(((inning - 1) + ((float) outs / 3)) - inningStarted);
            }
            try {
                FileOutputStream fos = new FileOutputStream(pitchers);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                for(Pitcher pitcher : pitchersList) {
                    bw.write(pitcher.toString() + "\n");
                }
                bw.close();
                for(Pitcher p : pitchersList) {
                    if(p.getAtBats().size() > 0 && p.getAtBats().get(0).getPitches().size() > 0) {
                        File file = new File(currentPitcher.getName() + ", " + LocalDate.now().format(DateTimeFormatter.ofPattern("EEE, LLL d, yyyy")) + ".txt");
                        FileOutputStream newFos = new FileOutputStream(file);
                        BufferedWriter newBw = new BufferedWriter(new OutputStreamWriter(newFos));
                        newBw.write(p.export());
                        newBw.close();
                        Desktop.getDesktop().open(file);
                    }
                }
            } catch(IOException ex) {
                ex.printStackTrace();
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