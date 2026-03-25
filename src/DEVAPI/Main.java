package DEVAPI;
import UniverseEngine.Game;
import UniverseEngine.GameUniverse;
import RenderObject.*;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     */
    public Game gameEngine; //DO NOT REMOVE
    public ArrayList<GameObject> mustInstanceObj = new ArrayList<>();
    

    public Block pikachu2;
    public KinematicObject physicPikachu;
    public Camera cam1;
    public Camera cam2;
    public ViewPort window;
    public AreaDetector area;
    public Block Flyingfloor;
    public ScaredPikachu scaredpik;
    
    public double lf=0;
    public double fall=-1;
    public boolean added = false;
    public int startjump=0;
    public int maxjump=3;
    public boolean jumping=false;
    public String pikachuImage = "res/GameAssets/Textures/placeholder.jpg";
    
    @Override
    public void run() {      //this run very fast use with caution....  

    }
    /** run once when the game runs
     */
    public void initialize() {
        try {
            GameUniverse.loadImage("pikachuImage", pikachuImage);
            physicPikachu = new KinematicObject("PikachuPlayer");
            area = new AreaDetector("trampoline");
            Door newDoor = new Door("bgtransition");
            for (int i = 0; i < 6; i++) {
                Block floor = new Block("Platform");
                floor.setSize(new Vector2D(100,100));
                floor.setPosition(new Vector2D(i*100, 500));
                floor.setTexture("pikachuImage");
                floor.createInstance();
            }
            for (int i = 0; i < 10; i++) {
                Block floor = new Block("Platform");
                floor.setSize(new Vector2D(100,100));
                floor.setPosition(new Vector2D(700+i*50, 500-i*3));
                floor.setTexture("pikachuImage");
                floor.createInstance();
            }
            for (int i = 0; i < 40; i++) {
                Block floor = new Block("Platform");
                floor.setSize(new Vector2D(100,100));
                floor.setPosition(new Vector2D(1000+i, 500-i*4));
                floor.setTexture("pikachuImage");
                floor.createInstance();
            }
            Flyingfloor = new Block("FlyingPlatform");
            Flyingfloor.setSize(new Vector2D(100,50));
            Flyingfloor.setPosition(new Vector2D(100, 220));
            Flyingfloor.createInstance();
            
            newDoor.setSize(new Vector2D(50, 50));
            newDoor.setPosition(new Vector2D(100, 100));
            newDoor.createInstance();
            
            Block LongPlatform = new Block("LongPlatform");
            LongPlatform.setSize(new Vector2D(500,150));
            LongPlatform.setPosition(new Vector2D(251, -200));
            LongPlatform.createInstance();
            
            area.setSize(new Vector2D(50, 5000));
            area.setPosition(new Vector2D(200, 200));
            area.createInstance();
            
            physicPikachu.setSize(new Vector2D(50, 50));
            physicPikachu.setPosition(new Vector2D(0,0));
            physicPikachu.createInstance();
            
            scaredpik = new ScaredPikachu("ScaredPikachu");
            scaredpik.setSize(new Vector2D(70, 40));
            scaredpik.setPosition(new Vector2D(450, 450));
            scaredpik.createInstance();
            
            Decoration scaredpikfriend = new Decoration("ScaredPikachuFriend");
            scaredpikfriend.setSize(new Vector2D(50, 70));
            scaredpikfriend.setPosition(new Vector2D(0, 0));
            scaredpikfriend.createInstance();
            
            scaredpik.addConstraint(scaredpikfriend);
            
            window = new ViewPort("Game");
            window.createInstance();
            window.setEnabled(true);
            cam1 = new Camera("GameCam");
            cam1.setSize(new Vector2D(1200, 800));
            cam1.setPosition(new Vector2D(0, 0));
            cam1.createInstance();
            cam2 = new Camera("GameCam2");
            cam2.setSize(new Vector2D(1400, 500));
            cam2.setPosition(new Vector2D(0, 0));
            cam2.createInstance();
            physicPikachu.getSize();
            window.setCamera(cam1);
            physicPikachu.addConstraint(cam1);
            StyledObject[] textureObjects = {
                physicPikachu, area, Flyingfloor, 
                LongPlatform, scaredpik, scaredpikfriend};
            StyledObject.setTextureAll(textureObjects, "pikachuImage");

            GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
        } catch (InvalidGameObjectPropertyException e) {
            e.printStackTrace();
        }
    }
    
    /** run every tick when the game is running
     * @param deltaTime
     */
    public void process(double deltaTime) {
        if (added == false) {
            physicPikachu.addAcceleration(new Vector2D(lf, fall));
            added = true;
        }
            if (InputManager.isKeyDown('1')) {
                GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
                try {
                    window.setCamera(cam1);
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
                lf=5;
            }
            else if (InputManager.isKeyDown('a')) {
                lf=-5;
            }
            else{
                lf=0;
            }
            if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                if(!jumping&&physicPikachu.getTouchedFloor()){//add chack is tuch ground after addhitbox
                    startjump=maxjump;
                    jumping=true;
                }
            }
            if(jumping&&startjump>0){
                fall=-1;
                physicPikachu.addAcceleration(new Vector2D(0, fall));
                startjump--;
            }
            else{
                fall=1;
            }
            if(jumping&&startjump<=0){
                jumping=false;
                startjump=0;
            }
            physicPikachu.movePostion(new Vector2D(lf, 0));

            if (InputManager.isKeyDown('j')) {
                physicPikachu.removeConstraint(cam1);
                cam1.movePostion(new Vector2D(-10, 0));
            }
            if (InputManager.isKeyDown('l')) {
                                cam1.movePostion(new Vector2D(10, 0));
            }
            if (InputManager.isKeyDown('k')) {
                cam1.movePostion(new Vector2D(0, 10));
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
                scaredpik.destroyInstance();
            }
            
            if (InputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
    }

}
