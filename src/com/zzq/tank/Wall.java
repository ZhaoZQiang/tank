package com.zzq.tank;


import java.awt.*;

/**
 * @description:
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 16:27
 */
public class Wall extends GameObject {
    public  Rectangle rectangle;


    public Wall( int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rectangle = new Rectangle(x,y);
        GameModel.getInstance().objects.add(this);
    }



    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }
}
