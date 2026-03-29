/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import DEVAPI.CustomGameObject.Door;
import DEVAPI.CustomGameObject.Player.PlayerLoaderScript;
import DEVAPI.CustomGameObject.SceneController;
import RenderObject.BaseObject;
import RenderObject.Creatable.OneWayPlatform;

import RenderObject.Creatable.Animator;
import RenderObject.Creatable.AreaDetector;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Camera;
import RenderObject.Creatable.Decoration;
import RenderObject.Creatable.Ladder;
import RenderObject.Creatable.Portal1;
import RenderObject.Creatable.Vector2D;
import RenderObject.Creatable.ViewPort;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import RenderObject.KinematicObject;
import RenderObject.ScriptSheet;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author asiau
 */
public class LevelTutorialLoaderScript extends ScriptSheet implements InputListener, SceneController {
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
    private KinematicObject playerObj;
    private Portal1 portalObj;
    public ArrayList<BaseObject> itemInScene = new ArrayList<>();
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
            itemInScene.add(floor1);
        }
        //floor 2,5,8,11
        for (int i = 0; i < 4; i++) {
            Block floor2 = GameUniverse.createInstance(new Block("Platform2"));
            floor2.setSize(new Vector2D(100,100));
            floor2.setPosition(new Vector2D(100 + (i*300), 700));
            floor2.setTexture("floorImage2");
            itemInScene.add(floor2);
        }
        //floor 3,6,9,12
        for (int i = 0; i < 4; i++) {
            Block floor3 = GameUniverse.createInstance(new Block("Platform3"));
            floor3.setSize(new Vector2D(100,100));
            floor3.setPosition(new Vector2D(200 + (i*300), 700));
            floor3.setTexture("floorImage1");
            itemInScene.add(floor3);
        }
            
        //roof
        for (int i = 0; i < 12; i++) {
            Block roof = GameUniverse.createInstance(new Block("Platform"));
            roof.setSize(new Vector2D(100,100));
            roof.setPosition(new Vector2D(i*100, -100));
            roof.setTexture("floorImage4");
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
            
        //ladder-Pillar as example for climbing
        for (int i = 0; i < 5; i++) {
        Ladder PillarBase = GameUniverse.createInstance(new Ladder("PillarBase"));
        PillarBase.setSize(new Vector2D(100,100));
        PillarBase.setPosition(new Vector2D(300,(200 + i * 100)));
        PillarBase.setTexture("PillarBase");
        itemInScene.add(PillarBase);
        }
        //unclimbableTopPillar
        Decoration TopPillar = GameUniverse.createInstance(new Decoration("Top"));
        TopPillar.setSize(new Vector2D(100,100));
        TopPillar.setPosition(new Vector2D(300,100));
        TopPillar.setTexture("PillarTop");
        itemInScene.add(TopPillar);
        
        //platform on the top right of ladder 
        OneWayPlatform Platform1 = GameUniverse.createInstance(new OneWayPlatform("platform1"));
        Platform1.setSize(new Vector2D(200,50));
        Platform1.setPosition(new Vector2D(400,300));
        Platform1.setTexture("TutorialPlatform");
        itemInScene.add(Platform1);
           
        //platform on the top of trashcan to make it jumpable
        OneWayPlatform Platform2 = GameUniverse.createInstance(new OneWayPlatform("platform2"));
        Platform2.setSize(new Vector2D(200,50));
        Platform2.setPosition(new Vector2D(500,550));
        Platform2.setTexture("blankImage");
        itemInScene.add(Platform2);
        
        //platform on the top of trashcan2 to make it jumpable
        OneWayPlatform Platform3 = GameUniverse.createInstance(new OneWayPlatform("platform3"));
        Platform3.setSize(new Vector2D(200,50));
        Platform3.setPosition(new Vector2D(800,550));
        Platform3.setTexture("blankImage");
        itemInScene.add(Platform3);
        
        //fence on the right of trashcan
        Block fence = GameUniverse.createInstance(new Block("fence"));
            fence.setSize(new Vector2D(100,300));
            fence.setPosition(new Vector2D(700, 400));
            fence.setTexture("fenceImage");
        itemInScene.add(fence);

        this.portalObj = GameUniverse.createInstance(new Portal1("PortalObj"));
        this.portalObj.setSize(new Vector2D(200, 300));
        this.portalObj.setPosition(new Vector2D(1000, 400));
        portalObj.addAnimator("portalAnim", GameUniverse.getObjectByName("portalAnim", Animator.class));
        portalObj.setCurrentAnimator("portalAnim", 0);
        itemInScene.add(portalObj);
        
        //camera settings
        GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
        InputManager.registerInputListenerObject(this);
        
        //player 
    }
    
    @Override
    public void deleteSceneItem() {
    for (BaseObject obj : itemInScene) {
        obj.destroyInstance();
    }
    itemInScene.clear(); // Good practice to empty the list
    
    }
    @Override
    public void process(double deltaTime) {
        //Non
    }

    @Override
    public void onInput() {
        //not used yet
    }
}
