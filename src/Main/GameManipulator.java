package Main;
import RenderObject.*;
import Logic.Main;

public class GameManipulator {    
    private Main devAPI;
    
    public GameManipulator(Main api) {
        this.devAPI = api;
    }
    
    public void tick(double deltaTime) {
        devAPI.process(deltaTime);
    }
}
