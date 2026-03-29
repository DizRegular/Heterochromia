/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import Boss3ass.Boss3;
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
import UI.Pause;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author asiau
 */
public class Level3LoaderScript extends ScriptSheet implements InputListener {
    public String floorImage = "res/GameAssets/Textures/Blank.png";
    public String wallImage = "res/GameAssets/Textures/Blank.png";
    public String shelfImage = "res/GameAssets/Textures/Blank.png";
    public String platformImage = "res/GameAssets/Textures/Blank.png";
    public String ladderImage = "res/GameAssets/Textures/Blank.png";
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
    public Boss3 level1Boss;
    private Pause pauseUI;
    public Level3LoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate(){
        GameUniverse.loadImage("floorImage", floorImage);
        GameUniverse.loadImage("wallImage", wallImage);
        GameUniverse.loadImage("shelfImage", shelfImage);
        GameUniverse.loadImage("platformImage", platformImage);
        GameUniverse.loadImage("ladderImage", ladderImage);
        GameUniverse.loadImage("tub", "res/waring.png");
        //floor
        pauseUI =GameUniverse.createInstance(new Pause("PauseScreen", 0, 0, 1280, 720));
        for (int i = 0; i < 12; i++) {
            Block floor = GameUniverse.createInstance(new Block("Platform"));
            floor.setSize(new Vector2D(100,100));
            floor.setPosition(new Vector2D(i*100, 700));
            floor.setTexture("floorImage");
        }
        //roof
        for (int i = 0; i < 12; i++) {
            Block floor = GameUniverse.createInstance(new Block("Platform"));
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
        
        // Left platform1 floor1.1
        Block floor11 = GameUniverse.createInstance(new Block("floor11"));
            floor11.setSize(new Vector2D(75,10));
            floor11.setPosition(new Vector2D(325, 540));
            floor11.setTexture("floorImage");
            
        // Left platform1 floor1.2
        Block floor12 = GameUniverse.createInstance(new Block("floor12"));
            floor12.setSize(new Vector2D(550,10));
            floor12.setPosition(new Vector2D(500, 540));
            floor12.setTexture("floorImage");
        
        // Left platform1 floor1plat
        OneWayPlatform leftPlatform1 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform1.setSize(new Vector2D(250,10));
        leftPlatform1.setPosition(new Vector2D(0,450));
        leftPlatform1.setTexture("platformImage");
        
        // Right platform2 floor2plat
        OneWayPlatform rightPlatform2 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        rightPlatform2.setSize(new Vector2D(125,10));
        rightPlatform2.setPosition(new Vector2D(775,375));
        rightPlatform2.setTexture("platformImage");
        
        // Left platform2 floor2.1
        Block floor21 = GameUniverse.createInstance(new Block("floor21"));
            floor21.setSize(new Vector2D(475,10));
            floor21.setPosition(new Vector2D(325, 375));
            floor21.setTexture("floorImage");
            
        // Left platform2 floor2.2
        Block floor22 = GameUniverse.createInstance(new Block("floor22"));
            floor22.setSize(new Vector2D(150,10));
            floor22.setPosition(new Vector2D(900, 375));
            floor22.setTexture("floorImage");
        
        // Right platform3 floor3
        OneWayPlatform rightPlatform3 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        rightPlatform3.setSize(new Vector2D(100,10));
        rightPlatform3.setPosition(new Vector2D(500,210));
        rightPlatform3.setTexture("platformImage");
        
        // Left platform3 floor3.1
        Block floor31 = GameUniverse.createInstance(new Block("floor31"));
            floor31.setSize(new Vector2D(175,10));
            floor31.setPosition(new Vector2D(325, 210));
            floor31.setTexture("floorImage");
            
        // Left platform3 floor3.2
        Block floor32 = GameUniverse.createInstance(new Block("floor32"));
            floor32.setSize(new Vector2D(450,10));
            floor32.setPosition(new Vector2D(600, 210));
            floor32.setTexture("floorImage");
        
        //1st floor ladder
        Ladder Righttruss = GameUniverse.createInstance(new Ladder("trussRight"));
        Righttruss.setSize(new Vector2D(100,160));
        Righttruss.setPosition(new Vector2D(400,540));
        Righttruss.setTexture("ladderImage");
        
        //2nd floor ladder
        Ladder Righttruss2 = GameUniverse.createInstance(new Ladder("trussRight"));
        Righttruss2.setSize(new Vector2D(100,155));
        Righttruss2.setPosition(new Vector2D(800,385));
        Righttruss2.setTexture("ladderImage");
        
        //3rd floor ladder
        Ladder Righttruss3 = GameUniverse.createInstance(new Ladder("trussRight"));
        Righttruss3.setSize(new Vector2D(100,155));
        Righttruss3.setPosition(new Vector2D(500,220));
        Righttruss3.setTexture("ladderImage");
        
        //1st floor platform truck top
        OneWayPlatform Plat1 = GameUniverse.createInstance(new OneWayPlatform("Plat1"));
        Plat1.setSize(new Vector2D(100,10));
        Plat1.setPosition(new Vector2D(400,540));
        Plat1.setTexture("platformImage");
        
        //boss
        level1Boss = GameUniverse.createInstance(new Boss3("ArtilleryBoss"));
        level1Boss.setSize(new Vector2D(150, 200));
        level1Boss.setPosition(new Vector2D(800, 500));
        level1Boss.setTexture("boss1Texture");
        level1Boss.setCollision(false);
        Animator run=GameUniverse.createInstance(new Animator("samurun"));
        run.createAnimationSheet(new Vector2D(96, 96), "res/betasamu/a/b/RUN.png");
        run.setSpeed(10);
        run.setEnabled(true);
        level1Boss.addAnimator("samurun", run);
        Animator yeen=GameUniverse.createInstance(new Animator("sumumairun"));
        yeen.createAnimationSheet(new Vector2D(96, 96), "res/betasamu/a/b/IDLE.png");
        yeen.setSpeed(10);
        yeen.setEnabled(true);
        level1Boss.addAnimator("samumairun",yeen) ;
        
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
        GameUniverse.setBackground("res/GameAssets/Background/Stage3Placeholder.png");
        try {
            window1.setCamera(cam1);
        } catch (InvalidGameObjectPropertyException e) {
            e.printStackTrace();
        }
        InputManager.registerInputListenerObject(this);
    }
    @Override
    public void process(double deltaTime) {
        level1Boss.updateBossAI(deltaTime);
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
