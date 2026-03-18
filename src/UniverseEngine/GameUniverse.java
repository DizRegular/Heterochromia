package UniverseEngine;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import RenderObject.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class GameUniverse {
    private static CopyOnWriteArrayList<FounderObject> objectList = new CopyOnWriteArrayList<>();
    private static ArrayList<CollisionObject> PhysicObjectList = new ArrayList<>();
    private static int currentNewObjectID = -1;
    private static BufferedImage background;
    private RenderManager renderManager;
    
    public static int generateNewID() {
        currentNewObjectID++;
        return GameUniverse.currentNewObjectID;
    }
    
    public static CopyOnWriteArrayList<FounderObject> ObserveUniverse() {
        return GameUniverse.objectList;
    }
    
    public static ArrayList<CollisionObject> ObservePhysic() {
        return GameUniverse.PhysicObjectList;
    }
    
    public static int numObjectUniverse() {
        return GameUniverse.objectList.size();
    }
    
    public static void newInstance(FounderObject obj) {
        if (obj instanceof Camera cam) {
            RenderManager.registerNewViewer(cam);
            return;
        }
        objectList.add(obj);
        if (obj instanceof CollisionObject collision) {
            PhysicObjectList.add(collision);
        } 

    }
    
    public static void newInstanceAll(ArrayList<FounderObject> allObject) {
        for (FounderObject obj : allObject) {
            GameUniverse.newInstance(obj);
        }
    }
    
    public static void delete(GameObject obj) {
        System.out.println((objectList.remove(obj)));
    }
    
    public static BufferedImage getBackground() {
        return GameUniverse.background;
    }
    
    public static void setBackground(String loc) {
        try {
            GameUniverse.background = ImageIO.read(new File(loc));
        } catch (Exception e) {
            System.out.println("Did you type wrong path? Did the file exist in res?");
        }
    }
}
