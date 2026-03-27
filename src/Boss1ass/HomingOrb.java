/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss1ass;

/**
 *
 * @author tin_sel
 */
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;



public class HomingOrb extends StaticObject implements touchable,Scriptable {

    bullet bu;
    int tx=0;
    int ty=0;
    boolean build=false;
    private int destroytime=0;
    public HomingOrb(String name) {
        super(name);
    }
    
        

    @Override
    public void onTouched(GameObject obj) {
    }

    @Override
    public void process(double deltaTime) {
        destroytime++;
        KinematicObject player=GameUniverse.getObjectByName("PikachuPlayer", KinematicObject.class);
        if(!build){
        this.bu=GameUniverse.createInstance(new bullet("sniper"));
        bu.setPosition(this.position);
        bu.setSize(new Vector2D(50,50));
        build=true;
        }
        if(this.getPosition().getXCoord()<player.getPosition().getXCoord()){
            tx=2;
        }
        else if(this.getPosition().getXCoord()>player.getPosition().getXCoord()){
            tx=-2;
        }
        else{
            tx=0;
        }
        if(this.getPosition().getYCoord()<player.getPosition().getYCoord()){
            ty=2;
        }
        else if(this.getPosition().getYCoord()>player.getPosition().getYCoord()){
            ty=-2;
        }
        else{
            ty=0;
        }
        this.movePostion(new Vector2D(tx, ty));
        if (destroytime>=300){
            this.destroyInstance();
        }
    }

}

