/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI.CustomGameObject;

import RenderObject.Creatable.AreaDetector;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import RenderObject.Creatable.Vector2D;
import RenderObject.Addon.touchable;
import UniverseEngine.GameUniverse;
import UniverseEngine.InputManager;

/**
 *
 * @author tin_sel
 */
public class Door extends StaticObject implements touchable{
    public Door(/*gamesecen*/String name){
        super(name);
    }

    @Override
    public void onTouched(GameObject obj) {
            if(InputManager.isKeyDown('e')){
                GameUniverse.setBackground("res/GameAssets/Background/bgplaceholder2.png");
                AreaDetector area = GameUniverse.createInstance(new AreaDetector("ExtraPlatform"));
                area.setSize(new Vector2D(50, 500));
                area.setPosition(new Vector2D(200, -200));
    }
    }
    
}
