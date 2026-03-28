package DEVAPI;

import DEVAPI.CustomGameObject.Door;
import DEVAPI.CustomGameObject.ScaredPikachu;
import Boss1ass.Boss1;
import Boss2ass.Boss2;
import RenderObject.Creatable.Animator;
import RenderObject.Creatable.AreaDetector;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Camera;
import RenderObject.Creatable.Decoration;
import RenderObject.InputListener;
import RenderObject.InvalidGameObjectPropertyException;
import RenderObject.KinematicObject;
import RenderObject.ScriptSheet;
import RenderObject.StyledObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Creatable.ViewPort;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

public class DebugLevelLoaderScript extends ScriptSheet implements InputListener {
    public Block pikachu2;
    public KinematicObject physicPikachu;
    public Camera cam1;
    public Camera cam2;
    public ViewPort window;
    public AreaDetector area;
    public Block Flyingfloor;
    public ScaredPikachu scaredpik;

    public String pikachuImage = "res/GameAssets/Textures/placeholder.jpg";

    public boolean setSize = false;
    public double lf = 0;
    public double fall = -1;
    public boolean added = false;
    public int startjump = 0;
    public int maxjump = 3;
    public boolean jumping = false;

    public Boss1 level1Boss;

    public DebugLevelLoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        GameUniverse.loadImage("pikachuImage", pikachuImage);
        GameUniverse.loadImage("realLaserImage", "res/Boss1/Skill 1/reallaser.png");
        GameUniverse.loadImage("realLaserXImage", "res/Boss1/Skill 1/reallaser X.png");
        Animator idle = GameUniverse.createInstance(new Animator("idle"));
        idle.createAnimationSheet(new Vector2D(128, 128), "res/free_sprite/individual_sheets/male_hero_template-idle.png");
        idle.debugSpriteSheet();
        idle.setSpeed(10);
        //-----------------------------------------------------------------------------------------------------------
        GameUniverse.loadImage("redWarning", "res/GameAssets/Textures/red_warning.jpg");
        physicPikachu = GameUniverse.createInstance(new KinematicObject("PikachuPlayer"));
        physicPikachu.setSize(new Vector2D(70, 120));
        physicPikachu.setPosition(new Vector2D(0, 0));
        physicPikachu.addAnimator("idle", idle);
        physicPikachu.setCurrentAnimator("idle");
        idle.setEnabled(true);
        //------------------------------------------------------------------------------------------------------------
        area = GameUniverse.createInstance(new AreaDetector("trampoline"));
        Door newDoor = GameUniverse.createInstance(new Door("bgtransition"));

        for (int i = 0; i < 6; i++) {
            Block floor = GameUniverse.createInstance(new Block("Platform"));
            floor.setSize(new Vector2D(100, 100));
            floor.setPosition(new Vector2D(i * 100, 500));
            floor.setTexture("pikachuImage");
        }
        for (int i = 0; i < 10; i++) {
            Block floor = GameUniverse.createInstance(new Block("Platform"));
            floor.setSize(new Vector2D(100, 100));
            floor.setPosition(new Vector2D(700 + i * 50, 500 - i * 3));
            floor.setTexture("pikachuImage");
        }
        for (int i = 0; i < 40; i++) {
            Block floor = GameUniverse.createInstance(new Block("Platform"));
            floor.setSize(new Vector2D(100, 100));
            floor.setPosition(new Vector2D(1000 + i, 500 - i * 4));
            floor.setTexture("pikachuImage");
        }

        Flyingfloor = GameUniverse.createInstance(new Block("FlyingPlatform"));
        Flyingfloor.setSize(new Vector2D(100, 50));
        Flyingfloor.setPosition(new Vector2D(100, 220));

        newDoor.setSize(new Vector2D(50, 50));
        newDoor.setPosition(new Vector2D(100, 100));

        Block LongPlatform = GameUniverse.createInstance(new Block("LongPlatform"));
        LongPlatform.setSize(new Vector2D(500, 150));
        LongPlatform.setPosition(new Vector2D(251, -200));

        area.setSize(new Vector2D(50, 5000));
        area.setPosition(new Vector2D(200, 200));

        scaredpik = GameUniverse.createInstance(new ScaredPikachu("ScaredPikachu"));
        scaredpik.setSize(new Vector2D(70, 40));
        scaredpik.setPosition(new Vector2D(450, 450));

        Decoration scaredpikfriend = GameUniverse.createInstance(new Decoration("ScaredPikachuFriend"));
        scaredpikfriend.setSize(new Vector2D(50, 70));
        scaredpikfriend.setPosition(new Vector2D(0, 0));

