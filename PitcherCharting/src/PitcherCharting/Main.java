package PitcherCharting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Main extends JPanel {
    private static JFrame frame;
    private static ArrayList<Pitcher> pitchers;
    private static int lastAccessed = 0;
    private static JLabel playerName, playerNumber, playerAge, playerTeam, pitches, highOrLow, inOrOut, result, baseRunners;
    private static JTextField name, number, age, team, pitchList;
    private static JRadioButton high, low, inside, outside, strike, ball, first, second, third;
    private static JButton newPitcher, addPitch, exportPitchLog, getPitcher, savePitcher, trackPitches;
    private static JComboBox<String> pitchBox;

    public Main() {
        setLayout(new FlowLayout());
        high = new JRadioButton("High");
        high.addActionListener(new highListener());
        low = new JRadioButton("Low");
        low.addActionListener(new lowListener());
        inside = new JRadioButton("Inside");
        inside.addActionListener(new insideListener());
        outside = new JRadioButton("Outside");
        outside.addActionListener(new outsideListener());
        strike = new JRadioButton("Strike");
        strike.addActionListener(new strikeListener());
        ball = new JRadioButton("Ball");
        ball.addActionListener(new ballListener());
        pitchBox = new JComboBox<>();
        addPitch = new JButton("Add pitch");
        addPitch.addActionListener(new addPitchListener());
        exportPitchLog = new JButton("Export pitch log");
        exportPitchLog.addActionListener(new exportListener());
        newPitcher = new JButton("Add new pitcher");
        newPitcher.addActionListener(new newPitcherListener());
        getPitcher = new JButton("Search pitcher");
        getPitcher.addActionListener(new getPitcherListener());
        savePitcher = new JButton("Save pitcher");
        savePitcher.addActionListener(new savePitcherListener());
        trackPitches = new JButton("Track Pitches");
        trackPitches.addActionListener(new trackPitchesListener());
        first = new JRadioButton("First");
        second = new JRadioButton("Second");
        third = new JRadioButton("Third");
        highOrLow = new JLabel("High/Low:");
        inOrOut = new JLabel("Inside/Outside");
        result = new JLabel("Strike/Ball");
        baseRunners = new JLabel("Bases occupied");
        add(playerName = new JLabel("Pitcher name:"));
        add(name = new JTextField(10));
        add(playerNumber = new JLabel("Pitcher number:"));
        add(number = new JTextField(10));
        add(playerAge = new JLabel("Pitcher age:"));
        add(age = new JTextField(10));
        add(playerTeam = new JLabel("Pitcher team:"));
        add(team = new JTextField(10));
        add(pitches = new JLabel("Pitch types:"));
        add(pitchList = new JTextField(10));
        add(newPitcher);
    }

    public static void main(String[] args) {
        frame = new JFrame("Pitch Charting");
        frame.setSize(250, 205);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((screenSize.width / 2) - 125, (screenSize.height / 2) - 190);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Main());
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        pitchers = new ArrayList<>();
    }

    private class newPitcherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!name.getText().isBlank() && !number.getText().isBlank() && !age.getText().isBlank() && !team.getText().isBlank() && !pitchList.getText().isBlank()) {
                ArrayList<String> pitchTypes = new ArrayList<>();
                StringBuilder str = new StringBuilder();
                for(int i = 0; i < pitchList.getText().length(); i++) {
                    if(pitchList.getText().charAt(i) != ',' && pitchList.getText().charAt(i) != ' ') {
                        str.append(pitchList.getText().charAt(i));
                    } else {
                        pitchTypes.add(str.toString());
                        str = new StringBuilder();
                        i++;
                    }
                }
                pitchTypes.add(str.toString());
                pitchers.add(new Pitcher(name.getText(), Integer.parseInt(number.getText()), Integer.parseInt(age.getText()), team.getText(), pitchTypes));
                lastAccessed = pitchers.size() - 1;
                resetPage1();
                page2();
                frame.setTitle(pitchers.get(lastAccessed).getName());
            }
        }
    }

    private class getPitcherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(getPitcher.getText().equals("Options")) {
                page1();
                name.setText(pitchers.get(lastAccessed).getName());
                age.setText(String.valueOf(pitchers.get(lastAccessed).getAge()));
                number.setText(String.valueOf(pitchers.get(lastAccessed).getNumber()));
                team.setText(pitchers.get(lastAccessed).getTeam());
                pitchList.setText(pitchers.get(lastAccessed).pitchTypesString());
            } else if(getPitcher.getText().equals("Search Pitcher")) {
                if(!name.getText().isBlank()) {
                    for(Pitcher p : pitchers) {
                        if(p.getName().equalsIgnoreCase(name.getText())) {
                            name.setText(p.getName());
                            age.setText(String.valueOf(p.getAge()));
                            number.setText(String.valueOf(p.getNumber()));
                            team.setText(String.valueOf(p.getTeam()));
                            pitchList.setText(p.pitchTypesString());
                            lastAccessed = pitchers.indexOf(p);
                            frame.setTitle(pitchers.get(lastAccessed).getName());
                        }
                    }
                }
            }
        }
    }

    private class savePitcherListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            pitchers.get(lastAccessed).setName(name.getText());
            pitchers.get(lastAccessed).setNumber(Integer.parseInt(number.getText()));
            pitchers.get(lastAccessed).setAge(Integer.parseInt(age.getText()));
            pitchers.get(lastAccessed).setTeam(team.getText());
            ArrayList<String> pitchTypes = new ArrayList<>();
            StringBuilder str = new StringBuilder();
            for(int i = 0; i < pitchList.getText().length(); i++) {
                if(pitchList.getText().charAt(i) != ',') {
                    str.append(pitchList.getText().charAt(i));
                } else {
                    pitchTypes.add(str.toString());
                    str = new StringBuilder();
                    i++;
                }
            }
            pitchTypes.add(str.toString());
            pitchers.get(lastAccessed).getPitchTypesList().clear();
            pitchers.get(lastAccessed).getPitchTypesList().addAll(pitchTypes);
            frame.setTitle(pitchers.get(lastAccessed).getName());
            page2();
        }
    }

    private class addPitchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(ball.isSelected() || strike.isSelected() && !(ball.isSelected() && strike.isSelected())) {
                Pitch pitch = new Pitch();
                if(ball.isSelected()) {
                    pitch.setBallOrStrike(PitchResult.Ball);
                }
                if(low.isSelected()) {
                    pitch.setHighOrLow(PitchAltitude.Low);
                }
                else if(high.isSelected()) {
                    pitch.setHighOrLow(PitchAltitude.High);
                }
                if(outside.isSelected()) {
                    pitch.setInsideOrOutside(PitchLongitude.Outside);
                }
                else if(inside.isSelected()) {
                    pitch.setInsideOrOutside(PitchLongitude.Inside);
                }
                if(first.isSelected()) {
                    pitch.setFirstBase(true);
                }
                if(second.isSelected()) {
                    pitch.setSecondBase(true);
                }
                if(third.isSelected()) {
                    pitch.setThirdBase(true);
                }
                pitch.setPitchType(pitchBox.getSelectedIndex());
                pitchers.get(lastAccessed).addPitch(pitch);
                deselectAll();
            }
        }
    }

    private class trackPitchesListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            page2();
        }
    }

    private static class exportListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for(Pitcher p : pitchers) {
                try {
                    File file = new File(p.getName() + " Pitch Log.txt");
                    FileOutputStream fos = new FileOutputStream(file);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    bw.write(p.toString());
                    bw.close();
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file);
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        }
    }

    private class ballListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(strike.isSelected()) {
                strike.setSelected(false);
            }
        }
    }

    private class strikeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(ball.isSelected()) {
                ball.setSelected(false);
            }
        }
    }

    private class highListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(low.isSelected()) {
                low.setSelected(false);
            }
        }
    }

    private class lowListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(high.isSelected()) {
                high.setSelected(false);
            }
        }
    }

    private class insideListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(outside.isSelected()) {
                outside.setSelected(false);
            }
        }
    }

    private class outsideListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(inside.isSelected()) {
                inside.setSelected(false);
            }
        }
    }

    private void page1() {
        if(high.isVisible()) {
            remove(highOrLow);
            remove(high);
            remove(low);
            remove(inOrOut);
            remove(inside);
            remove(outside);
            remove(result);
            remove(strike);
            remove(ball);
            remove(baseRunners);
            remove(first);
            remove(second);
            remove(third);
            remove(pitches);
            remove(pitchBox);
            remove(addPitch);
            remove(getPitcher);
            remove(exportPitchLog);
            add(playerName);
            add(name);
            add(playerNumber);
            add(number);
            add(playerAge);
            add(age);
            add(playerTeam);
            add(team);
            add(pitches);
            add(pitchList);
            add(newPitcher);
            if(pitchers.size() > 1) {
                getPitcher.setText("Search Pitcher");
                add(getPitcher);
                frame.setSize(255, 265);
            } else {
                frame.setSize(255, 235);
            }
            add(savePitcher);
            add(trackPitches);
        }
    }

    private void resetPage1() {
        name.setText("");
        number.setText("");
        age.setText("");
        team.setText("");
        pitchList.setText("");
    }

    private void page2() {
        remove(playerName);
        remove(name);
        remove(playerNumber);
        remove(number);
        remove(playerAge);
        remove(age);
        remove(playerTeam);
        remove(team);
        remove(pitches);
        remove(pitchList);
        remove(newPitcher);
        remove(getPitcher);
        remove(savePitcher);
        remove(exportPitchLog);
        remove(trackPitches);
        add(highOrLow);
        add(high);
        add(low);
        add(inOrOut);
        add(inside);
        add(outside);
        add(result);
        add(strike);
        add(ball);
        add(baseRunners);
        add(first);
        add(second);
        add(third);
        add(pitches);
        pitchBox.removeAllItems();
        for(int i = 0; i < pitchers.get(lastAccessed).getPitchTypesCount(); i++) {
            pitchBox.addItem(pitchers.get(lastAccessed).getPitchType(i));
        }
        add(pitchBox);
        add(addPitch);
        add(getPitcher);
        add(exportPitchLog);
        getPitcher.setText("Options");
        frame.setSize(255, 255);
    }

    private void deselectAll() {
        ball.setSelected(false);
        strike.setSelected(false);
        low.setSelected(false);
        high.setSelected(false);
        outside.setSelected(false);
        inside.setSelected(false);
    }
}