package com.zzq.tank;

import java.awt.*;

/**
 * 坦克
 */
public class Tank {
    private int x;
    private int y;
    private static  int SPEED = 10;
    private Dir dir;
    private boolean moving;
    private TankFrame tf;
    private static final int TANK_WIDTH=50,TANK_HEIGHT=50;

    public Tank(int x, int y, Dir dir, boolean moving, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
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

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        g.setColor(c);
        this.move();

    }

    /**
     * 设置坦克移动方向
     */
    private void  move(){
        if(!moving) return;
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
    }

    /**
     * 射击
     */
    public void fire(){
       tf.bullets.add(new Bullet(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,dir,true,tf));
    }

}
