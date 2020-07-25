package com.zzq.tank;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet extends GameObject{
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    private int x, y;
    private Dir dir;
    private boolean isLive = true;
    private GameModel gm;
    private Group group = Group.BAD;
    public Rectangle rectangle=new Rectangle();

    public static int BULLET_WIDTH = ResourceMgr.bulletD.getWidth(), BULLET_HEIGHT = ResourceMgr.bulletD.getHeight();

    public Bullet(int x, int y, Dir dir, boolean isLive, GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.isLive = isLive;
        this.gm = gm;
        this.group = group;
        rectangle.x=x;
        rectangle.y=y;
        rectangle.width=BULLET_WIDTH;
        rectangle.height=BULLET_HEIGHT;
        //子弹添加到集合
        gm.objects.add(this);
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

    public GameModel getGm() {
        return gm;
    }

    public void setGm(GameModel gm) {
        this.gm = gm;
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
        if (!isLive) gm.objects.remove(this);
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
        //update rectangle
        rectangle.x = x;
        rectangle.y = y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            isLive = false;
        }

    }

    /**
     * 子弹消亡
     */
    public void die() {
        this.isLive = false;
    }
}
