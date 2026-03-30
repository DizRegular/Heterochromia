/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss3ass;

import DEVAPI.CustomGameObject.Damagable;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;

/**
 *
 * @author tin_sel
 */
public class funtung2 extends StaticObject implements touchable, Scriptable {

    private double delaydestroy = 0;
    boolean hit = false;

    public funtung2(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onTouched(GameObject obj) {
        if (obj instanceof Damagable d) {
            if (!hit) {
                d.takeDamage(80, GameUniverse.getObjectByName("rock", Boss3.class));
                hit = true;
            }
        }
    }

    @Override
    public void process(double deltaTime) {
        delaydestroy++;
        if (delaydestroy >= 40) {
            this.destroyInstance();
        }
    }
}
