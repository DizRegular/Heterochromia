/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import Boss2ass.Boss2;
import DEVAPI.CustomGameObject.Door;
import RenderObject.Creatable.Animator;
import RenderObject.Creatable.AreaDetector;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Camera;
import RenderObject.Creatable.Decoration;
import RenderObject.Creatable.Vector2D;
import RenderObject.Creatable.ViewPort;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import RenderObject.KinematicObject;
import RenderObject.ScriptSheet;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author asiau
 */
public class Level2LoaderScript extends ScriptSheet implements InputListener {
    public String floorImage = "res/GameAssets/Textures/Blank.png";
    public String wallImage = "res/GameAssets/Textures/Cobblestone.png";
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
    public Boss2 level1Boss;
    public static boolean boss2die=false;
    public Level2LoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate(){
        GameUniverse.loadImage("floorImage", floorImage);
        GameUniverse.loadImage("wallImage", wallImage);
        GameUniverse.loadImage("shelfImage", shelfImage);
        GameUniverse.loadImage("samuwarning", "res/waring.png");
        GameUniverse.loadImage("slashsamurai", "res/Boss 2/Big slash/IMG_4187.png");
        GameUniverse.loadImage("funkunte","res/Boss 2/Slash/IMG_4181.png" );
        //floor
        for (int i = 0; i < 12; i++) {
            Block floor = GameUniverse.createInstance(new Block("Floor"));
            floor.setSize(new Vector2D(100,150));
            floor.setPosition(new Vector2D(i*100, 650));
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
        
         //boss
        level1Boss = GameUniverse.createInstance(new Boss2("ArtilleryBoss"));
        level1Boss.setSize(new Vector2D(150, 200));
        level1Boss.setPosition(new Vector2D(800, 450));
        level1Boss.setTexture("boss1Texture");
        level1Boss.setCollision(false);
        Animator run=GameUniverse.createInstance(new Animator("samurun"));
        run.createAnimationSheet(new Vector2D(1024, 1024), "res/Boss 2/Dash/realdash.png");
        run.setSpeed(10);
        run.setEnabled(true);
        level1Boss.addAnimator("samurun", run);
        Animator yeen=GameUniverse.createInstance(new Animator("sumumairun"));
        yeen.createAnimationSheet(new Vector2D(1024, 1024), "res/Boss 2/IMG_4173.png");
        yeen.setSpeed(10);
        yeen.setEnabled(true);
        level1Boss.addAnimator("samumairun",yeen);
        level1Boss.addTags("boss");
        
        //player 
        Animator idle = GameUniverse.createInstance(new Animator("idle"));
        idle.createAnimationSheet(new Vector2D(128, 128),"res/free_sprite/individual_sheets/male_hero_template-idle.png");
        idle.setSpeed(10);
        player = GameUniverse.createInstance(new KinematicObject("player"));
        player.setSize(new Vector2D(70, 120));
        player.setPosition(new Vector2D(0,500));
        player.addAnimator("idle", idle);
        player.setCurrentAnimator("idle", 0);
        idle.setEnabled(true);
        
        //camera settings
        cam1 = GameUniverse.createInstance(new Camera("cam1"));
        cam1.setSize(new Vector2D(1200, 800));
        cam1.setPosition(new Vector2D(0, 0));
        ViewPort window1 = GameUniverse.createInstance(new ViewPort("window1"));
        window1.setEnabled(true);
        GameUniverse.setBackground("res/GameAssets/Background/Stage2Placeholder.jpg");
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
        if(boss2die){
            //plase potal this
            boss2die=false;
        }
    }

    @Override
    public void onInput() {
        //nuh uh
    }
}
