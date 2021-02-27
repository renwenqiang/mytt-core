package com.boot.mytt.core.thread.chap03.case01;

/**
 * @author renwq
 * @date 2021/2/27 下午3:37
 */
public interface LoadBalancer {

    void updateCandidate(Candidate candidate);
    Endpoint nextEndpoint();
}
