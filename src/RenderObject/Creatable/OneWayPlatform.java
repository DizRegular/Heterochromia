package DEVAPI.CustomGameObject;

import RenderObject.Addon.Scriptable;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.Addon.touchable;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

public class OneWayPlatform extends Block implements touchable,Scriptable{
    public OneWayPlatform(String name) {
        super(name);
        this.setCollision(true);
    }
    
    @Override
    public void onTouched(GameObject obj) {
       //non
    }

    @Override
    public void process(double deltaTime) {
        KinematicObject playerObj = GameUniverse.getObjectByName("player", KinematicObject.class);
            
            double playerBottom = playerObj.getPosition().getYCoord() + playerObj.getSize().getYCoord();
            double platformTop = this.getPosition().getYCoord();
            boolean holdingDown = InputManager.isKeyDown('s') || InputManager.isKeyDown(KeyEvent.VK_DOWN);
            
        // Logic: If player is above the platform top AND not holding 'S'
            if (playerBottom <= platformTop + 1 && !holdingDown) {
                this.setCollision(true); // Make it a floor
            } else {
                this.setCollision(false); // Let them pass through
        }
    }
}