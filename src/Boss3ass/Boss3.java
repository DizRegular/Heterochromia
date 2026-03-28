/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boss3ass;

/**
 *
 * @author tin_sel
 */
import Boss2ass.*;
import Boss1ass.laserwaringY;
import RenderObject.Addon.Scriptable;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import UniverseEngine.GameUniverse;
import java.util.Random;
public class Boss3 extends StaticObject implements touchable {
    private boss2stat stats;
    int shootTimer = 0;
    int shootCooldown = 180;
    int randomskill=0;
    int speed=1;
    boolean skillchage=false;
    boolean skill1useing=false;
    double dashSpeed = 20.0;
    boolean lef=false;
    boolean havelife=true;
    int dashtime=0;
    Vector2D taget;
    Random rand = new Random();
    public Boss3(String name) {
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
            havelife=false;
        }
    }

    public void updateBossAI(double deltaTime){
        KinematicObject player=GameUniverse.getObjectByName("PikachuPlayer", KinematicObject.class);
        shootTimer++;
        if (shootTimer>=shootCooldown&&havelife){
            randomskill=rand.nextInt(3) + 1;
            shootTimer=0;
        }
        if(randomskill==1){
            if (!skill1useing) {
                skill1useing = true; 
                if(this.getPosition().getXCoord()>player.getPosition().getXCoord()){
                    lef=true;
                }
                else{
                    lef=false;
            }
            randomskill=0;
        }}
        if(randomskill==2){
             if(this.getPosition().getXCoord()>player.getPosition().getXCoord()){
                funna(new Vector2D(this.getPosition().getXCoord()-300,this.getPosition().getYCoord()-200));
            }
            else{
                funna(new Vector2D(this.getPosition().getXCoord()+100,this.getPosition().getYCoord()-200));
            }
            randomskill=0;
        }
        if(randomskill==3){
           
            randomskill=0;
        }
         if(skill1useing&&!!lef){
            this.movePostion(new Vector2D(dashSpeed,0));
        }
        else if(skill1useing&&lef){
            this.movePostion(new Vector2D((dashSpeed*(-1)),0));
        }
        else if(this.getPosition().getXCoord()>player.getPosition().getXCoord()&&this.getPosition().getXCoord()-player.getPosition().getXCoord()>100){
            this.movePostion(new Vector2D((speed*(-1)),0));
        }
        else if (this.getPosition().getXCoord()<player.getPosition().getXCoord()&&player.getPosition().getXCoord()-this.getPosition().getXCoord()>200){
            this.movePostion(new Vector2D(speed,0));
        }
        if(skill1useing){
            dashtime++;
        }
        if(dashtime>=30){
            dashtime=0;
            skill1useing=false;

        }
        if(this.getPosition().getXCoord()>=1200 ||this.getPosition().getXCoord()<=-100){
            dashtime=0;
            skill1useing=false;
        }
            
        
    }
    public void leserX(Vector2D leserposition) {
    laserwaringX3 warning = GameUniverse.createInstance(new laserwaringX3("skill4boss2", leserposition));
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

   
     public void funna(Vector2D taget){
        areasey fun=GameUniverse.createInstance(new areasey("sey"));
        fun.setPosition(taget);
        fun.setSize(new Vector2D(300,500));
    } 
    
    }

