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
    private static Random random = new Random();
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

    /*** 
    * @Description:  画出坦克
    * @Param: @param g 
    * @return: void 
    * @Author: bjzhaoziqiang 
    * @Date: 2020/7/15 2:17
    */    
    public void paint(Graphics g) {
        //        Color c = g.getColor();
        //        g.setColor(Color.YELLOW);
        //        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        //        g.setColor(c);
        if (!isLive && group == Group.BAD)
            tf.tanks.remove(this);
        if (!isLive && group == Group.GOOD)
            //主战坦克被击中销毁
            tf.myTank = null;
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
        //随机设置敌军坦克移动方向
        if (Group.BAD == group) {
            int r = random.nextInt(1000);
            if (0 < r && r <= 500) dir = Dir.DOWN;
            if (500 < r && r <= 600) dir = Dir.UP;
            if (600 < r && r <= 800) dir = Dir.RIGHT;
            if (800 < r && r <= 1000) dir = Dir.LEFT;
        }
        this.move();

    }

   /**
    *
    * @Description:  根据坦克运动方向移动坦克
    * @Param: @param
    * @return: void
    * @Author: bjzhaoziqiang
    * @Date: 2020/7/15 2:22
    */
    private void move() {
        //坦克静止或者消亡不移动
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
        //敌军塔克随机发射子弹
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

    /**
     *
     * @Description: 消亡
     * @Param: @param
     * @return: void
     * @Author: bjzhaoziqiang
     * @Date: 2020/7/15 2:22
     */
    public void die() {
        this.isLive = false;
    }
}
