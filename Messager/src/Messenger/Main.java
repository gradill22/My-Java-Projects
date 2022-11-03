package Messenger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends JPanel {
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static JFrame frame;
    private static JLabel error;
    private static JPasswordField password;
    private static JTextField userName;
    public static final ArrayList<Profile> profiles = new ArrayList<>();
    private static final File users = new File("messenger_users.txt");
    public static Profile currentProfile;

    public Main() {
        add(new JLabel("Welcome to Messenger! Sign in below!"));
        add(new JLabel("Username:"));
        add(userName = new JTextField(15));
        add(new JLabel("Password:"));
        add(password = new JPasswordField(15));
        JButton signIn = new JButton("Sign in");
        signIn.addActionListener(new SignInListener());
        add(signIn);
        add(error = new JLabel());
    }

    public static void main(String[] args) {
        frame = new JFrame("Messenger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Main());
        frame.addWindowListener(new Close());
        frame.setSize(275, 150);
        centerFrame();
        frame.setAlwaysOnTop(true);
        try {
            FileInputStream fis = new FileInputStream(users);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = br.readLine();
            while(line != null) {
                profiles.add(getProfile(line));
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setVisible(true);
    }

    public static void signIn() {
        frame.setContentPane(new MessageCenter());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(false);
        frame.setVisible(true);
        centerFrame();
    }

    private static class SignInListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(!userName.getText().isBlank() && password.getPassword().length != 0) {
                Profile theProfile = null;
                boolean incorrectPassword = false;
                for(Profile p : profiles) {
                    if(p.userName().equals(userName.getText())) {
                        if(Arrays.equals(p.password(), password.getPassword())) {
                            theProfile = p;
                            currentProfile = p;
                            signIn();
                        } else {
                            error.setText("Incorrect password");
                            password.setText("");
                            incorrectPassword = true;
                        }
                        break;
                    }
                }
                if(theProfile == null && !incorrectPassword) {
                    profiles.add(new Profile(userName.getText(), String.valueOf(password.getPassword())));
                    currentProfile = profiles.get(profiles.size() - 1);
                    signIn();
                    frame.setTitle("Welcome new user!");
                }
            } else {
                error.setText("Please fill in all fields.");
            }
        }
    }

    private static class Close implements WindowListener {
        public void windowOpened(WindowEvent e) {

        }

        public void windowClosing(WindowEvent e) {
            try {
                File file = new File("messenger_users.txt");
                FileOutputStream fos = new FileOutputStream(file);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                for(Profile p : profiles) {
                    bw.write(p.toString());
                    bw.write("\n");
                }
                bw.close();
            } catch (Exception exception) {
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

    public static void centerFrame() {
        frame.setLocation((int) (screenSize.getWidth() / 2) - (frame.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frame.getHeight() / 2));
    }

    private static Profile getProfile(String s) {
        String fName = "null";
        String lName = "null";
        String uName = "";
        String pWord = "";
        int slashCount = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '/') {
                slashCount++;
            }
        }
        while(slashCount > 0) {
            String str = "";
            for(int i = 0; i < s.length(); i++) {
                if(s.charAt(i) == '/') {
                    if(uName.equals("")) {
                        uName = str;
                    } else if(pWord.equals("")) {
                        pWord = str;
                    } else if(fName.equals("null")) {
                        fName = str;
                    } else if(lName.equals("null")) {
                        lName = str;
                    }
                    str = "";
                    slashCount--;
                } else {
                    str += s.charAt(i);
                }
            }
        }
        return new Profile(fName, lName, uName, pWord);
    }
}