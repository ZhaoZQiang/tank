package com.zzq.tank;

import java.awt.*;
import java.util.Random;

/**
 * 坦克
 */
public class Tank {
    private int x;
    private int y;
    private static int SPEED = 5;
    private Dir dir;
    private boolean moving;
    private TankFrame tf;
    private boolean isLive = true;
    private Group group = Group.BAD;
    private Random random = new Random();
    static final int TANK_WIDTH = ResourceMgr.tankU.getWidth(), TANK_HEIGHT = ResourceMgr.tankU.getHeight();

    public Tank(int x, int y, Dir dir, boolean moving, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.tf = tf;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
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

    public static int getSPEED() {
        return SPEED;
    }

    public static void setSPEED(int SPEED) {
        Tank.SPEED = SPEED;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void paint(Graphics g) {
        //        Color c = g.getColor();
        //        g.setColor(Color.YELLOW);
        //        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        //        g.setColor(c);
        if (!isLive&&group==Group.BAD)
            tf.tanks.remove(this);
        if(!isLive&&group==Group.GOOD)
            return;
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            default:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
        }
        this.move();

    }

    /**
     * 设置坦克移动方向
     */
    private void move() {
        if (!moving || !isLive)
            return;
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
        if (random.nextInt(10) > 8)
            this.fire();
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            isLive = false;
        }

    }

    /**
     * 射击
     */
    public void fire() {
        tf.bullets.add(
            new Bullet(x + (TANK_WIDTH - Bullet.BULLET_WIDTH) / 2, y + (TANK_HEIGHT - Bullet.BULLET_HEIGHT) / 2, dir,
                true, tf, this.group));
    }

    public void die() {
        this.isLive = false;
    }
}
