/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss1ass;

import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.StaticObject;

/**
 *
 * @author tin_sel
 */
public class bullet extends StaticObject implements touchable,Scriptable {
    private int destroytime=0;
    
    public bullet(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
        
    }

    @Override
    public void process(double deltaTime) {
        destroytime++;
        if (destroytime>=150){
            this.destroyInstance();
        }
    }
    
}
