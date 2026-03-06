package Main;
import RenderObject.*;
import Logic.Main;
import java.util.ArrayList;
public class GameManipulator {    
    
    /** Controls physic and order of action
     */
    private Main devAPI;
    
    public GameManipulator(Main api) {
        this.devAPI = api;
    }
    
    public void tick(double deltaTime) {
        this.applyPhysic();
        devAPI.processEvent();
        devAPI.process(deltaTime);
    }
        
    public void applyPhysic() {
        ArrayList<KinematicObject> PhysicObjects = GameUniverse.ObservePhysic();
        for (KinematicObject obj : PhysicObjects) {
            obj.addVelocity(obj.getAcceleration());
            obj.addAcceleration(new Vector2D(0, 9.8/60));
            obj.movePostion(obj.getVelocity());
            obj.setAcceleration(new Vector2D(0, 9.8/60));
        }
    }
}
