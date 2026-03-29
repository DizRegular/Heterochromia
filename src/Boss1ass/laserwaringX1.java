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
public class laserwaringX1 extends StaticObject implements touchable,Scriptable {
    private double timer = 0;
    private double delaydestroy=0;
    private int blinkCount = 0;
    private final double blinkInterval = 15;
    private final double destroy=100; 
    private boolean havelaser=true;
    private spawnRealLaser real;
    public laserwaringX1(String name , Vector2D pos) {
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

            if (blinkCount >= 6) {
                this.real = GameUniverse.createInstance(new spawnRealLaser("reallaser"));
                real.setPosition(this.getPosition());
                real.setSize(this.getSize());
                real.setTexture("realLaserXImage");
                real.setCollision(false);
                real.setPosition(this.getPosition());
                blinkCount=0;
                System.out.println(real.getCollision());
            }}
            
        if(destroy<delaydestroy){ 
//             GameUniverse.getObjectByName("reallaser", spawnRealLaser.class).destroyInstance();
            this.destroyInstance();
            }
        
    }

//    private void spawnRealLaser() {
//        
//        Block realLaser = GameUniverse.createInstance(new Block("RealLaserBeam"));
//        realLaser.setPosition(this.getPosition());
//        realLaser.setSize(new Vector2D(66,10000));
//        realLaser.setTexture("realLaserImage");
//        realLaser.setCollision(false);
//        
//    }
    
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onTouched(GameObject obj) {
    }

    
}