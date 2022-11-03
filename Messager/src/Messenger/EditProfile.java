package Messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class EditProfile extends JPanel {
    private static JTextField fName, lName, uName;
    private static JPasswordField oldPassword, newPassword;
    private static JLabel error;

    public EditProfile() {
        setLayout(new FlowLayout());
        add(new JLabel("First name:"));
        add(fName = new JTextField(15));
        add(new JLabel("Last name:"));
        add(lName = new JTextField(15));
        add(new JLabel("Username:"));
        add(uName = new JTextField(15));
        add(new JLabel("Old password:"));
        add(oldPassword = new JPasswordField(15));
        add(new JLabel("New password:"));
        add(newPassword = new JPasswordField(15));
        JButton save = new JButton("Save");
        save.addActionListener(new SaveListener());
        add(save);
        JButton back = new JButton("Back");
        back.addActionListener(new BackListener());
        add(back);
        JButton deleteProfile = new JButton("Delete profile");
        deleteProfile.addActionListener(new DeleteProfileListener());
        add(deleteProfile);
        add(error = new JLabel());
        fName.setText(Main.currentProfile.firstName());
        lName.setText(Main.currentProfile.lastName());
        uName.setText(Main.currentProfile.userName());
    }

    private static class SaveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(uName.getText().isBlank()) {
                error.setText("Username required");
                uName.setText(Main.currentProfile.userName());
                Main.frame.setSize(300, 225);
                return;
            }
            boolean userNameAlreadyTaken = false;
            for(Profile p : Main.profiles) {
                if(uName.getText().equals(p.userName()) && !p.equals(Main.currentProfile)) {
                    userNameAlreadyTaken = true;
                    break;
                }
            }
            if(oldPassword.getPassword().length > 0) {
                if(Arrays.equals(oldPassword.getPassword(), Main.currentProfile.password())) {
                    if(!userNameAlreadyTaken) {
                        Main.currentProfile.setFirstName(fName.getText());
                        Main.currentProfile.setLastName(lName.getText());
                        Main.currentProfile.newUserName(uName.getText());
                        Main.currentProfile.newPassword(String.valueOf(newPassword.getPassword()));
                        Main.frame.setContentPane(new MessageCenter());
                        Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        Main.frame.setUndecorated(true);
                        Main.centerFrame();
                        Main.frame.setTitle("Messenger");
                    } else {
                        error.setText("Username already exists");
                    }
                } else {
                    oldPassword.setText("");
                    newPassword.setText("");
                    error.setText("New password doesn't match old password");
                }
            } else {
                if(!userNameAlreadyTaken) {
                    Main.currentProfile.setFirstName(fName.getText());
                    Main.currentProfile.setLastName(lName.getText());
                    Main.currentProfile.newUserName(uName.getText());
                    Main.frame.setContentPane(new MessageCenter());
                    Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    Main.frame.setUndecorated(true);
                    Main.centerFrame();
                    Main.frame.setTitle("Messenger");
                } else {
                    error.setText("Username already exists");
                }
            }
        }
    }

    private static class BackListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.frame.setContentPane(new MessageCenter());
            Main.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            Main.frame.setUndecorated(true);
            Main.centerFrame();
            Main.frame.setTitle("Messenger");
        }
    }

    private static class DeleteProfileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.profiles.remove(Main.currentProfile);
            Main.frame.setContentPane(new Main());
            Main.frame.setSize(275, 150);
            Main.centerFrame();
            Main.frame.setTitle("Messenger");
        }
    }
}