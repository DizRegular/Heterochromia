package RenderObject;

import UniverseEngine.Game;
import UniverseEngine.NormalRenderer;
import UniverseEngine.Renderer;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewPort extends FounderObject {
    private Camera currView;
    private JFrame window;
    private JPanel screen;
    private boolean fullScreen = false;
    private Renderer renderStyle;
    
    public ViewPort(String name) {
        super(name);
        this.window = Game.createNewWindow(name);
        screen = (JPanel)this.window.getContentPane();
    }
    
    public Camera getCamera(Camera cam) {
        return this.currView;
    }
    
    public void setCamera(Camera cam) {
        this.currView = cam;
        currView.setWindow(window);
    }
    
    public void removeCamera() {
        this.currView.setWindow(null);
        this.currView = null;
    }
    @Override
    public void setEnabled(boolean Enabled) {
        if (Enabled == true) {
            window.setVisible(true);
        } else {
            window.setVisible(false);
        }
        this.Enabled = Enabled;
    }
    

    @Override
    public void createInstance() throws InvalidGameObjectPropertyException {
        super.createInstance();
        if (currView == null) {
            throw new InvalidGameObjectPropertyException(this.ID + " : this viewport has not been assigned to any camera");
        }
        if (currView.instanced == false) {
            throw new InvalidGameObjectPropertyException(this.ID + " : this viewport's camera has not been initiated.");
        }
        try {
            currView.getPosition();
            currView.getSize();
        } catch (NullPointerException e) {
            throw new InvalidGameObjectPropertyException(this.ID + " : camera of this viewport doesn't initiate size or position");
        }
        renderStyle = new NormalRenderer(window, currView);
        currView.setRenderStyle(renderStyle);
        screen.setPreferredSize(new Dimension((int)currView.getSize().getXCoord(), (int)currView.getSize().getYCoord()));
        window.pack();
        this.setEnabled(this.Enabled);
    }
    
    
}
