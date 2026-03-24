package RenderObject;
public class AreaDetector extends StaticObject implements touchable {
    
    public AreaDetector(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
        if (obj instanceof KinematicObject k) {
            k.addVelocity(new Vector2D(0, -0.2));
        }
    }
}
