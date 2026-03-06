package Main;
import java.util.concurrent.CopyOnWriteArrayList;
import RenderObject.*;
public class GameUniverse {
    private static CopyOnWriteArrayList<GameObject> objectList = new CopyOnWriteArrayList<>();
    
    public static CopyOnWriteArrayList<GameObject> ObserveUniverse() {
        return GameUniverse.objectList;
    }
    
    public static int numObjectUniverse() {
        return GameUniverse.objectList.size();
    }
    
    public static void summon(GameObject obj) {
        objectList.add(obj);
    }
    
    public static void sacrifice(GameObject obj) {
        System.out.println((objectList.remove(obj)));
    }
}
