package com.zzq.cor;

import com.zzq.tank.Bullet;
import com.zzq.tank.Explode;
import com.zzq.tank.GameObject;
import com.zzq.tank.Tank;

/**
 * @description: 子弹坦克碰撞
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 14:28
 */
public class BtCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet b = (Bullet) o1;
            Tank t = (Tank) o2;
            if (b.getGroup() == t.getGroup()) return true;
            //判断两个矩形相交
            if (b.rectangle.intersects(t.rectangle)) {
                //增加坦克爆炸效果
                t.getGm().add(new Explode(t.getX(), t.getY(), t.getGm()));
                //子弹消亡
                b.die();
                //坦克消亡
                t.die();
                return false;
            }
        } else if (o2 instanceof Bullet && o1 instanceof Tank) {
            collide(o2, o1);
        }
        return true;
    }
}
