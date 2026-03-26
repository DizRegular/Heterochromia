package UniverseEngine;
import RenderObject.Creatable.Camera;
import RenderObject.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JFrame;
public class Renderer {
    /** Render every that this game is meant to see to the player
     */
    private Fetcher gameAssetsLoader;
    private static final CopyOnWriteArrayList<StyledObject> texturedObject = new CopyOnWriteArrayList<>();
    private static String text = ""; //use this for debugging
    
    public static void render(Camera camera, JFrame viewPort) {
        BufferStrategy bs = camera.getWindow().getBufferStrategy();
        if (bs == null) {
            camera.getWindow().createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.setColor(EngineSettings.DEBUG_TEXT_COLOR);
        g.fillRect(0, 0, (int)camera.getSize().getXCoord(), (int)camera.getSize().getYCoord());
        g.drawImage(GameUniverse.getBackground(), 0, 0,(int)camera.getSize().getXCoord(), (int)camera.getSize().getYCoord(), null);
        
        double zoomFactor = camera.getZoomFactor();
        double XCenter = viewPort.getSize().getWidth() / 2;
        double YCenter = viewPort.getSize().getHeight()/ 2;
        AffineTransform transform = g.getTransform();
        g.translate(XCenter, YCenter);
        g.scale(zoomFactor, zoomFactor);
        g.translate(-XCenter, -YCenter);
        for (BaseObject object : texturedObject) {
            if (object instanceof StyledObject styled && styled.getVisibility()) {
                g.drawImage(GameUniverse.fetchImage(styled.getTexture()), 
                        (int)styled.getPosition().getXCoord() + (int)styled.getSize().getXCoord()/2 - (int)styled.getTextureSize().getXCoord()/2 - (int)camera.getPosition().getXCoord(), 
                        (int)styled.getPosition().getYCoord() + + (int)styled.getSize().getYCoord()/2 - (int)styled.getTextureSize().getYCoord()/2- (int)camera.getPosition().getYCoord(), 
                        (int)styled.getTextureSize().getXCoord(), 
                        (int)styled.getTextureSize().getYCoord(), 
                        null); 
                if (styled instanceof CollisionObject cobj && EngineSettings.SHOW_HITBOXES == true) {
                    g.drawRect((int)cobj.getBounds().getMinX() - (int)camera.getPosition().getXCoord(), (int)cobj.getBounds().getMinY()  - (int)camera.getPosition().getYCoord(), (int)cobj.getBounds().getWidth(), (int)cobj.getBounds().getHeight());
                }
            }
        }
        g.setTransform(transform);

        g.drawString("Frames:" + Game.getFramesPerSecond(), 100, 50);
        g.drawString("Tick:" + Game.getTickPerSecond(), 100, 70);
        g.drawString(camera.getSize().toString() , 100, 90);
        g.drawString(text, (int)camera.getSize().getXCoord()/2 , (int)camera.getSize().getYCoord()/2);
        g.dispose();
        bs.show();
        Toolkit.getDefaultToolkit().sync();
    }
    
    public static void registerStyledObject(StyledObject styObj) {
        Renderer.texturedObject.add(styObj);
    }
    
    public static void unregisterStyledObject(StyledObject styObj) {
        Renderer.texturedObject.remove(styObj);
    }
    
    public static void setPrintingTextOnScreen(String s) {
        Renderer.text = s;
    }
}
