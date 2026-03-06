package RenderObject;
abstract public class StaticObject extends CollisionObject {
    
    public StaticObject(String name, Vector2D pos, Vector2D size, String imageName, String tag) {
        super(name, pos, size, imageName, tag);
    }
    public StaticObject(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
    }
}
