package com.boot.mytt.core.log;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author renwq
 * @since 2020/6/10 23:38
 */
public class JdkLogDemo {
    public static Logger logger = Logger.getLogger(JdkLogDemo.class.toString());
    static {
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    public static void main(String[] args) {
        logger.setLevel(Level.INFO);
        logger.fine("jdk fine log");
    }
}
