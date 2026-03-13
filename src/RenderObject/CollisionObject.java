package RenderObject;

import java.awt.Rectangle;

abstract public class CollisionObject extends StyledObject {
    protected Rectangle boundary;
    protected int[] insideQudrant;
    protected boolean hasPhysicChange = false;

    public CollisionObject(String name, Vector2D pos, Vector2D size, String imageName, String tag) {
        super(name, pos, size, imageName, tag);
        this.boundary = new Rectangle((int)position.getXCoord(), (int)position.getYCoord(), (int)size.getXCoord(), (int)size.getYCoord());
    }
    public CollisionObject(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
        this.boundary = new Rectangle((int)position.getXCoord(), (int)position.getYCoord(), (int)size.getXCoord(), (int)size.getYCoord());
    }
    
    public Rectangle getBounds(){
        return this.boundary;
    }
    
    @Override
    public void movePostion(Vector2D pos) {
        this.setPosition(new Vector2D(this.position.getXCoord() + pos.getXCoord(), this.position.getYCoord() + pos.getYCoord()));
        this.boundary.setLocation((int)this.position.getXCoord() + (int)pos.getXCoord(), (int)this.position.getYCoord() + (int)pos.getYCoord());
        this.hasPhysicChange = true;
    }
    
    public int[] getQuadrants() {
        return this.insideQudrant;
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
}
