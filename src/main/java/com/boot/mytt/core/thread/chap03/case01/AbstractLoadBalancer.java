package com.boot.mytt.core.thread.chap03.case01;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author renwq
 * @date 2021/2/27 下午3:39
 */
public abstract class AbstractLoadBalancer implements LoadBalancer {

    private static final Logger LOGGER = Logger.getAnonymousLogger();
    protected volatile Candidate candidate;
    protected final Random random;
    private Thread heartbeatThread;

    public AbstractLoadBalancer(Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invalid candidate: " + candidate);
        }
        this.candidate = candidate;
        random = new Random();
    }

    public synchronized void init() {
        if (heartbeatThread == null) {
            heartbeatThread = new Thread(new HeartbeatThread(), "LB_Heartbeat");
            heartbeatThread.start();
        }
    }

    @Override
    public void updateCandidate(Candidate candidate) {
        if (null == candidate || 0 == candidate.getEndpointCount()) {
            throw new IllegalArgumentException("Invalid candidate: " + candidate);
        }
        this.candidate = candidate;
    }

    @Override
    public abstract Endpoint nextEndpoint();

    protected void monitorEndpoints() {
        final Candidate curCandidate = candidate;
        boolean isTheEndpointOnLine;
        for (Endpoint endpoint: curCandidate) {
            isTheEndpointOnLine = endpoint.isOnLine();
            if (doDetect(endpoint) != isTheEndpointOnLine) {
                endpoint.setOnLine(!isTheEndpointOnLine);
                if (isTheEndpointOnLine) {
                    LOGGER.log(Level.SEVERE, endpoint + " offline");
                } else {
                    LOGGER.log(Level.SEVERE, endpoint + " online");
                }
            }
        }
    }

    private boolean doDetect(Endpoint endpoint) {
        boolean onLine = true;
        int rand = random.nextInt(1000);
        if (rand > 500) {
            onLine = false;
        }
        return onLine;
    }

    private class HeartbeatThread implements Runnable{

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                monitorEndpoints();
                Thread.sleep(2000);
            }
        }
    }
}
