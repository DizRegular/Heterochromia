package RenderObject;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;

abstract public class GameObject { 
    protected String name;
    protected Vector2D position;
    protected Vector2D size;
    protected String Tag;
    protected boolean isSolid = true;
    protected boolean visibility = true;
    protected ArrayList<GameObject> constraints = new ArrayList<>();
    
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
    synchronized public void setPosition(Vector2D pos) {
        this.position = pos;
        for (GameObject obj : constraints) {
            if (obj instanceof Camera camera) {
                camera.setPositionByCenter(this);
            } else {
                obj.position = pos;
            }
        }
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
    synchronized public void movePostion(Vector2D pos) {
        this.setPosition(new Vector2D(this.position.getXCoord() + pos.getXCoord(), this.position.getYCoord() + pos.getYCoord()));
    }
    
    public void addConstraint(GameObject obj) {
        this.constraints.add(obj);
    }
    
    public void removeConstraint(GameObject obj) {
        this.constraints.remove(obj);
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
