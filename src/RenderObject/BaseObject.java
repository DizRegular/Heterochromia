package RenderObject;

import UniverseEngine.GameUniverse;
import java.util.ArrayList;

abstract public class BaseObject {
    protected String name;
    protected String ID;
    protected boolean Enabled = true;
    protected boolean isInstanced = false;
    protected boolean isAlive = true;
    protected ArrayList<String> TagsContainer = new ArrayList<>();
    
    public BaseObject(String name) {
        this.name = name;
        this.ID = this.name + "#" + GameUniverse.generateNewID();
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

    public void setID(String ID) throws InvalidGameObjectPropertyException {
        throw new InvalidGameObjectPropertyException("ID property cannot be set.");
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
    
    public void createInstance() throws InvalidGameObjectPropertyException {
        this.isInstanced = true;
        GameUniverse.newInstance(this);
    }
    
    public void onCreate() {
        //Nothing
    }
}
