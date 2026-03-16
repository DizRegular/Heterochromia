package RenderObject;

import java.awt.geom.Rectangle2D;

abstract public class CollisionObject extends StyledObject {
    protected Rectangle2D boundary;
    protected int[] insideQudrant;
    protected boolean hasPhysicChange = true;

    public CollisionObject(String name, Vector2D pos, Vector2D size, String imageName, String tag) {
        super(name, pos, size, imageName, tag);
        this.boundary = new Rectangle2D.Double(position.getXCoord(), position.getYCoord(), size.getXCoord(), size.getYCoord());
    }
    public CollisionObject(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
        this.boundary = new Rectangle2D.Double(position.getXCoord(), position.getYCoord(), size.getXCoord(), size.getYCoord());
    }
    
    public Rectangle2D getBounds(){
        return this.boundary;
    }
    
    @Override
    synchronized public void  movePostion(Vector2D pos) {
        Vector2D startPos = this.position;
        this.setPosition(new Vector2D(startPos.getXCoord() + pos.getXCoord(), startPos.getYCoord() + pos.getYCoord()));
        this.boundary.setFrame(startPos.getXCoord() + pos.getXCoord(), startPos.getYCoord() + pos.getYCoord(), this.boundary.getWidth(), this.boundary.getHeight());
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
