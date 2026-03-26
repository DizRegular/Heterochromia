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
    
    public static int numObjectUniverse() {
        return GameUniverse.objectList.size();
    }
    
    public static <C extends BaseObject> C createInstance(C object) {
        object.setID(object.getName() + "#" + GameUniverse.generateNewID());
        object.instance();
        spawnQueue.add(object); 
        return object;
    }
    
    public static void newInstanceAll(ArrayList<BaseObject> allObject) {
        for (BaseObject obj : allObject) {
            GameUniverse.createInstance(obj);
        }
    }
    
    public static void processSpawningObject() {
        if (spawnQueue.isEmpty()) {return;}
        for (BaseObject newObj : spawnQueue) {
            objectList.add(newObj);
//            newObj.onCreate();
            GameEventListener.handleCreate(newObj);
        }
        spawnQueue.clear();
    }
    
    public static void cleanObj(BaseObject bObj) {
        if (bObj instanceof GameObject obj) {
            ArrayList<GameObject> constraints = obj.getConstraints();
            if (deadObjects.contains(obj)) {return;}
            deadObjects.add(obj);
            GameEventListener.handleDelete(obj);
            if (constraints != null) {
                for (GameObject constraint : constraints) {
                    cleanObj(constraint);
                }
            }
        } else {
            deadObjects.add(bObj);
            GameEventListener.handleDelete(bObj);
        }
    }
    
    public static void clean() {
        for (BaseObject obj : objectList) {
            if (obj.isObjAlive() == true) {continue;}
            if (obj instanceof BaseObject bObj) {
                GameUniverse.cleanObj(bObj);
            }
        }
        objectList.removeAll(deadObjects);
        deadObjects.clear();
    }
    
    public static <C extends BaseObject> C getObjectById(String id, Class<C> clazz) {
        for (BaseObject obj : objectList) {
            if (obj.getID().equals(id) && clazz.isInstance(obj)) {
                return clazz.cast(obj);
            }
        }
        return null;
    }
    
    public static <C extends BaseObject> C getObjectByName(String name, Class<C> clazz) {
        for (BaseObject obj : objectList) {
            if (obj.getName().equals(name) && clazz.isInstance(obj)) {
                return clazz.cast(obj);
            }
        }
        return null;
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
