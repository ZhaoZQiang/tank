package com.zzq.tank.factory;

import com.zzq.tank.*;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 11:40
 */
public class DefaultElementFactory extends GameElementFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, boolean moving, TankFrame tf, Group group) {
       if(group==Group.GOOD){
           return new Tank(x,y,dir,false,tf,group);
       }
        return new Tank(x,y,dir,true,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, boolean isLive, TankFrame tf, Group group) {
        return new Bullet(x,y,dir,true,tf,group);
    }
}
