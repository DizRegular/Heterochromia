/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss2ass;

/**
 *
 * @author tin_sel
 */
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.StaticObject;

public class areasey  extends StaticObject implements touchable,Scriptable {
    int destroytime=0;
    public areasey(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
        System.out.println("hitplayer");
    }

    @Override
    public void process(double deltaTime) {
        destroytime++;
        if(destroytime>=40){
            this.destroyInstance();
        }
    }
    
}
