package RenderObject;
import RenderObject.Creatable.Vector2D;
import java.util.ArrayList;

abstract public class GameObject extends BaseObject { 
    protected Vector2D position;
    protected Vector2D size;
    protected boolean isMoving = false;
    
    public GameObject(String name) {
        super(name);
    }
    
    public Vector2D getPosition() {
        return this.position;
    }
    
    public Vector2D getSize() {
        return this.size;
    }
    
    public void setSize(Vector2D v) {
        this.size = v;
    }
    
    /** teleport position of this object to another position 
     * 
     * @param pos
     */
    synchronized public void setPosition(Vector2D pos) {
        this.position = pos;
    }
    
    public void updateConstraint(GameObject parent) {
        for (BaseObject constraint : this.constraints) {
            if (constraint instanceof GameObject gConstraint) {
                double XCoord = parent.position.getXCoord() + parent.size.getXCoord()/2;
                double YCoord = parent.position.getYCoord() + parent.size.getYCoord()/2;
                gConstraint.movePostion(new Vector2D(XCoord, YCoord));
            }
        }

    }
    
    /** move position of this object relative to their current position
     * 
     * @param pos
     */
    synchronized public void movePostion(Vector2D pos) {
        if (isMoving) {return;}
        this.isMoving = true;
        if (this.isConstrained == true) { 
            this.setPosition( new Vector2D(pos.getXCoord() - size.getXCoord()/2, pos.getYCoord() - size.getYCoord()/2));
        } else {
            this.setPosition(new Vector2D(this.position.getXCoord() + pos.getXCoord(), this.position.getYCoord() + pos.getYCoord()));
        }
        if (this.constraints.isEmpty() == false) {this.updateConstraint(this);}
        this.isMoving = false;
    } 
    
    @Override
    public void addConstraint(BaseObject obj) throws InvalidGameObjectPropertyException {
        super.addConstraint(obj);
        this.movePostion(new Vector2D(0,0));
    }
    
    @Override
    public void instance() {
        super.instance();
        this.size = new Vector2D(10, 10);
        this.position = new Vector2D(0, 0);
        
    }
    
    @Override
    public String toString() {
        return this.getID();
    }
    
}
