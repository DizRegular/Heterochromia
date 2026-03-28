/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class escUI implements ActionListener{
    private JFrame fr;
    private JPanel p1;
    private JPanel p2;
    private JButton b1;
    private JButton b2;
    private JButton b3;
    
    public escUI(){
        fr = new JFrame("Pause");
        p1 = new JPanel();
        p2 = new JPanel();
        b1 = new JButton("Resume");
        b2 = new JButton("Setting");
        b3 = new JButton("Exit");
        
        p1.setLayout(new BorderLayout());
        p2.setLayout(new GridLayout(3,1,10,10));
        b1.setFont(new Font("Arial", Font.BOLD, 30));
        b2.setFont(new Font("Arial", Font.BOLD, 30));
        b3.setFont(new Font("Arial", Font.BOLD, 30));
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        p1.add(p2, BorderLayout.CENTER);
        fr.add(p1);
        
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setSize(300,250);
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            
        }
        if (e.getSource() == b2){
            
        }
        if (e.getSource() == b3) {
            fr.dispose();
        }
    }
}
