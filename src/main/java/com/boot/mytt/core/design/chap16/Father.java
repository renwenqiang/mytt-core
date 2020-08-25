package com.boot.mytt.core.design.chap16;

/**
 * @author renwq
 * @since 2020/7/5 19:59
 */
public class Father extends Handler {
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        // 处理业务
    }
}
