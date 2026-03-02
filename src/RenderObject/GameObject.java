package RenderObject;
import java.awt.image.*;
import java.io.*;
abstract public class GameObject {    
    protected BufferedImage image;
    protected Vector2D postion;
    
    public GameObject(Vector2D pos, Vector2D size, file image) {
        this.image = image;
        this.postion = pos;
    }
    
    
    
}
