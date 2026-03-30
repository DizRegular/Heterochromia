package DEVAPI;
import UniverseEngine.Game;
import UniverseEngine.GameUniverse;
public class Main {
    /** Acts like codes that controls logic from a higher level like Unity.
     */
    /** run  once when the game runs, mainly use for script
     */
    public void initialize() {
        GameUniverse.createInstance(new GameLoaderScript("GameLoaderScript"));
    }
    
    
    /** run every tick when the game is running
     * @param deltaTime
     */
    public void process(double deltaTime) {
        
    }
}
