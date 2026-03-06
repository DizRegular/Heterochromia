package RenderObject;
abstract public class KineticObject extends StyledObject implements Physic {
    private double acceleration = 0;
    private double velocity = 0;
    public KineticObject(String name, Vector2D pos, Vector2D size, String imageName, String tag) {
        super(name, pos, size, imageName, tag);
    }
    public KineticObject(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
    }
}
