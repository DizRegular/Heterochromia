/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import Boss1ass.Boss1;
import DEVAPI.CustomGameObject.Door;
import DEVAPI.CustomGameObject.Player.PlayerObject;
import DEVAPI.CustomGameObject.SceneController;
import RenderObject.BaseObject;
import RenderObject.Creatable.OneWayPlatform;
import RenderObject.Creatable.Animator;
import RenderObject.Creatable.AreaDetector;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Camera;
import RenderObject.Creatable.Decoration;
import RenderObject.Creatable.Ladder;
import RenderObject.Creatable.Portal2;
import RenderObject.Creatable.Vector2D;
import RenderObject.Creatable.ViewPort;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import RenderObject.KinematicObject;
import RenderObject.ScriptSheet;
import RenderObject.StaticObject;
import UI.Boss1HpDisplay;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author asiau
 */
public class Level1LoaderScript extends ScriptSheet implements InputListener, SceneController {
    public String floorImage1 = "res/GameAssets/Textures/Blank.png";
    public String wallImage1 = "res/GameAssets/Textures/Cobblestone.png";
    public String platformImage1 = "res/GameAssets/Textures/Platform.png";
    public String shelfImage1 = "res/GameAssets/Textures/Shelf.jpg";
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
    public Block leftWall;
    public Block rightWall;
    public OneWayPlatform leftPlatform1;
    public OneWayPlatform leftPlatform2;
    public OneWayPlatform leftPlatform3;
    public  OneWayPlatform rightPlatform1;
    public OneWayPlatform rightPlatform2;
    public OneWayPlatform rightPlatform3;
    public  Ladder LeftLadder1;
    public Ladder LeftLadder2;
    public  Ladder LeftLadder3;
    public Ladder RightLadder1;
    public Ladder RightLadder2;
    public Ladder RightLadder3;
    public ArrayList<BaseObject> itemInScene = new ArrayList<>();
    private Portal2 PortalObj;
    public static boolean boss1die=false;
    public boolean hasBoss1Create = false;
    
    public Level1LoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate(){
        GameUniverse.loadImage("floorImage", floorImage1);
        GameUniverse.loadImage("wallImage", wallImage1);
        GameUniverse.loadImage("platformImage", platformImage1);
        GameUniverse.loadImage("shelfImage", shelfImage1);
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
            itemInScene.add(floor);
        }
        //roof
        for (int i = 0; i < 12; i++) {
            Block Roof = GameUniverse.createInstance(new Block("Roof"));
            Roof.setSize(new Vector2D(100,100));
            Roof.setPosition(new Vector2D(i*100, -100));
            Roof.setTexture("floorImage");
            itemInScene.add(Roof);
        }
        
        // Left Wall
        for (int i = 0; i < 8; i++) {
        leftWall = GameUniverse.createInstance(new Block("WallL"));
        leftWall.setSize(new Vector2D(100, 100));
        leftWall.setPosition(new Vector2D(-100, i*100));
        leftWall.setTexture("wallImage");
        itemInScene.add(leftWall);
        }
        
        // Right Wall
        for (int i = 0; i < 8; i++) {
        rightWall = GameUniverse.createInstance(new Block("WallR"));
        rightWall.setSize(new Vector2D(100, 100));
        rightWall.setPosition(new Vector2D(1200, i*100));
        rightWall.setTexture("wallImage");
        itemInScene.add(rightWall);
        }
        
        // Left platform1
        leftPlatform1 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform1.setSize(new Vector2D(325,10));
        leftPlatform1.setPosition(new Vector2D(70,455));
        leftPlatform1.setTexture("platformImage");
        itemInScene.add(leftPlatform1);
        
        // Left platform2
        leftPlatform2 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform2.setSize(new Vector2D(325,10));
        leftPlatform2.setPosition(new Vector2D(70,350));
        leftPlatform2.setTexture("platformImage");
        itemInScene.add(leftPlatform2);
        
        // Left platform3
        leftPlatform3 = GameUniverse.createInstance(new OneWayPlatform("platformL"));
        leftPlatform3.setSize(new Vector2D(325,10));
        leftPlatform3.setPosition(new Vector2D(70,235));
        leftPlatform3.setTexture("platformImage");
        itemInScene.add(leftPlatform3);
        
        // Right platform1
        rightPlatform1 = GameUniverse.createInstance(new OneWayPlatform("platformR"));
        rightPlatform1.setSize(new Vector2D(325,10));
        rightPlatform1.setPosition(new Vector2D(800,455));
        rightPlatform1.setTexture("platformImage");
        itemInScene.add(rightPlatform1);
        
