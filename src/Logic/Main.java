package Logic;
import Main.GameUniverse;
import RenderObject.*;
import Main.InputManager;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     * 
     */
    public Block pikachu;
    public Block pikachu2;
    public int count = 1;
    
    @Override
    public void run() {      //this run very fast use with caution....  
        
    }
    
    /** run once when the game runs
     */
    public void initialize() {
        pikachu = new Block("Pikachu1", new Vector2D(), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
//        pikachu2 = new Block("Pikachu2", new Vector2D(203, 100), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.summon(pikachu);
//        GameUniverse.summon(pikachu2);
    }
    
    /** run every tick when the game is running
     * @param deltaTime
     */
    public void process(double deltaTime) {

    }
    
    /** Do what will happen when an event Occur 
     * run before process and run after every tick
     */
    public void processEvent() {
            System.out.println(InputManager.isKeyDown('d') + " " + InputManager.isKeyDown('w'));
            if ((InputManager.isKeyDown('d')) && (InputManager.isKeyDown('w'))) {
                pikachu.movePostion(new Vector2D(1,-1));
            } 
            if ((InputManager.isKeyDown('a')) && (InputManager.isKeyDown('w'))) {
                pikachu.movePostion(new Vector2D(-1,-1));
            } 
            if ((InputManager.isKeyDown('a')) && (InputManager.isKeyDown('s'))) {
                pikachu.movePostion(new Vector2D(-1,1));
            } 
            if ((InputManager.isKeyDown('d')) && (InputManager.isKeyDown('s'))) {
                pikachu.movePostion(new Vector2D(1,1));
            } 
            if (InputManager.isKeyDown('d')) {
                pikachu.movePostion(new Vector2D(1,0));
                return;
            }
            if (InputManager.isKeyDown('a')) {
                pikachu.movePostion(new Vector2D(-1,0));
                return;
            }
            if (InputManager.isKeyDown('w')) {
                pikachu.movePostion(new Vector2D(0,-1));
                return;
            }
            if (InputManager.isKeyDown('s')) {
                pikachu.movePostion(new Vector2D(0,1));
                return;
            }

        }

}
