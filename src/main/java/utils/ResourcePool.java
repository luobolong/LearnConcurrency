package utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ResourcePool {
    public static BlockingQueue<String> resourcesA = new ArrayBlockingQueue<String>(3, true) {{
        offer("resource-A-1");
        offer("resource-A-2");
        offer("resource-A-3");
    }};
    public static BlockingQueue<String> resourcesB = new ArrayBlockingQueue<String>(3, true) {{
        offer("resource-B-1");
        offer("resource-B-2");
        offer("resource-B-3");
    }};
}
