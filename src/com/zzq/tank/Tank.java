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

    public Tank(int x, int y, Dir dir, boolean moving) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
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
        g.fillRect(x, y, 50, 50);
        this.move();

    }
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

}
