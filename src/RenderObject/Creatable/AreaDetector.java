package RenderObject.Creatable;

import RenderObject.Addon.touchable;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.StaticObject;
import UniverseEngine.InputManager;

public class AreaDetector extends StaticObject implements touchable {
    
    public AreaDetector(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
        if (obj instanceof KinematicObject k && InputManager.isKeyDown(' ')) {
             k.addVelocity(new Vector2D(0, -0.2));
        }
    }
}
