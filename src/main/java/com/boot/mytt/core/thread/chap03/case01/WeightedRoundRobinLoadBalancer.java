package com.boot.mytt.core.thread.chap03.case01;

/**
 * @author renwq
 * @date 2021/2/27 下午4:48
 */
public class WeightedRoundRobinLoadBalancer extends AbstractLoadBalancer {
    public WeightedRoundRobinLoadBalancer(Candidate candidate) {
        super(candidate);
    }

    public static LoadBalancer getNewInstance(Candidate candidate) {
        WeightedRoundRobinLoadBalancer wb = new WeightedRoundRobinLoadBalancer(candidate);
        wb.init();
        return wb;
    }

    @Override
    public Endpoint nextEndpoint() {
        Endpoint selectedEndpoint = null;
        int subWeight = 0;
        int dynamicTotalWeight;
        final double rawRd = super.random.nextDouble();
        int rand;
        final Candidate candidate = super.candidate;
        dynamicTotalWeight = candidate.getEndpointCount();
        for (Endpoint endpoint: candidate) {
            if (!endpoint.isOnLine()) {
                dynamicTotalWeight -= endpoint.weight;
                continue;
            }
            rand = (int) (rawRd * dynamicTotalWeight);
            subWeight += endpoint.weight;
            if (subWeight >= rand) {
                selectedEndpoint = endpoint;
                break;
            }
        }
        return selectedEndpoint;
    }
}
