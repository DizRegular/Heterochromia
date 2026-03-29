package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import UniverseEngine.Game;

public class PrologueGUI {
    
    private JFrame fr;
    private JPanel mainPanel;
    private JPanel spritePanel;
    private JPanel bottomPanel;
    private JLabel spriteLabel;
    private JLabel nameLabel;
    private JTextArea textArea;
    private JScrollPane scrollPane;
    private int step = 0;
    
    private String[] dialogues = {
        "Somewhere in a bad neighborhood of Tokyo.",
        "Three groups of teenagers that control the local area are meeting up for their showdown.",
        "But sundenly…",
        "Why aren't those two showing up yet. Are they trying to set me up or something?",
        "Well, I better be careful and look around the place for a bit."
    };
    
    private String[] speakers = {
        null,
        null,
        null,
        "Seki",
        "Seki"
    };
    
    public PrologueGUI() {
        createAndShowGUI();
    }
    
    private void createAndShowGUI() {
        fr = new JFrame("Prologue");
        mainPanel = new JPanel();
        spritePanel = new JPanel();
        bottomPanel = new JPanel();
        spriteLabel = new JLabel();
        nameLabel = new JLabel();
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        
        if (SettingGUI.fullscreen) {
            fr.setUndecorated(true);
            fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        } else {
            fr.setUndecorated(false);
            String[] res = SettingGUI.resolution.split("x");
            int width = Integer.parseInt(res[0]);
            int height = Integer.parseInt(res[1]);
            fr.setSize(width, height);
            fr.setLocationRelativeTo(null);
        }
        
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setFocusable(true);
        
        spritePanel.setBackground(Color.BLACK);
        spritePanel.setPreferredSize(new Dimension(400, 400));
        spriteLabel.setHorizontalAlignment(JLabel.CENTER);
        spritePanel.add(spriteLabel);
        
        textArea.setFont(new Font("Arial", Font.PLAIN, 24));
        textArea.setForeground(Color.WHITE);
        textArea.setBackground(Color.BLACK);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setMargin(new Insets(20, 20, 20, 20));
        
        scrollPane.setBorder(null);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        nameLabel.setForeground(Color.YELLOW);
        nameLabel.setBackground(Color.BLACK);
        nameLabel.setOpaque(true);
        
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(nameLabel, BorderLayout.NORTH);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.add(spritePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                advanceStory();
            }
        });
        
        fr.add(mainPanel);
        fr.setVisible(true);
        
        showCurrentText();
    }
    
    private void advanceStory() {
        step++;
        if (step >= dialogues.length) {
            fr.dispose();
            Game game = new Game();
            game.start();
        } else {
            showCurrentText();
        }
    }
    
    private void showCurrentText() {
        textArea.setText(dialogues[step]);
        
        if (speakers[step] != null) {
            nameLabel.setText("  " + speakers[step] + "  ");
        } else {
            nameLabel.setText("");
        }
    }
}