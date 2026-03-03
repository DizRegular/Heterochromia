package Logic;
import Main.GameUniverse;
import RenderObject.*;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     * 
     */
    
    public void run() {
        Block pikachu = new Block(new Vector2D(), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
        
        GameUniverse.summon(pikachu);

        while (true) {
            pikachu.movePostion(new Vector2D(1,0));
            try { Thread.sleep(12); } catch (InterruptedException e) {e.printStackTrace();};
        }
    }
}
