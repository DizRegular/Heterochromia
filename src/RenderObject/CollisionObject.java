package RenderObject;

import RenderObject.Creatable.Vector2D;
import UniverseEngine.GameManipulator;
import java.awt.geom.Rectangle2D;

abstract public class CollisionObject extends StyledObject {
    protected Rectangle2D boundary;
    protected int[] insideQudrant;
    protected boolean hasPhysicChange = true;
    protected boolean Collision = true;
    
    public CollisionObject(String name) {
        super(name);
    }
    
    public Rectangle2D getBounds(){
        return this.boundary;
    }
    
    @Override
    public void setPosition(Vector2D pos) {
        super.setPosition(pos);
        this.boundary.setFrame(pos.getXCoord(), pos.getYCoord(), this.boundary.getWidth(), this.boundary.getHeight());
    }
    
    @Override
    synchronized public void  movePostion(Vector2D pos) {
        super.movePostion(pos);
        Vector2D startPos = this.position;
        this.boundary.setFrame(startPos.getXCoord() + pos.getXCoord(), startPos.getYCoord() + pos.getYCoord(), this.boundary.getWidth(), this.boundary.getHeight());
        this.hasPhysicChange = true;
    }
    
    public int[] getQuadrants() {
        return this.insideQudrant;
    }
    
    public boolean getCollision() {
        return this.Collision;
    }
    
    public void setQuadrant(int[] quads) {
        this.insideQudrant = quads;
    }
    
    public boolean getPhysicChange() {
        return this.hasPhysicChange;
    }
    
    public void setPhysicChange(boolean b) {
        this.hasPhysicChange = b;
    }
    
    public void setCollision(boolean b) {
        this.Collision = b;
    }
    
    @Override
    public void setSize(Vector2D size) {
        super.setSize(size);
        this.boundary = new Rectangle2D.Double(this.position.getXCoord(), this.position.getYCoord()+0, size.getXCoord()+0, size.getYCoord()+0);
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        GameManipulator.unregisterPhysicObject(this);
    }
    
    @Override
    public void instance(){
        super.instance();
        this.boundary = new Rectangle2D.Double(position.getXCoord(), position.getYCoord()+0, size.getXCoord()+0, size.getYCoord()+0);
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        GameManipulator.registerPhysicObject(this);
    }
}
