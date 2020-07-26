package com.zzq.tank;

/**
 * @description: 默认发射一颗子弹(单例模式)
 * @author: bjzhaoziqiang
 * @time: 2020/7/18 11:45
 */
public class DefaultFireStrategy implements FireStrategy<Tank> {

    private DefaultFireStrategy(){}

    private static class FireStrategy1Holder{
        private static final DefaultFireStrategy instacnce= new DefaultFireStrategy();
    }

    public static DefaultFireStrategy getInstance(){
        return FireStrategy1Holder.instacnce;
    }

    @Override
    public void fire(Tank a) {
        new Bullet(a.getX() + (Tank.TANK_WIDTH - Bullet.BULLET_WIDTH) / 2, a.getY() + (Tank.TANK_HEIGHT - Bullet.BULLET_HEIGHT) / 2, a.getDir(),
                true, a.getGroup());
        if (Group.GOOD == a.getGroup())
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
