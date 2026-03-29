/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RenderObject.Creatable;

import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

/**
 *
 * @author asiau
 */
public class Ladder extends AreaDetector implements touchable,Scriptable{
    
    public Ladder(String name) {
        super(name);
        this.setCollision(false);
    }
    
    @Override
    public void onTouched(GameObject obj) {
       //non
    }
    
    @Override
    public void process(double deltaTime) {
    KinematicObject playerObj = GameUniverse.getObjectByName("ThePlayer", KinematicObject.class);
    if (playerObj == null) return;

    // --- 1. GET BOUNDS ---
    double pCenterX = playerObj.getPosition().getXCoord() + (playerObj.getSize().getXCoord() / 2);
    double pTop = playerObj.getPosition().getYCoord();
    double pBottom = pTop + playerObj.getSize().getYCoord();

    double lLeft = this.getPosition().getXCoord();
    double lRight = lLeft + this.getSize().getXCoord();
    double lTop = this.getPosition().getYCoord();
    double lBottom = lTop + this.getSize().getYCoord();

    // check if player is inside the ladder || not
    boolean isInside = (pCenterX > lLeft && pCenterX < lRight) 
                    && (pBottom > lTop && pTop < lBottom);

    if (isInside) {
        boolean holdingUp = InputManager.isKeyDown(KeyEvent.VK_W) || InputManager.isKeyDown(KeyEvent.VK_UP);
        boolean holdingDown = InputManager.isKeyDown(KeyEvent.VK_S) || InputManager.isKeyDown(KeyEvent.VK_DOWN);
        System.out.println("A");
        if (holdingUp) {
            //make player climb up
            playerObj.setVelocity(new Vector2D(playerObj.getVelocity().getXCoord(), -3));
        } else if (holdingDown) {
            //make player climb down
            playerObj.setVelocity(new Vector2D(playerObj.getVelocity().getXCoord(), 3));
        } else if (!playerObj.getTouchedFloor()) {
            // make it so you doesn't fall instantly when still climbing the ladder
            playerObj.setVelocity(new Vector2D(playerObj.getVelocity().getXCoord(), -0.15));
        }
    }
}
}
