package RenderObject.Addon;

import RenderObject.Creatable.Vector2D;

public interface Gravity {
    abstract public void addVelocity(Vector2D v);
    abstract public Vector2D getVelocity();
    abstract public void setVelocity(Vector2D v);
    
    abstract public void addAcceleration(Vector2D v);
    abstract public Vector2D getAcceleration();
    abstract public void setAcceleration(Vector2D v);
    abstract public boolean getGravity();
    abstract public void setGravity(boolean b);
    
    abstract public void showPhysicProperties();
    
}
