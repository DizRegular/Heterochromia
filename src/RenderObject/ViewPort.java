package RenderObject;

import UniverseEngine.Game;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewPort extends FounderObject {
    private Camera currView;
    private JFrame window;
    private JPanel screen;
    private boolean fullScreen = false;
    
    public ViewPort(String name) {
        super(name);
        this.window = Game.createNewWindow(name);
        this.screen = (JPanel)this.window.getContentPane();
    }
    
    public Camera getCamera(Camera cam) {
        return this.currView;
    }
    
    public void setCamera(Camera cam) throws InvalidGameObjectPropertyException {
        if (cam.hasInstance() == false) {
            throw new InvalidGameObjectPropertyException(this.ID + " : Can't Set viewPort to a camera that hasn't been initialized.");
        }
        if (this.currView != null) {
            this.removeCamera();
        }
        this.currView = cam;
        currView.setEnabled(true);
        currView.setWindow(window);
        screen.setPreferredSize(new Dimension((int)currView.getSize().getXCoord(), (int)currView.getSize().getYCoord()));
        
        window.pack();
    }
    
    public void removeCamera() {
        this.currView.setWindow(null);
        this.currView.setEnabled(false);
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
    }
    
    
}
