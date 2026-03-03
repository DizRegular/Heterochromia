package RenderObject;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Block extends GameObject {
    protected BufferedImage image;
    
    public Block(Vector2D pos, Vector2D size, String ImageName) {
        super(pos, size);
        try {
            this.image = ImageIO.read(new File(ImageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getTexture() {
        return this.image;
    }
}