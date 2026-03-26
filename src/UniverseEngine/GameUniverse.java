package UniverseEngine;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import RenderObject.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class GameUniverse {
    private static CopyOnWriteArrayList<BaseObject> objectList = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<BaseObject> spawnQueue = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<BaseObject> deadObjects = new CopyOnWriteArrayList<>();
    private static ArrayList<Scriptable> scriptableObjects = new ArrayList<>();
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
        int i = 0;
        while (!spawnQueue.isEmpty() && i < EngineSettings.SPAWN_BUFFER_QUEUE_SIZE_LIMIT) {
            BaseObject newObj = spawnQueue.removeFirst();
            objectList.add(newObj);
            newObj.onCreate();
            if (newObj instanceof Scriptable script) {
                scriptableObjects.add(script);
            }
            i++;
        }
    }
    
    public static void cleanObj(BaseObject bObj) {
        ArrayList<BaseObject> constraints = bObj.getConstraints();
        if (deadObjects.contains(bObj)) {return;}
        deadObjects.add(bObj);
        if (bObj instanceof Scriptable script) {
            scriptableObjects.remove(script);
        } 
        GameEventListener.handleDelete(bObj);
        if (constraints != null) {
            for (BaseObject constraint : constraints) {
                cleanObj(constraint);
            }
        }

    }
    
    public static void clean() {
        for (BaseObject obj : objectList) {
            if (obj.isObjAlive() == true) {continue;}
               GameUniverse.cleanObj(obj);
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
    
    public static void processScriptable(double deltaTime) {
        for (Scriptable script : scriptableObjects) {
            script.process(deltaTime);
        }
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
