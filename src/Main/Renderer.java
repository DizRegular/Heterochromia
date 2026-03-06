package Main;
import RenderObject.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
public class Renderer {
    /** Render every that this game is meant to see to the player
     * 
     */
    private Fetcher gameAssetsLoader;
    
    private JFrame gameWindow;
    
    public Renderer(Fetcher f, JFrame window) {
        this.gameAssetsLoader = f;
        this.gameWindow = window;
    }
    
    public void render() {
        BufferStrategy bs = this.gameWindow.getBufferStrategy();
        if (bs == null) {
            this.gameWindow.createBufferStrategy(3);
            return;
        }
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());
        
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        
        for (GameObject object : GameUniverse.ObserveUniverse()) {
            if (object instanceof StyledObject styled) {
                if (styled.getVisibility() == true) {
                    g.drawImage(styled.getTexture(), (int)styled.getPostion().getXCoord(), (int)styled.getPostion().getYCoord(), (int)styled.getSize().getXCoord(), (int)styled.getSize().getYCoord(), null); 
                }
            }

        }
        g.dispose();
        bs.show();
    }
}
