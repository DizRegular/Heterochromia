package Logic;
import Main.Game;
import Main.GameUniverse;
import RenderObject.*;
import Main.InputManager;
import java.awt.event.KeyEvent;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     * 
     */
    public Game gameEngine; //DO NOT REMOVE
    
    public double lf=0;
    public double fall=-1;
    public Block pikachu2;
    public KinematicObject physicPikachu;
    public int count = 1;
    public boolean added = false;
    public int startjump=0;
    public int maxjump=25;
    public boolean jumping=false;
    
    @Override
    public void run() {      //this run very fast use with caution....  
        
    }
    /** run once when the game runs
     */
    public void initialize() {
        physicPikachu = new KinematicObject("Pikachu3", new Vector2D(0, 0), new Vector2D(50,50), "res/GameAssets/Textures/placeholder.jpg");
//        for (int i = 1; i < 5; i++) {
//            pikachu2 = new Block("Pikachu2", new Vector2D(i*100, 350), new Vector2D(100,100), "res/GameAssets/Textures/placeholder.jpg");
//            GameUniverse.newInstance(pikachu2);
//
//        }
//        for (int i = 1; i < 5; i++) {
//            pikachu2 = new Block("Pikachu2", new Vector2D(200 + i*100, 100), new Vector2D(100,100), "res/GameAssets/Textures/placeholder.jpg");
//            GameUniverse.newInstance(pikachu2);
//        }
        for (int i = 0; i < 2; i++) {
        pikachu2 = new Block("Pikachu3", new Vector2D(350, 350), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.newInstance(pikachu2);
        }
        GameUniverse.newInstance(physicPikachu);
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
        if (physicPikachu.getPostion().getYCoord() > gameEngine.getGameWindow().getSize().getHeight() ) {
            physicPikachu.setPosition(new Vector2D(physicPikachu.getPostion().getXCoord(), 800));
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
//                if(!jumping){//add chack is tuch ground after addhitbox
//                    startjump=maxjump;
//                    jumping=true;
//                }
                physicPikachu.addAcceleration(new Vector2D(0, -1));
            }
//            if(jumping&&startjump>0){
//                fall=-1;
//                physicPikachu.addAcceleration(new Vector2D(0, fall));
//                startjump--;
//            }
//            if(jumping&&startjump<=0){
//                jumping=false;
//                startjump=0;
//            }
            physicPikachu.movePostion(new Vector2D(lf, fall));
            if (InputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
        }

}
