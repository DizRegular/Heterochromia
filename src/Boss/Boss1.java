/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author tin_sel
 */
package Boss;

import RenderObject.CollisionObject;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import RenderObject.Vector2D;
import RenderObject.touchable;
import java.awt.Graphics2D;

public class Boss1  extends StaticObject implements touchable {
    private boss1stat stats;
    private int attackTimer = 0;

    public Boss1(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name);
        this.stats = new boss1stat();
    }

    public void takeDamage(double amount) {
        stats.boss1takedamage(amount);
        if (stats.getHp() <= 0) {
            this.destroyInstance();
        }
    }

    public boss1stat getStats() {
        return stats;
    }

    
    public void update() {

        if (stats.isPhase2()) {
            executePhase2Logic();
        }
        
    }

    private void executePhase2Logic() {

    }
    @Override
    public void onTouched(GameObject obj) {
        this.movePostion(new Vector2D(-1, 0));
    }

    
}