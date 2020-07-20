package com.zzq.tank.factory;

import com.zzq.tank.Dir;
import com.zzq.tank.Group;
import com.zzq.tank.TankFrame;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 15:04
 */
public class RectElementFactory extends GameElementFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, boolean moving, TankFrame tf, Group group) {
        return new RectTank(x,y,dir,moving,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x,y,tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, boolean isLive, TankFrame tf, Group group) {
        return new RectBullet(x,y,dir,isLive,tf,group);
    }
}
