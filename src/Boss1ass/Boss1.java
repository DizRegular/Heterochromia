/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author tin_sel
 */
package Boss1ass;

import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import UniverseEngine.GameUniverse;

public class Boss1 extends StaticObject implements touchable {
    
    private boss1stat stats;
    
    public Boss1(String name) {
        super(name);
        this.stats = new boss1stat();
    }

    public boss1stat getStats() {
        return stats;
    }


    public void takeDamage(double damage) {
        stats.boss1takedamage(damage);
        System.out.println("Boss HP: " + stats.getHp());
        
        if (stats.getHp() <= 0) {
            System.out.println("Boss Defeated!");
            this.destroyInstance();
        }
    }

    public void updateBossAI(double deltaTime, Vector2D playerPos) {
        if (stats.isPhase2()) {
            
        }
    }

    @Override
    public void onTouched(GameObject obj) {
    }
    public void leserY(Vector2D leserposition) {
    laserwaringY warning = GameUniverse.createInstance(new laserwaringY(name, leserposition));
    warning.setPosition(leserposition);  
    warning.setSize(new Vector2D(66,10000));
    
    }
}