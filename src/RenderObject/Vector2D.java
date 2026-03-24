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

    
    public void setX(double x) {
        this.x = x;
    }

    /** calculate the result of two 2d vector combine
     * @param v
     * @return 
     */
    public void setY(double y) {    
        this.y = y;
    }

    public Vector2D addVector2D(Vector2D v) {
        double newX = this.x + v.getXCoord();
        double newY = this.y + v.getYCoord();
        return new Vector2D(newX, newY);
    }
    
    public Vector2D multiply(double n) {
        double newX = this.x * n;
        double newY = this.y * n;
        return new Vector2D(newX, newY);
    }
    
    /** print (x, y) for easier reading
     * @return 
     */
    @Override
    public String toString() {
        return "("+ this.x + ", " +this.y + ")";
    }
}
