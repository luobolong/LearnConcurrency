package task;

import utils.ResourcePool;
import utils.SmallTool;

import java.util.function.Supplier;

public class InstanceTask implements Supplier<String> {
    private String instanceStatus;
    private String resourceType;
    public InstanceTask(String instanceStatus, String resourceType) {
        this.instanceStatus = instanceStatus;
        this.resourceType = resourceType;
    }
    @Override
    public String get() {
        try {
            instanceStatus = "running";
            SmallTool.printTimeAndThread(String.format("instance status: ") + instanceStatus);
            if (resourceType.equals("A")) {
                String resource = ResourcePool.resourcesA.take();
                SmallTool.printTimeAndThread("Take " + resource + ". Pool size: " + ResourcePool.resourcesA.size());
                SmallTool.sleepMillis(3000);
                ResourcePool.resourcesA.put(resource);
                SmallTool.printTimeAndThread("Put " + resource + ". Pool size: " + ResourcePool.resourcesA.size());
            } else if (resourceType.equals("B")) {
                String resource = ResourcePool.resourcesB.take();
                SmallTool.printTimeAndThread("Take " + resource + ". Pool size: " + ResourcePool.resourcesB.size());
                SmallTool.sleepMillis(3000);
                ResourcePool.resourcesB.put(resource);
                SmallTool.printTimeAndThread("Put " + resource + ". Pool size: " + ResourcePool.resourcesB.size());
            }

            instanceStatus = "success";
//            Random random = new Random();
//            int r = random.nextInt(100);
//            if (r < 30) {
//                instanceStatus = "failed";
//            }
//            SmallTool.printTimeAndThread(String.format("instance status: ") + instanceStatus);
            return instanceStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
    }
}
