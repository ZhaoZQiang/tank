package com.zzq.tank.factory;


import com.zzq.tank.Audio;
import com.zzq.tank.TankFrame;

import java.awt.*;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 11:20
 */
public class RectExplode extends  BaseExplode {
    private int x, y;
    private boolean isLive = true;
    private TankFrame tf;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        //添加爆炸声效
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.green);
        g.fillRect(this.getX(),this.getY(),this.step++*5,this.step++*5);
        if (this.step >= 10) getTf().explodeList.remove(this);
        g.setColor(color);
    }

}
