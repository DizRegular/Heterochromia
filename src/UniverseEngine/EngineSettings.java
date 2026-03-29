package UniverseEngine;

import java.awt.Color;
public class EngineSettings {
    /** Store important game data
     */
    
    public static String MISSING_TEXTURE_PATH = "res/GameAssets/Textures/NoImagePlaceHolder.png";
    
    public static Color LOADING_BACKGROUND_COLOR = Color.PINK;
    
     public static Color DEBUG_TEXT_COLOR = Color.BLUE;
    
    public static double GRAVITY_CONSTANT = 9.8;
    
    public static boolean SHOW_HITBOXES = false;
    
    public static int SPAWN_BUFFER_QUEUE_SIZE_LIMIT = 1000;
    
    public static double time_scale = 1;
}
