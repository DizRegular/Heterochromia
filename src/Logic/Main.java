package Logic;
import Main.GameUniverse;
import RenderObject.*;
import Main.InputManager;
import Main.InputManager.EventList;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     * 
     */
    public Block pikachu;
    public Block pikachu2;
    public int count = 1;
    
    public void run() {      //this run very fast use with caution....  
        
    }
    
    /** run once when the game runs
     */
    public void initialize() {
        pikachu = new Block("Pikachu1", new Vector2D(), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        pikachu2 = new Block("Pikachu2", new Vector2D(203, 100), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        GameUniverse.summon(pikachu);
        GameUniverse.summon(pikachu2);
    }
    
    /** run every tick when the game is running
     */
    public void process(double deltaTime) {
        this.processEvent(InputManager.getCurrentEventFlag());
    }
    
    /** Do what will happen when an event Occur
     * @param event 
     */
    public void processEvent(EventList event) {
        switch (event) {
            case EventList.Nothing:
                break;
            case EventList.WalkEast:
                pikachu.movePostion(new Vector2D(1,0));
                break;
            case EventList.WalkWest:
                pikachu.movePostion(new Vector2D(-1, 0));
                break;
            case EventList.WalkNorth:
                pikachu.movePostion(new Vector2D(0, 1));
                break;
            case EventList.WalkSouth:
                pikachu.movePostion(new Vector2D(0, -1));
                break;
        }
    }
    
    
}
