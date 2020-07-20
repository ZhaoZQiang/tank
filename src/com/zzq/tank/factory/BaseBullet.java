package com.zzq.tank.factory;




import java.awt.*;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 11:17
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);
}
