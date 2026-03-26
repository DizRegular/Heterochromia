package RenderObject;

import RenderObject.Addon.Scriptable;
import java.awt.Color;

abstract public class FadeProfile extends BaseObject implements Scriptable {
    private Color fadeColor;
    private double lifeSpan;
    private String Interpolation = "Linear";
    private int maxOpacity = 100;

    public FadeProfile(String name) {
        super(name);
    }
    
    @Override
    public void process(double deltaTime) {
        
    }
    
}
