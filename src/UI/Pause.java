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

    // สร้างกล่องสี่เหลี่ยม (Rectangle) เพื่อเก็บตำแหน่งและขนาดของปุ่ม
    private Rectangle btnResume;
    private Rectangle btnQuit;

    public Pause(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
        
        int btnWidth = 200;
        int btnHeight = 50;
        
        int centerX = x + (width - btnWidth) / 2;
        
        btnResume = new Rectangle(centerX, y + (height / 2) - 30, btnWidth, btnHeight);
        btnQuit = new Rectangle(centerX, y + (height / 2) + 40, btnWidth, btnHeight);
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

        drawButton(g, btnResume, "Resume", new Color(70, 130, 180)); // สีฟ้า

        drawButton(g, btnQuit, "Quit", new Color(200, 50, 50)); // สีแดง
    }

    private void drawButton(Graphics2D g, Rectangle rect, String text, Color bgColor) {
        g.setColor(bgColor);
        g.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 15, 15); // ขอบมน
        g.setColor(Color.WHITE);
        g.drawRoundRect(rect.x, rect.y, rect.width, rect.height, 15, 15);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics fm = g.getFontMetrics();
        int textX = rect.x + (rect.width - fm.stringWidth(text)) / 2;
        int textY = rect.y + ((rect.height - fm.getHeight()) / 2) + fm.getAscent();
        g.drawString(text, textX, textY);
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
