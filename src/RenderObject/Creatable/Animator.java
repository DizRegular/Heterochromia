package RenderObject.Creatable;

import RenderObject.BaseObject;
import RenderObject.Creatable.Decoration;
import RenderObject.Creatable.Vector2D;
import UniverseEngine.AnimationManager;
import UniverseEngine.Fetcher;
import UniverseEngine.GameUniverse;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Animator extends BaseObject{
    String[] SpriteImages;
    Vector2D imageSize;
    Vector2D spriteSize;
    int currFrameIndex = 0;
    double speed = 1;
    double accumulateTime = 0;
    
    public Animator(String name) {
        super(name);
    }
    
    public String getCurrentFrameImage() {
        return SpriteImages[currFrameIndex];
    }
    
    public void setAnimationSheet(Vector2D spriteSize, String pathfile) {
        this.spriteSize = spriteSize;
        try (FileInputStream fin = new FileInputStream(pathfile)) {
            BufferedImage image = ImageIO.read(fin);
            int currX = image.getWidth()/(int)spriteSize.getXCoord();
            int currY = image.getHeight()/(int)spriteSize.getYCoord();
            SpriteImages = new String[currX*currY];
            int i = 0;
            for (int x = 0; x<currX; x++) {
                for (int y = 0; y<currY; y++) {
                    BufferedImage sprite = image.getSubimage(x*(int)spriteSize.getXCoord(), y*(int)spriteSize.getYCoord(), (int)spriteSize.getXCoord(), (int)spriteSize.getYCoord());
                    Fetcher.storeTexture(sprite, name + x + y);
                    SpriteImages[i] = name + x + y;
                    i++;
                }
            }
            this.StoreSpriteImages(SpriteImages, new Vector2D(image.getWidth(), image.getHeight()));
        } catch (IOException e) {Fetcher.fixNoImageFound(name);}
        
    }
    
    private void StoreSpriteImages(String[] spim, Vector2D imageSize) {
        this.SpriteImages = spim;
        this.imageSize = imageSize;
    }
    
    public void debugSpriteSheet() {
        int i = 0;
        for (String SpriteName : SpriteImages) {
            Decoration image = GameUniverse.createInstance(new Decoration(SpriteName+"debug"));
            image.setTexture(SpriteName);
            image.setSize(spriteSize.multiply(5));
            image.setPosition(new Vector2D(spriteSize.getXCoord()*i, 0));
            i++;
        }
    }
    
    public double getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    
    public void animate(double deltaTime) {
        this.accumulateTime += deltaTime;
        if (this.accumulateTime >= speed) {
            if (SpriteImages.length-1 <= currFrameIndex) {
                currFrameIndex = 0;
            } else {
                currFrameIndex++;
            }
            accumulateTime = 0;
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        this.Enabled = false;
        AnimationManager.registerStyledObject(this);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        AnimationManager.unregisterStyledObject(this);
    }
}
