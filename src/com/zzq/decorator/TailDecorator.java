package com.zzq.decorator;


import com.zzq.tank.GameObject;

import java.awt.*;

/**
 * @description: 尾巴装饰器
 * @author: bjzhaoziqiang
 * @time: 2020/7/26 17:44
 */
public class TailDecorator extends GoDecorator{


    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x=go.x;
        this.y=go.y;
        go.paint(g);
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        g.drawLine(x,y,x+5,y+5);
        g.setColor(color);
    }

}
