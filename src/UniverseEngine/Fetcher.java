package UniverseEngine;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.image.BufferedImage;
public class Fetcher {
    /** Load games assets so other game handler can use the resource.
     */
    private static String MISSING_TEXTURE_PATH = EngineSettings.MISSING_TEXTURE_PATH;
    private static HashMap<String, BufferedImage> texturesHolder = new HashMap<>();
    
    public static void initiate() {
        try (FileInputStream fin = new FileInputStream(MISSING_TEXTURE_PATH)) {
            BufferedImage missingTexureImage = ImageIO.read(fin);
            texturesHolder.put("missingTexureImage", missingTexureImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void loadTexture(String name, String pathfile) {
        try (FileInputStream fin = new FileInputStream(pathfile)) {
            BufferedImage texture = ImageIO.read(fin);
            texturesHolder.put(name, texture);
        } catch (IOException e) {
            
        }
    }
    
    public static BufferedImage getTextures2D(String name) {
        BufferedImage texture = texturesHolder.get(name);
        if (texture == null) {
            return texturesHolder.get("missingTexureImage");
        }
        return texture;
    }
    
}
