package RenderObject;
import java.util.ArrayList;

abstract public class GameObject extends FounderObject { 
    protected Vector2D position;
    protected Vector2D size;
    protected ArrayList<GameObject> constraints = new ArrayList<>();
    
    public GameObject(String name) {
        super(name);
    }
    
    public Vector2D getPostion() {
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
        for (GameObject obj : constraints) {
            if (obj instanceof Camera camera) {
                camera.setPositionByCenter(this);
            } else {
                obj.position = pos;
            }
        }
    }
    
    /** move position of this object relative to their current position
     * 
     * @param pos
     */
    synchronized public void movePostion(Vector2D pos) {
        this.setPosition(new Vector2D(this.position.getXCoord() + pos.getXCoord(), this.position.getYCoord() + pos.getYCoord()));
    }
    
    public ArrayList<GameObject> getConstraints() {
        return this.constraints;
    }
    
    public void addConstraint(GameObject obj) throws InvalidGameObjectPropertyException {
        if (obj == null) {
            throw new InvalidGameObjectPropertyException(this.ID + " : this object tries to add constraint that is \"null\"");
        }
        this.constraints.add(obj);
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
