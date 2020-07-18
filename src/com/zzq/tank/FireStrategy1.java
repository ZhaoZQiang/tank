package com.zzq.tank;

/**
 * @description: 默认发射一颗子弹(单例模式)
 * @author: bjzhaoziqiang
 * @time: 2020/7/18 11:45
 */
public class FireStrategy1 implements FireStrategy<Tank> {

    private FireStrategy1(){}

    private static class FireStrategy1Holder{
        private static final FireStrategy1 instacnce= new FireStrategy1();
    }

    public static FireStrategy1 getInstance(){
        return FireStrategy1Holder.instacnce;
    }

    @Override
    public void fire(Tank a) {
        new Bullet(a.getX() + (Tank.TANK_WIDTH - Bullet.BULLET_WIDTH) / 2, a.getY() + (Tank.TANK_HEIGHT - Bullet.BULLET_HEIGHT) / 2, a.getDir(),
                true, a.getTf(), a.getGroup());
        if (Group.GOOD == a.getGroup())
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