        window = GameUniverse.createInstance(new ViewPort("Game"));
        window.setEnabled(true);
        cam1 = GameUniverse.createInstance(new Camera("GameCam"));
        cam1.setSize(new Vector2D(1200, 800));
        cam1.setPosition(new Vector2D(0, 0));
        cam2 = GameUniverse.createInstance(new Camera("GameCam2"));
        cam2.setSize(new Vector2D(1400, 500));
        cam2.setPosition(new Vector2D(0, 0));

        try {
            scaredpik.addConstraint(scaredpikfriend);
            window.setCamera(cam1);
            physicPikachu.addConstraint(cam1);
        } catch (InvalidGameObjectPropertyException e) {
            e.printStackTrace();
        }

        level1Boss = GameUniverse.createInstance(new Boss1("ArtilleryBoss"));
    
        level1Boss.setSize(new Vector2D(150, 150));
        level1Boss.setPosition(new Vector2D(800, 350));
        level1Boss.setTexture("boss1Texture");
        
        StyledObject[] textureObjects = {
            physicPikachu, area, Flyingfloor,
            LongPlatform, scaredpik, scaredpikfriend
        };
        StyledObject.setTextureAll(textureObjects, "pikachuImage");

        GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
        InputManager.registerInputListenerObject(this);
    }

    @Override
    public void onDestroy() {
        // FIX: เคย register ซ้ำ แก้เป็น unregister ให้ถูกต้อง
        InputManager.unregisterInputListenerObject(this);
    }

    @Override
    public void process(double deltaTime) {
        if (setSize == false) {
            setSize = true;
            physicPikachu.setTextureSize(new Vector2D(500, 500));
        }
        if (InputManager.isKeyDown('1')) {
            GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
            try {
                Camera a = GameUniverse.getObjectByName("GameCam", Camera.class);
                window.setCamera(a);
            } catch (InvalidGameObjectPropertyException e) {
                e.printStackTrace();
            }
        }
        if (InputManager.isKeyDown('2')) {
            GameUniverse.setBackground("res/GameAssets/Background/bgplaceholder2.png");
            try {
                window.setCamera(cam2);
            } catch (InvalidGameObjectPropertyException e) {
                e.printStackTrace();
            }
        }
        if (InputManager.isKeyDown('d')) {
            lf = 5;
            physicPikachu.flipXTexture(false);
        } else if (InputManager.isKeyDown('a')) {
            lf = -5;
            physicPikachu.flipXTexture(true);
        } else {
            lf = 0;
        }
        if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
            if (!jumping && physicPikachu.getTouchedFloor()) {
                startjump = maxjump;
                jumping = true;
            }
        }
        if (jumping && startjump > 0) {
            fall = -1;
            physicPikachu.addAcceleration(new Vector2D(0, fall));
            startjump--;
        } else {
            fall = 1;
        }
        if (jumping && startjump <= 0) {
            jumping = false;
            startjump = 0;
        }
        physicPikachu.movePostion(new Vector2D(lf, 0));

        if (InputManager.isKeyDown('j')) {
            physicPikachu.removeConstraint(cam1);
            cam1.movePostion(new Vector2D(-10, 0));
        }
        if (InputManager.isKeyDown('l')) {
            cam1.movePostion(new Vector2D(10, 0));
        }
        if (InputManager.isKeyDown('i')) {
            cam1.movePostion(new Vector2D(0, -10));
        }
        if (InputManager.isKeyDown('=')) {
            cam1.setZoomFactor(cam1.getZoomFactor() + 0.01);
        }
        if (InputManager.isKeyDown('-')) {
            cam1.setZoomFactor(cam1.getZoomFactor() - 0.01);
        }
        if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
            if (physicPikachu.getTouchedFloor()) {
                physicPikachu.setVelocity(new Vector2D(0, -5));
            }
        }
        if (InputManager.isKeyDown(KeyEvent.VK_DELETE)) {
            AreaDetector d = GameUniverse.getObjectByName("ExtraPlatform", AreaDetector.class);
            if (d != null) {
                d.destroyInstance();
            }
        }
        if (InputManager.isKeyDown('q')) {
        if (physicPikachu.getBounds().intersects(level1Boss.getBounds())) {
            level1Boss.takeDamage(10);
//            level1Boss.leserY(new Vector2D(500,-350));
        }
        if (InputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
            System.exit(0);
        }
        //---------------------------------------------------------------------
        
        level1Boss.updateBossAI(deltaTime);

//        if (InputManager.isKeyDown('z')) {
//                level1Boss.leserY(new Vector2D(physicPikachu.getPosition().getXCoord(), 0));
//        }
        
        
    }
    }
    @Override
    public void onInput() {
    }
}
