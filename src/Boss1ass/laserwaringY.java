/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss1ass;

/**
 *
 * @author tin_sel
 */
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.*;
import UniverseEngine.GameUniverse;
import java.util.logging.Level;
import java.util.logging.Logger;
public class laserwaringY extends StaticObject implements touchable,Scriptable {
    private double timer = 0;
    private double delaydestroy=0;
    private int blinkCount = 0;
    private final double blinkInterval = 15;
    private final double destroy=100; 
    private boolean havelaser=true;
    public laserwaringY(String name , Vector2D pos) {
        super(name);
        
        this.setTexture("redWarning");
    }
    @Override
    public void process(double deltaTime) {
        timer++;
        delaydestroy++;
        if (timer >= blinkInterval) {
            timer = 0;
            blinkCount++;
 
            this.setVisibility(!this.getVisibility());

            if (blinkCount >= 4) {
                spawnRealLaser();
                blinkCount=0;
                
            }}
        if(destroy<delaydestroy&&havelaser){ 
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa");
            GameUniverse.getObjectByName("RealLaserBeam", Block.class).destroyInstance();
            havelaser=false;
            this.destroyInstance();
            }
        
    }

    private void spawnRealLaser() {
        System.out.println("BAM!!");
        
        Block realLaser = GameUniverse.createInstance(new Block("RealLaserBeam"));
        realLaser.setPosition(this.getPosition());
        realLaser.setSize(this.getSize());
        realLaser.setTexture("realLaserImage");
        
    }

    @Override
    public void onTouched(GameObject obj) {
        System.out.println("hitlaser");
    }

    
}
