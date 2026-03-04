package RenderObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

abstract public class StyledObject extends GameObject {
    protected BufferedImage image;

    public StyledObject(String name, Vector2D pos, Vector2D size, String imageName,String type) {
        super(name, pos, size,type);
        try {
            this.image = ImageIO.read(new File(imageName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getTexture() {
        return this.image;
    }
}
