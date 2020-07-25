package com.zzq.tank;


import java.awt.*;

/**
 * @description:
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 16:27
 */
public class Wall extends GameObject {
    public  Rectangle rectangle;
    private int x, y;
    private int width,height;


    public Wall( int x, int y, int width, int height) {
        this.rectangle = new Rectangle(x,y);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    @Override
    void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}
