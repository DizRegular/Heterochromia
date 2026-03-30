/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss2ass;

/**
 *
 * @author tin_sel
 */
import DEVAPI.CustomGameObject.Damagable;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;

public class areasey  extends StaticObject implements touchable,Scriptable {
    int destroytime=0;
    boolean hit=false;
    public areasey(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
        if(obj instanceof Damagable d){
            if(!hit){
            d.takeDamage(30,GameUniverse.getObjectByName("samurai", Boss2.class) );hit=true;}
        }
    }

    @Override
    public void process(double deltaTime) {
        destroytime++;
        if(destroytime>=40){
            this.destroyInstance();
        }
    }
    
}
