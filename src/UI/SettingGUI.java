/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.*;
import java.awt.*;

public class SettingGUI {
    
    private JDialog dialog;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel p5;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel value1; // Value label for master volume
    private JLabel value2; // Value label for SFX volume
    private JLabel value3; // Value label for music volume
    private JSlider sl1;
    private JSlider sl2;
    private JSlider sl3;
    private JButton b1;
    private JButton b2;
    private JComboBox combo;
    private JCheckBox check;
    private NewClass mainMenu;
    
    // Static variables to store settings
    public static int masterVolume = 70;
    public static int sfxVolume = 70;
    public static int musicVolume = 70;
    public static String resolution = "1920x1080";
    public static boolean fullscreen = false;
    
    public SettingGUI(NewClass mainMenuRef) {
        this.mainMenu = mainMenuRef;
        dialog = new JDialog(mainMenuRef.getFrame(), "Settings", true);
        
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        
        l1 = new JLabel("SETTINGS");
        l2 = new JLabel("Master Volume");
        l3 = new JLabel("SFX Volume");
        l4 = new JLabel("Music Volume");
        l5 = new JLabel("Resolution");
        
        // Value labels for volume numbers
        value1 = new JLabel(masterVolume + "%");
        value2 = new JLabel(sfxVolume + "%");
        value3 = new JLabel(musicVolume + "%");
        
        sl1 = new JSlider(0, 100, masterVolume);
        sl2 = new JSlider(0, 100, sfxVolume);
        sl3 = new JSlider(0, 100, musicVolume);
        
        b1 = new JButton("SAVE");
        b2 = new JButton("CANCEL");
        
        combo = new JComboBox();
        check = new JCheckBox("Fullscreen");
        
        // Set layouts
        dialog.setLayout(new BorderLayout());
        p1.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(6, 3, 15, 25)); // Changed to 3 columns for label, slider, value
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 25));
        p4.setLayout(new BorderLayout());
        p5.setLayout(new BorderLayout());
        
        // Set fonts
        l1.setFont(new Font("Arial", Font.BOLD, 32));
        l1.setHorizontalAlignment(JLabel.CENTER);
        
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        l4.setFont(new Font("Arial", Font.BOLD, 18));
        l5.setFont(new Font("Arial", Font.BOLD, 18));
        
        l2.setHorizontalAlignment(SwingConstants.RIGHT);
        l3.setHorizontalAlignment(SwingConstants.RIGHT);
        l4.setHorizontalAlignment(SwingConstants.RIGHT);
        l5.setHorizontalAlignment(SwingConstants.RIGHT);
        
        // Set value label fonts
        value1.setFont(new Font("Arial", Font.BOLD, 16));
        value2.setFont(new Font("Arial", Font.BOLD, 16));
        value3.setFont(new Font("Arial", Font.BOLD, 16));
        value1.setForeground(Color.DARK_GRAY);
        value2.setForeground(Color.DARK_GRAY);
        value3.setForeground(Color.DARK_GRAY);
        
        // Set slider sizes
        Dimension sliderSize = new Dimension(250, 45);
        sl1.setPreferredSize(sliderSize);
        sl2.setPreferredSize(sliderSize);
        sl3.setPreferredSize(sliderSize);
        
        // Set slider ticks
        sl1.setMajorTickSpacing(25);
        sl1.setMinorTickSpacing(5);
        sl1.setPaintTicks(true);
        sl1.setPaintLabels(true);
        
        sl2.setMajorTickSpacing(25);
        sl2.setMinorTickSpacing(5);
        sl2.setPaintTicks(true);
        sl2.setPaintLabels(true);
        
        sl3.setMajorTickSpacing(25);
        sl3.setMinorTickSpacing(5);
        sl3.setPaintTicks(true);
        sl3.setPaintLabels(true);
        
        // Add change listeners to update value labels
        sl1.addChangeListener(e -> {
            value1.setText(sl1.getValue() + "%");
        });
        
        sl2.addChangeListener(e -> {
            value2.setText(sl2.getValue() + "%");
        });
        
        sl3.addChangeListener(e -> {
            value3.setText(sl3.getValue() + "%");
        });
        
        // Set combo box
        combo.addItem("1920x1080");
        combo.addItem("1600x900");
        combo.addItem("1280x720");
        combo.addItem("1024x768");
        combo.setPreferredSize(new Dimension(180, 40));
        combo.setFont(new Font("Arial", Font.PLAIN, 16));
        combo.setSelectedItem(mainMenu.getCurrentResolution());
        
        // Set checkbox
        check.setFont(new Font("Arial", Font.BOLD, 18));
        check.setSelected(mainMenu.isFullscreen());
        check.setBackground(new Color(238, 238, 238));
        
        // Add action listener to lock/unlock resolution
        check.addActionListener(e -> {
            if (check.isSelected()) {
                combo.setEnabled(false);
                combo.setBackground(Color.LIGHT_GRAY);
            } else {
                combo.setEnabled(true);
                combo.setBackground(Color.WHITE);
            }
        });
        
        // Set buttons
        b1.setFont(new Font("Arial", Font.BOLD, 18));
        b2.setFont(new Font("Arial", Font.BOLD, 18));
        b1.setPreferredSize(new Dimension(120, 45));
        b2.setPreferredSize(new Dimension(120, 45));
        
        b1.setBackground(new Color(60, 120, 60));
        b1.setForeground(Color.WHITE);
        b2.setBackground(new Color(120, 60, 60));
        b2.setForeground(Color.WHITE);
        
        b1.setFocusPainted(false);
        b2.setFocusPainted(false);
        
        // Add action listeners
        b1.addActionListener(e -> saveSettings());
        b2.addActionListener(e -> dialog.dispose());
        
        // Add components to p2 (GridLayout with 6 rows, 3 columns)
        // Row 1: Master Volume
        p2.add(l2);
        p2.add(sl1);
        p2.add(value1);
        
        // Row 2: SFX Volume
        p2.add(l3);
        p2.add(sl2);
        p2.add(value2);
        
        // Row 3: Music Volume
        p2.add(l4);
        p2.add(sl3);
        p2.add(value3);
        
        // Row 4: Resolution
        p2.add(l5);
        JPanel resPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        resPanel.setBackground(new Color(238, 238, 238));
        resPanel.add(combo);
        p2.add(resPanel);
        p2.add(new JLabel("")); // Empty cell for alignment
        
        // Row 5: Fullscreen
        JLabel emptyLabel = new JLabel("");
        p2.add(emptyLabel);
        JPanel checkPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        checkPanel.setBackground(new Color(238, 238, 238));
        checkPanel.add(check);
        p2.add(checkPanel);
        p2.add(new JLabel("")); // Empty cell for alignment
        
        // Row 6: Empty row for spacing
        JLabel emptyLabel2 = new JLabel("");
        p2.add(emptyLabel2);
        JLabel emptyLabel3 = new JLabel("");
        p2.add(emptyLabel3);
        JLabel emptyLabel4 = new JLabel("");
        p2.add(emptyLabel4);
        
        // Add buttons to p3
        p3.add(b1);
        p3.add(b2);
        
        // Add title with spacing
        p4.add(l1, BorderLayout.NORTH);
        p4.add(Box.createVerticalStrut(15), BorderLayout.CENTER);
        
        // Add padding around p2
        p2.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        // Add spacing around p1
        p1.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Set initial state of resolution dropdown based on fullscreen
        if (mainMenu.isFullscreen()) {
            combo.setEnabled(false);
            combo.setBackground(Color.LIGHT_GRAY);
        }
        
        // Assemble everything
        p1.add(p4, BorderLayout.NORTH);
        p1.add(p2, BorderLayout.CENTER);
        p1.add(p3, BorderLayout.SOUTH);
        
        dialog.add(p1);
        
        // Set dialog size
        dialog.setSize(800, 650);
        dialog.setLocationRelativeTo(mainMenuRef.getFrame());
        dialog.setResizable(false);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }
    
    private void saveSettings() {
        // Save to static variables
        masterVolume = sl1.getValue();
        sfxVolume = sl2.getValue();
        musicVolume = sl3.getValue();
        
        // Get the new settings
        boolean newFullscreen = check.isSelected();
        String newResolution = (String) combo.getSelectedItem();
        
        // Apply settings through main menu
        mainMenu.saveAndApplySettings(newResolution, newFullscreen);
        
        // Show confirmation
        String message = "Settings Saved!\n\n" +
                         "Master Volume: " + masterVolume + "%\n" +
                         "SFX Volume: " + sfxVolume + "%\n" +
                         "Music Volume: " + musicVolume + "%\n" +
                         "Resolution: " + newResolution + "\n" +
                         "Fullscreen: " + (newFullscreen ? "ON" : "OFF");
        
        JOptionPane.showMessageDialog(dialog, message, "Settings Saved", JOptionPane.INFORMATION_MESSAGE);
        
        // Close the settings window
        dialog.dispose();
    }
}