package Boss3ass;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.*;
import UniverseEngine.GameUniverse;
public class laserwaringX3 extends StaticObject implements touchable,Scriptable {
    private double timer = 0;
    private double delaydestroy=0;
    private int blinkCount = 0;
    private final double blinkInterval = 15;
    private final double destroy=100; 
    private funtung2 real;
    public laserwaringX3(String name , Vector2D pos) {
        super(name);
        
        this.setTexture("tub");
    }
    @Override
    public void process(double deltaTime) {
        timer++;
        delaydestroy++;
        if (timer >= blinkInterval) {
            timer = 0;
            blinkCount++;
 
            this.setVisibility(!this.getVisibility());

            if (blinkCount >= 4) {
                this.real = GameUniverse.createInstance(new funtung2("golemtub"));
                real.setPosition(this.getPosition());
                real.setSize(this.getSize());
                real.setTexture("AAA");
                real.setCollision(false);
                real.setPosition(this.getPosition());
                blinkCount=0;
                
            }}
            
        if(destroy<delaydestroy){ 
            this.destroyInstance();
            }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onTouched(GameObject obj) {
    }

    
}
