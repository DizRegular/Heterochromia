package RenderObject;
import java.awt.image.*;
import java.io.*;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

abstract public class GameObject { 
    protected String name;
    protected Vector2D position;
    protected Vector2D size;
    protected Rectangle boundary;
    protected String Tag;
    protected boolean visibility = true;
    public GameObject(String name, Vector2D pos, Vector2D size,String tag) {
        this.name = name;
        this.size=size;
        this.position = pos;
        this.Tag=tag;
        this.boundary = new Rectangle((int)position.getXCoord(), (int)position.getYCoord(), (int)size.getXCoord(), (int)size.getYCoord());
        
    }
    public GameObject(String name, Vector2D pos, Vector2D size) {
        this.name = name;
        this.size=size;
        this.position = pos;
        this.boundary = new Rectangle((int)position.getXCoord(), (int)position.getYCoord(), (int)size.getXCoord(), (int)size.getYCoord());
        
    }
    
    public Rectangle getBounds(){
        return this.boundary;
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
}
