package com.zzq.cor;

import com.zzq.tank.*;

/**
 * @description:
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 16:40
 */
public class WbCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            //判断两个矩形相交
            if (b.rectangle.intersects(w.rectangle)) {
                //子弹消亡
                b.die();
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            collide(o2, o1);
        }
        return true;
    }
}
