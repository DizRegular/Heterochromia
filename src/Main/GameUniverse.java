package Main;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import RenderObject.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class GameUniverse {
    private static CopyOnWriteArrayList<GameObject> objectList = new CopyOnWriteArrayList<>();
    private static ArrayList<CollisionObject> PhysicObjectList = new ArrayList<>();
    private static BufferedImage background;
    
    public static CopyOnWriteArrayList<GameObject> ObserveUniverse() {
        return GameUniverse.objectList;
    }
    
    public static ArrayList<CollisionObject> ObservePhysic() {
        return GameUniverse.PhysicObjectList;
    }
    
    public static int numObjectUniverse() {
        return GameUniverse.objectList.size();
    }
    
    public static void newInstance(GameObject obj) {
        objectList.add(obj);
        if (obj instanceof CollisionObject collision) {
            PhysicObjectList.add(collision);
        } 
    }
    
    public static void newInstanceAll(ArrayList<GameObject> allObject) {
        for (GameObject obj : allObject) {
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
