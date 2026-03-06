package RenderObject;
abstract public class StaticObject extends GameObject {
    private boolean has_physinc = false;
    public StaticObject(String name, Vector2D pos, Vector2D size, String tag) {
        super(name, pos, size, tag);
    }
    
}
