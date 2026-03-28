/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

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
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author asiau
 */
public class LevelTutorialLoaderScript extends ScriptSheet implements InputListener {
    public String floorImage1 = "res/GameAssets/Textures/Road1.png";
    public String floorImage2 = "res/GameAssets/Textures/Road2.png";
    public String floorImage3 = "res/GameAssets/Textures/Road3.png";
    public String floorImage4 = "res/GameAssets/Textures/Road4.png";
    public String PillarBase = "res/GameAssets/Textures/PillarBase.png";
    public String PillarTop = "res/GameAssets/Textures/PillarTop.png";
    public String wallImage = "res/GameAssets/Textures/Cobblestone.png";
    public String controlImage = "res/GameAssets/Textures/ControlGuide.jpg";
    public String controlImage2 = "res/GameAssets/Textures/ControlGuide2.jpg";
    public String controlImage3 = "res/GameAssets/Textures/ControlGuide3.jpg";
    public String TutorialPlatform = "res/GameAssets/Textures/TutorialPlatform.png";
    public String fenceImage = "res/GameAssets/Textures/Fence.png";
    public String blankImage = "res/GameAssets/Textures/Blank.png";
    public String dumpster = "res/GameAssets/Textures/dumpster.png";
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
        GameUniverse.loadImage("floorImage1", floorImage1);
        GameUniverse.loadImage("floorImage2", floorImage2);
        GameUniverse.loadImage("floorImage3", floorImage3);
        GameUniverse.loadImage("floorImage4", floorImage4);
        GameUniverse.loadImage("wallImage", wallImage);
        GameUniverse.loadImage("controlImage", controlImage);
        GameUniverse.loadImage("controlImage2", controlImage2);
        GameUniverse.loadImage("controlImage3", controlImage3);
        GameUniverse.loadImage("PillarBase", PillarBase);
        GameUniverse.loadImage("PillarTop", PillarTop);
        GameUniverse.loadImage("TutorialPlatform", TutorialPlatform);
        GameUniverse.loadImage("fenceImage", fenceImage);
        GameUniverse.loadImage("blankImage", blankImage);
        GameUniverse.loadImage("Trashcan", dumpster);
        
        //floor 1,4,7,10
        for (int i = 0; i < 4; i++) {
            Block floor1 = GameUniverse.createInstance(new Block("Platform1"));
            floor1.setSize(new Vector2D(100,100));
            floor1.setPosition(new Vector2D(0 + (i*300), 700));
            floor1.setTexture("floorImage1");
        }
        //floor 2,5,8,11
        for (int i = 0; i < 4; i++) {
            Block floor2 = GameUniverse.createInstance(new Block("Platform2"));
            floor2.setSize(new Vector2D(100,100));
            floor2.setPosition(new Vector2D(100 + (i*300), 700));
            floor2.setTexture("floorImage2");
        }
        //floor 3,6,9,12
        for (int i = 0; i < 4; i++) {
            Block floor3 = GameUniverse.createInstance(new Block("Platform3"));
            floor3.setSize(new Vector2D(100,100));
            floor3.setPosition(new Vector2D(200 + (i*300), 700));
            floor3.setTexture("floorImage1");
        }
            
        //roof
        for (int i = 0; i < 12; i++) {
            Block roof = GameUniverse.createInstance(new Block("Platform"));
            roof.setSize(new Vector2D(100,100));
            roof.setPosition(new Vector2D(i*100, -100));
            roof.setTexture("floorImage4");
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
        
        //ControlGuide
        Decoration Control1 = GameUniverse.createInstance(new Decoration("Control1"));
        Control1.setSize(new Vector2D(200, 150));
        Control1.setPosition(new Vector2D(100,400));
        Control1.setTexture("controlImage");
        
        //ControlGuide2
        Decoration Control2 = GameUniverse.createInstance(new Decoration("Control2"));
        Control2.setSize(new Vector2D(200, 150));
        Control2.setPosition(new Vector2D(100,200));
        Control2.setTexture("controlImage2");
        
        //ControlGuide3
        Decoration Control3 = GameUniverse.createInstance(new Decoration("Control3"));
        Control3.setSize(new Vector2D(250, 150));
        Control3.setPosition(new Vector2D(425,375));
        Control3.setTexture("controlImage3");
        
        //ladder-Pillar as example for climbing
        for (int i = 0; i < 5; i++) {
        Ladder PillarBase = GameUniverse.createInstance(new Ladder("PillarBase"));
        PillarBase.setSize(new Vector2D(100,100));
        PillarBase.setPosition(new Vector2D(300,(200 + i * 100)));
        PillarBase.setTexture("PillarBase");
        }
        //unclimbableTopPillar
        Decoration TopPillar = GameUniverse.createInstance(new Decoration("Top"));
        TopPillar.setSize(new Vector2D(100,100));
        TopPillar.setPosition(new Vector2D(300,100));
        TopPillar.setTexture("PillarTop");
        
        //platform on the top right of ladder 
        OneWayPlatform Platform1 = GameUniverse.createInstance(new OneWayPlatform("platform1"));
        Platform1.setSize(new Vector2D(200,50));
        Platform1.setPosition(new Vector2D(400,300));
        Platform1.setTexture("TutorialPlatform");
         
        //trashcan
        Decoration Trashcan = GameUniverse.createInstance(new Decoration("Trashcan"));
        Trashcan.setSize(new Vector2D(200, 150));
        Trashcan.setPosition(new Vector2D(500,550));
        Trashcan.setTexture("Trashcan");
        
        //platform on the top of trashcan to make it jumpable
        OneWayPlatform Platform2 = GameUniverse.createInstance(new OneWayPlatform("platform2"));
        Platform2.setSize(new Vector2D(200,50));
        Platform2.setPosition(new Vector2D(500,550));
        Platform2.setTexture("blankImage");
        
        //trashcan2
        Decoration Trashcan2 = GameUniverse.createInstance(new Decoration("Trashcan2"));
        Trashcan2.setSize(new Vector2D(200, 150));
        Trashcan2.setPosition(new Vector2D(800,550));
        Trashcan2.setTexture("Trashcan");
        
        //platform on the top of trashcan2 to make it jumpable
        OneWayPlatform Platform3 = GameUniverse.createInstance(new OneWayPlatform("platform3"));
        Platform3.setSize(new Vector2D(200,50));
        Platform3.setPosition(new Vector2D(800,550));
        Platform3.setTexture("blankImage");
        
        //fence on the right of trashcan
        Block fence = GameUniverse.createInstance(new Block("fence"));
            fence.setSize(new Vector2D(100,300));
            fence.setPosition(new Vector2D(700, 400));
            fence.setTexture("fenceImage");
            
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
