package UniverseEngine;
import RenderObject.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
abstract public class Renderer {
    /** Render every that this game is meant to see to the player
     */
    private Fetcher gameAssetsLoader;
    private Camera camera;
    private JFrame viewPort;
    
    private static String text = ""; //use this for debugging
    public Renderer(JFrame window, Camera camera) {
        this.camera = camera;
        this.viewPort = camera.getWindow();
    }
    
    public void render() {
        BufferStrategy bs = this.camera.getWindow().getBufferStrategy();
        if (bs == null) {
            this.camera.getWindow().createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.setColor(Color.blue);
        g.fillRect(0, 0, (int)this.camera.getSize().getXCoord(), (int)this.camera.getSize().getYCoord());
        g.drawImage(GameUniverse.getBackground(), 0, 0,(int)this.camera.getSize().getXCoord(), (int)this.camera.getSize().getYCoord(), null);
        
        double zoomFactor = this.camera.getZoomFactor();
        double XCenter = viewPort.getSize().getWidth() / 2;
        double YCenter = viewPort.getSize().getHeight()/ 2;
        AffineTransform transform = g.getTransform();
        g.translate(XCenter, YCenter);
        g.scale(zoomFactor, zoomFactor);
        g.translate(-XCenter, -YCenter);
        for (GameObject object : GameUniverse.ObserveUniverse()) {
            if (object instanceof StyledObject styled && styled.getVisibility()) {
                g.drawImage(styled.getTexture(), (int)styled.getPostion().getXCoord() - (int)camera.getPosition().getXCoord(), (int)styled.getPostion().getYCoord() - (int)camera.getPosition().getYCoord(), (int)styled.getSize().getXCoord(), (int)styled.getSize().getYCoord(), null); 
            }

        }
        g.setTransform(transform);

        g.drawString("Frames:" + Game.getFramesPerSecond(), 100, 10);
        g.drawString("Tick:" + Game.getTickPerSecond(), 100, 30);
        g.drawString(text, 100, 50);
        g.dispose();
        bs.show();
    }
    
    public static void setPrintingTextOnScreen(String s) {
        Renderer.text = s;
    }
}
