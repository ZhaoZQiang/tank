package com.zzq.tank.factory;

import com.zzq.tank.*;

import java.awt.*;
import java.util.Random;

/**
 * @author zhaoziqiang
 * @Description: 方形坦克
 * @date 2020/7/20 11:21
 */
public class RectTank extends  BaseTank {
    private int x;
    private int y;
    private static int SPEED = PropertyMgr.getInt("tankSpeed");
    private Dir dir;
    private boolean moving;
    private TankFrame tf;
    private boolean isLive = true;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();
    private FireStrategy fireStrategy;

    private static Random random = new Random();
    static final int TANK_WIDTH = ResourceMgr.goodTankU.getWidth(), TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();

    public RectTank(int x, int y, Dir dir, boolean moving, TankFrame tf, Group group) {
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
        //敌我不同开火策略
        //        if(this.group==Group.GOOD) fireStrategy=new FireStrategy2();
        //反射获取主站策略对象
        if (this.group == Group.GOOD) {
            try {
                fireStrategy = (FireStrategy) Class.forName(PropertyMgr.getStr("goodFs")).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else fireStrategy = DefaultFireStrategy.getInstance();
    }

    @Override
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
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
        RectTank.SPEED = SPEED;
    }

    public Dir getDir() {
        return dir;
    }

    @Override
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    @Override
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    /***
     * @Description: 画出坦克
     * @Param: @param g
     * @return: void
     * @Author: bjzhaoziqiang
     * @Date: 2020/7/15 2:17
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(this.group==Group.GOOD?Color.YELLOW:Color.blue);
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        g.setColor(c);
        if (!isLive && group == Group.BAD)
            tf.tanks.remove(this);
        if (!isLive && group == Group.GOOD)
            //主战坦克被击中销毁
            tf.myTank = null;

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
    @Override
    public void fire() {
        //        tf.bullets.add(
        //            new Bullet(x + (TANK_WIDTH - Bullet.BULLET_WIDTH) / 2, y + (TANK_HEIGHT - Bullet.BULLET_HEIGHT) / 2, dir,
        //                true, tf, this.group));
        //        if (Group.GOOD == this.group)
        //            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        fireStrategy.fire(this);
    }

    /**
     * @Description: 消亡
     * @Param: @param
     * @return: void
     * @Author: bjzhaoziqiang
     * @Date: 2020/7/15 2:22
     */
    @Override
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
        if (x > TankFrame.GAME_WIDTH - RectTank.TANK_WIDTH)
            x = TankFrame.GAME_WIDTH - RectTank.TANK_WIDTH;
        if (y < 30)
            y = 30;
        if (y > TankFrame.GAME_HEIGHT - RectTank.TANK_HEIGHT)
            y = TankFrame.GAME_HEIGHT - RectTank.TANK_HEIGHT;
    }

}
