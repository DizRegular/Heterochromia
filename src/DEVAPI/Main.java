package DEVAPI;
import UniverseEngine.Game;
import UniverseEngine.GameUniverse;
import RenderObject.*;
import UniverseEngine.InputManager;
import UniverseEngine.Renderer;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     */
    public Game gameEngine; //DO NOT REMOVE
    public ArrayList<GameObject> mustInstanceObj = new ArrayList<>();
    

    public Block pikachu2;
    public KinematicObject physicPikachu;
    public Camera cam1;
    public AreaDetector area;
    
    public double lf=0;
    public double fall=-1;
    public boolean added = false;
    public int startjump=0;
    public int maxjump=3;
    public boolean jumping=false;
    public File pikachuImage = new File("res/GameAssets/Textures/placeholder.jpg");
    public File NoImage = new File("res/GameAssets/Textures/NoImagePlaceHolder.png");
    
    @Override
    public void run() {      //this run very fast use with caution....  

    }
    /** run once when the game runs
     */
    public void initialize() {
        try {
            physicPikachu = new KinematicObject("PikachuPlayer");
            AreaDetector area = new AreaDetector("trampoline");
            for (int i = 0; i < 6; i++) {
                Block floor = new Block("Platform");
                floor.setSize(new Vector2D(100,100));
                floor.setPosition(new Vector2D(i*100, 500));
                floor.setTexture(pikachuImage);
                floor.createInstance();
            }
            for (int i = 0; i < 10; i++) {
                Block floor = new Block("Platform");
                floor.setSize(new Vector2D(100,100));
                floor.setPosition(new Vector2D(700+i*50, 500-i*3));
                floor.setTexture(pikachuImage);
                floor.createInstance();
            }
            for (int i = 0; i < 40; i++) {
                Block floor = new Block("Platform");
                floor.setSize(new Vector2D(100,100));
                floor.setPosition(new Vector2D(1000+i, 500-i*4));
                floor.setTexture(pikachuImage);
                floor.createInstance();
            }
            Block floor = new Block("Platform");
            floor.setSize(new Vector2D(100,50));
            floor.setPosition(new Vector2D(100, 220));
            floor.createInstance();
            
            area.setSize(new Vector2D(50, 500));
            area.setPosition(new Vector2D(200, 200));
            area.createInstance();
            
            physicPikachu.setSize(new Vector2D(50, 50));
            physicPikachu.setPosition(new Vector2D(0,0));
            physicPikachu.createInstance();
            
            ViewPort window = new ViewPort("viewPort");
            cam1 = new Camera("GameCam");
            cam1.setSize(new Vector2D(1200, 800));
            cam1.setPosition(new Vector2D(0, 0));
            physicPikachu.addConstraint(cam1);
            StyledObject[] textureObjects = {physicPikachu, area, floor};
            StyledObject.setTextureAll(textureObjects, pikachuImage);
            window.setCamera(cam1);
            cam1.createInstance();
            window.createInstance();
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
    }
    /** Do what will happen when an event Occur 
     * run before process and run after every tick
     */
    public void processEvent() {
            if (InputManager.isKeyDown('1')) {
                GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
            }
            if (InputManager.isKeyDown('2')) {
                GameUniverse.setBackground("res/GameAssets/Background/bgplaceholder2.png");
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

            if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                if (physicPikachu.getTouchedFloor() && physicPikachu.getVelocity().getYCoord() < 10) {
                    physicPikachu.setVelocity(new Vector2D(0, -5));
                }
            }
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

            if (InputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
        }

}
