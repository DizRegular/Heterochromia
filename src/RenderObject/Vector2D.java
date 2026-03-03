package RenderObject;
public class Vector2D {
    private int x;
    private int y;
    
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /** get X coordinate of this vector
    @return return integer of this vector in X axis
    **/
    public int getXCoord() {
        return this.x;
    }
    /** get X coordinate of this vector
    @return return integer of this vector in Y axis
    **/
    public int getYCoord() {
        return this.y;
    }
}
