package RenderObject;

import RenderObject.Creatable.Vector2D;

public class Tween {
    public static Vector2D Lerp2D(Vector2D start, Vector2D end, double time) {
        return start.multiply((1-time)).addVector2D(end.multiply(time));
    }
}
