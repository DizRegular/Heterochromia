/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

import RenderObject.UiItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author disk1
 */
public class WinningScreen extends UiItem {
    public Font font = new Font("Arial", Font.BOLD, 100);
    
    public WinningScreen(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW);
        g.setFont(font);
        g.drawString("Thanks for playing", 100, 400);
    }
    
}
