package Messenger;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MessageCenter extends JPanel {
    public MessageCenter() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel panel1 = new JPanel();
        panel1.setLayout(new CardLayout());
        panel1.setSize(new Dimension(Main.screenSize.width / 3, Main.screenSize.height));
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.ipadx = Main.screenSize.width / 3;
        c.ipady = Main.screenSize.height;
        c.anchor = GridBagConstraints.PAGE_START;
        panel1.setBackground(Color.BLACK);
        add(panel1, c);

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        panel2.setSize(new Dimension(Main.screenSize.width * 2 / 3, Main.screenSize.height));
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.ipadx = Main.screenSize.width / 7;
        c.ipady = Main.screenSize.height * 6 / 13;
        add(panel2, c);

        JLabel label = new JLabel();
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        panel2.add(label, c);

        JButton signOut = new JButton("Sign Out");
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.ipady = 0;
        signOut.addActionListener(new SignOutListener());
        panel2.add(signOut, c);

        JButton editProfile = new JButton("Edit Profile");
        c.gridx = 2;
        c.gridy = 0;
        c.ipady = 0;
        editProfile.addActionListener(new EditProfileListener());
        panel2.add(editProfile, c);

        JTextArea theMsg = new JTextArea(1, 50);
        c.ipady = 0;       //reset to default
        c.weighty = 2.0;   //request any extra vertical space
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 2;   //2 columns wide
        c.gridy = 2;       //third row
        c.anchor = GridBagConstraints.LAST_LINE_START;
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        theMsg.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        JScrollPane sb = new JScrollPane(theMsg);
        sb.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sb.createVerticalScrollBar();
        panel2.add(theMsg, c);

        JButton send = new JButton("Send");
        c.ipady = 12;
        c.weightx = 0.0;
        c.gridwidth = 0;
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LAST_LINE_END;
        panel2.add(send, c);
    }

    private static class SignOutListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.frame.setContentPane(new Main());
            Main.frame.setSize(275, 150);
            Main.centerFrame();
            Main.frame.setTitle("Messenger");
        }
    }

    private static class EditProfileListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Main.frame.setContentPane(new EditProfile());
            Main.frame.setSize(300, 200);
            Main.centerFrame();
            Main.frame.setTitle("Edit Profile");
        }
    }
}