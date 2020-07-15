package com.zzq.tank;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {
    private static final int SPEED = 10;
    private int x, y;
    private Dir dir;
    private boolean isLive = true;
    private TankFrame tf;
    private Group group = Group.BAD;
    public static int BULLET_WIDTH = ResourceMgr.bulletD.getWidth(), BULLET_HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(int x, int y, Dir dir, boolean isLive, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isLive = isLive;
        this.tf = tf;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        if (!isLive) tf.bullets.remove(this);
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

    /***
     * 碰撞检测
     * @param tank
     */
    public void collideWith(Tank tank) {
        //
        if (tank.getGroup() == this.getGroup()) return;
        //子弹矩形
        Rectangle bRectangle = new Rectangle(x, y, Bullet.BULLET_WIDTH, Bullet.BULLET_HEIGHT);
        //坦克矩形
        Rectangle tRectangle = new Rectangle(tank.getX(), tank.getY(), Tank.TANK_WIDTH, Tank.TANK_HEIGHT);
        //判断两个矩形相交
        if (bRectangle.intersects(tRectangle)) {
            //增加坦克爆炸效果
            tf.explodeList.add(new Explode(tank.getX(), tank.getY(), tf));
            //子弹消亡
            this.die();
            //坦克消亡
            tank.die();
        }
    }

    /**
     * 子弹消亡
     */
    private void die() {
        this.isLive = false;
    }
}
