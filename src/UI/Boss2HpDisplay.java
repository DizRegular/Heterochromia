package UI;

import Boss2ass.Boss2;
import RenderObject.Tween;
import RenderObject.Creatable.UiItem;
import UniverseEngine.GameUniverse;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Boss2HpDisplay extends UiItem {
    public Boss2 boss;
    
    public static final int BossUiPosX = 320;
    public static final int BossUiPosY = 750;
    public static final int BossUiHeight = 50;
    public static final double BossUiWidthRatio = 0.5;
    public static final int fontSize = 40;
    public Font font = new Font("Gothic", Font.PLAIN, fontSize);
    
    public Boss2HpDisplay(String name, int x, int y, int width, int height) {
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
        g.setFont(new Font("Arial", Font.PLAIN, fontSize));
        g.drawString((int)boss.getStats().getHp() + "", BossUiPosX, BossUiPosY + fontSize);
        g.setColor(Color.RED);
        g.setFont(font);
        g.drawString("Kagehito the Traitor", BossUiPosX, BossUiPosY - fontSize);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        boss = GameUniverse.getObjectByName("samurai", Boss2.class);
    }

}
