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
    
    private Block blackblock = new Block(new Vector2D(), new Vector2D(500,500), "res/GameAssets/Textures/placeholder.jpg");
    private Graphics2D blackblock2 = blackblock.getTexture().createGraphics();
    
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
        blackblock.movePostion(new Vector2D(-1, 0));
        Graphics2D g = (Graphics2D)bs.getDrawGraphics();
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gameWindow.getWidth(), gameWindow.getHeight());
        
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
        RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        
        g.drawImage(blackblock.getTexture(), blackblock.getPostion().getXCoord(), blackblock.getPostion().getYCoord(), blackblock.getSize().getXCoord(), blackblock.getSize().getYCoord(), null);
        g.dispose();
        bs.show();
    }
}
