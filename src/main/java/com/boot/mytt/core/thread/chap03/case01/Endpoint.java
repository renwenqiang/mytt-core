package com.boot.mytt.core.thread.chap03.case01;

import java.util.Objects;

/**
 * @author renwq
 * @date 2021/2/27 下午12:10
 */
public class Endpoint {

    public final String host;
    public final int port;
    public final int weight;
    private volatile boolean onLine;

    public Endpoint(String host, int port, int weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }

    public boolean isOnLine() {
        return onLine;
    }

    public void setOnLine(boolean onLine) {
        this.onLine = onLine;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((host == null) ? 0 : host.hashCode());
        result = prime * result + port;
        result = prime * result + weight;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Endpoint other = (Endpoint) obj;
        if (host == null) {
            if (other.host != null)
                return false;
        } else if (!host.equals(other.host))
            return false;
        if (port != other.port)
            return false;
        if (weight != other.weight)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "EndPoint{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", weight=" + weight +
                ", onLine=" + onLine +
                '}';
    }
}
