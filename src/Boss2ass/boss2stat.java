/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss2ass;

import Boss1ass.*;

/**
 *
 * @author tin_sel
 */
public class boss2stat {
    double maxhp=100;
    double hp=maxhp;
    double atk=20;
    boolean phase2=false;
    
    public boss2stat(){
        
    }
    
    public double getMaxhp() {
        return maxhp;
    }

    public void setMaxhp(double maxhp) {
        this.maxhp = maxhp;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public double getAtk() {
        return atk;
    }

    public void setAtk(double atk) {
        this.atk = atk;
    }

    public boolean isPhase2() {
        return phase2;
    }

    public void setPhase2(boolean phase2) {
        this.phase2 = phase2;
    }
    public void boss1takedamage(double a) {
        setHp(hp - a);
        
        if (hp <= maxhp / 2 && !phase2) {
            phase2 = true;
            atk = 30;
        }
    }
    
}
