package DEVAPI.CustomGameObject;

public interface Damagable {
    public void takeDamage(double damagePoint, Damagable culprit);
    public void thorn(double damage);
}
