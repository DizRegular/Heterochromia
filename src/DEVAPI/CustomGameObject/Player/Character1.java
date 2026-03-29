package DEVAPI.CustomGameObject.Player;

import DEVAPI.CustomGameObject.Damagable;
import RenderObject.Creatable.Vector2D;
import RenderObject.GameObject;
import RenderObject.StaticObject;
import UniverseEngine.GameUniverse;

public class Character1 extends  CharacterSet {
    private final static Vector2D hitboxSize = new Vector2D(50, 50);

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
        hitbox.setDealDamge(player.getBaseDamage());
    }

    @Override
    public void useSkill(PlayerObject player) {
            player.takeDamage(-10, player);
        
    }

    @Override
    public int ShiftInteraction(PlayerObject player) {
        player.setParry(true);
        return 0;
    }

    @Override
    public String getSpecificCharacterAnimationName(int animName) {
       if (animName == 0) return "playerSwordIdleAnim";
       if (animName == 1) return  "playerSwordRunAnim";
       if (animName == 2) return "playerCrouchWalkAnim";
       if (animName == 3) return  "playerJumpAnim";
       if (animName == 4) return "playerSwordStabAnim";
       if (animName == 5) return "playerShiftAnim";
       if (animName == 6) return "playerUseSkill1Anim";
       return "";
    }
    
    
    
    
}
