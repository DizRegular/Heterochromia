package Boss3ass;
import DEVAPI.CustomGameObject.Damagable;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;

public class ngudkun  extends StaticObject implements touchable,Scriptable {
    int destroytime=0;
    boolean hit=false;
    public ngudkun(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
       if(obj instanceof Damagable d){
           if(!hit){
            d.takeDamage(40,GameUniverse.getObjectByName("rock", Boss3.class) );
           hit=true;}
        }
    }

    @Override
    public void process(double deltaTime) {
        destroytime++;
        if(destroytime>=40){
            this.destroyInstance();
        }
    }
    
}
