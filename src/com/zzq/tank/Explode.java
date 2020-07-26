package com.zzq.tank;

import java.awt.*;

public class Explode extends GameObject{
    private boolean isLive = true;
    public static int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth(), EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width=EXPLODE_WIDTH;
        this.height=EXPLODE_HEIGHT;
        GameModel.getInstance().objects.add(this);
        //添加爆炸声效
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= 16) GameModel.getInstance().objects.remove(this);
    }

}
