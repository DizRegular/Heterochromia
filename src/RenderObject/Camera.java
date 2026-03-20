package RenderObject;

import UniverseEngine.Renderer;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Camera extends GameObject {
    private JFrame view;
    private Renderer renderStyle;
    private double zoomFactor = 1;
    
    public Camera(String name) {
        super(name);
        super.Enabled = false;
    }
    
    @Override
    public void setSize(Vector2D v) throws InvalidGameObjectPropertyException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenSizeX = screenSize.width;
        double screenSizeY = screenSize.height;
        if ((screenSizeX >= v.getXCoord()) &&
            (screenSizeY >= v.getYCoord())) {
            this.size = v;
        } else {
            throw new InvalidGameObjectPropertyException(this.name + "camera size is larger than player's screen");
        }
        
    }

    public Renderer getRenderStyle() {
        return renderStyle;
    }

    public void setRenderStyle(Renderer renderStyle) {
        this.renderStyle = renderStyle;
    }
    
    public JFrame getWindow() {
        return this.view;
    }
    
    public void setWindow(JFrame view) {
        this.view = view;
    }
    
    public double getZoomFactor() {
        return this.zoomFactor;
    }
    
    public void setZoomFactor(double n) {
        this.zoomFactor = n;
    }
    public Vector2D getPosition() {
        return this.position;
    }
    
    public void setPositionByCenter(GameObject constraint) {
        this.position = new Vector2D(constraint.getPostion().getXCoord() + constraint.getSize().getXCoord()/2 - size.getXCoord()/2
                , constraint.getPostion().getYCoord() + constraint.getSize().getYCoord()/2 - size.getYCoord()/2);
    }
    
    public void snap() {
        Renderer.render(this, view);
    }
}
