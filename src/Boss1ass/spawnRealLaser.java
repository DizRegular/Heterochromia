/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss1ass;

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
public class spawnRealLaser extends StaticObject implements touchable,Scriptable {
     private double delaydestroy=0;
    public spawnRealLaser(String name) {
        super(name);
    }

    
    @Override
    public void onCreate(){
        super.onCreate();
        
    }

    @Override
    public void onTouched(GameObject obj) {
                        System.out.println("kuyyyyyyyyy");

    }

    @Override
    public void process(double deltaTime) {
        delaydestroy++;
        if(delaydestroy>=40){
            this.destroyInstance();
        }
    }
}