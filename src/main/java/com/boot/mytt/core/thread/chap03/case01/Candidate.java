package com.boot.mytt.core.thread.chap03.case01;


import java.util.Iterator;
import java.util.Set;

/**
 * @author renwq
 * @date 2021/2/27 下午3:21
 */
public final class Candidate implements Iterable<Endpoint> {

    private final Set<Endpoint> endpoints;
    private final int totalWeight;

    public Candidate(Set<Endpoint> endpoints) {
        int sum = 0;
        for (Endpoint endpoint: endpoints) {
            sum += endpoint.weight;
        }
        this.endpoints = endpoints;
        this.totalWeight = sum;
    }

    public int getEndpointCount() {
        return endpoints.size();
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "endpoints=" + endpoints +
                ", totalWeight=" + totalWeight +
                '}';
    }

    @Override
    public final Iterator<Endpoint> iterator() {
        return ReadOnlyIterator.with(endpoints.iterator());
    }
}
