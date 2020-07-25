package com.zzq.cor;

import com.zzq.tank.Bullet;
import com.zzq.tank.GameObject;
import com.zzq.tank.Tank;
import com.zzq.tank.Wall;

/**
 * @description:
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 16:40
 */
public class WtCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Wall && o2 instanceof Tank) {
            Tank t = (Tank) o2;
            Wall w = (Wall) o1;
            //判断两个矩形相交
            if (t.rectangle.intersects(w.rectangle)) {
                //坦克回退
                t.back();
            }
        } else if (o1 instanceof Tank && o2 instanceof Wall) {
            collide(o2, o1);
        }
        return true;
    }
}
