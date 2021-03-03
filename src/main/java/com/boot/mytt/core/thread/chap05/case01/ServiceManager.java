package com.boot.mytt.core.thread.chap05.case01;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author renwq
 * @date 2021/3/3 下午10:28
 */
public class ServiceManager {
    static volatile CountDownLatch latch;
    static Set<Service> services;

    public static void startServices() {
        services = getServices();
        for (Service service: services) {
            service.start();
        }
    }

    public static boolean checkServiceStatus() {
        boolean allIsOK = true;
        // 等待服务启动结束
        try {
            latch.await();
        } catch (InterruptedException e) {
            return false;
        }

        for (Service service : services) {
            if (!service.isStarted()) {
                allIsOK = false;
                break;
            }
        }
        return allIsOK;
    }

    static Set<Service> getServices() {
        latch = new CountDownLatch(3);
        Set<Service> services = new HashSet<>();
        services.add(new SampleServiceA(latch));
        services.add(new SampleServiceB(latch));
        services.add(new SampleServiceC(latch));
        return services;
    }
}
