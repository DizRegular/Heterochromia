package RenderObject;

import UniverseEngine.Fetcher;
import UniverseEngine.InputManager;
import UniverseEngine.NormalRenderer;
import UniverseEngine.Renderer;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Camera extends GameObject {
    private JFrame view;
    private JPanel screenDisplay;
    private Renderer renderStyle;
    private double zoomFactor = 1;
    
    public Camera(String name, Vector2D pos, Vector2D size) {
        super(name, pos, size);
        view = new JFrame(this.name);
        screenDisplay = new JPanel();

        screenDisplay.setPreferredSize(new Dimension((int)size.getXCoord(), (int)size.getYCoord()));
        screenDisplay.setBackground(Color.CYAN);
        
        JLabel label = new JLabel("Loading...");
        screenDisplay.add(label, BorderLayout.CENTER);
        view.add(screenDisplay);
        view.addKeyListener(new InputManager());

        view.pack();
//        GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().setFullScreenWindow(view);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        view.setPreferredSize(new Dimension((int)size.getXCoord(), (int)size.getYCoord()));
        renderStyle = new NormalRenderer(view, this);
    }
    
    public JFrame getWindow() {
        return this.view;
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
        renderStyle.render();
    }
}
