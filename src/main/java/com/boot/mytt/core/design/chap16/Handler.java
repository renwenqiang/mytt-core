package com.boot.mytt.core.design.chap16;

/**
 * @author renwq
 * @since 2020/7/5 19:51
 */
public abstract class Handler {

    public static final int FATHER_LEVEL_REQUEST = 1;
    public static final int HUSBAND_LEVEL_REQUEST = 2;
    public static final int SON_LEVEL_REQUEST = 3;

    private int level = 0;

    private Handler nextHandler;

    public Handler(int _level) {
        this.level = _level;
    }

    public final void handleMessage(IWomen women) {
        if (women.getType() == this.level) {

        }
    }

    public void setNext(Handler _handler) {
        this.nextHandler = _handler;
    }

    protected abstract void response(IWomen women);
}
