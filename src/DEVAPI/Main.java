package DEVAPI;
import UniverseEngine.Game;
import UniverseEngine.GameUniverse;
import RenderObject.*;
import UniverseEngine.InputManager;
import UniverseEngine.Renderer;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     */
    public Game gameEngine; //DO NOT REMOVE
    public ArrayList<GameObject> mustInstanceObj = new ArrayList<>();
    
    public double lf=0;
    public double fall=-1;
    public Block pikachu2;
    public KinematicObject physicPikachu;
    public Camera cam1;
    public AreaDetector area;
    public int count = 1;
    public boolean added = false;
    public int startjump=0;
    public int maxjump=3;
    public boolean jumping=false;
    
    @Override
    public void run() {      //this run very fast use with caution....  

    }
    /** run once when the game runs
     */
    public void initialize() {
        physicPikachu = new KinematicObject("Pikachu3", new Vector2D(0, 0), new Vector2D(50,50), "res/GameAssets/Textures/placeholder.jpg");
        area = new AreaDetector("Pikachu3", new Vector2D(300, 500), new Vector2D(300,50), "res/GameAssets/Textures/placeholder.jpg");
        Block block2 = new Block("Pikachu3", new Vector2D(0, 700), new Vector2D(2000, 50), "res/GameAssets/Textures/placeholder.jpg");
        cam1 = new Camera("cam1", new Vector2D(0,0), new Vector2D(1920, 1080));
        Camera cam2 = new Camera("cam1", new Vector2D(0,0), new Vector2D(1920, 1080));
        for (int i = 0; i < 2; i++) {
        pikachu2 = new Block("Pikachu3", new Vector2D(350, 350), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.newInstance(pikachu2);
        }
        pikachu2 = new Block("Pikachu3", new Vector2D(350, 100), new Vector2D(100,50), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.newInstance(pikachu2);
        pikachu2 = new Block("Pikachu3", new Vector2D(499, 346), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        physicPikachu.addConstraint(cam1);
        GameUniverse.newInstance(block2);
        GameUniverse.newInstance(cam1);
        GameUniverse.newInstance(cam2);
        GameUniverse.newInstance(pikachu2);
        GameUniverse.newInstance(physicPikachu);
        GameUniverse.newInstance(area);
        GameUniverse.setBackground("res/GameAssets/Background/bgplacegholder.jpg");
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
//                System.out.println(physicPikachu.getTouchedFloor());
//                if (physicPikachu.getTouchedFloor()) {
//                    fall=-5;
//                }
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
