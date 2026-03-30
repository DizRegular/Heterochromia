package UI;

import Boss1ass.Boss1;
import Boss2ass.Boss2;
import Boss3ass.Boss3;
import DEVAPI.CustomGameObject.Player.PlayerObject;
import RenderObject.Addon.Scriptable;
import RenderObject.Tween;
import RenderObject.UiItem;
import UniverseEngine.GameUniverse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Boss3HpDisplay extends UiItem {
    public Boss3 boss;
    
    public static final int BossUiPosX = 200;
    public static final int BossUiPosY = 750;
    public static final int BossUiHeight = 50;
    public static final double BossUiWidthRatio = 0.75;
    public static final int fontSize = 40;
    public Font font = new Font("Arial", Font.PLAIN, fontSize);
    
    public Boss3HpDisplay(String name, int x, int y, int width, int height) {
        super(name, x, y, width, height);
    }

    @Override
    public void draw(Graphics2D g) {
        double plyMaxHp = boss.getStats().getMaxhp();
        double plyCurrHp = boss.getStats().getHp();
        
        double hpUIWidth = plyMaxHp/BossUiWidthRatio;
        
        double plyCurrHpWidth = Tween.Lerp1D(0, hpUIWidth, plyCurrHp/plyMaxHp);
        plyCurrHpWidth = Math.max(Math.min(plyCurrHpWidth, hpUIWidth), 0);
        g.setColor(Color.BLACK);
        g.fillRect(BossUiPosX, BossUiPosY, (int)hpUIWidth, BossUiHeight);
        g.setColor(Color.RED);
        g.fillRect(BossUiPosX, BossUiPosY, (int)plyCurrHpWidth, BossUiHeight);
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString((int)boss.getStats().getHp() + "", BossUiPosX, BossUiPosY + fontSize);
        g.setColor(Color.lightGray);
        g.setFont(new Font("Gothic", Font.PLAIN, fontSize));
        g.drawString("Obsidion", BossUiPosX, BossUiPosY - fontSize);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        boss = GameUniverse.getObjectByName("rock", Boss3.class);
    }

}
