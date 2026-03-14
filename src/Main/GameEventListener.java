package Main;

import RenderObject.*;
import java.util.concurrent.*;

public class GameEventListener {
    private static final ExecutorService eventPool = Executors.newCachedThreadPool();
    
    public static void handleTouch(GameObject eventSource, GameObject eventCause,String msg) {
        if (eventSource instanceof touchable t) {
            eventPool.execute(() -> {
                t.onTouched(eventCause);
            });
        }
    }
    
}
