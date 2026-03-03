package RenderObject;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
abstract public class GameObject {    
    protected BufferedImage image;
    protected Vector2D postion;
    protected Vector2D size;
    public GameObject(Vector2D pos, Vector2D size, String ImageName) {
        this.size=size;
        this.postion = pos;
        
        try{
            this.image = ImageIO.read(new File(ImageName));
        }catch (IOException e) {
    e.printStackTrace();
}
    }
    
    
    
}
