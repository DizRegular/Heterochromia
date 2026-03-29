
package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import UniverseEngine.Game;

public class MainMenuGUI implements ActionListener{

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

    public MainMenuGUI(){
        isFullscreen = SettingGUI.fullscreen;
        currentResolution = SettingGUI.resolution;
        createAndShowGUI();
    }
    
    private void createAndShowGUI(){
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

        Dimension buttonSize = new Dimension(200, 70);
        b1.setPreferredSize(buttonSize);
        b2.setPreferredSize(buttonSize);
        b3.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Arial", Font.BOLD, 18);
        b1.setFont(buttonFont);
        b2.setFont(buttonFont);
        b3.setFont(buttonFont);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);

        JPanel labelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelWrapper.setOpaque(false);
        labelWrapper.add(Box.createVerticalStrut(150));
        labelWrapper.add(l1);
        p3.add(labelWrapper, BorderLayout.NORTH);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(p2);
        p4.add(buttonWrapper, BorderLayout.NORTH);
        p4.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

        p1.add(p3, BorderLayout.CENTER);
        p1.add(p4, BorderLayout.SOUTH);
        fr.add(p1);
        
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
        SettingGUI.resolution = newResolution;
        SettingGUI.fullscreen = newFullscreen;
        
        isFullscreen = newFullscreen;
        currentResolution = newResolution;
        
        if (newFullscreen) {
            fr.dispose();
            fr.setUndecorated(true);
            fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            fr.dispose();
            fr.setUndecorated(false);
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
    @Override
    public void actionPerformed(ActionEvent e) {
        //start game here
        if (e.getSource() == b1){
            fr.dispose();
            new PrologueGUI();
        }
        //open setting
        if (e.getSource() == b2){
            new SettingGUI(this);
        }
        //close the game
        if (e.getSource() == b3) {
            System.exit(0);
        }
    }
}