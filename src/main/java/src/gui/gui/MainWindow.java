package src.gui.gui;

import src.gui.logic.InputCheckerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow {

    private JFrame frame;
    private JButton btnTokenProd;
    private JLabel lblTokenProd;
    private JLabel lblServerID;
    private JButton btnServerID;
    private JLabel lblTextLogChannelID;
    private JButton btnTextLogChannel;

    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }

    /**
     * creates a gui with a label for each button
     * the structure is:
     * label1 button1
     * label2 button2
     * label3 button3
     * ...
     * so every field in the .env file has a label and a button
     * when a button is clicked, a popup window opens to ask the user for the value
     */
    private void initialize() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setBounds(100, 100, 690, 413);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));
        frame.getContentPane().add(getLblTokenProd());
        frame.getContentPane().add(getBtnTokenProd());
        frame.getContentPane().add(getLblServerID());
        frame.getContentPane().add(getBtnServerID());
        frame.getContentPane().add(getLblTextLogChannelID());
        frame.getContentPane().add(getBtnTextLogChannel());
    }

    public JFrame getFrame() {
        return frame;
    }
    private JButton getBtnTokenProd() {
        if (btnTokenProd == null) {
            btnTokenProd = new JButton("TOKENPROD");
            btnTokenProd.addActionListener(new ActionListener() {
                /**
                 * opens a popup window to ask the user for the token
                 */
                public void actionPerformed(ActionEvent e) {
                    String token = JOptionPane.showInputDialog("Please enter the token");
                    if (InputCheckerGUI.checkProdToken(token)) {
                        //the token has right format
                    } else {
                        //the token has wrong format
                    }
                }
            });
        }
        return btnTokenProd;
    }
    private JLabel getLblTokenProd() {
        if (lblTokenProd == null) {
            lblTokenProd = new JLabel("click the button to write the discord bot token");
            lblTokenProd.setLabelFor(getBtnTokenProd());
            lblTokenProd.setHorizontalAlignment(SwingConstants.CENTER);

        }
        return lblTokenProd;
    }
    private JLabel getLblServerID() {
        if (lblServerID == null) {
            lblServerID = new JLabel("click the button to write the id of your server");
            lblServerID.setLabelFor(getBtnServerID());
            lblServerID.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return lblServerID;
    }
    private JButton getBtnServerID() {
        if (btnServerID == null) {
            btnServerID = new JButton("SERVERID");
            btnServerID.addActionListener(new ActionListener() {
                /**
                 * opens a popup window to ask the user for the serverID
                 */
                public void actionPerformed(ActionEvent e) {
                    String serverID = JOptionPane.showInputDialog("Please enter the serverID");
                    if (InputCheckerGUI.checkProdToken(serverID)) {
                        //the serverID has right format
                    } else {
                        //the serverID has wrong format
                    }
                }
            });
        }
        return btnServerID;
    }
    private JLabel getLblTextLogChannelID() {
        if (lblTextLogChannelID == null) {
            lblTextLogChannelID = new JLabel("click the button to write the id of your text messages log channel");
            lblTextLogChannelID.setLabelFor(getBtnTextLogChannel());
            lblTextLogChannelID.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return lblTextLogChannelID;
    }
    private JButton getBtnTextLogChannel() {
        if (btnTextLogChannel == null) {
            btnTextLogChannel = new JButton("TEXTLOGCHANNELID");
        }
        return btnTextLogChannel;
    }

}