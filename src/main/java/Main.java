import task.HistoryTask;
import task.InstanceThreadPool;
import utils.SmallTool;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        ExecutorService historyThreadPool = Executors.newFixedThreadPool(5);
        CompletableFuture<String> c1 = CompletableFuture.supplyAsync(new HistoryTask("waiting", new ArrayList<String>() {{
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
        }}, "A"), historyThreadPool);
        Thread.sleep(300);
        CompletableFuture<String> c2 = CompletableFuture.supplyAsync(new HistoryTask("waiting", new ArrayList<String>() {{
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
            add("waiting");
        }}, "B"), historyThreadPool);
        c1.join();
        c2.join();
        SmallTool.printTimeAndThread("request id");
        historyThreadPool.shutdown();
        InstanceThreadPool.instanceThreadPoolA.shutdown();
        InstanceThreadPool.instanceThreadPoolB.shutdown();
    }
}
