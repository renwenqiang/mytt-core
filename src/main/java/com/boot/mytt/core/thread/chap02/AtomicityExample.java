package com.boot.mytt.core.thread.chap02;

/**
 * @author renwq
 * @date 2021/2/22 下午10:34
 */
public class AtomicityExample {

    private HostInfo hostInfo;

    public void updateHostInfo(String ip, int port) {
        // 以下操作不是原子操作
        hostInfo.setIp(ip);
        hostInfo.setPort(port);
    }

    public void updateHostInfo2(String ip, int port) {
        // 以下操作是原子操作
        // 利用java语言对变量(long/double类型除外)写操作的原子性来保障原子性
        HostInfo newHostInfo = new HostInfo(ip, port);
        hostInfo = newHostInfo;// 原子操作
    }

    public void connectToHost() {
        String ip = hostInfo.getIp();
        int port = hostInfo.getPort();
        connectToHOst(ip, port);
    }

    private void connectToHOst(String ip, int port) {
        System.out.println(ip + " : " + port);
    }

    static class HostInfo{
        private String ip;
        private int port;

        public HostInfo() {
        }

        public HostInfo(String ip, int port) {
            this.ip = ip;
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }
    }
}