        // Right platform2
        rightPlatform2 = GameUniverse.createInstance(new OneWayPlatform("platformR"));
        rightPlatform2.setSize(new Vector2D(325,10));
        rightPlatform2.setPosition(new Vector2D(800,350));
        rightPlatform2.setTexture("platformImage");
        itemInScene.add(rightPlatform2);
        
        // Right platform3
        rightPlatform3 = GameUniverse.createInstance(new OneWayPlatform("platformR"));
        rightPlatform3.setSize(new Vector2D(325,10));
        rightPlatform3.setPosition(new Vector2D(800,235));
        rightPlatform3.setTexture("platformImage");
        itemInScene.add(rightPlatform3);
        
        //1st floor ladder L
        LeftLadder1 = GameUniverse.createInstance(new Ladder("trussRight"));
        LeftLadder1.setSize(new Vector2D(100,200));
        LeftLadder1.setPosition(new Vector2D(275,460));
        LeftLadder1.setTexture("floorImage");
        itemInScene.add(LeftLadder1);
        
        //2nd floor ladder L
        LeftLadder2 = GameUniverse.createInstance(new Ladder("trussRight"));
        LeftLadder2.setSize(new Vector2D(75,100));
        LeftLadder2.setPosition(new Vector2D(200,360));
        LeftLadder2.setTexture("floorImage");
        itemInScene.add(LeftLadder2);
        
        //3rd floor ladder L
        LeftLadder3 = GameUniverse.createInstance(new Ladder("trussRight"));
        LeftLadder3.setSize(new Vector2D(75,110));
        LeftLadder3.setPosition(new Vector2D(255,230));
        LeftLadder3.setTexture("floorImage");
        itemInScene.add(LeftLadder3);
        
        //1st floor ladder R
        RightLadder1 = GameUniverse.createInstance(new Ladder("trussRight"));
        RightLadder1.setSize(new Vector2D(100,200));
        RightLadder1.setPosition(new Vector2D(825,460));
        RightLadder1.setTexture("floorImage");
        itemInScene.add(RightLadder1);
        
        //2nd floor ladder R
        RightLadder2 = GameUniverse.createInstance(new Ladder("trussRight"));
        RightLadder2.setSize(new Vector2D(75,100));
        RightLadder2.setPosition(new Vector2D(900,360));
        RightLadder2.setTexture("floorImage");
        itemInScene.add(RightLadder2);
        
        //3rd floor ladder R
        RightLadder3 = GameUniverse.createInstance(new Ladder("trussRight"));
        RightLadder3.setSize(new Vector2D(75,110));
        RightLadder3.setPosition(new Vector2D(825,230));
        RightLadder3.setTexture("floorImage");
        itemInScene.add(RightLadder3);
        
        //boss
        level1Boss = GameUniverse.createInstance(new Boss1("ArtilleryBoss"));
    
        level1Boss.setSize(new Vector2D(200, 300));
        level1Boss.setPosition(new Vector2D(500, 250));
        level1Boss.setTexture("boss1Texture");
        level1Boss.setCollision(false);
        level1Boss.addTags("boss");
        level1Boss.setTextureSize(new Vector2D(600,300));

        GameUniverse.setBackground("res/GameAssets/Background/Stage1Placeholder.jpg");
        PlayerObject player = GameUniverse.getObjectByName("ThePlayer", PlayerObject.class);
        player.setHealthPoint(player.getMaxHealthPoint());
        InputManager.registerInputListenerObject(this);
    }
    @Override
    public void process(double deltaTime) {
        if (GameUniverse.getObjectByName("ArtilleryBoss", Boss1.class) != null && !hasBoss1Create) {
            hasBoss1Create = true;
            Boss1HpDisplay Boss1Ui = GameUniverse.createInstance(new Boss1HpDisplay("BossHPUI", 5 , 5, 5, 5));
            itemInScene.add(Boss1Ui);
        }
        level1Boss.updateBossAI(deltaTime);
        if(boss1die){
            PortalObj = GameUniverse.createInstance(new Portal2("PortalObj"));
            PortalObj.addAnimator("portalAnim", GameUniverse.getObjectByName("portalAnim", Animator.class));
            PortalObj.setCurrentAnimator("portalAnim", 0);
            PortalObj.setSize(new Vector2D(200, 300));
            PortalObj.setPosition(new Vector2D(1000, 350));
            itemInScene.add(PortalObj);
            boss1die=false;
            PlayerObject player = GameUniverse.getObjectByName("ThePlayer", PlayerObject.class);
            player.unlockedCharacter(2);
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
