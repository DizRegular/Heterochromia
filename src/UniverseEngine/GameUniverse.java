package UniverseEngine;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import RenderObject.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class GameUniverse {
    private static CopyOnWriteArrayList<BaseObject> objectList = new CopyOnWriteArrayList<>();
    private static ArrayList<BaseObject> spawnQueue = new ArrayList<>();
    private static ArrayList<BaseObject> deadObjects = new ArrayList<>();
    private static int currentNewObjectID = -1;
    private static BufferedImage background;
    private RenderManager renderManager;
    
    public static int generateNewID() {
        currentNewObjectID++;
        return GameUniverse.currentNewObjectID;
    }
    
    public static CopyOnWriteArrayList<BaseObject> ObserveUniverse() {
        return GameUniverse.objectList;
    }
    
    public static int numObjectUniverse() {
        return GameUniverse.objectList.size();
    }
    
    public static void newInstance(BaseObject obj) {
        spawnQueue.add(obj); 
    }
    
    public static void newInstanceAll(ArrayList<BaseObject> allObject) {
        for (BaseObject obj : allObject) {
            GameUniverse.newInstance(obj);
        }
    }
    
    public static void processSpawningObject() {
        if (spawnQueue.isEmpty()) {return;}
        for (BaseObject newObj : spawnQueue) {
            objectList.add(newObj);
            newObj.onCreate();
        }
        spawnQueue.clear();
    }
    
    public static void cleanObj(GameObject obj) {
        ArrayList<GameObject> constraints = obj.getConstraints();
        if (deadObjects.contains(obj)) {return;}
        deadObjects.add(obj);
        obj.onDestroy();
        if (constraints != null) {
            for (GameObject constraint : constraints) {
                cleanObj(constraint);
            }
        }
    }
    
    public static void clean() {
        for (BaseObject obj : objectList) {
            if (obj.isObjAlive() == true) {continue;}
            if (obj instanceof GameObject gObj) {
                GameUniverse.cleanObj(gObj);
            }
        }
        objectList.removeAll(deadObjects);
        deadObjects.clear();
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
    
    public static void loadImage(String name, String pathfile) {
        Fetcher.loadTexture(name, pathfile);
    }
    
    public static BufferedImage fetchImage(String name) {
        return Fetcher.getTextures2D(name);
    }
}
