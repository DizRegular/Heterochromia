package UniverseEngine;

import RenderObject.Camera;
import java.util.ArrayList;

public class RenderManager {
    private static ArrayList<Camera> allViewers = new ArrayList<>();
    
    public static void registerNewViewer(Camera cam) {
        allViewers.add(cam);
    }
    
    public static void render() {
        for (Camera cam : allViewers) {
            cam.snap();
        }
    }
}
