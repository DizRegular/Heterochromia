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
    public int getXCoord() {
        return this.x;
    }
    public int getYCoord() {
        return this.y;
    }
}
