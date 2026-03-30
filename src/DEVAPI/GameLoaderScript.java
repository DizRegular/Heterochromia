/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

import DEVAPI.CustomGameObject.Player.PlayerLoaderScript;
import DEVAPI.CustomGameObject.Player.PlayerObject;
import RenderObject.Creatable.*;
import RenderObject.ScriptSheet;
import RenderObject.InvalidGameObjectPropertyException;
import UI.HpDisplay;
import UniverseEngine.GameUniverse;

/**
 *
 * @author disk1
 */
public class GameLoaderScript extends ScriptSheet{
    public PlayerObject player;
    public ViewPort window;
    public Camera playerCamera;
    public String portalPathfile = "res/PixelPortal/OrangePortal.png";
    public boolean hasCreateHud = false;
    public GameLoaderScript() {
        super(null);
    }

    
    public GameLoaderScript(String name) {
        super(name);
    }

    @Override
    public void onCreate() {

        window = GameUniverse.createInstance(new ViewPort("DisplayPort"));
        playerCamera = GameUniverse.createInstance(new Camera("PlayerCamera"));
        playerCamera.setSize(new Vector2D(1200, 800));
        playerCamera.setPosition(new Vector2D(0, 0));
        window.setEnabled(true);
        
        Animator portalAnim = GameUniverse.createInstance(new Animator("portalAnim"));
        portalAnim.createAnimationSheet(new Vector2D(320, 320), portalPathfile);
        portalAnim.setSpeed(3);
        try {
            window.setCamera(playerCamera);
        } catch (InvalidGameObjectPropertyException e) {
            e.printStackTrace();
        }

        try {
            window.setCamera(playerCamera);
        } catch (InvalidGameObjectPropertyException e) {
            e.printStackTrace();
        }
        window.setEnabled(true);
        
        GameUniverse.createInstance(new LevelTutorialLoaderScript("LevelTutorialLoaderScript"));
        GameUniverse.createInstance(new PlayerLoaderScript("PlayerLoaderScript"));

    }
    
    @Override
    public void process(double deltaTime) {
        if (GameUniverse.getObjectByName("ThePlayer", PlayerObject.class) != null && !hasCreateHud) {
            hasCreateHud = true;
            GameUniverse.createInstance(new HpDisplay("PlayerHUD", 5 , 5, 5, 5));
        }
    }
    
}
