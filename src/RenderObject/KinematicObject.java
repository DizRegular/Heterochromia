package RenderObject;

import UniverseEngine.GameManipulator;
import UniverseEngine.GameUniverse;

public class KinematicObject extends CollisionObject implements Gravity {
    protected Vector2D acceleration = new Vector2D(0,0);
    protected Vector2D velocity = new Vector2D(0,0);
    protected boolean touchedFloor = false;
    
    public KinematicObject(String name) {
        super(name);
    }
    
    @Override
    public void createInstance() throws InvalidGameObjectPropertyException {
        super.createInstance();
        GameManipulator.sortPhysicObjectPosition(this);

    }
    
     @Override
    public void addVelocity(Vector2D v) {
        this.velocity = this.velocity.addVector2D(v);
    }

    @Override
    public Vector2D getVelocity() {
        return this.velocity;
    }
    
    @Override
    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    @Override
    public void addAcceleration(Vector2D a) {
        this.acceleration = this.acceleration.addVector2D(a);
    }

    @Override
    public Vector2D getAcceleration() {
        return this.acceleration;
    }

    @Override
    public void setAcceleration(Vector2D a) {
        this.acceleration = a;
    }
    
    @Override
    public void showPhysicProperties() {
        System.out.println("{A: " + this.getAcceleration().toString() + "}\n" + "{V: " + this.getAcceleration().toString() + "}");
    }
    
    public boolean getTouchedFloor() {
        return this.touchedFloor;
    }
    
    public void setTouchedFloor(boolean t) {
        this.touchedFloor = t;
    }
}
