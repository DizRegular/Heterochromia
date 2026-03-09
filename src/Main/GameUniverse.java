package Main;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;
import RenderObject.*;
public class GameUniverse {
    private static CopyOnWriteArrayList<GameObject> objectList = new CopyOnWriteArrayList<>();
    private static ArrayList<KinematicObject> PhysicObjectList = new ArrayList<>();
    
    public static CopyOnWriteArrayList<GameObject> ObserveUniverse() {
        return GameUniverse.objectList;
    }
    
    public static ArrayList<KinematicObject> ObservePhysic() {
        return GameUniverse.PhysicObjectList;
    }
    
    public static int numObjectUniverse() {
        return GameUniverse.objectList.size();
    }
    
    public static void newInstance(GameObject obj) {
        objectList.add(obj);
        if (obj instanceof KinematicObject Kinematic_Object) {
            PhysicObjectList.add(Kinematic_Object);
        }
    }
    
    public static void explode(GameObject obj) {
        System.out.println((objectList.remove(obj)));
    }
}
