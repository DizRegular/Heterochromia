package DEVAPI.CustomGameObject;

import RenderObject.GameObject;

public interface Damagable {
    public void takeDamage(double damagePoint, Damagable culprit);
}
