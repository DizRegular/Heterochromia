package UniverseEngine;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import RenderObject.*;
import DEVAPI.Main;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
public class GameManipulator {    
    
    /** Controls physic and order of action
     */
    private Main devAPI;
    private static final ArrayList<ArrayList<CollisionObject>> quadrantContainer = new ArrayList<>();
    private static final CopyOnWriteArrayList<CollisionObject> physicObjects = new CopyOnWriteArrayList<>();
    private static final int gameQuadrantWidth = 1080;
    private static  final int gameQuadrantHeight = 1920;
    
    public GameManipulator(Main api) {
        this.devAPI = api;
        for (int i = 0; i < 4; i++) {
            quadrantContainer.add(i, new ArrayList<>());
        }
    }
    
    public void tick(double deltaTime) {
        GameUniverse.processSpawningObject();
        this.applyPhysic();
        GameUniverse.processScriptable(deltaTime);
        GameUniverse.clean();
        AnimationManager.animateTime(deltaTime);
    }
    
    
    public static void registerPhysicObject(CollisionObject obj) {
        physicObjects.add(obj);
    }
    
    public static void unregisterPhysicObject(CollisionObject obj) {
         physicObjects.remove(obj);
    }
    
    public boolean notifyGameEventListener(GameObject eventSource, GameObject eventCause) {
        if (eventSource instanceof touchable) {
            GameEventListener.handleTouch(eventSource, eventCause, "");
            return true;
        }
        return false;
    } 
    
    public static int getQuadrant(Vector2D v) {
        /** find what quadrant this vertex belongs to
         */
        int gameQuadrantHeight = GameManipulator.gameQuadrantWidth;
        int gameQuadrantWidth = GameManipulator.gameQuadrantHeight;
        if ((v.getXCoord() < gameQuadrantWidth/2) && (v.getYCoord() < gameQuadrantHeight/2)) {
            return 0;
        } else if ((v.getXCoord() > gameQuadrantWidth/2) && (v.getYCoord() < gameQuadrantHeight/2)) {
            return 1;
        } else if ((v.getXCoord() < gameQuadrantWidth/2) && (v.getYCoord() > gameQuadrantHeight/2)) {
            return 2;
        } else {
            return 3;
        }
    }
    
    public static void sortPhysicObjectPosition(CollisionObject obj) {
        /** Sort obj to their respective quadrant to reduce amount of calculation
         * And keep that infomation in quadrant container
         */
        Vector2D objPosition = obj.getPosition();
        Vector2D objSize = obj.getSize();
        int ltVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord(), objPosition.getYCoord() + objSize.getYCoord()));
        int rtVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord()+ objSize.getXCoord(), objPosition.getYCoord() + objSize.getYCoord()));
        int lbVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord(), objPosition.getYCoord()));
        int rbVertexQuadrant = GameManipulator.getQuadrant(new Vector2D(objPosition.getXCoord() + objSize.getXCoord(), objPosition.getYCoord()));
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
    

    
    private void setYCollsion(double YIntersect, KinematicObject k, CollisionObject otherObj, double YAxis) {
        if (YIntersect <= 0) {return;}
        if (YAxis >= 0) {
            k.getPosition().setY(otherObj.getBounds().getMinY() - k.getSize().getYCoord());
        } else {
            k.getPosition().setY(otherObj.getBounds().getMaxY());
        }
        k.getAcceleration().setY(0);
        k.getVelocity().setY(0);
    }

    private void setXCollsion(double XIntersect, KinematicObject k, CollisionObject otherObj, double XAxis) {
        if (XIntersect <= 0) { return; }
        if (XAxis >= 0) {
            k.getPosition().setX((otherObj.getBounds().getMinX() - k.getSize().getXCoord()));
        } else {
            k.getPosition().setX((otherObj.getBounds().getMaxX()));
        }
        k.getAcceleration().setX(0);
        k.getVelocity().setX(0);
    }
    
    public void applyPhysic() {
        
        for (ArrayList quadrant : GameManipulator.quadrantContainer) {
            quadrant.clear();
        }
        for (CollisionObject obj : physicObjects) { //Sort quadrant for every collision object 
            GameManipulator.sortPhysicObjectPosition(obj);
        }
        for (CollisionObject obj : physicObjects) { //Apply collsion and gravity to kinematic
            if (obj instanceof KinematicObject k) {
                k.addVelocity(k.getAcceleration());
                k.movePostion(k.getVelocity());
                k.setAcceleration(new Vector2D(0, EngineSettings.GRAVITY_CONSTANT/60));
                int[] IsInside = k.getQuadrants();
                boolean touchedFloor = false;
                for (int quadrant : IsInside) {
                    if (quadrant == -1) { break;}
                    for (CollisionObject otherObj : GameManipulator.quadrantContainer.get(quadrant)) {
                        if (k.equals(otherObj) || otherObj.getCollision() == false) {continue;}
                        Rectangle2D collidedArea = k.getBounds().createIntersection(otherObj.getBounds());
                        if (collidedArea.isEmpty()) {continue;}
                        boolean skip = notifyGameEventListener(otherObj, k);
                        if (skip == true) {continue;}
                        double XOverlap = collidedArea.getWidth();
                        double YOverlap = collidedArea.getHeight();
                        double XAxis = otherObj.getBounds().getCenterX() - k.getBounds().getCenterX();
                        double YAxis = otherObj.getBounds().getCenterY() - k.getBounds().getCenterY();
                        if (Math.abs(XAxis) < Math.abs(YAxis) 
                                || (XOverlap > YOverlap)) {
                            setYCollsion(YOverlap, k, otherObj, YAxis);
                        } else {
                            setXCollsion(XOverlap, k, otherObj, XAxis);
                        }
                        
                        Rectangle2D floorDetector = new Rectangle2D.Double(k.getBounds().getMinX() , k.getBounds().getMaxY(), k.getSize().getXCoord(), 2);
                        touchedFloor |= floorDetector.intersects(otherObj.getBounds());
                    }
                }
                k.setTouchedFloor(touchedFloor);
            }
        }
        
    }
    
    public static void printPhysicObject() {
        for (int i = 0; i < 4; i++) {
            System.out.println(i + "-------------");
            for (CollisionObject obj : GameManipulator.quadrantContainer.get(i)) {
                System.out.println(obj.getName());
            }
        }
        System.out.println("End <><><>><><><><><><><>");
    }
}
