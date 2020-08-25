package com.boot.mytt.core.design.chap11;

/**
 * 游戏接口类
 *
 * @author renwq
 * @since 2020/7/5 16:17
 */
public interface IGamePlayer {

    public void login(String username, String password);

    public void killBoss();

    public void upgrade();
}
