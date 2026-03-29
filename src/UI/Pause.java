/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import RenderObject.UiItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Rectangle;

public class Pause extends UiItem {

    private Rectangle btnResume;
    private Rectangle btnQuit;

    public Pause(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
        
        int btnWidth = 200;
        int btnHeight = 50;
        
        int centerX = x + width  / 2;
        
        
    }

    @Override
    public void draw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(x, y, width, height); 

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        FontMetrics fmTitle = g.getFontMetrics();
        int titleX = x + (width - fmTitle.stringWidth("PAUSED")) / 2;
        g.drawString("PAUSED", titleX, y + (height / 3));

        
    }

    
    public String checkMouseClick(int mouseX, int mouseY) {
        if (btnResume.contains(mouseX, mouseY)) {
            return "RESUME";
        } 
        else if (btnQuit.contains(mouseX, mouseY)) {
            return "QUIT";
        }
        return "NONE";
    }
}
