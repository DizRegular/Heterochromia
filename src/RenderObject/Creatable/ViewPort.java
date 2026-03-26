package RenderObject.Creatable;

import RenderObject.BaseObject;
import RenderObject.InvalidGameObjectPropertyException;
import UniverseEngine.Game;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewPort extends BaseObject {
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
        screen.revalidate();
        window.pack();
        window.setLocationRelativeTo(null);
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
}
