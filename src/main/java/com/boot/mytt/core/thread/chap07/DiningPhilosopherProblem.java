package com.boot.mytt.core.thread.chap07;

import com.boot.mytt.core.thread.util.Debug;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author renwq
 * @date 2021/3/21 13:49
 */
public class DiningPhilosopherProblem {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int numOfPhilosopers;
        numOfPhilosopers = args.length > 0 ? Integer.valueOf(args[0]) : 2;
        // 创建筷子
        Chopstick[] chopsticks = new Chopstick[numOfPhilosopers];
        for (int i = 0; i < numOfPhilosopers; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        String philosopherImplClassName = System.getProperty("x.philo.impl");
        if (null == philosopherImplClassName) {
            philosopherImplClassName = "DeadlockingPhilosopher";
        }
        Debug.info("Using %s as implementation.", philosopherImplClassName);
        for (int i = 0; i < numOfPhilosopers; i++) {
            // 创建哲学家
            createPhilosopher(philosopherImplClassName, i, chopsticks);
        }
    }

    private static void createPhilosopher(String philosopherImplClassName, int id, Chopstick[] chopsticks) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        int numsOfPh = chopsticks.length;
        Class<Philosopher> philosopherClass = (Class<Philosopher>) Class.forName(DiningPhilosopherProblem.class.getPackage().getName() + "." + philosopherImplClassName);
        Constructor<Philosopher> philosopherConstructor = philosopherClass.getConstructor(int.class, Chopstick.class, Chopstick.class);
        Philosopher philosopher = philosopherConstructor.newInstance(id, chopsticks[id], chopsticks[(id + 1) % numsOfPh]);
        philosopher.start();
    }
}
