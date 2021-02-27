package com.boot.mytt.core.thread.chap03.case01;

import com.boot.mytt.core.util.Debug;
import com.boot.mytt.core.util.LocalDateUtils;

/**
 * @author renwq
 * @date 2021/2/27 下午5:15
 */
public class ServiceInvoker {

    private static final ServiceInvoker INSTANCE = new ServiceInvoker();
    private volatile LoadBalancer loadBalancer;

    private ServiceInvoker() {

    }

    public static ServiceInvoker getInstance() {
        return INSTANCE;
    }

    public void dispatchRequest(Request request) {
        Endpoint endpoint = getLoadBalancer().nextEndpoint();
        if (null == endpoint) {
            System.out.println();
            return;
        }
        dispatchToDownstream(request, endpoint);
    }

    private void dispatchToDownstream(Request request, Endpoint endpoint) {
        Debug.info("Dispatch request to " + endpoint + ":" + request);
    }

    public LoadBalancer getLoadBalancer() {
        return loadBalancer;
    }

    public void setLoadBalancer(LoadBalancer loadBalancer) {
        this.loadBalancer = loadBalancer;
    }
}
