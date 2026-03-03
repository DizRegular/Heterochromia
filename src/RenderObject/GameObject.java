package RenderObject;
import java.awt.image.*;
import java.io.*;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
abstract public class GameObject {    
    protected BufferedImage image;
    protected Vector2D position;
    protected Vector2D size;
    public GameObject(Vector2D pos, Vector2D size, String ImageName) {
        this.size=size;
        this.position = pos;
        
        try{
            this.image = ImageIO.read(new File(ImageName));
        }catch (IOException e) {
    e.printStackTrace();
}
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)position.getXCoord(), (int)position.getYCoord(), (int)size.getXCoord(), (int)size.getYCoord());
    }
    
}
