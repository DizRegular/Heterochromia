package RenderObject;
import java.awt.image.*;
import java.io.*;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

abstract public class GameObject { 
    protected String name;
    protected Vector2D position;
    protected Vector2D size;
    protected String Tag;
    protected boolean isSolid = true;
    protected boolean visibility = true;
    
    public GameObject(String name, Vector2D pos, Vector2D size,String tag) {
        this.name = name;
        this.size=size;
        this.position = pos;
        this.Tag=tag;        
    }

    public boolean isIsSolid() {
        return isSolid;
    }

    public void setIsSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }
    
    public GameObject(String name, Vector2D pos, Vector2D size) {
        this.name = name;
        this.size=size;
        this.position = pos;        
    }
        
    public String getName() {
        return this.name;
    }
    
    public Vector2D getPostion() {
        return this.position;
    }
    
    public Vector2D getSize() {
        return this.size;
    }
    
    /** teleport position of this object to another position 
     * 
     * @param pos
     */
    public void setPosition(Vector2D pos) {
        this.position = pos;
    }
    
    public boolean getVisibility() {
        return this.visibility;
    }
    
    public void setVisibility(boolean b) {
        this.visibility = b;
    }
    /** move position of this object relative to their current position
     * 
     * @param pos
     */
    public void movePostion(Vector2D pos) {
        this.setPosition(new Vector2D(this.position.getXCoord() + pos.getXCoord(), this.position.getYCoord() + pos.getYCoord()));
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
