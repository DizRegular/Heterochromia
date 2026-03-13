package Main;
import RenderObject.*;
import Logic.Main;
import java.awt.Rectangle;
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
    

    
    private boolean setYCollsion(double YIntersect, KinematicObject k, Rectangle collidedArea, Vector2D startVelo, Vector2D startAcce) {
        if (YIntersect > 0) {
            k.movePostion(new Vector2D(0, collidedArea.getHeight() * -1));
            k.setAcceleration(new Vector2D(startAcce.getXCoord(), 0));
            k.setVelocity(new Vector2D(startVelo.getXCoord(),0));
            return true;
        }
        return false;
    }
    
    private void setXCollsion(double XIntersect, KinematicObject k, Rectangle collidedArea, Vector2D startVelo, Vector2D startAcce) {
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
            if (obj.getPhysicChange() == true) {
                GameManipulator.sortPhysicObjectPosition(obj);
                obj.setPhysicChange(false);
            }
        }
        for (CollisionObject obj : PhysicObjects) { //Apply collsion and gravity to kinematic
            if (obj instanceof KinematicObject k) {
                int[] IsInside = k.getQuadrants();
                boolean touchedFloor = false;
                Vector2D startVelo = k.getVelocity();
                Vector2D startAcce = k.getAcceleration();
                for (int quadrant : IsInside) {
                    if (quadrant == -1) { break;}
                    for (CollisionObject otherObj : GameManipulator.quadrantContainer.get(quadrant)) {
                        if (k.equals(otherObj)) {continue;}
                        Rectangle collidedArea = k.getBounds().intersection(otherObj.getBounds());
                    
                        if (collidedArea.isEmpty()) {continue;}
                        double XIntersect = collidedArea.getHeight();
                        double YIntersect = collidedArea.getWidth();
                        if (XIntersect < YIntersect) {
                            touchedFloor = setYCollsion(YIntersect, k, collidedArea, startVelo, startAcce);
                            collidedArea = k.getBounds().intersection(otherObj.getBounds());
                            if (collidedArea.isEmpty()) {continue;}
                            setXCollsion(XIntersect, k, collidedArea, startVelo, startAcce);
                        } else {
                            setXCollsion(XIntersect, k, collidedArea, startVelo, startAcce);
                            collidedArea = k.getBounds().intersection(otherObj.getBounds());
                            if (collidedArea.isEmpty()) {continue;}
                            touchedFloor = setYCollsion(YIntersect, k, collidedArea, startVelo, startAcce);
                        }
                    }
                if (!touchedFloor) {
                    k.addVelocity(k.getAcceleration());
                    k.movePostion(k.getVelocity());
                    k.setAcceleration(new Vector2D(0, 9.8/60));
                }
                k.setTouchedFloor(touchedFloor);
                }
            }
        }
        
    }
}
