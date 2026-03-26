package DEVAPI.CustomGameObject;

import RenderObject.GameObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;

public class ScaredPikachu extends StaticObject implements touchable{
    public ScaredPikachu(String name) {
        super(name);
    }
    
    @Override
    public void onTouched(GameObject obj) {
        this.movePostion(new Vector2D(-1, 0));
    }
    
}
