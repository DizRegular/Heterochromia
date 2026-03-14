package RenderObject;
public class AreaDetector extends StaticObject implements touchable {
    
    public AreaDetector(String name, Vector2D pos, Vector2D size, String imageName, String tag) {
        super(name, pos, size, imageName, tag);
    }
    
    public AreaDetector(String name, Vector2D pos, Vector2D size, String imageName) {
        super(name, pos, size, imageName);
    }

    @Override
    public void onTouched(GameObject obj) {
        if (obj instanceof KinematicObject k) {
            k.addVelocity(new Vector2D(0, -2));
        }
    }
}
