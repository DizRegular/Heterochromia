/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI.CustomGameObject.Player;

import DEVAPI.CustomGameObject.Damagable;
import DEVAPI.CustomGameObject.SpeedController;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;

/**
 *
 * @author disk1
 */
public class HitBox extends KinematicObject implements Scriptable, touchable{
    private double timeOut = 999999999;
    private double timer = 0;
    private Damagable owner;
    private double OriginalSpeed;
    private double dealDamge = 0;
    private boolean hasHit = false;
    
    public HitBox(String name) {
        super(name);
    }
    
    public void setTimer(double d) {
        this.timeOut = d;
    }
    
    @Override
    public void onTouched(GameObject obj) {
        if (obj.getTags().contains("boss")) {
            if (obj instanceof Damagable d && !hasHit) {
                hasHit = true;
                d.takeDamage(this.getDealDamge(), d);
            }
        }
    }

    @Override
    public void process(double deltaTime) {
        if (this.hasCreate == false) return;
        timer += deltaTime;
        if (timer > timeOut) {
            this.destroyInstance();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.hasGravity = false;
    }

    
    
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public Damagable getOwner() {
        return owner;
    }

    public void setOwner(Damagable owner) {
        this.owner = owner;
    }

    public double getOriginalSpeed() {
        return OriginalSpeed;
    }

    public void setOriginalSpeed(double OriginalSpeed) {
        this.OriginalSpeed = OriginalSpeed;
    }

    public double getDealDamge() {
        return dealDamge;
    }

    public void setDealDamge(double dealDamge) {
        this.dealDamge = dealDamge;
    }
    
}
