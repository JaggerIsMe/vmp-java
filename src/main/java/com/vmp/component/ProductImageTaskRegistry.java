package com.vmp.component;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component("productImageTaskRegistry")
public class ProductImageTaskRegistry {

    /**
     * 只保证当前JVM实例内任务不重复。
     */
    private final Set<String> runningTasks = ConcurrentHashMap.newKeySet();

    /**
     * 尝试登记任务。
     *
     * @return true：当前调用获得执行权；false：已有相同任务正在执行
     */
    public boolean acquire(String taskKey) {
        return runningTasks.add(taskKey);
    }

    /**
     * 下载完成、失败或者任务提交失败后释放。
     */
    public void release(String taskKey) {
        if (taskKey != null) {
            runningTasks.remove(taskKey);
        }
    }

    /**
     * 主要用于测试和状态检查。
     */
    public boolean isRunning(String taskKey) {
        return runningTasks.contains(taskKey);
    }
}