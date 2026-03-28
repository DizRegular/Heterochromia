/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.*;
import java.awt.*;

public class NewClass {

    private JFrame fr;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JLabel l1;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private boolean isFullscreen = false;
    private String currentResolution = "1920x1080";

    public NewClass() {
        // Load saved settings
        isFullscreen = SettingGUI.fullscreen;
        currentResolution = SettingGUI.resolution;
        createAndShowGUI();
    }
    
    private void createAndShowGUI() {
        fr = new JFrame("Heterochromia");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        l1 = new JLabel("Heterochromia");
        b1 = new JButton("Play");
        b2 = new JButton("Setting");
        b3 = new JButton("Exit");

        p1.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(3, 1, 10, 10));
        p3.setLayout(new BorderLayout());
        p4.setLayout(new BorderLayout());

        l1.setFont(new Font("Arial", Font.BOLD, 70));
        l1.setHorizontalAlignment(JLabel.CENTER);

        // Set button sizes
        Dimension buttonSize = new Dimension(200, 70);
        b1.setPreferredSize(buttonSize);
        b2.setPreferredSize(buttonSize);
        b3.setPreferredSize(buttonSize);

        // Set button font
        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        b1.setFont(buttonFont);
        b2.setFont(buttonFont);
        b3.setFont(buttonFont);
        
        // Add action listeners to buttons
        b1.addActionListener(e -> startGame());
        b2.addActionListener(e -> openSettings());
        b3.addActionListener(e -> exitGame());

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);

        // Center the label with top spacing
        JPanel labelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelWrapper.setOpaque(false);
        labelWrapper.add(Box.createVerticalStrut(150));
        labelWrapper.add(l1);
        p3.add(labelWrapper, BorderLayout.NORTH);

        // Center the buttons
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(p2);
        p4.add(buttonWrapper, BorderLayout.NORTH);
        p4.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

        p1.add(p3, BorderLayout.CENTER);
        p1.add(p4, BorderLayout.SOUTH);
        fr.add(p1);
        
        // Apply the saved settings
        applyWindowSettings();
    }
    
    private void applyWindowSettings() {
        if (isFullscreen) {
            fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
            fr.setVisible(true);
        } else {
            String[] res = currentResolution.split("x");
            int width = Integer.parseInt(res[0]);
            int height = Integer.parseInt(res[1]);
            
            fr.setSize(width, height);
            fr.setLocationRelativeTo(null);
            fr.setVisible(true);
        }
    }
    
    public void saveAndApplySettings(String newResolution, boolean newFullscreen) {
        // Save to static variables
        SettingGUI.resolution = newResolution;
        SettingGUI.fullscreen = newFullscreen;
        
        // Update local variables
        isFullscreen = newFullscreen;
        currentResolution = newResolution;
        
        if (newFullscreen) {
            fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            fr.setExtendedState(JFrame.NORMAL);
            
            String[] res = newResolution.split("x");
            int width = Integer.parseInt(res[0]);
            int height = Integer.parseInt(res[1]);
            fr.setSize(width, height);
            fr.setLocationRelativeTo(null);
        }
        
        fr.setVisible(true);
    }
    
    public JFrame getFrame() {
        return fr;
    }
    
    public boolean isFullscreen() {
        return isFullscreen;
    }
    
    public String getCurrentResolution() {
        return currentResolution;
    }
    
    private void startGame() {
    String message = "=== GAME STARTING ===\n\n" +
                     "AUDIO SETTINGS:\n" +
                     "• Master Volume: " + SettingGUI.masterVolume + "%\n" +
                     "• SFX Volume: " + SettingGUI.sfxVolume + "%\n" +
                     "• Music Volume: " + SettingGUI.musicVolume + "%\n\n" +
                     "VIDEO SETTINGS:\n" +
                     "• Resolution: " + SettingGUI.resolution + "\n" +
                     "• Fullscreen: " + (SettingGUI.fullscreen ? "ON" : "OFF") + "\n\n" +
                     "Click OK to start the game!";
    
    JOptionPane.showMessageDialog(fr, message, "Launching Heterochromia", JOptionPane.INFORMATION_MESSAGE);
    
    // Here you will add your friend's game code later
    // fr.setVisible(false); // Uncomment to hide menu when game starts
}
    
    private void openSettings() {
        new SettingGUI(this);
    }
    
    private void exitGame() {
        System.exit(0);
    }

    public static void main(String[] args) {
        new NewClass();
    }
}