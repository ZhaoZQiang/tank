package com.zzq.tank;

import java.awt.*;
import java.util.Random;

/**
 * 坦克
 */
public class Tank {
    private int x;
    private int y;
    private static int SPEED =  PropertyMgr.getInt("tankSpeed");
    private Dir dir;
    private boolean moving;
    private TankFrame tf;
    private boolean isLive = true;
    private Group group = Group.BAD;
    private Rectangle rectangle=new Rectangle();

    private static Random random = new Random();
    static final int TANK_WIDTH = ResourceMgr.goodTankU.getWidth(), TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();

    public Tank(int x, int y, Dir dir, boolean moving, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.tf = tf;
        this.group = group;
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = TANK_WIDTH;
        rectangle.height = TANK_HEIGHT;
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    /***
     * @Description: 画出坦克
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
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            default:
                //
                break;
        }
        //随机设置敌军坦克移动方向
        if (Group.BAD == group && random.nextInt(100) > 95) {
            randomDir();

        }
        this.move();
    }

    private void randomDir() {
        dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * @Description: 根据坦克运动方向移动坦克
     * @Param: @param
     * @return: void
     * @Author: bjzhaoziqiang
     * @Date: 2020/7/15 2:22
     */
    private void move() {
        //坦克静止或者消亡不移动
        if (!moving || !isLive)
            return;
        //根据dir设置移动位置
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
        //update rectangle
        rectangle.x = x;
        rectangle.y = y;
        //敌军塔克随机发射子弹
        if (this.group == Group.BAD && random.nextInt(100) > 95)
            this.fire();
        //坦克运动边界检测
        boundsCheck();
    }

    /**
     * 射击
     */
    public void fire() {
        tf.bullets.add(
            new Bullet(x + (TANK_WIDTH - Bullet.BULLET_WIDTH) / 2, y + (TANK_HEIGHT - Bullet.BULLET_HEIGHT) / 2, dir,
                true, tf, this.group));
        if (Group.GOOD == this.group)
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
    }

    /**
     * @Description: 消亡
     * @Param: @param
     * @return: void
     * @Author: bjzhaoziqiang
     * @Date: 2020/7/15 2:22
     */
    public void die() {
        this.isLive = false;
    }

    /**
     * @param
     * @return void
     * @Description 边界检测
     * @author zhaoziqiang
     * @date 2020/7/15 17:39
     */
    public void boundsCheck() {
        if (x < 0)
            x = 0;
        if (x > TankFrame.GAME_WIDTH - Tank.TANK_WIDTH)
            x = TankFrame.GAME_WIDTH - Tank.TANK_WIDTH;
        if (y < 30)
            y = 30;
        if (y > TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT)
            y = TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT;
    }
}
