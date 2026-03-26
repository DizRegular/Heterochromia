package RenderObject;

import UniverseEngine.RenderManager;
import UniverseEngine.Renderer;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Camera extends GameObject {
    private JFrame view;
    private Renderer renderStyle;
    private double zoomFactor = 1;
    private Vector2D oldPosition = new Vector2D();
    
    public Camera(String name) {
        super(name);
        super.Enabled = false;
    }

    @Override
    public void setSize(Vector2D v) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenSizeX = screenSize.width;
        double screenSizeY = screenSize.height;
        if ((screenSizeX >= v.getXCoord()) &&
            (screenSizeY >= v.getYCoord())) {
            this.size = v;
        } else {
            this.size = new Vector2D(screenSizeX, screenSizeY);
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
    
    public Vector2D getOldPosition() {
        return this.oldPosition;
    }
    
    public void setOldPosition(Vector2D currPos) {
        this.oldPosition = currPos;
    }
    
    public void transition() {
        
    }
    
    @Override
    public void onDestroy() {
        RenderManager.unregisterViewer(this);
    }
    
    @Override
    public void instance() {
        super.instance();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenSizeX = screenSize.width;
        double screenSizeY = screenSize.height;
        this.size = new Vector2D(screenSizeX, screenSizeY);
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("aA");
        RenderManager.registerNewViewer(this);
    }
    
    public void snap() {
        Renderer.render(this, view);
    }
}
