package RenderObject;
public class Vector2D {
    private double x;
    private double y;
    
    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }
    
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    /** get X coordinate of this vector
    @return return integer of this vector in X axis
    **/
    public double getXCoord() {
        return this.x;
    }
    /** get X coordinate of this vector
    @return return integer of this vector in Y axis
    **/
    public double getYCoord() {
        return this.y;
    }
}
