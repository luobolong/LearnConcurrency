package task;

import utils.SmallTool;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class HistoryTask implements Supplier<String> {
    private String historyStatus;
    private List<String> instanceStatusList;
    private String resourceType;
    public HistoryTask(String status, List<String> instanceStatusList, String resourceType) {
        this.historyStatus = status;
        this.instanceStatusList = instanceStatusList;
        this.resourceType = resourceType;
    }

    @Override
    public String get() {
        SmallTool.printTimeAndThread("history status: " + historyStatus);
//        while(ResourcePool.resources.isEmpty()) {
//            SmallTool.sleepMillis(100);
//        }

        historyStatus = "running";
        SmallTool.printTimeAndThread("history status: " + historyStatus);
        List<String> resultList = null;
        if (resourceType.equals("A")) {
            resultList = instanceStatusList.stream()
                    .map(instanceStatus -> CompletableFuture.supplyAsync(new InstanceTask(instanceStatus, resourceType), InstanceThreadPool.instanceThreadPoolA))
                    .collect(Collectors.toList())
                    .stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
        } else if (resourceType.equals("B")) {
            resultList = instanceStatusList.stream()
                    .map(instanceStatus -> CompletableFuture.supplyAsync(new InstanceTask(instanceStatus, resourceType), InstanceThreadPool.instanceThreadPoolB))
                    .collect(Collectors.toList())
                    .stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList());
        }

        historyStatus = "success";
        historyStatus = resultList.stream().reduce("success", (res, status) -> {
            if (res.equals("failed") || status.equals("failed")) {
                return "failed";
            }
            return status;
        });
        SmallTool.printTimeAndThread("history status: " + historyStatus);
        return historyStatus;
    }
}
