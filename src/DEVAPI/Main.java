package DEVAPI;
import UniverseEngine.Game;
import UniverseEngine.GameUniverse;
public class Main implements Runnable {
    /** Acts like codes that controls logic from a higher level like Unity.
     */
    @Override
    public void run() {      //this run very fast use with caution....  

    }
    /** run  once when the game runs, mainly use for script
     */
    public void initialize() {
        //GameUniverse.createInstance(new DebugLevelLoaderScript("DebugLevelLoaderScript"));
        GameUniverse.createInstance(new LevelTutorialLoaderScript("LevelTutorialLoaderScript"));
        //GameUniverse.createInstance(new Level1LoaderScript("Level1LoaderScript")); 
        //GameUniverse.createInstance(new Level2LoaderScript("Level2LoaderScript"));
        //GameUniverse.createInstance(new Level3LoaderScript("Level3LoaderScript"));
    }
    
    
    /** run every tick when the game is running
     * @param deltaTime
     */
    public void process(double deltaTime) {
        
    }
}
