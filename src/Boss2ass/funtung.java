/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss2ass;

import Boss1ass.*;
import DEVAPI.CustomGameObject.Damagable;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;

/**
 *
 * @author tin_sel
 */
public class funtung extends StaticObject implements touchable,Scriptable {
     private double delaydestroy=0;
     boolean hit=false;
    public funtung(String name) {
        super(name);
    }

    
    @Override
    public void onCreate(){
        super.onCreate();
        
    }

    @Override
    public void onTouched(GameObject obj) {
                       if(obj instanceof Damagable d){
                           if(!hit){
            d.takeDamage(50,GameUniverse.getObjectByName("samurai", Boss2.class) );hit=true;}
        }
    }

    @Override
    public void process(double deltaTime) {
        delaydestroy++;
        if(delaydestroy>=40){
            this.destroyInstance();
        }
    }
}
