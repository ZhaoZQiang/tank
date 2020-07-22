package com.zzq.tank;

/**
 * @description: 发射四颗不同方向的子弹
 * @author: bjzhaoziqiang
 * @time: 2020/7/18 11:45
 */
public class FourDirFireStrategy implements FireStrategy<Tank> {
    @Override
    public void fire(Tank a) {
        Dir[] dirs = Dir.values();
        for (int i = 0; i < dirs.length; i++) {
            new Bullet(a.getX() + (Tank.TANK_WIDTH - Bullet.BULLET_WIDTH) / 2, a.getY() + (Tank.TANK_HEIGHT - Bullet.BULLET_HEIGHT) / 2, dirs[i], true, a.getGm(), a.getGroup());
        }
        if (Group.GOOD == a.getGroup())
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }
}
