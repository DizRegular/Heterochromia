/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI;

/**
 *
 * @author tin_sel
 */
public abstract class scene {
    protected SceneManager sm;
    public abstract void update();
    public scene(SceneManager sm){
        this.sm=sm;
    }
    
}
