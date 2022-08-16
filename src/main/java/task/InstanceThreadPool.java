package task;

import utils.ResourcePool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class InstanceThreadPool {
    public static ExecutorService instanceThreadPoolA = new ThreadPoolExecutor(ResourcePool.resourcesA.size(), ResourcePool.resourcesA.size(), 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(1000));
    public static ExecutorService instanceThreadPoolB = new ThreadPoolExecutor(ResourcePool.resourcesA.size(), ResourcePool.resourcesA.size(), 0, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(1000));
}
