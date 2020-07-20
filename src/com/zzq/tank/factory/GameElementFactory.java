package com.zzq.tank.factory;

import com.zzq.tank.Dir;
import com.zzq.tank.Group;
import com.zzq.tank.TankFrame;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 11:33
 */
public abstract class GameElementFactory {
    public abstract BaseTank createTank(int x,int y,Dir dir,boolean moving,TankFrame tf,Group group);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, boolean isLive, TankFrame tf, Group group);
}
