package com.zzq.cor;

import com.zzq.tank.GameObject;
import com.zzq.tank.Tank;

/**
 * @description: 坦克坦克碰撞
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 14:28
 */
public class TtCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.rectangle.intersects(t2.rectangle)) {
                //回退到上一步
                t1.back();
                t2.back();
                return true;
            }
        }
        return true;
    }


}
