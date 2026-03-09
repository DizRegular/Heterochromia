package Main;
import RenderObject.*;
import Logic.Main;
import java.util.ArrayList;
public class GameManipulator {    
    
    /** Controls physic and order of action
     */
    private Main devAPI;
    private static final ArrayList<ArrayList> quadrantContainer = new ArrayList<>();
    
    public GameManipulator(Main api) {
        this.devAPI = api;
        for (int i = 0; i < 4; i++) {
            quadrantContainer.add(i, new ArrayList<KinematicObject>());
        }
    }
    
    public void tick(double deltaTime) {
        ArrayList<KinematicObject> PhysicObjects = GameUniverse.ObservePhysic();

        this.applyPhysic(PhysicObjects);
        devAPI.processEvent();
        devAPI.process(deltaTime);
    }
        
    public static int getQuadrant(Vector2D v) {
        int windowHeight = Game.getGameWindow().getSize().height;
        int windowWidth = Game.getGameWindow().getSize().width;
        if ((v.getXCoord() < windowWidth/2) && (v.getYCoord() < windowHeight/2)) {
            return 0;
        } else if ((v.getXCoord() > windowWidth/2) && (v.getYCoord() < windowHeight/2)) {
            return 1;
        } else if ((v.getXCoord() < windowWidth/2) && (v.getYCoord() > windowHeight/2)) {
            return 2;
        } else {
            return 3;
        }
    }
    
    public static void sortPhysicObjectPosition(KinematicObject obj) {
        Vector2D objPosition = obj.getPostion();
        Vector2D objSize = obj.getSize();
        int ltVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord(), objPosition.getYCoord() + objSize.getYCoord()));
        int rtVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord()+ objSize.getXCoord(), objPosition.getYCoord() + objSize.getYCoord()));
        int lbVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord(), objPosition.getYCoord()));
        int rbVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord() + objSize.getXCoord(), objSize.getYCoord()));
        int[] vertexes = {ltVertexQuadrant, rtVertexQuadrant, lbVertexQuadrant, rbVertexQuadrant};
        int[] countedQuadrant = {-1, -1, -1, -1};
        int countSize = 0;
        for (int vertex : vertexes) {
            boolean checked = false;
            for (int counted : countedQuadrant) {
                if (counted  == vertex) {
                    checked = true;
                    break;
                }
            }
            if (checked == true) {
                continue;
            }
            quadrantContainer.get(vertex).add(obj);
            countedQuadrant[countSize] = vertex;
            countSize++;
        }
        obj.setQuadrant(countedQuadrant);
        Renderer.setPrintingTextOnScreen("|"+ obj.getQuadrants()[0] + "|" + obj.getQuadrants()[1] + "|" +obj.getQuadrants()[2] + "|" +obj.getQuadrants()[3] + "|");
        
    }
    
    public void applyPhysic(ArrayList<KinematicObject> PhysicObjects) {
        for (ArrayList quadrant : GameManipulator.quadrantContainer) {
            quadrant.clear();
        }
        for (KinematicObject obj : PhysicObjects) {
            if (obj.getPhysicChange() == true) {
                GameManipulator.sortPhysicObjectPosition(obj);
                obj.setPhysicChange(true);
            }
            obj.addVelocity(obj.getAcceleration());
            obj.movePostion(obj.getVelocity());
            obj.setAcceleration(new Vector2D(0, 9.8/60));
        }
    }
}
