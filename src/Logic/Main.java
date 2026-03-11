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
    public int startjump=0;
    public int maxjump=25;
    public boolean jumping=false;
    @Override
    public void run() {      //this run very fast use with caution....  
        
    }
    
    /** run once when the game runs
     */
    public void initialize() {
        physicPikachu = new KinematicObject("Pikachu3", new Vector2D(0, 0), new Vector2D(550,200), "res/GameAssets/Textures/placeholder.jpg");
        for (int i = 1; i < 11; i++) {
            pikachu2 = new Decoration("Pikachu2", new Vector2D(i*100, 0), new Vector2D(200,200), "res/GameAssets/Textures/placeholder.jpg");
            GameUniverse.newInstance(pikachu2);

        }
        GameUniverse.newInstance(physicPikachu);
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
            physicPikachu.setPosition(new Vector2D(physicPikachu.getPostion().getXCoord(), 799));
        }
    }
    /** Do what will happen when an event Occur 
     * run before process and run after every tick
     */
    public void processEvent() {
            if (InputManager.isKeyDown('d')) {
                lf=5;
                physicPikachu.movePostion(new Vector2D(lf,fall));
                
            }
            else if (InputManager.isKeyDown('a')) {
                lf=-5;
                physicPikachu.movePostion(new Vector2D(lf,fall));
                
            }
            if (InputManager.isKeyDown(KeyEvent.VK_SPACE)) {
                if(!jumping){//add chack is tuch ground after addhitbox
                    startjump=maxjump;
                    jumping=true;
                }}
            if(jumping&&startjump>0){
                
                fall=-1;
                physicPikachu.addAcceleration(new Vector2D(0, fall));
                startjump--;
            }
            if(jumping&&startjump<=0){
                jumping=false;
                startjump=0;
            }
            
            if (InputManager.isKeyDown(KeyEvent.VK_ESCAPE)) {
                System.exit(0);
            }
        }

}
