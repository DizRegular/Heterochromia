/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import Boss2ass.Boss2;
import DEVAPI.CustomGameObject.SceneController;
import static DEVAPI.Level2LoaderScript.boss2die;
import RenderObject.BaseObject;
import RenderObject.Creatable.Animator;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Camera;
import RenderObject.Creatable.Portal3;
import RenderObject.Creatable.Vector2D;
import RenderObject.Creatable.ViewPort;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import RenderObject.KinematicObject;
import RenderObject.ScriptSheet;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.util.ArrayList;

/**
 *
 * @author asiau
 */
public class Level2LoaderScript extends ScriptSheet implements InputListener, SceneController {
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
    public Boss2 level2Boss;
    public static boolean boss2die=false;
    public ArrayList<BaseObject> itemInScene = new ArrayList<>();
    private Portal3 PortalObj;
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
            itemInScene.add(floor);
        }
        //roof
        for (int i = 0; i < 12; i++) {
            Block roof = GameUniverse.createInstance(new Block("Platform"));
            roof.setSize(new Vector2D(100,100));
            roof.setPosition(new Vector2D(i*100, -100));
            roof.setTexture("floorImage");
            itemInScene.add(roof);
        }
        
        // Left Wall
        for (int i = 0; i < 8; i++) {
            Block leftWall = GameUniverse.createInstance(new Block("WallL"));
            leftWall.setSize(new Vector2D(100, 100));
            leftWall.setPosition(new Vector2D(-100, i*100));
            leftWall.setTexture("wallImage");
            itemInScene.add(leftWall);
        }
        
        // Right Wall
        for (int i = 0; i < 8; i++) {
            Block rightWall = GameUniverse.createInstance(new Block("WallR"));
            rightWall.setSize(new Vector2D(100, 100));
            rightWall.setPosition(new Vector2D(1200, i*100));
            rightWall.setTexture("wallImage");
            itemInScene.add(rightWall);
        }
        
         //boss
        level2Boss = GameUniverse.createInstance(new Boss2("samurai"));
        level2Boss.setSize(new Vector2D(150, 200));
        level2Boss.setPosition(new Vector2D(800, 450));
        level2Boss.setTexture("boss1Texture");
        level2Boss.setCollision(false);
        Animator run=GameUniverse.createInstance(new Animator("samurun"));
        run.createAnimationSheet(new Vector2D(1024, 1024), "res/Boss 2/Dash/realdash.png");
        run.setSpeed(10);
        run.setEnabled(true);
        level2Boss.addAnimator("samurun", run);
        Animator yeen=GameUniverse.createInstance(new Animator("sumumairun"));
        yeen.createAnimationSheet(new Vector2D(1024, 1024), "res/Boss 2/IMG_4173.png");
        yeen.setSpeed(10);
        yeen.setEnabled(true);
        level2Boss.addAnimator("samumairun",yeen);
        level2Boss.addTags("boss");
        
        GameUniverse.setBackground("res/GameAssets/Background/Stage2Placeholder.jpg");
         InputManager.registerInputListenerObject(this);
    }
    @Override
    public void process(double deltaTime) {
        level2Boss.updateBossAI(deltaTime);
        if(boss2die){
            PortalObj = GameUniverse.createInstance(new Portal3("PortalObj"));
            PortalObj.addAnimator("portalAnim", GameUniverse.getObjectByName("portalAnim", Animator.class));
            PortalObj.setCurrentAnimator("portalAnim", 0);
            PortalObj.setSize(new Vector2D(200, 300));
            PortalObj.setPosition(new Vector2D(1000, 350));
            itemInScene.add(PortalObj);
            boss2die=false;
    }
    }
    @Override
    public void deleteSceneItem() {
    for (BaseObject obj : itemInScene) {
        obj.destroyInstance();
    }
    itemInScene.clear(); // Good practice to empty the list
    
    }
    
    @Override
    public void onInput() {
        //nuh uh
    }
}