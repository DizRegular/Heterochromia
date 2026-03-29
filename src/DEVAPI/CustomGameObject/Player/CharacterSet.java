package DEVAPI.CustomGameObject.Player;

import RenderObject.Creatable.Vector2D;


abstract public class CharacterSet {
    
   abstract public int ShiftInteraction(PlayerObject player);
   abstract public void normalAttack(PlayerObject player);
   abstract public void useSkill(PlayerObject player);
   abstract public String getSpecificCharacterAnimationName(int animName);
}
