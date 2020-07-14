package com.zzq.tank;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {
    private static final int SPEED = 10;
    private static final int WIDTH = 10, HEIGHT = 10;
    private int x, y;
    private Dir dir;
    private boolean isLive = true;
    private TankFrame tf;
    public static int BULLET_WIDTH = ResourceMgr.bulletD.getWidth(), BULLET_HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(int x, int y, Dir dir, boolean isLive, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isLive = isLive;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
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

    /**
     * 画出子弹
     *
     * @param g
     */
    public void paint(Graphics g) {
        //        Color c = g.getColor();
        //        g.setColor(Color.RED);
        //        g.fillOval(x, y, WIDTH, HEIGHT);
        //        g.setColor(c);
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            default:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
        }
        this.move();
    }

    /**
     * 设置子弹移动
     */
    private void move() {
        switch (dir) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            default:
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            isLive = false;
        }

    }
}
