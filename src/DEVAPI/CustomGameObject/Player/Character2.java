/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DEVAPI.CustomGameObject.Player;

import RenderObject.Creatable.Vector2D;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;

/**
 *
 * @author disk1
 */
public class Character2 extends CharacterSet{

    private final static Vector2D hitboxSize = new Vector2D(100, 50);

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
        hitbox.setDealDamge(player.getBaseDamage()*1.2);
    }

    @Override
    public void useSkill(PlayerObject player) {
        player.setBaseDamage(player.getBaseDamage() * 1.5);
    }

    @Override
    public int ShiftInteraction(PlayerObject player) {
        return 0;
    }

    @Override
    public String getSpecificCharacterAnimationName(int animName) {
       if (animName == 0) return "playerSwordIdle2Anim";
       if (animName == 1) return  "playerSwordRun2Anim";
       if (animName == 2) return "playerCrouchWalk2Anim";
       if (animName == 3) return  "playerJump2Anim";
       if (animName == 4) return "playerSwordStab2Anim";
       if (animName == 5) return "playerDash2Anim";
       if (animName == 6) return "playerUseSkill2Anim";
       return "";
    }
    
}
