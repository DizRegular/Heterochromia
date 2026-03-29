package RenderObject;
public class EntityDataModifier extends DataModifier {
    public double maxHp;
    public double hp;
    public double baseDamage;
    public boolean invicibility = false;
    public boolean Alive = true;
    
    public EntityDataModifier(double maxHp, double baseDamage) {
        this.maxHp = maxHp;
        this.baseDamage = baseDamage;
    }
    
    public boolean getInviciblity() {
        return this.invicibility;
    }
    
    public void setInvicibility(boolean b) {
        this.invicibility = b;
    }
    
    public double getHp() {
        return this.hp;
    }
    
    public void setHp(double hp) {
        this.hp = hp;
    }
    
    public boolean isAlive() {
        return this.Alive;
    }
    
    public void setAlive(boolean b) {
        this.Alive = b;
    }
    
    public void takeDamage(double damage) {
        if (invicibility == true) {return;}
        this.hp = this.getHp() - damage;
        if (this.getHp() <= 0) {
            this.setAlive(false);
        }
    }
    
}
