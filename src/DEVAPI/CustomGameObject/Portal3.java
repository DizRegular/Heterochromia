/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI.CustomGameObject;

import DEVAPI.Level3LoaderScript;
import RenderObject.Addon.Scriptable;
import RenderObject.Creatable.Block;
import RenderObject.GameObject;
import RenderObject.KinematicObject;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Block;
import RenderObject.ScriptSheet;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;
import java.awt.event.KeyEvent;

public class Portal3 extends Block implements touchable{
    public Portal3(String name) {
        super(name);
        this.setCollision(false);
    }
    
    @Override
    public void onTouched(GameObject obj) {
           // 1. Find the player (Ensure your PlayerLoaderScript names the object "ThePlayer")
    KinematicObject playerObj = GameUniverse.getObjectByName("ThePlayer", KinematicObject.class);
    
    if (playerObj == null) return;

    double playerBottom = playerObj.getPosition().getYCoord() + playerObj.getSize().getYCoord();
    double platformTop = this.getPosition().getYCoord();
    boolean holdingDown = InputManager.isKeyDown('e') || InputManager.isKeyDown(KeyEvent.VK_E);

    // 2. Check if player is overlapping the portal while holding 'S'
    if (playerBottom >= platformTop && holdingDown) {
        
        // Use ScriptSheet.class instead of Object.class to fix the IDE error
        ScriptSheet script = GameUniverse.getObjectByName("Level2LoaderScript", ScriptSheet.class);
        
        // 3. Trigger the cleanup and load the next level
        if (script instanceof DEVAPI.CustomGameObject.SceneController s) {
            s.deleteSceneItem(); // Deletes everything in the Tutorial
            
            // Spawn the Boss Level
            GameUniverse.createInstance(new Level3LoaderScript("Level3LoaderScript"));
            
            // Remove this portal so it doesn't trigger twice
            this.destroyInstance(); 
        }
    }
    }

}