/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI.CustomGameObject.Player;

import RenderObject.Creatable.Vector2D;
import UniverseEngine.GameUniverse;

/**
 *
 * @author disk1
 */
public class Character3 extends CharacterSet{

    private final static Vector2D hitboxSize = new Vector2D(200, 50);

    @Override
    public void normalAttack(PlayerObject player) {
        HitBox hitbox = GameUniverse.createInstance(new HitBox("playerHitBox"));
        hitbox.setSize(hitboxSize);
        if (player.getDirectionX() == 1) {
            hitbox.setPosition(new Vector2D(player.getBounds().getMaxX(), player.getBounds().getCenterY() - hitbox.getSize().getYCoord()/2));
        } else {
            hitbox.setPosition(new Vector2D(player.getBounds().getMinX() - hitbox.getSize().getXCoord(), player.getBounds().getCenterY() - hitbox.getSize().getYCoord()/2));
        }
        hitbox.setOwner(player);
        hitbox.setTimer(30);
        hitbox.setOriginalSpeed(player.getSpeed());
        hitbox.setDealDamge(player.getBaseDamage()*2);
    }

    @Override
    public void useSkill(PlayerObject player) {
    }

    @Override
    public int ShiftInteraction(PlayerObject player) {
        return 0;
    }

    @Override
    public String getSpecificCharacterAnimationName(int animName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
