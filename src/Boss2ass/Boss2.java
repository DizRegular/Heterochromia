/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss2ass;

/**
 *
 * @author tin_sel
 */
import Boss1ass.laserwaringY;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import UniverseEngine.GameUniverse;
import java.util.Random;
public class Boss2 extends StaticObject implements touchable {
    private boss2stat stats;
    int shootTimer = 0;
    int shootCooldown = 180;
    int randomskill=0;
    int speed=5;
    boolean skillchage=false;
    boolean skill1useing=false;
    double dashSpeed = 20.0;
    int dashtime=0;
    Vector2D taget;
    Random rand = new Random();
    public Boss2(String name) {
        super(name);
        this.stats = new boss2stat();
    }

    public boss2stat getStats() {
        return stats;
    }


    public void takeDamage(double damage) {
        stats.boss1takedamage(damage);
        if (stats.getHp() <= 0) {
            this.destroyInstance();
        }
    }

    public void updateBossAI(double deltaTime){
        KinematicObject player=GameUniverse.getObjectByName("PikachuPlayer", KinematicObject.class);
        shootTimer++;
        if (shootTimer>=shootCooldown){
            randomskill=rand.nextInt(4) + 1;
            shootTimer=0;
        }
        if(randomskill==1){
            
            if (!skill1useing) {
                skill1useing = true; 
            }
            randomskill=0;
        }
        if(randomskill==2){
           
                leserX(new Vector2D(-1000, this.getPosition().getYCoord()));
            
            randomskill=0;
        }
        if(randomskill==3){
            
            randomskill=0;
        }
        if(randomskill==4){
            randomskill=0;
        }
        if(skill1useing&&this.getPosition().getXCoord()<player.getPosition().getXCoord()){
            this.movePostion(new Vector2D(dashSpeed,0));
        }
        else if(skill1useing&&this.getPosition().getXCoord()>player.getPosition().getXCoord()){
            this.movePostion(new Vector2D((dashSpeed*(-1)),0));
        }
        if(skill1useing){
            dashtime++;
        }
        if(deltaTime>=30){
            skill1useing=false;
        }
        
    }
    public void leserX(Vector2D leserposition) {
    laserwaringY warning = GameUniverse.createInstance(new laserwaringY("skill4boss2", leserposition));
    warning.setPosition(leserposition);  
    warning.setSize(new Vector2D(10000,100));
    
    }
    @Override
    public void onTouched(GameObject obj) {
        if(skill1useing){
            System.out.println("hp player--");
            skill1useing=false;
            //set anim defalse
        }
    }
}
