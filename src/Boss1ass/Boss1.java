/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author tin_sel
 */
package Boss1ass;

import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Animator;
import UniverseEngine.GameUniverse;
import java.util.Random;

public class Boss1 extends StaticObject implements touchable {
    
    private boss1stat stats;
    int shootTimer = 0;
    int shootCooldown = 180;
    int randomskill=0;
    boolean havelife=true;
    public Animator frieanim;
    Random rand = new Random();
    public Boss1(String name) {
        super(name);
        this.stats = new boss1stat();
    }

    public boss1stat getStats() {
        return stats;
    }


    public void takeDamage(double damage) {
        stats.boss1takedamage(damage);
        if (stats.getHp() <= 0) {
            this.destroyInstance();
            havelife=false;
        }
    }
//    @Override
//    public void onCreate(){
//        frieanim=GameUniverse.createInstance(new Animator("frie"));
//        frieanim.createAnimationSheet(new Vector2D(1024, 1024), "res/Boss1/Skill 2/2/animfire.png");
//        frieanim.setSpeed(10);
//    }
    public void updateBossAI(double deltaTime){
        
        KinematicObject player=GameUniverse.getObjectByName("player", KinematicObject.class);
        shootTimer++;
        if (shootTimer>=shootCooldown&&havelife){
            randomskill=rand.nextInt(4) + 1;
            shootTimer=0;
        }
        if(randomskill==1){
            for (int i=0 ;i<5;i++){
                leserY(new Vector2D(this.getPosition().getXCoord()-500+(i*250), -350));
            }
            randomskill=0;
        }
        if(randomskill==2){
            for (int i=0 ;i<5;i++){
                leserX(new Vector2D(-1000, this.getPosition().getYCoord()-450+(i*250)));
            }
            randomskill=0;
        }
        if(randomskill==3){
            for (int i=0 ;i<3;i++){
                shooting(new Vector2D(this.getPosition().getXCoord()-100+(i*200),this.getPosition().getYCoord()));
            }
            randomskill=0;
        }
        if(randomskill==4){
            leserY(new Vector2D(player.getPosition().getXCoord(), -350));
            randomskill=0;
        }
        if (stats.isPhase2()) {
            
            randomskill=0;
        }
    }

    @Override
    public void onTouched(GameObject obj) {
    }
    public void leserY(Vector2D leserposition) {
    laserwaringY warning = GameUniverse.createInstance(new laserwaringY("laser", leserposition));
    warning.setPosition(leserposition);  
    warning.setSize(new Vector2D(66,10000));
    warning.setCollision(false);
    
    }
    public void leserX(Vector2D leserposition) {
    laserwaringX1 warning = GameUniverse.createInstance(new laserwaringX1("laser", leserposition));
    warning.setPosition(leserposition);  
    warning.setSize(new Vector2D(10000,66));
    warning.setCollision(false);
    }
    public void shooting(Vector2D st){
        HomingOrb bul=GameUniverse.createInstance(new HomingOrb("bullet"));
        bul.setPosition(st);
        bul.setSize(new Vector2D(40,40));
        bul.setTextureSize(new Vector2D(200,200));
        bul.setCollision(false);
        bul.addAnimator("frie",GameUniverse.getObjectByName("frie",Animator.class ) );
        bul.setCurrentAnimator("frie");
    }
}