package DEVAPI.CustomGameObject.Player;

import DEVAPI.CustomGameObject.Damagable;
import DEVAPI.CustomGameObject.Parryable;
import DEVAPI.CustomGameObject.SpeedController;
import RenderObject.KinematicObject;

public class PlayerObject extends KinematicObject implements Damagable, SpeedController, Parryable{
    private double MaxHealthPoint = 200;
    private double HealthPoint = 200;
    private double BaseDamage = 12;
    
    private boolean invicibility = false;
    private boolean parryingb = false;
    private boolean death = false;
    
    private CharacterSet currCharacterSet = new Character1();
    private boolean hasCharacter1 = true;
    private boolean hasCharacter2 = false;
    private boolean hasCharacter3 = false;
    
    private Character1 char1 = new Character1();
    private Character2 char2 = new Character2();
    private Character3 char3 = new Character3();
    
    private double Speed = 5;
    
    public PlayerObject(String name) {
        super(name);
    }
    
    public CharacterSet getCurrentCharacterSet() {
        return this.currCharacterSet;
    }
    
    public boolean hasCharacter(int i) {
        if (i ==1) {
            return hasCharacter1;
        } else if (i ==2) {
            return hasCharacter2;
        } else if (i ==3) {
            return hasCharacter3;
        }
        return false;
    }
    
    public void switchCharacter(int i) {
        if (i == 1 && hasCharacter1) {
            currCharacterSet = char1;
        } else if (i ==2 && hasCharacter2) {
            currCharacterSet = char2;
        } else if (i ==3 && hasCharacter3) {
            currCharacterSet = char3;
        }
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public double getMaxHealthPoint() {
        return MaxHealthPoint;
    }

    public void setMaxHealthPoint(double MaxHealthPoint) {
        this.MaxHealthPoint = MaxHealthPoint;
    }

    
    @Override
    public double getSpeed() {
        return Speed;
    }

    @Override
    public void setSpeed(double Speed) {
        this.Speed = Speed;
    }
    
    public void unlockedCharacter(int i) {
        if (i == 1) this.hasCharacter2 = true;
        if (i == 2) this.hasCharacter2 = true;
        if (i == 3) this.hasCharacter3 = true;
    }

    @Override
    public void takeDamage(double damagePoint, Damagable culprit) {
        if (invicibility == true) return;
        if (this.getHealthPoint() - damagePoint >= this.getMaxHealthPoint()) {return;}
        this.setHealthPoint(this.getHealthPoint() - damagePoint);
    }

    public double getHealthPoint() {
        return HealthPoint;
    }

    public void setHealthPoint(double HealthPoint) {
        this.HealthPoint = HealthPoint;
    }

    public double getBaseDamage() {
        return BaseDamage;
    }

    public void setBaseDamage(double BaseDamage) {
        this.BaseDamage = BaseDamage;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.setGravity(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    
    
    
    @Override
    public boolean isParrying() {
        return this.parryingb;
    }

    @Override
    public void setParry(boolean b) {
        this.parryingb = b;
    }

    public boolean isInvicibility() {
        return invicibility;
    }

    public void setInvicibility(boolean invicibility) {
        this.invicibility = invicibility;
    }
    
    public void printStat() {
        System.out.println(this.HealthPoint + "HP| " + this.BaseDamage + "DP| ");
    }
    
}
