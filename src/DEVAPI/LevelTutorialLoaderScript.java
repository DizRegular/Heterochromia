/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

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
public class LevelTutorialLoaderScript extends ScriptSheet implements InputListener {
    public String floorImage = "res/GameAssets/Textures/Grass1.jpg";
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
    public LevelTutorialLoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate(){
        GameUniverse.loadImage("floorImage", floorImage);
        GameUniverse.loadImage("wallImage", wallImage);
        GameUniverse.loadImage("shelfImage", shelfImage);
        
        //floor
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
        
        // Left Shelf
        Decoration leftShelf = GameUniverse.createInstance(new Decoration("ShelfL"));
        leftShelf.setSize(new Vector2D(200, 200));
        leftShelf.setPosition(new Vector2D(0,500));
        leftShelf.setTexture("shelfImage");
        
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
        GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
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
