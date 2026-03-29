/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI.CustomGameObject;

import DEVAPI.CustomGameObject.Player.PlayerObject;
import RenderObject.Addon.Scriptable;
import RenderObject.Addon.touchable;
import RenderObject.Creatable.Block;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.ScriptSheet;
import UniverseEngine.GameUniverse;

/**
 *
 * @author disk1
 */
public class PortalToLevel1 extends Block implements touchable, Scriptable{

    public PortalToLevel1(String name) {
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
        PlayerObject ply =GameUniverse.getObjectByName("ThePlayer", PlayerObject.class);
        ply.movePostion(new Vector2D(10000, -10000));
        ScriptSheet a =GameUniverse.getObjectByName("LevelTutorialLoaderScript", ScriptSheet.class);
        if (a instanceof SceneController s) {
            s.deleteSceneItem();
        }
    }

    @Override
    public void process(double deltaTime) {
        System.out.println("This" + this.getCollision());
    }
    
    
}
