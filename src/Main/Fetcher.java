package Main;
import java.awt.Color;
import java.util.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
public class Fetcher {
    /** Load games assets so other game handler can use the resource.
     * 
     */
    private static final int pixelResolution = 64; //determine sprite sheet grid size
    private static final File texture2Dlocation = new File("res/GameAssets/Textures");
    
    
    private HashMap<String, BufferedImage> textures2D = new HashMap<>();
    public Fetcher() {
        String[] textureFolder = texture2Dlocation.list();
        for (String textureDes : textureFolder) {
            BufferedImage texture = null;
                try {
                    texture = ImageIO.read(new File(texture2Dlocation.getPath() + "/" + textureDes));
                } catch (IOException e) {
                    e.printStackTrace();
                    texture = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
                    texture.setRGB(0, 0, Color.MAGENTA.getRGB());
                }
            int imageHeight = texture.getHeight();
            int imageWidth = texture.getWidth();
            for (int i = 0; i < imageHeight/pixelResolution; i++) {
                for (int j = 0; j < imageWidth/pixelResolution; j++) {
                    String eye = String.valueOf(i);
                    String jay = String.valueOf(j);
                    textures2D.put(eye+jay, texture);
                }
            }   
        }
        System.out.println(textures2D.toString());
    }
    
    
    public HashMap<String, BufferedImage> getTextures2D() {
        return this.textures2D;
    }
}
