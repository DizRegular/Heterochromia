package RenderObject;
import java.util.ArrayList;

abstract public class GameObject extends BaseObject { 
    protected Vector2D position;
    protected Vector2D size;
    protected boolean isConstrained = false;
    protected boolean isMoving = false;
    protected ArrayList<GameObject> constraints = new ArrayList<>();
    
    public GameObject(String name) {
        super(name);
    }
    
    public Vector2D getPosition() {
        return this.position;
    }
    
    public Vector2D getSize() {
        return this.size;
    }
    
    public void setSize(Vector2D v) throws InvalidGameObjectPropertyException  {
        if ((5000 >= v.getXCoord()) &&
            (5000 >= v.getYCoord()) && 
            (10 <= v.getXCoord()) &&
            (10 <= v.getYCoord())) {
            this.size = v;
        } else {
            throw new InvalidGameObjectPropertyException(this.name + "'s size is larger than 5000 or smaller than 10");
        }
    }
    
    /** teleport position of this object to another position 
     * 
     * @param pos
     */
    synchronized public void setPosition(Vector2D pos) {
        this.position = pos;
    }
    
    public void updateConstraint(GameObject parent) {
        for (GameObject constraint : this.constraints) {
            double XCoord = parent.position.getXCoord() + parent.size.getXCoord()/2;
            double YCoord = parent.position.getYCoord() + parent.size.getYCoord()/2;
            constraint.movePostion(new Vector2D(XCoord, YCoord));
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
    
    public ArrayList<GameObject> getConstraints() {
        return this.constraints;
    }
    
    public boolean hasConstrained() {
        return this.isConstrained;
    }
    
    public void setIsConstrain(boolean b) {
        this.isConstrained = b;
    }
    
    public void addConstraint(GameObject obj) throws InvalidGameObjectPropertyException {
        if (obj == null) {
            throw new InvalidGameObjectPropertyException(this.ID + " : this object tries to add constraint that is \"null\"");
        }
        this.constraints.add(obj);
        obj.setIsConstrain(true);
        this.movePostion(new Vector2D(0,0));
    }
    
    public void removeConstraint(GameObject obj) {
        this.constraints.remove(obj);
    }
    
    @Override
    public void createInstance() throws InvalidGameObjectPropertyException {
        super.createInstance();
        if (this.size == null) {
            throw new InvalidGameObjectPropertyException(this.ID + " : Object size has not been initialized.");
        } else if (this.position == null) {
            throw new InvalidGameObjectPropertyException(this.ID + " : Object position has not been intialized.");
        }
    }
    
    @Override
    public String toString() {
        return this.getID();
    }
    
}
