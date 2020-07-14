package com.zzq.tank;

import java.awt.*;

public class Explode {
    private int x, y;
    private boolean isLive = true;
    private TankFrame tf;
    public static int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth(), EXPLODE_HEIGHT =  ResourceMgr.explodes[0].getHeight();
    private int step=0;
    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
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

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if(step>=16) tf.explodeList.remove(this);
    }

}
