package RenderObject;

import UniverseEngine.GameUniverse;
import java.util.ArrayList;

abstract public class BaseObject {
    protected String name;
    protected String ID;
    protected boolean Enabled = true;
    protected boolean isInstanced = false;
    protected boolean isAlive = true;
    protected boolean isConstrained = false;
    protected BaseObject parent = null;
    protected ArrayList<String> TagsContainer = new ArrayList<>();
    protected ArrayList<BaseObject> constraints = new ArrayList<>();

    
    public BaseObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public void setEnabled(boolean Enabled) {
        this.Enabled = Enabled;
    }

    public ArrayList<String> getTags() {
        return TagsContainer;
    }

    public void addTags(String s) {
        this.TagsContainer.add(s);
    }

    public void removeTags(String s) {
        this.TagsContainer.remove(s);
    }
    
    public boolean hasInstance() {
        return this.isInstanced;
    }
    
    public boolean isObjAlive() {
        return this.isAlive;
    }
    
    public void destroyInstance() {
        this.isAlive = false;
    }
    
    public void onDestroy() {
        //Nothing
    }
    
    public void instance() {
        this.isInstanced = true;
    }
    
    public void onCreate() {
        //Nothing
    }
    
    public ArrayList<BaseObject> getConstraints() {
        return this.constraints;
    }
    
    public boolean hasConstrained() {
        return this.isConstrained;
    }
    
    public void setIsConstrain(boolean b) {
        this.isConstrained = b;
    }
    
    public void addConstraint(BaseObject obj) throws InvalidGameObjectPropertyException {
        if (obj == null) {
            throw new InvalidGameObjectPropertyException(this.ID + " : this object tries to add constraint that is \"null\"");
        }
        this.constraints.add(obj);
        obj.setIsConstrain(true);
        obj.parent = this;
    }
    
    public void removeConstraint(BaseObject obj) {
        this.constraints.remove(obj);
        obj.setIsConstrain(false);
        obj.parent = null;
    }
    
}
