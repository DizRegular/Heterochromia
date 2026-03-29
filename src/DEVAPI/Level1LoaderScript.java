/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import Boss1ass.Boss1;
import DEVAPI.CustomGameObject.Door;
import DEVAPI.CustomGameObject.OneWayPlatform;
import RenderObject.Creatable.Animator;
import RenderObject.Creatable.AreaDetector;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Camera;
import RenderObject.Creatable.Decoration;
import RenderObject.Creatable.Ladder;
import RenderObject.Creatable.Vector2D;
import RenderObject.Creatable.ViewPort;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import RenderObject.KinematicObject;
import RenderObject.ScriptSheet;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author asiau
 */
public class Level1LoaderScript extends ScriptSheet implements InputListener {
    public String floorImage = "res/GameAssets/Textures/Blank.png";
    public String wallImage = "res/GameAssets/Textures/Cobblestone.png";
    public String platformImage = "res/GameAssets/Textures/Platform.png";
    public String shelfImage = "res/GameAssets/Textures/Shelf.jpg";
    public KinematicObject player;
    public KinematicObject ball;
    public boolean setSize = false;
    public double lf=0;
    public double fall=-1;
    public boolean added = false;
    public int startjump=0;
    public int maxjump=3;
    public boolean jumping=false;
    public Camera cam1;
    public Animator frieanim;
    public Boss1 level1Boss; 
    public Level1LoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate(){
        GameUniverse.loadImage("floorImage", floorImage);
        GameUniverse.loadImage("wallImage", wallImage);
        GameUniverse.loadImage("platformImage", platformImage);
        GameUniverse.loadImage("shelfImage", shelfImage);
        GameUniverse.loadImage("realLaserImage", "res/Boss1/Skill 1/reallaser.png");
        GameUniverse.loadImage("realLaserXImage", "res/Boss1/Skill 1/reallaser X.png");
        GameUniverse.loadImage("redWarning", "res/waring.png");
        GameUniverse.loadImage("boss1Texture", "res/Boss1/Boss1_Idel.png");
        frieanim=GameUniverse.createInstance(new Animator("frie"));
        frieanim.createAnimationSheet(new Vector2D(1024, 1024), "res/Boss1/Skill 2/2/animfire.png");
        frieanim.setSpeed(10);
        GameUniverse.loadImage("sniperimage", "res/Boss1/Skill 2/IMG_4167.png");
        //floor
        for (int i = 0; i < 12; i++) {
            Block floor = GameUniverse.createInstance(new Block("Floor"));
            floor.setSize(new Vector2D(100,150));
            floor.setPosition(new Vector2D(i*100, 650));
            floor.setTexture("floorImage");
        }
        //roof
        for (int i = 0; i < 12; i++) {
            Block floor = GameUniverse.createInstance(new Block("Roof"));
            floor.setSize(new Vector2D(100,100));
            floor.setPosition(new Vector2D(i*100, -100));
            floor.setTexture("floorImage");
        }
        
        // Left Wall
        for (int i = 0; i < 8; i++) {
        Block leftWall = GameUniverse.createInstance(new Block("WallL"));
        leftWall.setSize(new Vector2D(100, 100));
        leftWall.setPosition(new Vector2D(-100, i*100));
        leftWall.setTexture("wallImage");
        }
        
        // Right Wall
        for (int i = 0; i < 8; i++) {
        Block rightWall = GameUniverse.createInstance(new Block("WallR"));
        rightWall.setSize(new Vector2D(100, 100));
        rightWall.setPosition(new Vector2D(1200, i*100));
        rightWall.setTexture("wallImage");
        }
        
        // Left platform1
        OneWayPlatform leftPlatform1 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform1.setSize(new Vector2D(325,10));
        leftPlatform1.setPosition(new Vector2D(70,455));
        leftPlatform1.setTexture("platformImage");
        
        // Left platform2
        OneWayPlatform leftPlatform2 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform2.setSize(new Vector2D(325,10));
        leftPlatform2.setPosition(new Vector2D(70,350));
        leftPlatform2.setTexture("platformImage");
        
        // Left platform3
        OneWayPlatform leftPlatform3 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform3.setSize(new Vector2D(325,10));
        leftPlatform3.setPosition(new Vector2D(70,235));
        leftPlatform3.setTexture("platformImage");
        
        // Right platform1
        OneWayPlatform rightPlatform1 = GameUniverse.createInstance(new OneWayPlatform("platformR"));
        rightPlatform1.setSize(new Vector2D(325,10));
        rightPlatform1.setPosition(new Vector2D(800,455));
        rightPlatform1.setTexture("platformImage");
        
        // Right platform2
        OneWayPlatform rightPlatform2 = GameUniverse.createInstance(new OneWayPlatform("platformR"));
        rightPlatform2.setSize(new Vector2D(325,10));
        rightPlatform2.setPosition(new Vector2D(800,350));
        rightPlatform2.setTexture("platformImage");
        
        // Right platform3
        OneWayPlatform rightPlatform3 = GameUniverse.createInstance(new OneWayPlatform("platformR"));
        rightPlatform3.setSize(new Vector2D(325,10));
        rightPlatform3.setPosition(new Vector2D(800,235));
        rightPlatform3.setTexture("platformImage");
        
        //1st floor ladder L
        Ladder LeftLadder1 = GameUniverse.createInstance(new Ladder("trussRight"));
        LeftLadder1.setSize(new Vector2D(100,200));
        LeftLadder1.setPosition(new Vector2D(275,460));
        LeftLadder1.setTexture("floorImage");
        
        //2nd floor ladder L
        Ladder LeftLadder2 = GameUniverse.createInstance(new Ladder("trussRight"));
        LeftLadder2.setSize(new Vector2D(75,100));
        LeftLadder2.setPosition(new Vector2D(200,360));
        LeftLadder2.setTexture("floorImage");
        
        //3rd floor ladder L
        Ladder LeftLadder3 = GameUniverse.createInstance(new Ladder("trussRight"));
        LeftLadder3.setSize(new Vector2D(75,110));
        LeftLadder3.setPosition(new Vector2D(255,230));
        LeftLadder3.setTexture("floorImage");
        
        //1st floor ladder R
        Ladder RightLadder1 = GameUniverse.createInstance(new Ladder("trussRight"));
        RightLadder1.setSize(new Vector2D(100,200));
        RightLadder1.setPosition(new Vector2D(825,460));
        RightLadder1.setTexture("floorImage");
        
        //2nd floor ladder R
        Ladder RightLadder2 = GameUniverse.createInstance(new Ladder("trussRight"));
        RightLadder2.setSize(new Vector2D(75,100));
        RightLadder2.setPosition(new Vector2D(900,360));
        RightLadder2.setTexture("floorImage");
        
        //3rd floor ladder R
        Ladder RightLadder3 = GameUniverse.createInstance(new Ladder("trussRight"));
        RightLadder3.setSize(new Vector2D(75,110));
        RightLadder3.setPosition(new Vector2D(825,230));
        RightLadder3.setTexture("floorImage");
        
        //boss
        level1Boss = GameUniverse.createInstance(new Boss1("ArtilleryBoss"));
    
        level1Boss.setSize(new Vector2D(200, 300));
        level1Boss.setTextureSize(new Vector2D(300,300));
        level1Boss.setPosition(new Vector2D(500, 250));
        level1Boss.setTexture("boss1Texture");
        level1Boss.setCollision(false);
        
//        StaticObject door=GameUniverse.createInstance(new StaticObject(name) {
//        });
//        door.setCollision(false);
//        door.setSize(new Vector2D(100,100));
//        door.setPosition(new Vector2D(200,350));
        //player 
        Animator idle = GameUniverse.createInstance(new Animator("idle"));
        idle.createAnimationSheet(new Vector2D(128, 128),"res/free_sprite/individual_sheets/male_hero_template-idle.png");
        idle.setSpeed(10);
        player = GameUniverse.createInstance(new KinematicObject("player"));
        player.setSize(new Vector2D(70, 120));
        player.setPosition(new Vector2D(0,500));
        player.addAnimator("idle", idle);
        player.setCurrentAnimator("idle");
        idle.setEnabled(true);
        
        //camera settings
        cam1 = GameUniverse.createInstance(new Camera("cam1"));
        cam1.setSize(new Vector2D(1200, 800));
        cam1.setPosition(new Vector2D(0, 0));
        ViewPort window1 = GameUniverse.createInstance(new ViewPort("window1"));
        window1.setEnabled(true);
        GameUniverse.setBackground("res/GameAssets/Background/Stage1Placeholder.jpg");
        try {
            window1.setCamera(cam1);
        } catch (InvalidGameObjectPropertyException e) {
            e.printStackTrace();
        }
        InputManager.registerInputListenerObject(this);
    }
    @Override
    public void process(double deltaTime) {
        if (setSize == false) {
            setSize = true;
            player.setTextureSize(new Vector2D(500, 500));
        }
        if (InputManager.isKeyDown('d')) {
            lf=5;
            player.flipXTexture(false);
        }
        else if (InputManager.isKeyDown('a')) {
            lf=-5;
            player.flipXTexture(true);
        }
        else{
            lf=0;
        }
        if (InputManager.isKeyDown('=')) {
            cam1.setZoomFactor(cam1.getZoomFactor() + 0.01);
        }
        if (InputManager.isKeyDown('-')) {
            cam1.setZoomFactor(cam1.getZoomFactor() - 0.01);
        }
        if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
            if(!jumping&&player.getTouchedFloor()){//add chack is tuch ground after addhitbox
                startjump=maxjump;
                jumping=true;
            }
        }
        if(jumping&&startjump>0){
            fall=-1;
            player.addAcceleration(new Vector2D(0, fall));
            startjump--;
        }
        else{
            fall=1;
        }
        if(jumping&&startjump<=0){
            jumping=false;
            startjump=0;
        }
        level1Boss.updateBossAI(deltaTime);
        if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
            if (player.getTouchedFloor()) {
                player.setVelocity(new Vector2D(0, -5));
            }
        }
        player.movePostion(new Vector2D(lf, 0));
    }

    @Override
    public void onInput() {
        //nuh uh
    }
}
