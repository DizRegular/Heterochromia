package RenderObject;

import java.awt.Color;

abstract public class FadeProfile extends BaseObject {
    private Color fadeColor;
    private double lifeSpan;
    private String Interpolation = "Linear";
    private int maxOpacity = 100;

    public FadeProfile(String name) {
        super(name);
    }
    
    
}
