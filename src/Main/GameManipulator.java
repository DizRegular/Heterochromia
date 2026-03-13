package Main;
import RenderObject.*;
import Logic.Main;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
public class GameManipulator {    
    
    /** Controls physic and order of action
     */
    private Main devAPI;
    private static final ArrayList<ArrayList<CollisionObject>> quadrantContainer = new ArrayList<>();
    
    public GameManipulator(Main api) {
        this.devAPI = api;
        for (int i = 0; i < 4; i++) {
            quadrantContainer.add(i, new ArrayList<>());
        }
    }
    
    public void tick(double deltaTime) {
        ArrayList<CollisionObject> PhysicObjects = GameUniverse.ObservePhysic();
        
        this.applyPhysic(PhysicObjects);
        devAPI.processEvent();
        devAPI.process(deltaTime);
    }
        
    public static int getQuadrant(Vector2D v) {
        /** find what quadrant this vertex belongs to
         */
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
    
    public static void sortPhysicObjectPosition(CollisionObject obj) {
        /** Sort obj to their respective quadrant to reduce amount of calculation
         * And keep that infomation in quadrant container
         */
        Vector2D objPosition = obj.getPostion();
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
    

    
    private boolean setYCollsion(double YIntersect, KinematicObject k, CollisionObject otherObj, Rectangle2D collidedArea, Vector2D startVelo, Vector2D startAcce) {
        int direction = -1;
        double a2 = k.getBounds().getMaxY();
        double b2 = otherObj.getBounds().getMinY();
        double YDiff = k.getBounds().getMaxY() - otherObj.getBounds().getMinY();
        if (YDiff < 0) {
            direction *= -1;
        }
        if (YIntersect > 0) {
            k.movePostion(new Vector2D(0, YDiff * direction));
            k.setAcceleration(new Vector2D(startAcce.getXCoord(), 0));
            k.setVelocity(new Vector2D(startVelo.getXCoord(),0));
            System.out.println(collidedArea.getHeight() + "," + a2 + "," + b2);
            System.out.println(k.getBounds().getMaxY());
            return true;
        }
        return false;
    }
    
    private void setXCollsion(double XIntersect, KinematicObject k, CollisionObject otherObj, Rectangle2D collidedArea, Vector2D startVelo, Vector2D startAcce) {
        if (XIntersect > 0) {
            k.movePostion(new Vector2D(collidedArea.getWidth(), 0));
            k.setAcceleration(new Vector2D(0, startAcce.getYCoord()));
            k.setVelocity(new Vector2D(0,startVelo.getYCoord()));
        }
    }
    
    public void applyPhysic(ArrayList<CollisionObject> PhysicObjects) {
        for (ArrayList quadrant : GameManipulator.quadrantContainer) {
            quadrant.clear();
        }
        for (CollisionObject obj : PhysicObjects) { //Sort quadrant for every collision object 
            GameManipulator.sortPhysicObjectPosition(obj);
        }
        for (CollisionObject obj : PhysicObjects) { //Apply collsion and gravity to kinematic
            if (obj instanceof KinematicObject k) {
                k.addVelocity(k.getAcceleration());
                k.movePostion(k.getVelocity());
                k.setAcceleration(new Vector2D(0, 9.8/60));
                int[] IsInside = k.getQuadrants();
                boolean touchedFloor = false;
                Vector2D startVelo = k.getVelocity();
                Vector2D startAcce = k.getAcceleration();
                for (int quadrant : IsInside) {
                    if (quadrant == -1) { break;}
                    for (CollisionObject otherObj : GameManipulator.quadrantContainer.get(quadrant)) {
                        if (k.equals(otherObj)) {continue;}
                        Rectangle2D collidedArea = k.getBounds().createIntersection(otherObj.getBounds());
                        if (collidedArea.isEmpty()) {continue;}
                        double XIntersect = collidedArea.getHeight();
                        double YIntersect = collidedArea.getWidth();
                        touchedFloor = setYCollsion(YIntersect, k, otherObj, collidedArea, startVelo, startAcce);
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
