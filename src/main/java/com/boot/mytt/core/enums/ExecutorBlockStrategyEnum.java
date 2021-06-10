package com.boot.mytt.core.enums;

/**
 * @author renwq
 * @date 2021/5/31 18:56
 */
public enum ExecutorBlockStrategyEnum {

    SERIAL_EXECUTION("Serial execution"),
    DISCARD_LATER("Discard Later"),
    COVER_EARLY("Cover Early");

    private String title;

    private ExecutorBlockStrategyEnum(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ExecutorBlockStrategyEnum match(String name, ExecutorBlockStrategyEnum defaultItem) {
        if (name != null) {
            for (ExecutorBlockStrategyEnum item : ExecutorBlockStrategyEnum.values()) {
                if (item.name().equals(name)) {
                    return item;
                }
            }
        }
        return defaultItem;
    }

    public static void main(String[] args) {
        for (ExecutorBlockStrategyEnum item:ExecutorBlockStrategyEnum.values()) {
            System.out.println(item.name() + " : " + item.title);
        }
    }
}
