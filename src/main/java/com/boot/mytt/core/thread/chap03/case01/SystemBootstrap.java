package com.boot.mytt.core.thread.chap03.case01;

import java.util.HashSet;
import java.util.Set;

/**
 * @author renwq
 * @date 2021/2/27 下午5:24
 */
public class SystemBootstrap {

    public static void main(String[] args) {
        SystemBootstrap sys = new SystemBootstrap();
        ServiceInvoker serviceInvoker = ServiceInvoker.getInstance();
        LoadBalancer loadBalancer = sys.createLoadBalancer();
        serviceInvoker.setLoadBalancer(loadBalancer);
    }

    private LoadBalancer createLoadBalancer() {
        LoadBalancer loadBalancer;
        Candidate candidate = new Candidate(loadEndpoints());
        loadBalancer = WeightedRoundRobinLoadBalancer.getNewInstance(candidate);
        return loadBalancer;
    }

    private Set<Endpoint> loadEndpoints() {
        Set<Endpoint> endpoints = new HashSet<Endpoint>();

        // 模拟从数据库加载以下信息
        endpoints.add(new Endpoint("192.168.101.100", 8080, 3));
        endpoints.add(new Endpoint("192.168.101.101", 8080, 2));
        endpoints.add(new Endpoint("192.168.101.102", 8080, 5));
        endpoints.add(new Endpoint("192.168.101.103", 8080, 7));
        return endpoints;
    }
}
