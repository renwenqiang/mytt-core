package com.boot.mytt.core.design.chap11;

/**
 * @author renwq
 * @since 2020/7/5 16:20
 */
public class GamePlayer implements IGamePlayer {

    private String username;

    public GamePlayer(IGamePlayer _gamePlayer, String _name) throws Exception {
        if (null == _gamePlayer) {
            throw new Exception("不能创建真实角色");
        } else {
            this.username = _name;
        }
    }
    @Override
    public void login(String username, String password) {
        // 处理业务
        System.out.println(this.username + "login");
    }

    @Override
    public void killBoss() {
        // 处理业务
        System.out.println(this.username + "killBoss");
    }

    @Override
    public void upgrade() {
    // 处理业务
        System.out.println(this.username + "upgrade");
    }
}
