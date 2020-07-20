package com.zzq.tank.factory;

import com.zzq.tank.*;

import java.awt.*;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/20 11:20
 */
public class RectBullet extends BaseBullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    private int x, y;
    private Dir dir;
    private boolean isLive = true;
    private TankFrame tf;
    private Group group = Group.BAD;
    private Rectangle rectangle=new Rectangle();

    public static int BULLET_WIDTH = ResourceMgr.bulletD.getWidth(), BULLET_HEIGHT = ResourceMgr.bulletD.getHeight();

    public RectBullet(int x, int y, Dir dir, boolean isLive, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isLive = isLive;
        this.tf = tf;
        this.group = group;
        rectangle.x=x;
        rectangle.y=y;
        rectangle.width=BULLET_WIDTH;
        rectangle.height=BULLET_HEIGHT;
        //子弹添加到集合
        tf.bullets.add(this);
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
    @Override
    public void paint(Graphics g) {
        //        Color c = g.getColor();
        //        g.setColor(Color.RED);
        //        g.fillOval(x, y, WIDTH, HEIGHT);
        //        g.setColor(c);
        if (!isLive) tf.bullets.remove(this);
        Color color = g.getColor();
        g.setColor(this.group==Group.GOOD?Color.MAGENTA:Color.WHITE);
        g.fillRect(x,y,15,15);
        g.setColor(color);
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
        //update rectangle
        rectangle.x = x;
        rectangle.y = y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            isLive = false;
        }

    }

    /***
     * 碰撞检测
     * @param tank
     */
    @Override
    public void collideWith(BaseTank tank) {
        //
        if (tank.getGroup() == this.getGroup()) return;

        //判断两个矩形相交
        if (this.rectangle.intersects(tank.getRectangle())) {
            //增加坦克爆炸效果
            //            tf.explodeList.add(new Explode(tank.getX(), tank.getY(), tf));
            tf.explodeList.add(tf.gef.createExplode(tank.getX(), tank.getY(), tf));
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
