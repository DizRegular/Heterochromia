package RenderObject;
public class KinematicObject extends CollisionObject implements Physic {
    protected Vector2D acceleration = new Vector2D(0,0);
    protected Vector2D velocity = new Vector2D(0,0);
    public KinematicObject(String name, Vector2D pos, Vector2D size, String imageName, String tag) {
        super(name, pos, size, imageName, tag);
    }
    public KinematicObject(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
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
    
    @Override
    public void movePostion(Vector2D pos) {
        this.setPosition(new Vector2D(this.position.getXCoord() + pos.getXCoord(), this.position.getYCoord() + pos.getYCoord()));
        this.boundary.setLocation((int)this.position.getXCoord() + (int)pos.getXCoord(), (int)this.position.getYCoord() + (int)pos.getYCoord());
    }
}
