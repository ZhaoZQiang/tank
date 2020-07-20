package com.zzq.tank.factory;

import com.zzq.tank.Dir;
import com.zzq.tank.Group;
import com.zzq.tank.PropertyMgr;
import com.zzq.tank.TankFrame;

import java.awt.*;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 11:15
 */
public abstract class  BaseTank {
    private TankFrame tf;
    public abstract void paint(Graphics g);

    public abstract void setMoving(boolean b);

    public abstract void setDir(Dir left);

    public abstract void fire();

    public abstract Group getGroup();

    public abstract Rectangle getRectangle();

    public abstract int getX();

    public abstract int getY();

    public abstract void die();

    public abstract TankFrame getTf();

    public abstract Dir getDir();
}
