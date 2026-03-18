package RenderObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

abstract public class StyledObject extends GameObject {
    protected BufferedImage image;
    protected boolean visibility = true;

    public StyledObject(String name) {
        super(name);
        try {
            this.image = ImageIO.read(new File("res/GameAssets/Textures/NoImagePlaceHolder.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setTexture(File file) {
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setTexture(String pathfile) {
        try {
            this.image = ImageIO.read(new File(pathfile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public BufferedImage getTexture() {
        return this.image;
    }
    
    public boolean getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(boolean b) {
        this.visibility = b;
    }
    
    public static void setTextureAll(StyledObject[] objs, File file) {
        int size = objs.length;
        for (int i =0; i < size; i++) {
            objs[i].setTexture(file);
        }
    }
    
    public static void setTextureAll(StyledObject[] objs, String pathfile) {
        int size = objs.length;
        for (int i =0; i < size; i++) {
            objs[i].setTexture(pathfile);
        }
    }
}
