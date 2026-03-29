
package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuGUI implements ActionListener{

    private JFrame fr;
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    private JPanel p4;
    private JPanel Lwrap;
    private JLabel l1;
    private JLabel bg;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private ImageIcon icon;
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
        Lwrap = new JPanel();
        l1 = new JLabel("Heterochromia");
        b1 = new JButton("Play");
        b2 = new JButton("Setting");
        b3 = new JButton("Exit");
        icon = new ImageIcon("res/GameAssets/Background/mainmenubg.jpg");
        bg = new JLabel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        bg.setLayout(new BorderLayout());
        p1.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(3, 1, 10, 10));
        p3.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 70));
        p4.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 100));
        Lwrap.setLayout(new FlowLayout(FlowLayout.CENTER));
        l1.setFont(new Font("Comic Sans MS", Font.BOLD, 100));
        l1.setForeground(Color.white);
        l1.setHorizontalAlignment(JLabel.CENTER);
        

        Dimension buttonSize = new Dimension(200, 70);
        b1.setPreferredSize(buttonSize);
        b2.setPreferredSize(buttonSize);
        b3.setPreferredSize(buttonSize);

        Font buttonFont = new Font("Comic Sans MS", Font.BOLD, 18);
        b1.setFont(buttonFont);
        b2.setFont(buttonFont);
        b3.setFont(buttonFont);
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        p2.add(b1);
        p2.add(b2);
        p2.add(b3);

        Lwrap.add(Box.createVerticalStrut(150));
        Lwrap.add(l1);
        p3.add(l1);

        p4.add(p2);
        p4.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

        bg.add(p1);
        p1.add(p3, BorderLayout.CENTER);
        p1.add(p4, BorderLayout.SOUTH);
        
        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        p4.setOpaque(false);
        Lwrap.setOpaque(false);
        
        fr.add(bg);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        applyWindowSettings();
    }
    
    private void applyWindowSettings() {
        if (isFullscreen) {
            fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
            fr.setVisible(true);
        }
        else {
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
        if (e.getSource() == b1){
            fr.dispose();
            new PrologueGUI();
        }
        else if (e.getSource() == b2){
            new SettingGUI(this);
        }
        else if (e.getSource() == b3) {
            System.exit(0);
        }
    }
}