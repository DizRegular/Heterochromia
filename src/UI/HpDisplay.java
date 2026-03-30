package UI;

import DEVAPI.CustomGameObject.Player.PlayerObject;
import RenderObject.Tween;
import RenderObject.Creatable.UiItem;
import UniverseEngine.GameUniverse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class HpDisplay extends UiItem {
    public PlayerObject player;
    
    public static final int playerUiPosX = 0;
    public static final int playerUiPosY = 50;
    public static final int playerUiHeight = 50;
    public static final int playerUiWidthRatio = 1;
    
    
    public HpDisplay(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g) {
        double plyMaxHp = player.getMaxHealthPoint();
        double plyCurrHp = player.getHealthPoint();
        
        double hpUIWidth = plyMaxHp/playerUiWidthRatio;
        
        double plyCurrHpWidth = Tween.Lerp1D(0, hpUIWidth, plyCurrHp/plyMaxHp);
        plyCurrHpWidth = Math.max(Math.min(plyCurrHpWidth, hpUIWidth), 0);
        g.setColor(Color.BLACK);
        g.fillRect(playerUiPosX, playerUiPosY, (int)hpUIWidth, playerUiHeight);
        g.setColor(Color.RED);
        g.fillRect(playerUiPosX, playerUiPosY, (int)plyCurrHpWidth, playerUiHeight);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 40));
        g.drawString((int)player.getHealthPoint() + "", playerUiPosX, playerUiPosY + 40);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = GameUniverse.getObjectByName("ThePlayer", PlayerObject.class);
    }

    
    
}
