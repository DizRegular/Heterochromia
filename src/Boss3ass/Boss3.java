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
import DEVAPI.CustomGameObject.Damagable;
import RenderObject.Addon.Scriptable;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import UniverseEngine.GameUniverse;
import java.util.Random;

public class Boss3 extends StaticObject implements touchable, Damagable {
    private boss3stat stats;
    boolean skill2=false;
    int animing=0;
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
        this.stats = new boss3stat();
    }

    public boss3stat getStats() {
        return stats;
    }

//-----------------------------------------------------------------------------------------------------------------
    public void takeDamage(double damage, Damagable culprit) {
        stats.boss1takedamage(damage);
        //rammus
        if (stats.getHp() <= 0) {
            this.destroyInstance();
            havelife=false;
        }
    }
//------------------------------------------------------------------------------------------------------------------
    public void updateBossAI(double deltaTime){
        KinematicObject player=GameUniverse.getObjectByName("ThePlayer", KinematicObject.class);
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
            skill2=true;
             if(this.getPosition().getXCoord()>player.getPosition().getXCoord()){
                funna(new Vector2D(this.getPosition().getXCoord()-100,this.getPosition().getYCoord()));
                this.setCurrentAnimator("nomalatk",0);
                this.flipXTexture(false);
            }
            else{
                funna(new Vector2D(this.getPosition().getXCoord()+100,this.getPosition().getYCoord()));
                this.setCurrentAnimator("nomalatk",0);
                this.flipXTexture(true);
            }
            randomskill=0;
        }
        if(randomskill==3){
            leserX(new Vector2D(-1000, this.getPosition().getYCoord()+100));
            randomskill=0;
        }
         if(skill1useing&&!lef){
            this.movePostion(new Vector2D(dashSpeed,0));
            this.setCurrentAnimator("samurun", 0);
            this.flipXTexture(true);
        }
        else if(skill1useing&&lef){
            this.movePostion(new Vector2D((dashSpeed*(-1)),0));
            this.setCurrentAnimator("samurun", 0);
            this.flipXTexture(false);
        }
        else if (skill2){
            this.setCurrentAnimator("nomalatk",0);
        }
        else if(this.getPosition().getXCoord()>player.getPosition().getXCoord()&&this.getPosition().getXCoord()-player.getPosition().getXCoord()>100){
            this.movePostion(new Vector2D((speed*(-1)),0));

            this.setCurrentAnimator("samumairun", 0);
            this.flipXTexture(false);
        }
        else if (this.getPosition().getXCoord()<player.getPosition().getXCoord()&&player.getPosition().getXCoord()-this.getPosition().getXCoord()>200){
            this.movePostion(new Vector2D(speed,0));
            this.setCurrentAnimator("samumairun", 0);
            this.flipXTexture(true);
        }
        else{
            this.setCurrentAnimator("samumairun", 0);
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
        if(skill2){
            animing++;
        }
       if (animing>=60){
           skill2=false;
           animing=0;
       }    
        
    }
    public void leserX(Vector2D leserposition) {
    laserwaringX3 warning = GameUniverse.createInstance(new laserwaringX3("skill4boss2", leserposition));
    warning.setPosition(leserposition);  
    warning.setSize(new Vector2D(10000,100));
    warning.setCollision(false);
    
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
        fun.setCollision(false);
        fun.setSize(new Vector2D(150,150));
        fun.setVisibility(false);
    } 

    
    }

