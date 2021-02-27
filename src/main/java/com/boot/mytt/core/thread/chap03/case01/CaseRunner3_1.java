package com.boot.mytt.core.thread.chap03.case01;

import com.boot.mytt.core.util.Tools;

/**
 * @author renwq
 * @date 2021/2/27 下午5:31
 */
public class CaseRunner3_1 {

    public static void main(String[] args) {
        SystemBootstrap.main(new String[]{});
        for (int k=0; k<4; k++) {
            new RequestSender().start();
        }
    }

    static class RequestSender extends Thread{

        private static long id = -1;

        public RequestSender() {

        }

        static synchronized long nextId() {
            return ++id;
        }

        @Override
        public void run() {
            ServiceInvoker serviceInvoker = ServiceInvoker.getInstance();
            for (int k=0; k<100; k++) {
                serviceInvoker.dispatchRequest(new Request(nextId(), 1));
                Tools.randomPause(50);
            }
        }
    }
}
