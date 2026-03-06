package Logic;
import Main.GameUniverse;
import RenderObject.*;
import Main.InputManager;
import java.awt.event.KeyEvent;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     * 
     */
    public Decoration pikachu2;
    public KinematicObject physicPikachu;
    public int count = 1;
    public boolean added = false;
    @Override
    public void run() {      //this run very fast use with caution....  
        
    }
    
    /** run once when the game runs
     */
    public void initialize() {
        pikachu2 = new Decoration("Pikachu2", new Vector2D(203, 100), new Vector2D(400,400), "res/GameAssets/Textures/placeholder.jpg");
        physicPikachu = new KinematicObject("Pikachu3", new Vector2D(500, 0), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.summon(pikachu2);
        GameUniverse.summon(physicPikachu);
    }
    
    /** run every tick when the game is running
     * @param deltaTime
     */
    public void process(double deltaTime) {
        if (added == false) {
            physicPikachu.addAcceleration(new Vector2D(0, -1));
            added = true;
        }
    }
    /** Do what will happen when an event Occur 
     * run before process and run after every tick
     */
    public void processEvent() {
//            if ((InputManager.isKeyDown('d')) && (InputManager.isKeyDown('w'))) {
//                physicPikachu.movePostion(new Vector2D(1,-1));
//            } 
//            if ((InputManager.isKeyDown('a')) && (InputManager.isKeyDown('w'))) {
//                physicPikachu.movePostion(new Vector2D(-1,-1));
//            } 
//            if ((InputManager.isKeyDown('a')) && (InputManager.isKeyDown('s'))) {
//                physicPikachu.movePostion(new Vector2D(-1,1));
//            } 
//            if ((InputManager.isKeyDown('d')) && (InputManager.isKeyDown('s'))) {
//                physicPikachu.movePostion(new Vector2D(1,1));
//            } 
            if (InputManager.isKeyDown('d')) {
                physicPikachu.movePostion(new Vector2D(1,0));
                return;
            }
            if (InputManager.isKeyDown('a')) {
                physicPikachu.movePostion(new Vector2D(-1,0));
                return;
            }
//            if (InputManager.isKeyDown('w')) {
//                physicPikachu.movePostion(new Vector2D(0,-1));
//                return;
//            }
//            if (InputManager.isKeyDown('s')) {
//                physicPikachu.movePostion(new Vector2D(0,1));
//                return;
//            }
            if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                physicPikachu.addAcceleration(new Vector2D(0, -2));
            }

        }

}
