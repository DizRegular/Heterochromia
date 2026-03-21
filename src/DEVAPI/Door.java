/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import RenderObject.GameObject;
import RenderObject.StaticObject;
import RenderObject.touchable;
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
            SceneManager.setScene(/*scene*/null);
    }
    }
    
}
