package Logic;
import Main.Game;
import Main.GameUniverse;
import RenderObject.*;
import Main.InputManager;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     * 
     */
    public Game gameEngine;
    
    public double lf=0;
    public double fall=-1;
    public Decoration pikachu2;
    public KinematicObject physicPikachu;
    public int count = 1;
    public boolean added = false;
    @Override
    public void run() {      //this run very fast use with caution....  
        
    }
    
    /** run once when the game runs
     */
    public void initialize(Game gameEngine) {
        this.gameEngine = gameEngine;
        pikachu2 = new Decoration("Pikachu2", new Vector2D(203, 100), new Vector2D(400,400), "res/GameAssets/Textures/placeholder.jpg");
        physicPikachu = new KinematicObject("Pikachu3", new Vector2D(500, 0), new Vector2D(550,200), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.summon(pikachu2);
        GameUniverse.summon(physicPikachu);
        System.out.println(this.gameEngine.getGameWindow().getSize().toString());
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
            physicPikachu.setPosition(new Vector2D(physicPikachu.getPostion().getXCoord(), 0));
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
                lf=5;
                physicPikachu.movePostion(new Vector2D(lf,fall));
                
            }
            else if (InputManager.isKeyDown('a')) {
                lf=-5;
                physicPikachu.movePostion(new Vector2D(lf,fall));
                
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
                fall=-1;
                physicPikachu.addAcceleration(new Vector2D(0, fall));
            }
            
            if (InputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
        }

}
