package RenderObject.Addon;

import RenderObject.GameObject;

public interface touchable {
    /** trigger when object that implements touchable touch something
     * @param obj who touch with this obj
     */
    public void onTouched(GameObject obj);
}
